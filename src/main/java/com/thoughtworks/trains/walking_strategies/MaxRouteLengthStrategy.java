package com.thoughtworks.trains.walking_strategies;

import java.util.Set;

import com.thoughtworks.trains.RailwayWalkingStrategy;

/**
 * Accumulate routes that are not longer that given length. 
 */
public class MaxRouteLengthStrategy implements RailwayWalkingStrategy {
	
	private final int maxLength;
	
	/**
	 * @param maxLength The maximum length of the route.
	 */
	public MaxRouteLengthStrategy(int maxLength) {
		this.maxLength = maxLength;
	}
	
	// --- WalkingStrategy --------------------------------------
	
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
		return distance < maxLength;
	}

}
