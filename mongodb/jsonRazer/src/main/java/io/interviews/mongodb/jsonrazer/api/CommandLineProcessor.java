package io.interviews.mongodb.jsonrazer.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * 
 * Process application arguments and setup input and outputs
 * 
 * Implementors can use different mechanisms to process command line arguments
 * 
 * @author udhansingh
 *
 */
public interface CommandLineProcessor {
	/**
	 * Process command line arguments
	 * 
	 * @throws IOException when I/O errors encountered
	 */
	void parse() throws IOException;
	
	/**
	 * Get reader
	 * 
	 * @return input reader
	 * @throws FileNotFoundException when file is not found
	 */
	Reader getReader() throws FileNotFoundException;
	
	
	/**
	 * Get writer
	 * @return output writer 
	 * @throws IOException when file is not found
	 */
	Writer getWriter() throws IOException;
	

	/**
	 * Determine if input is file
	 * 
	 * @return true if input is a file, false otherwise
	 */
	boolean isFileInput();

	/**
	 * Determine if output is file
	 * 
	 * @return true if output is a file, false otherwise
	 */
	boolean isFileOutput();
}
