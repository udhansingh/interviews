package io.interviews.mongodb;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 
 * Provide test capabilities
 * 
 * @author udhansingh
 *
 */
public abstract class AbstractTests {
	protected String getTestResourcesBaseDir() {
		return "src/test/resources";
	}
	
	protected File getTemporaryOutputFile() throws IOException {
		final String outputFileName = String.format("%s-output-", UUID.randomUUID());
		return File.createTempFile(outputFileName, ".json");
	}
}
