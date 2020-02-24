package io.interviews.mongodb.jsonrazer.api;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import io.interviews.mongodb.jsonrazer.exceptions.ParseException;
import io.interviews.mongodb.jsonrazer.types.Style;

/**
 * 
 * @author udhansingh
 * 
 * A flattening engine that ties all components together
 *
 */
public interface RazerEngine {
	/**
	 * Get parser
	 * 
	 * @return parser instance
	 */
	Razer getParser();
	
	/**
	 * Set parser
	 * 
	 * @param parser instance
	 */
	void setParser(Razer parser);
	
	/**
	 * Engine runner, validates input, processes content and flattens data
	 * 
	 * @throws ParseException when bad data is provided
	 * @throws IOException when file system errors are encountered
	 */
	public void run() throws ParseException, IOException;


	/**
	 * Read from input file
	 * 
	 * @param reader input reader
	 * @throws IOException when I/O exceptions are encountered
	 */
	void readFrom(Reader reader) throws IOException;

	/**
	 * Write out to a writer
	 * 
	 * @param writer output writer
	 * @throws IOException when I/O exceptions are encountered
	 */
	void writeTo(Writer writer) throws IOException;

	/**
	 * Format output as per styles
	 * @param styles list of styles to apply
	 */
	void applyStyle(Style ... styles);
}
