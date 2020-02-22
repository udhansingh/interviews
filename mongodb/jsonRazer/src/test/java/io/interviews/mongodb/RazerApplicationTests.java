package io.interviews.mongodb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.interviews.mongodb.jsonrazer.RazerApplication;
import io.interviews.mongodb.jsonrazer.helpers.IOHelper;

/**
 * Tests command line parser
 * 
 * @author udhansingh
 *
 */
@SpringBootTest(classes = { RazerApplication.class }, args = { "--input=/tmp/valid-input-1.json", "--output=/tmp/output-1.json" })
public class RazerApplicationTests extends AbstractTests {
	/*
	 * Prepare input and out directories for testig before test execution begins
	 */
	@BeforeAll
	static void setup() throws IOException {
		final String inputResource = RazerApplicationTests.class.getClassLoader().getResource("inputs/valid-input-1.json").getFile();
		final File inputFile = new File(inputResource);
		
		final File outputFile = new File("/tmp/valid-input-1.json");
		Files.copy(inputFile.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
	/*
	 * Validate outcome after application as run
	 */
	@Test
	void testApplicationOutcome() throws IOException {
		final String actual = IOHelper.toString(new File("/tmp/output-1.json"));
		
		final String expectedOutputFileName = String.format("%s/outputs/flattened-pretty-1.json", getTestResourcesBaseDir());
		final File expectedOutputFile = new File(expectedOutputFileName);
		
		final String expected = IOHelper.toString(expectedOutputFile);
		
		assertEquals(expected, actual);
	}
}
