package io.interviews.mongodb.jsonrazer.helpers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Helper for input/output handling
 * 
 * Large file/content can be potentially getting API caller to
 * run into out-of-memory issues.
 * 
 * Using a stream and processing content is better
 * 
 * @author udhansingh
 *
 */
public class IOHelper {
	/**
	 * Convert reader to string
	 * 
	 * @param reader input
	 * @return file content as string
	 * @throws IOException when errors are encountered
	 */
	public static String toString(Reader reader) throws IOException {
		final StringBuilder stringBuilder = new StringBuilder();

		int charAsInt;
		while ((charAsInt = reader.read()) != -1) {
			stringBuilder.append((char)charAsInt);
		}

		return stringBuilder.toString();
	}
	
	/**
	 * Convert file to string
	 * 
	 * @param file input
	 * @return file content as string
	 * @throws IOException when errors are encountered
	 */
	public static String toString(File file) throws IOException {
		try (Reader reader = new FileReader(file)) {
			return toString(reader);
		}
	}
}
