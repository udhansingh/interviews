package io.interviews.mongodb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

import org.junit.jupiter.api.Test;

import io.interviews.mongodb.jsonrazer.api.Razer;
import io.interviews.mongodb.jsonrazer.api.RazerEngine;
import io.interviews.mongodb.jsonrazer.engines.SimpleRazerEngine;
import io.interviews.mongodb.jsonrazer.helpers.IOHelper;
import io.interviews.mongodb.jsonrazer.parsers.SimpleRazer;
import io.interviews.mongodb.jsonrazer.types.Style;

/**
 * Tests command line parser
 * 
 * @author udhansingh
 *
 */
public class RazerEngineTests extends AbstractTests {
	@Test
	void testSimpleRazerWithoutStyles() throws Exception {
		final String inputFileName = String.format("%s/inputs/valid-input-1.json", getTestResourcesBaseDir());
		final String expectedOutputFileName = String.format("%s/outputs/flattened-mini-1.json", getTestResourcesBaseDir());
		
		final File inputFile = new File(inputFileName);
		final File expectedOutputFile = new File(expectedOutputFileName);
		
		final File outputFile = getTemporaryOutputFile();
		final Razer razer = new SimpleRazer();
		
		final RazerEngine razerEngine = new SimpleRazerEngine();
		razerEngine.setParser(razer);
		
		try (final Reader reader = new FileReader(inputFile)) {
			razerEngine.readFrom(reader);
		}
		
		razerEngine.run();
		
		try (final Writer writer = new FileWriter(outputFile)) {
			razerEngine.writeTo(writer);
		}
		
		final String expected = IOHelper.toString(expectedOutputFile);
		final String actual = IOHelper.toString(outputFile);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testSimpleRazerWithStyles() throws Exception {
		final String inputFileName = String.format("%s/inputs/valid-input-1.json", getTestResourcesBaseDir());
		final String expectedOutputFileName = String.format("%s/outputs/flattened-pretty-1.json", getTestResourcesBaseDir());
		
		final File inputFile = new File(inputFileName);
		final File expectedOutputFile = new File(expectedOutputFileName);
		
		final File outputFile = getTemporaryOutputFile();
		final Razer razer = new SimpleRazer();
		
		final RazerEngine razerEngine = new SimpleRazerEngine();
		razerEngine.setParser(razer);
		
		try (final Reader reader = new FileReader(inputFile)) {
			razerEngine.readFrom(reader);
		}
		
		razerEngine.applyStyle(Style.PRETTY, Style.INCLUDE_NULLS, Style.ALLOW_SPECIAL_DECIMALS);
		
		razerEngine.run();
		
		try (final Writer writer = new FileWriter(outputFile)) {
			razerEngine.writeTo(writer);
		}
		
		final String expected = IOHelper.toString(expectedOutputFile);
		final String actual = IOHelper.toString(outputFile);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testSimpleRazerWithAllStyles() throws Exception {
		final String inputFileName = String.format("%s/inputs/valid-input-1.json", getTestResourcesBaseDir());
		final String expectedOutputFileName = String.format("%s/outputs/flattened-pretty-all-styles-1.json", getTestResourcesBaseDir());
		
		final File inputFile = new File(inputFileName);
		final File expectedOutputFile = new File(expectedOutputFileName);
		
		final File outputFile = getTemporaryOutputFile();
		final Razer razer = new SimpleRazer();
		
		final RazerEngine razerEngine = new SimpleRazerEngine();
		razerEngine.setParser(razer);
		
		try (final Reader reader = new FileReader(inputFile)) {
			razerEngine.readFrom(reader);
		}
		
		razerEngine.applyStyle(
			Style.PRETTY,
			Style.INCLUDE_NULLS, 
			Style.ALLOW_SPECIAL_DECIMALS, 
			Style.DO_NOT_ESCAPE_HTML,
			Style.TREAT_INTEGER_AS_STRING
		);
		
		razerEngine.run();
		
		try (final Writer writer = new FileWriter(outputFile)) {
			razerEngine.writeTo(writer);
		}
		
		final String expected = IOHelper.toString(expectedOutputFile);
		final String actual = IOHelper.toString(outputFile);
		
		assertEquals(expected, actual);
	}
}
