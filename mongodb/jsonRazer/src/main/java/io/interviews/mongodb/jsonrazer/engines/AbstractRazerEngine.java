package io.interviews.mongodb.jsonrazer.engines;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import io.interviews.mongodb.jsonrazer.api.Razer;
import io.interviews.mongodb.jsonrazer.api.RazerEngine;
import io.interviews.mongodb.jsonrazer.helpers.IOHelper;
import io.interviews.mongodb.jsonrazer.helpers.JsonHelper;
import io.interviews.mongodb.jsonrazer.types.Style;

/**
 * 
 * Abstract class that implements methods that may not be overridden
 * 
 * @author udhansingh
 *
 */
public abstract class AbstractRazerEngine implements RazerEngine {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected String json;
	protected Razer parser;
	protected List<Style> styles;
	
	@Override
	public Razer getParser() {
		return parser;
	}

	@Override
	public void applyStyle(Style... styles) {
		this.styles = Arrays.asList(styles);
	}
	
	@Override
	public void setParser(Razer jsonParser) {
		this.parser = jsonParser;
	}
	
	@Override
	public void readFrom(Reader reader) throws IOException {
		json = IOHelper.toString(reader);
		logger.debug("Input: {}", json);
	}
	
	@Override
	public void writeTo(Writer writer) throws IOException {
		write(writer, parser.getFlattenedData());
	}
	
	/**
	 * Convert flattened data to JSON and write to output
	 * 
	 * @param writer output writer
	 * @param data flattened data
	 * @throws IOException when data cannot be written into target file
	 */
	protected void write(Writer writer, Map<String, Object> data) throws IOException {
		final Gson newGson = JsonHelper.newGson(styles);
		final String json = newGson.toJson(data);
		logger.debug("Output: {}", json);
		writer.write(json);
	}
}
