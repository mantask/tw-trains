package com.thoughtworks.trains.walking_strategies;

import java.util.Set;

import com.thoughtworks.trains.RailwayWalkingStrategy;

/**
 * Accumulate the routes that have no more that specified number of towns.
 */
public class MaxTownsStrategy implements RailwayWalkingStrategy {
	
	private final int townLimit; 
	
	/**
	 * @param townLimit The max number of towns.
	 */
	public MaxTownsStrategy(int townLimit) {
		this.townLimit = townLimit;
	}
	
	// --- RouteAccumulator --------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void handleDestination(String route, int distance, Set<String> routes) {
		routes.add(route);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean continueTo(String route, int distance) {
		return route.length() <= townLimit;
	}

}
