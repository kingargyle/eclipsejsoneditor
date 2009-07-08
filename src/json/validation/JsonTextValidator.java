/**
 * 
 */
package json.validation;

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

import java.io.File;
import java.io.FileNotFoundException;

import json.JsonLog;
import json.util.reader.JsonFileReader;
import json.util.reader.JsonReaderException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

/**
 * JsonTextValidator parses a Json file and validates it. The parser exits 
 * when the first error is found.
 *  
 * @author Matt Garner
 *
 */
public class JsonTextValidator {
	
	private JsonFileReader parser;
	
	public static final String KEY = "key";
	public static final String VIOLATION = "violation";
	
	/**
	 * Constructor taking an IFile.
	 * 
	 * @param file
	 * @throws CoreException
	 */
	public JsonTextValidator(IFile file) throws CoreException {
		super();
		this.parser = new JsonFileReader(file);
	}
	
	/**
	 * Constructor taking a File object.
	 * @param file
	 * @throws FileNotFoundException
	 */
	public JsonTextValidator(File file) throws FileNotFoundException {
		this.parser = new JsonFileReader(file);
	}
	
	/**
	 * Parse the file and report the first error found.
	 */
	public void parse() {

		try {

			char current = parser.getNextClean();

			if (current != openCurly) {
				reportProblem("JSON should begin with {", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			}

			doJsonObject();


		} catch (Exception e) {
		//	JsonLog.logError("Read exception: ", e);
		}
	}
	
	/**
	 * Parse Json Object.
	 * 
	 * @throws JsonReaderException
	 * @throws JsonValidationException
	 */
	private void doJsonObject() throws JsonReaderException, JsonValidationException {
		
		char ch;
		do {
			ch = parser.getNextClean();
			
			// Check for empty object.
			if (ch == closeCurly) {
				parser.getNextClean();
				break;
			}
			
			if (ch != quote) {
				reportProblem("JSON key should begin with \"", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
				throw new JsonValidationException();
			}
			doJsonKey();

			ch = parser.getNextClean();
			if (ch != colon) {
				reportProblem("Expected colon key/value delimitor", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
				throw new JsonValidationException();
			}

			ch = parser.getNextClean();

			if (ch == openCurly) {
				doJsonObject();
			} else if (ch == openSquare) {
				doJsonArray();
			} else if (ch == n) {
				doJsonNull();
			} else if (ch == quote) {
				doJsonValue();
			}	else if (ch == t) {
				doJsonTrueValue();
			} else if (ch == f) {
				doJsonFalseValue();
			} else if (Character.isDigit(ch) || ch == minus) {
				doJsonNumber();
			} else {
				reportProblem("Expected json value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
				throw new JsonValidationException();
			}

			if (parser.getCurrent() == comma) {
				continue;
			}

			if (parser.getCurrent() == closeCurly) {
				parser.getNextClean();
				break;
			}
			
			reportProblem("Unexpected object character:" + ch, new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		} while (ch != eof);
	}
	
	/**
	 * Parse Json Array.
	 * 
	 * @throws JsonReaderException
	 * @throws JsonValidationException
	 */
	private void doJsonArray() throws JsonReaderException, JsonValidationException {
		
		char ch;
		
		do {
			ch = parser.getNextClean();

			if (ch == openCurly) {
				doJsonObject();
			} else if (ch == openSquare) {
				doJsonArray();
			} else if (ch == n) {
				doJsonNull();
			} else if (ch == quote) {
				doJsonValue();
			} else if (ch == t) {
				doJsonTrueValue();
			} else if (ch == f) {
				doJsonFalseValue();
			} else if (Character.isDigit(ch) || ch == minus) {
				doJsonNumber();
			} else if (ch == closeSquare) {
				parser.getNextClean();
				break;
			} else {
				reportProblem("Expected json value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
				throw new JsonValidationException();
			}
			
			ch = parser.getCurrent();
			if (ch == comma) {
				continue;
			}

			if (ch == closeSquare) {
				parser.getNextClean();
				break;
			}
			
			reportProblem("Unexpected array character:" + ch, new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		} while (ch != eof);
	}
	
	/**
	 * Parse true value.
	 * @throws JsonReaderException
	 * @throws JsonValidationException
	 */
	private void doJsonTrueValue() throws JsonReaderException, JsonValidationException {
		
		char ch = parser.getNextChar();
		if (ch != r) {
			reportProblem("Expect true value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		}
		
		ch = parser.getNextChar();
		if (ch != u) {
			reportProblem("Expect true value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		}
		
		ch = parser.getNextChar();
		if (ch != e) {
			reportProblem("Expect true value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		}
		
		ch = parser.getNextClean();
		if (isNotClosed(ch)) {
			reportProblem("Expected end value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		}
	}
	
	/**
	 * Parse false value.
	 * 
	 * @throws JsonReaderException
	 * @throws JsonValidationException
	 */
	private void doJsonFalseValue() throws JsonReaderException, JsonValidationException {
		
		char ch = parser.getNextChar();
		if (ch != a) {
			reportProblem("Expect true value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		}
		
		ch = parser.getNextChar();
		if (ch != l) {
			reportProblem("Expect true value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		}
		
		ch = parser.getNextChar();
		if (ch != s) {
			reportProblem("Expect true value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		}
		
		ch = parser.getNextChar();
		if (ch != e) {
			reportProblem("Expect true value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		}
		
		ch = parser.getNextClean();
		if (isNotClosed(ch)) {
			reportProblem("Expected end value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		}
		
	}
	
	/**
	 * Parse null value.
	 * 
	 * @throws JsonReaderException
	 * @throws JsonValidationException
	 */
	private void doJsonNull() throws JsonReaderException, JsonValidationException {
		
		char ch = parser.getNextChar();
		if (ch != u) {
			reportProblem("Expect null value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		}
		
		ch = parser.getNextChar();
		if (ch != l) {
			reportProblem("Expect null value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		}
		
		ch = parser.getNextChar();
		if (ch != l) {
			reportProblem("Expect null value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		}
		
		ch = parser.getNextClean();
		if (isNotClosed(ch)) {
			reportProblem("Expected end value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
			throw new JsonValidationException();
		}
	}
	
	/**
	 * Parse Json value.
	 * 
	 * @throws JsonReaderException
	 * @throws JsonValidationException
	 */
	private void doJsonValue() throws JsonReaderException, JsonValidationException {
		
		char ch;
		do {
			ch = parser.getNextChar();
			
			// TODO check format in values as well.
			if (ch == eof) {
				reportProblem("Expected quotation", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
				break;
			}
			
			if (ch != quote) {
				continue;
			}
			
			if (parser.getPrevious() == slash) {
				continue;
			}
			
			ch = parser.getNextClean();
			break;
		} while (ch != eof);
	}
	
	/**
	 * Parse Json number.
	 * 
	 * @throws JsonReaderException
	 * @throws JsonValidationException
	 */
	private void doJsonNumber() throws JsonReaderException, JsonValidationException {
		
		boolean decimalPointSet = false;
		char ch;
		do {
			ch = parser.getNextChar();
			if (Character.isDigit(ch)) {
				continue;
			}
			
			if (isClosed(ch)) {
				break;
			}
			
			if (!decimalPointSet && ch == point) {
				decimalPointSet = true;
				continue;
			}
			
			if (isNotWhiteSpace(ch)) {
				reportProblem("Value " + ch + " not expected here", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
				throw new JsonValidationException();
			}
			
			ch = parser.getNextClean();
			if (isNotClosed(ch)) {
				reportProblem("Expected end value", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
				throw new JsonValidationException();
			}
			
			break;
		} while (ch != eof);
		
	}
	
	/**
	 * Parse Json Key.
	 * 
	 * @throws JsonReaderException
	 * @throws JsonValidationException
	 */
	private void doJsonKey() throws JsonReaderException, JsonValidationException {
		
		char ch;
		do {
			ch = parser.getNextChar();
			
			if (ch == eof) {
				reportProblem("Invalid JSON key, no closing \"", new Location(parser.getIFile(),"", parser.getPosition(), parser.getPosition()),0, true);
				throw new JsonValidationException();
			}
			
			if (ch != quote) {
				continue;
			}
			
			if (parser.getPrevious() == slash) {
				continue;
			}
			
			break;
		} while (ch != eof);
	}
	
	/**
	 * Report the problem.
	 * 
	 * @param msg
	 * @param loc
	 * @param violation
	 * @param isError
	 */
	public void reportProblem(String msg, Location loc, int violation, boolean isError) {
		
		try {
			
			IMarker marker = loc.file.createMarker(IncrementalJsonValidator.MARKER_ID);
			marker.setAttribute(IMarker.MESSAGE, msg);
			marker.setAttribute(IMarker.CHAR_START, loc.charStart);
			marker.setAttribute(IMarker.CHAR_END, loc.charEnd);
			marker.setAttribute(IMarker.SEVERITY, isError ? IMarker.SEVERITY_ERROR : IMarker.SEVERITY_WARNING);
			marker.setAttribute(KEY, loc.key);
			marker.setAttribute(VIOLATION, violation);
		} catch (CoreException e) {
			JsonLog.logError(e);
		}
	}
	
	/**
	 * Location in the file of the issue.
	 * 
	 * @author Matt Garner
	 *
	 */
	private class Location {
		
		
		public Location(IFile file, String key, int charStart, int charEnd) {
			super();
			this.file = file;
			this.key = key;
			this.charStart = charStart;
			this.charEnd = charEnd;
		}
		
		IFile file;
		String key;
		int charStart;
		int charEnd;
	}
	
	
}

/**
 * Validation Exception
 * 
 * @author Matt Garner
 *
 */
class JsonValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -75665558665935017L;

	public JsonValidationException() {
		super();
	}
	
}