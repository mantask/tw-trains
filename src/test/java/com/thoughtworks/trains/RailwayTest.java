package com.thoughtworks.trains;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import com.thoughtworks.trains.Railway.IllegalTrackException;

public class RailwayTest {
	
	Railway railway() {
		return new Railway()
				.addTrack('A', 'B', 5)
				.addTrack('B', 'C', 4)
				.addTrack('C', 'D', 8)
				.addTrack('D', 'C', 8)
				.addTrack('D', 'E', 6)
				.addTrack('A', 'D', 5)
				.addTrack('C', 'E', 2)
				.addTrack('E', 'B', 3)
				.addTrack('A', 'E', 7);
	}

	@Test
	public void shouldAddSingleTrack() {
		Railway network = new Railway().addTrack('A', 'B', 1);
		assertEquals(new Integer(1), network.distance('A', 'B'));
		assertEquals(new Integer(1), network.distance("AB"));
		assertNull(network.distance('B', 'A'));
		assertNull(network.distance("BA"));
		assertEquals(new HashSet<Character>(Arrays.asList('B')), network.tracksFrom('A'));
		assertEquals(0, network.tracksFrom('B').size());
	}
	
	@Test(expected = IllegalTrackException.class)
	public void shouldNotAddTrackWithLoop() {
		new Railway().addTrack('A', 'A', 1);
	}

	@Test(expected = IllegalTrackException.class)
	public void shouldNotAddTrackWithZeroDistance() {
		new Railway().addTrack('A', 'B', 0);
	}

	@Test(expected = IllegalTrackException.class)
	public void shouldNotAddSameTrackTwice() {
		new Railway()
				.addTrack('A', 'B', 1)
				.addTrack('A', 'B', 3);
	}
	
	@Test
	public void shouldCalculateDistanceBetweenTowns() {
		Railway railway = railway();
		assertEquals(new Integer(5), railway.distance('A', 'B'));
		assertEquals(new Integer(4), railway.distance('B', 'C'));
		assertEquals(new Integer(8), railway.distance('C', 'D'));
		assertNull(railway.distance('A', 'C'));
		assertNull(railway.distance('A', 'A'));
	}
	
	@Test
	public void shouldCalculateDistanceForRoutes() {
		Railway railway = railway();
		assertEquals(new Integer(0), railway.distance("C"));
		assertEquals(new Integer(9), railway.distance("ABC"));
		assertEquals(new Integer(5), railway.distance("AD"));
		assertEquals(new Integer(13), railway.distance("ADC"));
		assertEquals(new Integer(22), railway.distance("AEBCD"));
		assertNull(railway.distance("AED"));
	}
	
	@Test
	public void shouldNotCalculateDistanceForNonExistingRoute() {
		Railway network = railway();
		assertNull(network.distance("ABCDEA"));
	}

}
