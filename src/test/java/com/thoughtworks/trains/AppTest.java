package com.thoughtworks.trains;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {
	
	static final String INPUT = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
	static final String[] EXPECTED_OUTPUT = new String[] {
		 " 1: 9",
		 " 2: 5",
		 " 3: 13",
		 " 4: 22",
		 " 5: NO SUCH ROUTE",
		 " 6: 2",
		 " 7: 3",
		 " 8: 9",
		 " 9: 9",
		 "10: 7",
	};
	static final String OUTPUT_FILENAME = "trains.out";
	PrintStream out;

	@Before
	public void before() throws Exception {
		out = System.out;
		System.setOut(new PrintStream(new FileOutputStream(OUTPUT_FILENAME)));
		System.setIn(new ByteArrayInputStream(INPUT.getBytes()));
	}
	
	@After
	public void after() throws Exception {
		System.setOut(out);
		new File(OUTPUT_FILENAME).delete();
	}
	
	@Test
	public void shouldHandleSampleInput() throws Exception {
		App.main(new String[0]);
		BufferedReader reader = new BufferedReader(new FileReader(OUTPUT_FILENAME));
		String line;
		int lineCount = 0;
		while ((line = reader.readLine()) != null) {
			assertEquals(EXPECTED_OUTPUT[lineCount], line);
			lineCount++;
		}
		reader.close();
	}
	
}
