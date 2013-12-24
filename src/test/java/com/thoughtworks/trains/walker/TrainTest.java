package com.thoughtworks.trains.walker;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.trains.Route;
import com.thoughtworks.trains.TrainNetwork;
import com.thoughtworks.trains.TrainNetworkWalker;
import com.thoughtworks.trains.WalkingStrategy;


public class TrainTest {
	
	private TrainNetwork network;
	
	@Before
	public void before() {
		network = new TrainNetwork();
		network.addTrack('A', 'B', 5);
		network.addTrack('B', 'C', 4);
		network.addTrack('C', 'D', 8);
		network.addTrack('D', 'C', 8);
		network.addTrack('D', 'E', 6);
		network.addTrack('A', 'D', 5);
		network.addTrack('C', 'E', 2);
		network.addTrack('E', 'B', 3);
		network.addTrack('A', 'E', 7);
	}

	@Test
	public void testDistanceForNode() {
		assertEquals(new Integer(5), network.distance('A', 'B'));
		assertEquals(new Integer(4), network.distance('B', 'C'));
		assertEquals(new Integer(8), network.distance('C', 'D'));
		assertEquals(null, network.distance('A', 'C'));
		assertEquals(null, network.distance('A', 'A'));
	}
	
	@Test
	public void testDistanceForRoute() {
		assertEquals(new Integer(0), network.distance(new Route('C')));
		assertEquals(new Integer(9), network.distance(new Route('A').add('B').add('C')));
		assertEquals(new Integer(5), network.distance(new Route('A').add('D')));
		assertEquals(new Integer(13), network.distance(new Route('A').add('D').add('C')));
		assertEquals(new Integer(22), network.distance(new Route('A').add('E').add('B').add('C').add('D')));
		assertEquals(null, network.distance(new Route('A').add('E').add('D')));
	}
	
	/**
	 * 6. The number of trips starting at C and ending at C with a maximum of 3 stops.  
	 * In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
	 */
	@Test
	public void testNumberOfTripsCToCMax3Stops() {
		WalkingStrategy spec = new MaxStopsStrategy(3);
		Set<Route> tripsActual = new TrainNetworkWalker(network, spec).walk('C', 'C');
		
		Set<Route> tripsExpected = new HashSet<Route>();
		tripsExpected.add(new Route('C').add('D').add('C'));
		tripsExpected.add(new Route('C').add('E').add('B').add('C'));
		
		assertEquals(tripsExpected, tripsActual);
	}
	
	@Test
	public void testNumberOfTripsAToCWith4Stops() {
		WalkingStrategy spec = new StopsCountStrategy(4);
		Set<Route> tripsActual = new TrainNetworkWalker(network, spec).walk('A', 'C');
		
		Set<Route> tripsExpected = new HashSet<Route>();
		tripsExpected.add(new Route('A').add('B').add('C').add('D').add('C'));
		tripsExpected.add(new Route('A').add('D').add('C').add('D').add('C'));
		tripsExpected.add(new Route('A').add('D').add('E').add('B').add('C'));
		
		assertEquals(tripsExpected, tripsActual);
	}
	
	@Test
	public void testNumberOfDiffRoutesFromCtoCWhereDistLt30() {
		WalkingStrategy spec = new MaxRouteLengthStrategy(30);
		Set<Route> tripsActual = new TrainNetworkWalker(network, spec).walk('C', 'C');
		
		Set<Route> tripsExpected = new HashSet<Route>();
		tripsExpected.add(new Route('C').add('D').add('C'));
		tripsExpected.add(new Route('C').add('E').add('B').add('C'));
		tripsExpected.add(new Route('C').add('E').add('B').add('C').add('D').add('C'));
		tripsExpected.add(new Route('C').add('D').add('C').add('E').add('B').add('C'));
		tripsExpected.add(new Route('C').add('D').add('E').add('B').add('C'));
		tripsExpected.add(new Route('C').add('E').add('B').add('C').add('E').add('B').add('C'));
		tripsExpected.add(new Route('C').add('E').add('B').add('C').add('E').add('B').add('C').add('E').add('B').add('C'));
		
		assertEquals(tripsExpected, tripsActual);
	}
	
	@Test
	public void testShortestRouteAtoC() {WalkingStrategy spec = new ShortestRouteStrategy();
		Set<Route> tripsActual = new TrainNetworkWalker(network, spec).walk('A', 'C');
		
		Set<Route> tripsExpected = new HashSet<Route>();
		tripsExpected.add(new Route('A').add('B').add('C'));
		
		assertEquals(tripsExpected, tripsActual);
		assertEquals(new Integer(9), network.distance(tripsActual.iterator().next()));
	}
	
	@Test
	public void testShortestRouteBtoB() {
		WalkingStrategy spec = new ShortestRouteStrategy();
		Set<Route> tripsActual = new TrainNetworkWalker(network, spec).walk('B', 'B');
		
		Set<Route> tripsExpected = new HashSet<Route>();
		tripsExpected.add(new Route('B').add('C').add('E').add('B'));
		
		assertEquals(tripsExpected, tripsActual);
		assertEquals(new Integer(9), network.distance(tripsActual.iterator().next()));
	}
}
