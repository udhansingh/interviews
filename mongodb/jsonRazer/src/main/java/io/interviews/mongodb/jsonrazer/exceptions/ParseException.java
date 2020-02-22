package io.interviews.mongodb.jsonrazer.exceptions;

/**
 * A parser exception class, thrown when JSON content parsing fails
 * 
 * @author udhansingh
 *
 */
public class ParseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4241163253558461845L;
	
	public ParseException(String message) {
		super(message);
	}

	public ParseException(Throwable throwable) {
		super(throwable);
	}

	public ParseException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
