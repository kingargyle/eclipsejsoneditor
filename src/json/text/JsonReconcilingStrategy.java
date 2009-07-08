/**
 * 
 */
package json.text;

import static json.util.JsonCharUtility.a;
import static json.util.JsonCharUtility.closeCurly;
import static json.util.JsonCharUtility.closeSquare;
import static json.util.JsonCharUtility.colon;
import static json.util.JsonCharUtility.comma;
import static json.util.JsonCharUtility.e;
import static json.util.JsonCharUtility.eof;
import static json.util.JsonCharUtility.f;
import static json.util.JsonCharUtility.isClosed;
import static json.util.JsonCharUtility.isNotClosed;
import static json.util.JsonCharUtility.isNotWhiteSpace;
import static json.util.JsonCharUtility.l;
import static json.util.JsonCharUtility.minus;
import static json.util.JsonCharUtility.n;
import static json.util.JsonCharUtility.openCurly;
import static json.util.JsonCharUtility.openSquare;
import static json.util.JsonCharUtility.point;
import static json.util.JsonCharUtility.quote;
import static json.util.JsonCharUtility.r;
import static json.util.JsonCharUtility.s;
import static json.util.JsonCharUtility.slash;
import static json.util.JsonCharUtility.t;
import static json.util.JsonCharUtility.u;

import java.util.ArrayList;

import json.editors.JsonTextEditor;
import json.util.reader.JsonDocReader;
import json.util.reader.JsonReader;
import json.util.reader.JsonReaderException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;
import org.eclipse.swt.widgets.Display;

/**
 * JsonReconcilingStrategy provides the reconciling of the document to allow
 * the folding of the text.
 * 
 * @author Matt Garner
 *
 */
