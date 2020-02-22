package io.interviews.mongodb.jsonrazer;

import java.io.Reader;
import java.io.Writer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.interviews.mongodb.jsonrazer.api.CommandLineProcessor;
import io.interviews.mongodb.jsonrazer.api.Razer;
import io.interviews.mongodb.jsonrazer.api.RazerEngine;
import io.interviews.mongodb.jsonrazer.engines.SimpleRazerEngine;
import io.interviews.mongodb.jsonrazer.parsers.SimpleRazer;
import io.interviews.mongodb.jsonrazer.processors.SpringCommandLineProcessor;
import io.interviews.mongodb.jsonrazer.types.Style;

/**
 *
 * Process command line arguments, determine input and output.
 * Invoke Razer Engine to process input, validate and write flattened output.
 * 
 * @author udhansingh
 */
@SpringBootApplication
public class RazerApplication implements ApplicationRunner {
	/**
	 * Entry point of the applications
	 * 
	 * @param args input and output arguments
	 * 
	 *   --input=/path/to/input/file.json
	 *   --output=/path/to/output/file.json
	 *   
	 *   when input and output arguments are provided, STDIN and STDOUT
	 *   is mapped for reading input and writing output
	 */
	public static void main(String[] args) {
		SpringApplication.run(RazerApplication.class, args);
	}
	
	/**
	 * Process arguments and run the flattening engine
	 */
	@Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
		final CommandLineProcessor commandLineProcessor = new SpringCommandLineProcessor(applicationArguments);
		commandLineProcessor.parse();
		
		/*
		 * Use a simple JSON parser
		 */
		final Razer parser = new SimpleRazer();
		
		/*
		 * Create instance of engine, 
		 */
		final RazerEngine razer = new SimpleRazerEngine();
		
		/*
		 * Set parser
		 */
		razer.setParser(parser);
		
		/*
		 * Read input from STDIN or a file
		 */
		try (final Reader reader = commandLineProcessor.getReader()) {
			razer.readFrom(reader);
		}
		
		/*
		 * Setup output style and formatting
		 * 
		 */
		razer.style(Style.PRETTY, Style.INCLUDE_NULLS, Style.ALLOW_SPECIAL_DECIMALS);
		
		/*
		 * Validate, Parse Content and Flatten
		 */
		razer.run();
		
		/*
		 * Write output to STDOUT or a file
		 */
		try (final Writer writer = commandLineProcessor.getWriter()) {
			razer.writeTo(writer);
		}
    }
}