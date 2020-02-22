package io.interviews.mongodb.jsonrazer.processors;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;

/**
 * 
 * @author udhansingh
 * 
 * This is a concrete class which implements command line arguments processor
 * using Spring libraries
 *
 */
public class SpringCommandLineProcessor extends AbstractCommandLineProcessor {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	final Optional<List<String>> optionalInputs;
	final Optional<List<String>> optionalOutputs;
	
	public SpringCommandLineProcessor(ApplicationArguments applicationArguments) {
		this.optionalInputs = Optional.ofNullable(applicationArguments.getOptionValues("input"));
		this.optionalOutputs = Optional.ofNullable(applicationArguments.getOptionValues("output"));
	}
	
	@Override
	public boolean isFileInput() {
		return optionalInputs.isPresent();
	}
	
	@Override
	public boolean isFileOutput() {
		return optionalOutputs.isPresent();
	}
	

	@Override
	public void parse() throws IOException {
		if(isFileInput()) {
			final List<String> inputs = optionalInputs.get();
			final String inputFileName = inputs.get(0);
			
			super.setInputFile(new File(inputFileName));
		}
		
		if(isFileOutput()) {
			final List<String> outputs = optionalOutputs.get();
			final String outputFileName = outputs.get(0);

			super.setOutputFile(new File(outputFileName));
		}
	}
}
