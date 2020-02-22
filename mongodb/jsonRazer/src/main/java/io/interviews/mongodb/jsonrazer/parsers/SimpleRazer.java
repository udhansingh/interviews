package io.interviews.mongodb.jsonrazer.parsers;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.interviews.mongodb.jsonrazer.exceptions.ParseException;
import io.interviews.mongodb.jsonrazer.helpers.JsonHelper;

/**
 * 
 * A Simple JSON Parser class that uses Gson library to traverse, process and flatten
 * input content
 * 
 * @author udhansingh
 *
 */
public class SimpleRazer extends AbstractRazer {
	@Override
	void parse(String json, Map<String, Object> map) throws ParseException {
		try {
			final Gson gson = JsonHelper.newGson();
			final JsonElement jsonElement = gson.fromJson(json, JsonElement.class);
			
			/*
			 * The algorithm here is expected to be a simple version of the processor.
			 * Traverse the object starting from the root (initial state).
			 * 
			 * As the JSON elements are traversed, inspect the type of element is observed
			 * the observation is explored deeply for further processing until, all elements
			 * in that path way is visited. 
			 */
			parse(jsonElement, map, Optional.empty());
		} catch (Throwable t) {
			throw new ParseException(t);
		}
	}
	
	/**
	 * Helper method that process different kinds of JSON elements
	 * 
	 * @param jsonElement initial JSON content
	 * @param map placeholder for flattened data
	 * @param optionalPath tracker of attribute/key names
	 * @throws ParseException when JSON parse exceptions are encountered
	 */
	void parse(JsonElement jsonElement, Map<String, Object> map, Optional<String> optionalPath) throws ParseException {
		final String path = optionalPath.isPresent() ? optionalPath.get() : "";
		
		if(jsonElement.isJsonArray()) {
			/*
			 * Visited element is an array, iterate through the elements and scan deeply
			 * as necessary
			 */
			final JsonArray jsonArray = jsonElement.getAsJsonArray();
			
			for(int index = 0; index < jsonArray.size(); index++) {
				parse(jsonArray.get(index), map, Optional.ofNullable(String.format("%s[%d]", path, index)));
			}
		} else if(jsonElement.isJsonObject()) {
			/*
			 * Visited element is an object, iterate theough the elements and scan deeply 
			 * as necessary
			 * 
			 */
			final JsonObject jsonObject = jsonElement.getAsJsonObject();
			final Iterator<Entry<String, JsonElement>> iterator = jsonObject.entrySet().iterator();

			while(iterator.hasNext()) {
				final Entry<String, JsonElement> nextEntry = iterator.next();
				final String key = nextEntry.getKey();
				final JsonElement value = nextEntry.getValue();
				
				final String correctedPath = (path.isEmpty()) ? key : String.format("%s.%s", path, key);
				parse(value, map, Optional.ofNullable(correctedPath));
			}
		} else if(jsonElement.isJsonPrimitive()) {
			/*
			 * A basic type is visited, and hence is an edge, flatten it!
			 */
			final Object value = asObject(jsonElement);
			map.put(path, value);
		} else {
			/*
			 * A null value was encountered
			 */
			map.put(path, null);
		}
	}
	
	@Override
	public boolean isValid(String json) throws ParseException {
		try {
			final Gson gson = JsonHelper.newGson();
			gson.fromJson(json, JsonElement.class);
			
			return true;
		} catch (Throwable t) {
			throw new ParseException(t);
		}
	}
	
	/**
	 * Process element and return right type
	 * 
	 * @param jsonElement input
	 * @return value as object
	 */
	Object asObject(JsonElement jsonElement) {
		final String value = jsonElement.getAsString();
		
		try {
			if(value.compareToIgnoreCase("true") == 0 || value.compareToIgnoreCase("false") == 0) {
				return Boolean.valueOf(value);
			}
		} catch (Throwable t) {
		}
		
		try {
			return Long.valueOf(value);
		} catch (Throwable t) {
		}
		
		try {
			return Double.valueOf(value);
		} catch (Throwable t) {
		}
		
		return value;
	}
}
