package com.thoughtworks.trains;

import java.util.HashSet;
import java.util.Set;

/**
 * Depth-first search graph walker.
 */
public class RailwayWalker {
	
	private final Railway railway;
	
	public RailwayWalker(Railway railway) {
		this.railway = railway;
	}

	/**
	 * @param startTown
	 * @param destTown
	 * @param strategy
	 */
	public Set<String> walk(char startTown, char destTown, WalkingStrategy strategy) {
		Set<String> routesFound = new HashSet<String>(0);
		walk(String.valueOf(startTown), 0, destTown, strategy, routesFound);
		return routesFound;
	}

	/**
	 * @param currRoute
	 * @param currDistance
	 * @param destTown
	 * @param strategy
	 * @param routesFound
	 */
	private void walk(String currRoute, int currDistance, char destTown, WalkingStrategy strategy, Set<String> routesFound) {
		char currTown = currRoute.charAt(currRoute.length() - 1);
		if (currRoute.length() > 1 && currTown == destTown) {
			strategy.handleDestination(currRoute, currDistance, routesFound);
		}
		for (char nextTown : railway.tracksFrom(currTown)) {
			String nextRoute = currRoute + nextTown;
			int nextDistance = currDistance + railway.distance(currTown, nextTown);
			if (strategy.continueTo(nextRoute, nextDistance)) {
				walk(nextRoute, nextDistance, destTown, strategy, routesFound);
			}
		}
	}

}