public class JsonReconcilingStrategy implements IReconcilingStrategy,
		IReconcilingStrategyExtension {
	
	private JsonTextEditor textEditor;
	
	private IDocument fDocument;
	
	private JsonReader parser;
	
	protected final ArrayList<Position> fPositions = new ArrayList<Position>();
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategy#reconcile(org.eclipse.jface.text.IRegion)
	 */
	public void reconcile(IRegion partition) {
		initialReconcile();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategy#reconcile(org.eclipse.jface.text.reconciler.DirtyRegion, org.eclipse.jface.text.IRegion)
	 */
	public void reconcile(DirtyRegion dirtyRegion, IRegion subRegion) {
		initialReconcile();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategy#setDocument(org.eclipse.jface.text.IDocument)
	 */
	public void setDocument(IDocument document) {
		this.fDocument = document;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension#initialReconcile()
	 */
	public void initialReconcile() {
		parser = new JsonDocReader(fDocument);		
		parse();
	}
	
	public void parse() {
		
		fPositions.clear();
		try {

			char current = parser.getNextClean();

			if (current != openCurly) {
				
				throw new JsonReconcilerParserException();
			}

			doJsonObject("", parser.getPosition());


		} catch (Exception e) {
			//JsonLog.logError("Read exception: ", e);
		}
		
		Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                    textEditor.updateFoldingStructure(fPositions);
            }

		});
	}
	
	/**
	 * Parse a Json Object.
	 * 
	 * @param key
	 * @param startPos
	 * @throws JsonReaderException
	 * @throws JsonReconcilerParserException
	 * @throws BadLocationException
	 * @throws BadPositionCategoryException
	 */
	private void doJsonObject(String key, int startPos) throws JsonReaderException, JsonReconcilerParserException, BadLocationException, BadPositionCategoryException {
		
		Position position = new Position(startPos);
		fPositions.add(position);
		
		char ch;
		do {
			ch = parser.getNextClean();
			
			// Check for empty object.
			if (ch == closeCurly) {
				parser.getNextClean();
				break;
			}
			
			if (ch != quote) {
				
				throw new JsonReconcilerParserException();
			}
			
			int start = parser.getPosition();
			
			String attributeKey = doJsonKey();

			ch = parser.getNextClean();
			if (ch != colon) {
				
				throw new JsonReconcilerParserException();
			}

			ch = parser.getNextClean();
			if (ch == openCurly) {
				doJsonObject(attributeKey, start);
			} else if (ch == openSquare) {
				doJsonArray(attributeKey, start);
			} else if (ch == n) {
				doJsonNull(attributeKey, start);
			} else if (ch == quote) {
				doJsonValue(attributeKey, start);
			}	else if (ch == t) {
				doJsonTrueValue(attributeKey, start);
			} else if (ch == f) {
				doJsonFalseValue(attributeKey, start);
			} else if (Character.isDigit(ch) || ch == minus) {
				doJsonNumber(attributeKey, start);
			} else {
				
				throw new JsonReconcilerParserException();
			}
			
			ch = parser.getCurrent();
			if (ch == comma) {
				continue;
			}

			if (ch == closeCurly) {
				position.setLength(parser.getPosition() - startPos);
				parser.getNextClean();
				break;
			}
			
			
			throw new JsonReconcilerParserException();
		} while (ch != eof);
	}
	
	/**
	 * Parse a Json Array.
	 * 
	 * @param key
	 * @param startPos
	 * @throws JsonReaderException
	 * @throws JsonReconcilerParserException
	 * @throws BadLocationException
	 * @throws BadPositionCategoryException
	 */
	private void doJsonArray(String key, int startPos) throws JsonReaderException, JsonReconcilerParserException, BadLocationException, BadPositionCategoryException {
		
		Position position = new Position(startPos);
		fPositions.add(position);
		
		char ch;
		do {
			ch = parser.getNextClean();
			int start = parser.getPosition();
			if (ch == openCurly) {
				doJsonObject("", start);
			} else if (ch == openSquare) {
				doJsonArray("", start);
			} else if (ch == n) {
				doJsonNull("", start);
			} else if (ch == quote) {
				doJsonValue("", start);
			} else if (ch == t) {
				doJsonTrueValue("", start);
			} else if (ch == f) {
				doJsonFalseValue("", start);
			} else if (Character.isDigit(ch) || ch == minus) {
				doJsonNumber("", start);
			} else if (ch == closeSquare) {
				position.setLength(parser.getPosition() - startPos);
				parser.getNextClean();
				break;
			} else {
				
				throw new JsonReconcilerParserException();
			}
			
			ch = parser.getCurrent();
			if (ch == comma) {
				continue;
			}

			if (ch == closeSquare) {
				position.setLength(parser.getPosition() - startPos);
				parser.getNextClean();
				break;
			}
			
			
			throw new JsonReconcilerParserException();
		} while (ch != eof);
	}
	
	/**
	 * Parse a Json "true" value.
	 * 
	 * @param key
	 * @param start
	 * @throws JsonReaderException
	 * @throws JsonReconcilerParserException
	 */
	private void doJsonTrueValue(String key, int start) throws JsonReaderException, JsonReconcilerParserException {
		
		
		char ch = parser.getNextChar();
		if (ch != r) {
			
			throw new JsonReconcilerParserException();
		}
		
		ch = parser.getNextChar();
		if (ch != u) {
			
			throw new JsonReconcilerParserException();
		}
		
		ch = parser.getNextChar();
		if (ch != e) {
			
			throw new JsonReconcilerParserException();
		}
		
		ch = parser.getNextClean();
		if (isNotClosed(ch)) {
			
			throw new JsonReconcilerParserException();
		}
	}
	
	/**
	 * Parse a Json "false" value.
	 * @param key
	 * @param start
	 * @throws JsonReaderException
	 * @throws JsonReconcilerParserException
	 */
	private void doJsonFalseValue(String key, int start) throws JsonReaderException, JsonReconcilerParserException {
		
		
		char ch = parser.getNextChar();
		if (ch != a) {
			
			throw new JsonReconcilerParserException();
		}
		
		ch = parser.getNextChar();
		if (ch != l) {
			
			throw new JsonReconcilerParserException();
		}
		
		ch = parser.getNextChar();
		if (ch != s) {
			
			throw new JsonReconcilerParserException();
		}
		
		ch = parser.getNextChar();
		if (ch != e) {
			
			throw new JsonReconcilerParserException();
		}
		

		ch = parser.getNextClean();
		if (isNotClosed(ch)) {
			
			throw new JsonReconcilerParserException();
		}
	}
	
	/**
	 * Parse a Json null value.
	 * 
	 * @param key
	 * @param start
	 * @throws JsonReaderException
	 * @throws JsonReconcilerParserException
	 */
	private void doJsonNull(String key, int start) throws JsonReaderException, JsonReconcilerParserException {

		char ch = parser.getNextChar();
		if (ch != u) {
			
			throw new JsonReconcilerParserException();
		}
		
		ch = parser.getNextChar();
		if (ch != l) {
			
			throw new JsonReconcilerParserException();
		}
		
		ch = parser.getNextChar();
		if (ch != l) {
			
			throw new JsonReconcilerParserException();
		}
		
		ch = parser.getNextClean();
		if (isNotClosed(ch)) {
			
			throw new JsonReconcilerParserException();
		}
		
	}
	
	/**
	 * Parse a Json string value.
	 * 
	 * @param key
	 * @param start
	 * @throws JsonReaderException
	 * @throws JsonReconcilerParserException
	 */
	private void doJsonValue(String key, int start) throws JsonReaderException, JsonReconcilerParserException {
		
		char ch;
		do {
			ch = parser.getNextChar();
			
			// TODO check format in values as well.
			
			if (ch != quote || parser.getPrevious() == slash) {
				
				continue;
			}
			ch = parser.getNextClean();
			break;
		} while (ch != eof);
	}
	
	/**
	 * Parse a json number.
	 * 
	 * @param key
	 * @param start
	 * @throws JsonReaderException
	 * @throws JsonReconcilerParserException
	 */
	private void doJsonNumber(String key, int start) throws JsonReaderException, JsonReconcilerParserException {
		
		boolean decimalPointSet = false;
		
		char ch;
		do {
			ch = parser.getNextChar();
			
			if (Character.isDigit(ch)) {

				continue;
			}
			
			if (!decimalPointSet && ch == point) {
				decimalPointSet = true;

				continue;
			}
			

			if (isClosed(ch)) {
				
				break;
			}
			
			if (isNotWhiteSpace(ch)) {
				
				throw new JsonReconcilerParserException();
			}
			
			
			ch = parser.getNextClean();
			if (isNotClosed(ch)) {
				
				throw new JsonReconcilerParserException();
			}
			
			break;
		} while (ch != eof);
		
	}
	
	/**
	 * Parse a json key.
	 * 
	 * @return
	 * @throws JsonReaderException
	 * @throws JsonReconcilerParserException
	 */
	private String doJsonKey() throws JsonReaderException, JsonReconcilerParserException {

		StringBuilder keyBuilder = new StringBuilder();
		char ch;
		do {
			ch = parser.getNextChar();

			if (ch == eof) {
				
				throw new JsonReconcilerParserException();
			}
			
			if (ch != quote || parser.getPrevious() == slash) {
				keyBuilder.append(ch);
				continue;
			}
			
			break;
		} while (ch != eof);
		
		return keyBuilder.toString();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension#setProgressMonitor(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setProgressMonitor(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	public JsonTextEditor getTextEditor() {
		return textEditor;
	}

	public void setTextEditor(JsonTextEditor textEditor) {
		this.textEditor = textEditor;
	}
}

/**
 * Exception thrown while parsing.
 * 
 * @author Matt Garner
 *
 */
class JsonReconcilerParserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -75665558665935017L;

	public JsonReconcilerParserException() {
		super();
	}
	
}
