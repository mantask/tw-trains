package com.thoughtworks.trains.walking_strategies;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.trains.WalkingStrategy;

public class ShortestRouteStrategy implements WalkingStrategy {
	
	private final Map<Character, Integer> distances = new HashMap<Character, Integer>();

	// --- RouteAccumulator --------------------------------------
	
	public void handleDestination(String route, int distance, Set<String> routes) {
		routes.clear();
		routes.add(route);
	}

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
