package io.interviews.mongodb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.interviews.mongodb.jsonrazer.api.Razer;
import io.interviews.mongodb.jsonrazer.exceptions.ParseException;
import io.interviews.mongodb.jsonrazer.parsers.SimpleRazer;

/**
 * Tests command line parser
 * 
 * @author udhansingh
 *
 */
public class RazerTests extends AbstractTests {
	@Test
	void testValidResourceFile() throws FileNotFoundException, ParseException, IOException {
		final String inputFileName = String.format("%s/inputs/valid-input-1.json", getTestResourcesBaseDir());
		final File inputFile = new File(inputFileName);
		
		final Razer razer = new SimpleRazer();
		final boolean valid = razer.isValid(inputFile);
		assertEquals(true, valid);
	}
	
	@Test
	void testValidJsonText() throws ParseException {
		final String json = "{ \"a\" : \"value\" }";

		final Razer razer = new SimpleRazer();
		final boolean valid = razer.isValid(json);
		assertEquals(true, valid);
	}
	
	@Test
	void testInvalidJsonText() {
		Assertions.assertThrows(ParseException.class, () -> {
			final String json = "{ \"a\" : \"value }";

			final Razer razer = new SimpleRazer();
			razer.isValid(json);
		});
	}
	
	@Test
	void testInvalidResourceFile() {
		final String inputFileName = String.format("%s/inputs/invalid-input-1.json", getTestResourcesBaseDir());
		final File inputFile = new File(inputFileName);
		
		Assertions.assertThrows(ParseException.class, () -> {
			final Razer razer = new SimpleRazer();
			razer.isValid(inputFile);
		});
	}
	
	@Test
	void testResourceFileParser() throws FileNotFoundException, ParseException, IOException {
		final String inputFileName = String.format("%s/inputs/valid-input-1.json", getTestResourcesBaseDir());
		final File inputFile = new File(inputFileName);
		
		final Razer razer = new SimpleRazer();
		razer.parse(inputFile);
		
		final Map<String, Object> flattenedData = razer.getFlattenedData();
		assertEquals(14, flattenedData.size());
	}
}
