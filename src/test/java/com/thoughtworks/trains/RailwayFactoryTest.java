package com.thoughtworks.trains;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RailwayFactoryTest {

	@Test
	public void shouldParseEmpty() {
		RailwayFactory.parse("");
	}

	@Test
	public void shouldParseSingle() {
		Railway network = RailwayFactory.parse("AB1");
		assertEquals(new Integer(1), network.distance('A', 'B'));
	}

	@Test
	public void shouldParseLowerCase() {
		Railway network = RailwayFactory.parse("aB1");
		assertEquals(new Integer(1), network.distance('A', 'B'));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotParseNegativeDistance() {
		RailwayFactory.parse("AB-1");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotParseZeroDistance() {
		RailwayFactory.parse("AB0");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotParseNull() {
		RailwayFactory.parse(null);
	}

	@Test
	public void shouldParseTwoTracks() {
		Railway network = RailwayFactory.parse("AB1, CD2");
		assertEquals(new Integer(1), network.distance('A', 'B'));
		assertEquals(new Integer(2), network.distance('C', 'D'));
	}

	@Test
	public void shouldParseSparseTrackWhitespace() {
		Railway network = RailwayFactory.parse("  AB1 ,   CD2   ");
		assertEquals(new Integer(1), network.distance('A', 'B'));
		assertEquals(new Integer(2), network.distance('C', 'D'));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotParseTrackWithoutDistance() {
		RailwayFactory.parse("AB");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotParseIncompleteTrack() {
		RailwayFactory.parse("A1");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotParseTrackWithTooManyTowns() {
		RailwayFactory.parse("ABC1");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotParseTracksWithoutSeparators() {
		RailwayFactory.parse("AB1CD2");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotParseLargeDistance() {
		RailwayFactory.parse("AB12345678901234567890");
	}
	
	@Test
	public void shouldParseMultipleTracks() {
		Railway network = RailwayFactory.parse("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		assertEquals(new Integer(5), network.distance('A', 'B'));
		assertEquals(new Integer(4), network.distance('B', 'C'));
		assertEquals(new Integer(8), network.distance('C', 'D'));
		assertEquals(new Integer(8), network.distance('D', 'C'));
		assertEquals(new Integer(6), network.distance('D', 'E'));
		assertEquals(new Integer(5), network.distance('A', 'D'));
		assertEquals(new Integer(2), network.distance('C', 'E'));
		assertEquals(new Integer(3), network.distance('E', 'B'));
		assertEquals(new Integer(7), network.distance('A', 'E'));
	}
	
}
