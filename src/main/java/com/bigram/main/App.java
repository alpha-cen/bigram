package com.bigram.main;
/**
 * 
 */


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
import org.apache.log4j.Logger;

import com.bigram.util.ParserUtil;

/**
 * Main class that accept a file as input and parse each word to find bigram count
 * @author Binu George
 *
 */
public class App {
	final static Logger logger = Logger.getLogger(App.class);
	/**
	 * 
	 */
	public App() {
		
	}

	/**
	 * Main method that accept file name as a parameter
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String fileName = null;
		try(Scanner scanner = new Scanner(System.in)){
			boolean isValid = false;
			while (!isValid) {
				System.out.println("Enter a File name. Use full path of the file.");
				
				isValid = scanner.hasNext();
				if (!isValid) {
					System.out.println("No valid file name!");
					logger.error("No valid file name!");
					scanner.next();
				} else {
					fileName = scanner.next();
				}
			}
		}
		//Split the lines and words and process it to fins Bigrams
 		try (final Stream<String> lines = Files.lines(Paths.get(fileName)).map(line -> line.split("[\\s]+"))
				.flatMap(Arrays::stream)) {
			Map<String, Integer> result = ParserUtil.parseStream(lines);
			for (String wordkey : result.keySet()) {
				System.out.println(wordkey + ":Count:-" + result.get(wordkey));
				logger.info(wordkey + ":Count:-" + result.get(wordkey));
			}
		}catch (Exception e) {
			logger.error("Tho  file (name/path) "+fileName+" is either invalid or corrupted. Unable to process.");
			System.out.println("Tho  file (name/path) "+fileName+" is either invalid or corrupted. Unable to process.");
		}
	}

}
