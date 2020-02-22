package io.interviews.mongodb.jsonrazer.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import io.interviews.mongodb.jsonrazer.exceptions.ParseException;

/**
 * 
 * Parser API support
 *
 * @author udhansingh
 * 
 */
public interface Razer {
	/**
	 * 
	 * Is input valid JSON
	 * 
	 * @param json content
	 * @return true if valid, false otherwise
	 * 
	 * @throws ParseException when JSON content parse errors are encountered
	 */
	boolean isValid(String json) throws ParseException;
	
	/**
	 * 
	 * Is input valid JSON
	 * 
	 * @param json file
	 * @return true if valid, false otherwise
	 * 
	 * @throws ParseException when JSON content parse errors are encountered
	 * @throws IOException when I/O errors are encountered
	 */
	boolean isValid(File file) throws ParseException, IOException;
	
	/**
	 * Process JSON content and generate flattened data
	 * 
	 * @param json content
	 * @throws ParseException when JSON content parse errors are encountered
	 */
	void parse(String json) throws ParseException;
	
	/**
	 * Process JSON content and generate flattened data
	 * 
	 * @param json file
	 * @throws ParseException when JSON content parse errors are encountered
	 * @throws IOException when I/O errors are encountered
	 * @throws FileNotFoundException when file is not found
	 */
	void parse(File file) throws ParseException, FileNotFoundException, IOException;
	
	/**
	 * Get flattened data
	 * 
	 * @return flattened data as Java Map (LinkedHashMap)
	 */
	Map<String, Object> getFlattenedData();
}
