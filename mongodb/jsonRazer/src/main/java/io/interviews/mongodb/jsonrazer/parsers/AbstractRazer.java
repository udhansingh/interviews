package io.interviews.mongodb.jsonrazer.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.Map;

import io.interviews.mongodb.jsonrazer.api.Razer;
import io.interviews.mongodb.jsonrazer.exceptions.ParseException;
import io.interviews.mongodb.jsonrazer.helpers.IOHelper;

/**
 * 
 * Abstract class to wrap functionality that may not be overridden
 * 
 * @author udhansingh
 *
 */
public abstract class AbstractRazer implements Razer {
	final Map<String, Object> flattenedData = new LinkedHashMap<>();

	/**
	 * JSON processor and flattener, this implementation must be provided
	 * by concrete classes when changing libraries or optimizing for certain 
	 * use cases
	 * 
	 * @param json content
	 * @param map placeholder for flattened data
	 * 
	 * @throws ParseException when JSON parse errors are encountered
	 */
	abstract void parse(String json, Map<String, Object> map) throws ParseException;
	
	@Override
	public void parse(File file) throws ParseException, FileNotFoundException, IOException {
		try (Reader reader = new FileReader(file)) {
			final String json = IOHelper.toString(reader);
			parse(json);
		}
	}
	
	@Override
	public boolean isValid(File file) throws ParseException, IOException {
		return isValid(IOHelper.toString(file));
	}
	
	@Override
	final public void parse(String json) throws ParseException {
		// Clear map to allow idempotent processing
		flattenedData.clear();
		
		// Process JSON and write to a map
		parse(json, flattenedData);
	}
	
	@Override
	public Map<String, Object> getFlattenedData() {
		return flattenedData;
	}
}