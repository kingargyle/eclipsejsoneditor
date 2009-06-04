/**
 * 
 */
package json.util.reader;

import static json.util.JsonCharUtility.isWhiteSpace;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

/**
 * JsonDocReader used to read an IDoc.
 * 
 * @author Matt Garner
 *
 */
public class JsonDocReader implements JsonReader {

	private IDocument doc;

	private char previous;

	private char current;

	private int position;

	public JsonDocReader(IDocument doc) {
		super();
		this.doc = doc;
	}

	/* (non-Javadoc)
	 * @see json.util.JsonReader#getCurrent()
	 */
	public char getCurrent() {
		return current;
	}

	/* (non-Javadoc)
	 * @see json.util.JsonReader#getNextChar()
	 */
	public char getNextChar() throws JsonReaderException {
		char ch = next();
		previous = current;
		current = ch;
		return current;
	}
	
	private char next() throws JsonReaderException {
		
		try {
			if (position < doc.getLength()) {
				char ch = (char) doc.getChar(position++);
			
				return ch;
			} else {
				return (char) -1;
			}
		} catch (BadLocationException e) {
			throw new JsonReaderException();
		}
	}

	/* (non-Javadoc)
	 * @see json.util.JsonReader#getNextClean()
	 */
	public char getNextClean() throws JsonReaderException {
		
		char ch = ' ';
		while (isWhiteSpace(ch)) {
			ch = next();
		}
		previous = current;
		current = ch;
		return current;
	}

	/* (non-Javadoc)
	 * @see json.util.JsonReader#getPosition()
	 */
	public int getPosition() {
		return position - 1;
	}

	/* (non-Javadoc)
	 * @see json.util.JsonReader#getPrevious()
	 */
	public char getPrevious() {
		return previous;
	}
}
