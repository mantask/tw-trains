package com.thoughtworks.trains.walking_strategies;

import java.util.Set;

import com.thoughtworks.trains.RailwayWalkingStrategy;

/**
 * Accumulate routes that visit exact number of towns before arriving
 * at the destination. 
 */
public class ExactTownCountStrategy implements RailwayWalkingStrategy {

	private final int townCount;
	
	/**
	 * @param townCount The number of towns that must be visited.
	 */
	public ExactTownCountStrategy(int townCount) {
		this.townCount = townCount;
	}
	
	// --- WalkingStrategy --------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void handleDestination(String route, int distance, Set<String> routes) {
		if (route.length() == townCount) {
			routes.add(route);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean continueTo(String route, int distance) {
		return route.length() <= townCount;
	}

}
