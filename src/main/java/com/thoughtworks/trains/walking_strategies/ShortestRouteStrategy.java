package com.thoughtworks.trains.walking_strategies;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.trains.RailwayWalkingStrategy;

/**
 * Always accumulates a single shortest route. Or none, if not found. Walks only
 * to the towns that have not been visited before, or a shorter route to that town
 * has been found.
 */
public class ShortestRouteStrategy implements RailwayWalkingStrategy {
	
	/**
	 * Minimal distances from start town so far.
	 */
	private final Map<Character, Integer> distances = new HashMap<Character, Integer>();

	// --- RouteAccumulator --------------------------------------
	
	/**
	 * {@inheritDoc}
	 */
	public void handleDestination(String route, int distance, Set<String> routes) {
		routes.clear();
		routes.add(route);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean continueTo(String newRoute, int newDistance) {
		char newTown = newRoute.charAt(newRoute.length() - 1);
		if (!distances.containsKey(newTown) || newDistance < distances.get(newTown)) {
			distances.put(newTown, newDistance);
			return true;
		} else {
			// don't walk, if route is longer
			return false;
		}
	}

}
