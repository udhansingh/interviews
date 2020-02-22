package io.interviews.mongodb.jsonrazer.processors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import io.interviews.mongodb.jsonrazer.api.CommandLineProcessor;

/**
 * 
 * @author udhansingh
 * 
 * Abstract class implements necessary methods which 
 * may be overridden by implementors on a case-by-case basis
 *
 */
public abstract class AbstractCommandLineProcessor implements CommandLineProcessor {
	protected File inputFile;
	protected File outputFile;
	
	/**
	 * Get input file
	 * 
	 * @return input file object
	 */
	public File getInputFile() {
		return inputFile;
	}

	/**
	 * Set input file
	 * 
	 * @param inputFile file object
	 */
	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

	/**
	 * Get output file
	 * 
	 * @return output file object
	 */
	public File getOutputFile() {
		return outputFile;
	}

	/**
	 * Set output file
	 * 
	 * @param outputFile file object
	 */
	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}
	
	@Override
	public Reader getReader() throws FileNotFoundException {
		if(inputFile != null) {
			return new FileReader(inputFile);
		} else {
			return new InputStreamReader(System.in);
		}
	}
	
	@Override
	public Writer getWriter() throws IOException {
		if(outputFile != null) {
			return new FileWriter(outputFile);
		} else {
			return new OutputStreamWriter(System.out);
		}
	}
}
