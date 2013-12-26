package com.thoughtworks.trains;

import java.util.HashSet;
import java.util.Set;

/**
 * Depth-first recursive graph walker. Uses plugable rules to accumulate
 * routes along the way and determine when to stop walking further.
 */
public class RailwayWalker {
	
	private final Railway railway;
	
	/**
	 * @param railway A railway network to be walked.
	 */
	public RailwayWalker(Railway railway) {
		this.railway = railway;
	}

	/**
	 * @param startTown Where to start the walk.
	 * @param destTown Where to end the walk.
	 * @param strategy Walking rules.
	 * @return A set of all routes from start to destination that satisfy walking rules.
	 */
	public Set<String> walk(char startTown, char destTown, RailwayWalkingStrategy strategy) {
		Set<String> routesFound = new HashSet<String>(0);
		walk(String.valueOf(startTown), 0, destTown, strategy, routesFound);
		return routesFound;
	}

	/**
	 * A recursive walking method to walk the railway network.
	 * 
	 * @param currRoute Walk route so far.
	 * @param currDistance Walk length so far.
	 * @param destTown Where are we heading to.
	 * @param strategy Walking rules.
	 * @param routesFound A set of already walked routes from start to destination that satisfy walking rules.
	 */
	private void walk(String currRoute, int currDistance, char destTown, 
			RailwayWalkingStrategy strategy, Set<String> routesFound) {
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
