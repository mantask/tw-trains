package com.thoughtworks.trains;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.thoughtworks.trains.walking_strategies.ExactTownCountStrategy;
import com.thoughtworks.trains.walking_strategies.MaxRouteLengthStrategy;
import com.thoughtworks.trains.walking_strategies.MaxTownsStrategy;
import com.thoughtworks.trains.walking_strategies.ShortestRouteStrategy;

public class RailwayWalkerTest {
	
	Railway railway() {
		return RailwayFactory.parse("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
	}
	
	@Test
	public void shoudFindNoRoute() {
		Railway railway = RailwayFactory.parse("AB1, BC1, DE1");
		Set<String> routes = new RailwayWalker(railway).walk('A', 'D', new ShortestRouteStrategy());
		assertEquals(true, routes.isEmpty());
	}
	
	@Test
	public void shouldWalkShortestRouteAtoC() {
		Railway railway = railway();
		Set<String> routesActual = new RailwayWalker(railway).walk('A', 'C', new ShortestRouteStrategy());

		Set<String> routesExpected = new HashSet<String>();
		routesExpected.add("ABC");
		
		assertEquals(routesExpected, routesActual);
		assertEquals(new Integer(9), railway.distance(routesActual.iterator().next()));
	}
	
	@Test
	public void shouldWalkShortestRouteBtoB() {
		Railway railway = railway();
		Set<String> routesActual = new RailwayWalker(railway).walk('B', 'B', new ShortestRouteStrategy());

		Set<String> routesExpected = new HashSet<String>();
		routesExpected.add("BCEB");
		
		assertEquals(routesExpected, routesActual);
		assertEquals(new Integer(9), railway.distance(routesActual.iterator().next()));
	}
	
	@Test
	public void shouldWalkRoutesWithMaxStops() {
		Railway railway = railway();
		Set<String> routesActual = new RailwayWalker(railway).walk('C', 'C', new MaxTownsStrategy(4));
		
		Set<String> routesExpected = new HashSet<String>();
		routesExpected.add("CDC");
		routesExpected.add("CEBC");
		
		assertEquals(routesExpected, routesActual);
	}
	
	@Test
	public void shouldWalkRoutesWithExactStops() {
		Railway railway = railway();
		Set<String> routesActual = new RailwayWalker(railway).walk('A', 'C', new ExactTownCountStrategy(5));
		
		Set<String> routesExpected = new HashSet<String>();
		routesExpected.add("ABCDC");
		routesExpected.add("ADCDC");
		routesExpected.add("ADEBC");
		
		assertEquals(routesExpected, routesActual);
	}
	
	@Test
	public void shouldWalkRoutesWithinCertainDistance() {
		Railway railway = railway();
		Set<String> routesActual = new RailwayWalker(railway).walk('C', 'C', new MaxRouteLengthStrategy(30));
		
		Set<String> routesExpected = new HashSet<String>();
		routesExpected.add("CDC");
		routesExpected.add("CEBC");
		routesExpected.add("CEBCDC");
		routesExpected.add("CDCEBC");
		routesExpected.add("CDEBC");
		routesExpected.add("CEBCEBC");
		routesExpected.add("CEBCEBCEBC");
		
		assertEquals(routesExpected, routesActual);
	}
	
	@Test
	public void shouldFindNoShortestRoute() {
		Railway railway = RailwayFactory.parse("AB1, BC1, CD1");
		Set<String> routesActual = new RailwayWalker(railway).walk('D', 'A', new ShortestRouteStrategy());
		assertEquals(true, routesActual.isEmpty());
	}

	@Test
	public void shouldFindLargeNumberOfRoutes() {
		Railway railway = RailwayFactory.parse("AB1, BC1, CA1");
		Set<String> routesActual = new RailwayWalker(railway).walk('A', 'A', new MaxRouteLengthStrategy(1000));
		assertEquals(333, routesActual.size());
	}

}
