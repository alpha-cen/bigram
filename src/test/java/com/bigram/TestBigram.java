package com.bigram;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bigram.util.ParserUtil;

/**
 * Unit Test class to test the functional correctness
 * @author Binu George
 *
 */
public class TestBigram {

	
	public static final String fileName = "test.txt";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBigramCountUtil() throws Exception {
		Stream<String> lines = Files.lines(Paths.get(fileName)).map(line -> line.split("[\\s]+"))
				.flatMap(Arrays::stream);
		Map<String, Integer> result = ParserUtil.parseStream(lines);
		assertEquals(2,result.get("the quick").intValue());
		assertEquals(1, result.get("blue hare").intValue());
	}	
	

}
