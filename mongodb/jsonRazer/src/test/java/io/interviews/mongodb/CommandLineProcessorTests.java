package io.interviews.mongodb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import io.interviews.mongodb.jsonrazer.api.CommandLineProcessor;
import io.interviews.mongodb.jsonrazer.processors.SpringCommandLineProcessor;

/**
 * Tests command line parser
 * 
 * @author udhansingh
 *
 */
public class CommandLineProcessorTests extends AbstractTests {
	File inputFile;
	File outputFile;
	
	String inputArgs;
	String outputArgs;
	
	@BeforeEach
	void beforeEachTest() throws IOException {
		final String inputFileName = String.format("%s/inputs/valid-input-1.json", getTestResourcesBaseDir());
		
		inputFile = new File(inputFileName);
		
		outputFile = File.createTempFile(UUID.randomUUID().toString(), ".json");
		outputFile.deleteOnExit();
		
		inputArgs = String.format("--input=%s", inputFile.getAbsolutePath());
		outputArgs = String.format("--output=%s", outputFile.getAbsolutePath());
	}
	
	@Test
	void testForEmptyArguments() {
		final ApplicationArguments applicationArguments = new DefaultApplicationArguments();
		final CommandLineProcessor commandLineProcessor = new SpringCommandLineProcessor(applicationArguments);

		assertEquals(false, commandLineProcessor.isFileInput());
		assertEquals(false, commandLineProcessor.isFileOutput());
	}
	
	@Test
	void testForInputOnlyArgument() {
		final ApplicationArguments applicationArguments = new DefaultApplicationArguments(inputArgs);
		final CommandLineProcessor commandLineProcessor = new SpringCommandLineProcessor(applicationArguments);

		assertEquals(true, commandLineProcessor.isFileInput());
		assertEquals(false, commandLineProcessor.isFileOutput());
	}
	
	@Test
	void testForOutputOnlyArgument() {
		final ApplicationArguments applicationArguments = new DefaultApplicationArguments(outputArgs);
		final CommandLineProcessor commandLineProcessor = new SpringCommandLineProcessor(applicationArguments);

		assertEquals(false, commandLineProcessor.isFileInput());
		assertEquals(true, commandLineProcessor.isFileOutput());
	}
	
	@Test
	void testForAllArguments() {
		final ApplicationArguments applicationArguments = new DefaultApplicationArguments(inputArgs, outputArgs);
		final CommandLineProcessor commandLineProcessor = new SpringCommandLineProcessor(applicationArguments);

		assertEquals(true, commandLineProcessor.isFileInput());
		assertEquals(true, commandLineProcessor.isFileOutput());
	}
	
	@Test
	void testArgumentParsingAndValidation() throws IOException {
		final ApplicationArguments applicationArguments = new DefaultApplicationArguments(inputArgs, outputArgs);
		final CommandLineProcessor commandLineProcessor = new SpringCommandLineProcessor(applicationArguments);
		
		commandLineProcessor.parse();
		
		final Reader reader = commandLineProcessor.getReader();
		final Writer writer = commandLineProcessor.getWriter();

		assertNotNull(reader);
		assertNotNull(writer);
	}
}
