package com.bigram.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class that defines helper methods for parsing process. All the
 * methods are static and stores no state.
 * 
 * @author Binu George
 *
 */
public final class ParserUtil {

	public ParserUtil() {

	}

	/**
	 * Utility method to parse Stream to Map. This method counts the bigrams in
	 * the stream and put store in the map accordingly
	 * 
	 * @param lines
	 * @return Map
	 * @throws Exception
	 */
	public static Map<String, Integer> parseStream(Stream<String> lines) throws Exception {
		
		if(lines ==null){
			throw new NullPointerException("The file content seems to be empty. Skipping the process.");
		}
		List<String> words = lines.collect(Collectors.toList());
		String lastWord = null;
		String newWord = null;
		String key = "";
		Integer val = null;
		Map<String, Integer> result = new HashMap<>();
		for (String word : words) {
			if (lastWord == null) {
				lastWord = word;
			} else {
				newWord = word;
				key = lastWord.toLowerCase().replaceAll("\\p{Punct}", "")+ " " + newWord.toLowerCase().replaceAll("\\p{Punct}", "");
				val = result.get(key);
				if (val == null) {
					result.put(key, 1);
				} else {
					result.put(key, val.intValue() + 1);
				}
				lastWord = newWord;
			}
		}
		return result;
	}
}
