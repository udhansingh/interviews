package io.interviews.mongodb.jsonrazer.engines;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.interviews.mongodb.jsonrazer.exceptions.ParseException;

/**
 * 
 * A simple implementation of the flattening engine.  Purpose of this class
 * is to allow use of any parser and writer to be plugged in via builders 
 * and run them to get the desired outcome
 *
 * @author udhansingh
 * 
 */
public class SimpleRazerEngine extends AbstractRazerEngine {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void run() throws ParseException, IOException {
		/*
		 * Validate input
		 */
		final boolean valid = parser.isValid(json);
		if (!valid) {
			throw new ParseException("Input JSON is invalid!");
		}
		
		/*
		 * Process input and prepare flattened data
		 */
		logger.debug("Processing JSON content");
		parser.parse(json);
	}
}
