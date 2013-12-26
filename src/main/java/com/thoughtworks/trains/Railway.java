package com.thoughtworks.trains;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.trains.walking_strategies.WalkingStrategy;

/**
 * Represents a track network (a directed weighted graph). 
 * Knows of towns (vertexes), tracks (edges) and distances between vertexes (edge weights).
 */
public class Railway {

	/**
	 * A directed graph where a node represents a town and an edge 
	 * represents a track between two towns. The weighting of the edge 
	 * represents the distance between the two towns.  A given route 
	 * will *never appear more than once*, and for a given route, the 
	 * starting and ending town will not be the same town
	 * 
	 * e.g. [A][B] => 5
	 */
	private Map<Character, Map<Character, Integer>> tracks = 
			new HashMap<Character, Map<Character, Integer>>();
	
	/**
	 * @param town1
	 * @param town2
	 * @param distance
	 * @throws IllegalArgumentException If cannot add a track to the network.
	 */
	public Railway addTrack(char town1, char town2, int distance) {
		if (town1 == town2) {
			throw new IllegalArgumentException("Starting and ending town cannot be the same!");
		}
		if (distance <= 0) {
			throw new IllegalArgumentException("Distance between two towns must be a positive integer!");
		}
		if (!tracks.containsKey(town1)) {
			tracks.put(town1, new HashMap<Character, Integer>());
		}
		if (tracks.get(town1).containsKey(town2)) {
			throw new IllegalArgumentException("A given track cannot appear more than once!");
		}
		tracks.get(town1).put(town2, distance);
		return this;
	}
	
	/**
	 * @param town
	 * @return
	 */
	public Set<Character> tracks(char town) {
		if (!tracks.containsKey(town)) {
			return Collections.emptySet();
		}
		return tracks.get(town).keySet();
	}
	
	/**
	 * @param town1
	 * @param town2
	 * @return
	 */
	public Integer distance(char town1, char town2) {
		if (!tracks.containsKey(town1) || !tracks.get(town1).containsKey(town2)) {
			return null;
		}
		return tracks.get(town1).get(town2);
	}
	
	/**
	 * @param route
	 * @return
	 */
	public Integer distance(String route) {
		int totalDistance = 0;
		for (int i = 0; i < route.length() - 1; i++) {
			char town1 = route.charAt(i);
			char town2 = route.charAt(i + 1);
			Integer distance = distance(town1, town2);
			if (distance == null) {
				return null;
			}
			totalDistance += distance;
		}
		return totalDistance;
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
		for (char nextTown : tracks(currTown)) {
			String nextRoute = currRoute + nextTown;
			int nextDistance = currDistance + distance(currTown, nextTown);
			if (strategy.continueTo(nextRoute, nextDistance)) {
				walk(nextRoute, nextDistance, destTown, strategy, routesFound);
			}
		}
	}

}
