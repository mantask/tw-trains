package com.thoughtworks.trains;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents the static train network (a directed weighted graph). 
 * Knows towns (vertexes), tracks (edges) and distances between vertexes (edge weights).
 */
public class TrainNetwork {

	private Map<Character, Map<Character, Integer>> routes = 
			new HashMap<Character, Map<Character, Integer>>();
	
	/**
	 * 
	 * @param town1
	 * @param town2
	 * @param distance
	 */
	public void addTrack(char town1, char town2, int distance) {
		if (!routes.containsKey(town1)) {
			routes.put(town1, new HashMap<Character, Integer>());
		}
		routes.get(town1).put(town2, distance);
	}
	
	/**
	 * 
	 * @param town
	 * @return
	 */
	public Set<Character> neighbours(char town) {
		return routes.get(town).keySet();
	}
	
	/**
	 * 
	 * @param town1
	 * @param town2
	 * @return
	 */
	public Integer distance(char town1, char town2) {
		if (!routes.containsKey(town1) || !routes.get(town1).containsKey(town2)) {
			return null;
		}
		return routes.get(town1).get(town2);
	}
	
	/**
	 * 
	 * @param route
	 * @return
	 */
	public Integer distance(Route route) {
		
		/*
			return distance(route.prevRoute()) + distance(route.prevRoute().lastTown(), route.lastTown());
		 */
		
		int totalDistance = 0;
		char lastTown = route.lastTown();
		while (route.prevRoute() != null) {
			route = route.prevRoute();
			Integer distance = distance(route.lastTown(), lastTown);
			lastTown = route.lastTown();
			if (distance == null) {
				return null;
			}
			totalDistance += distance;
		}
		return totalDistance;
	}
	
}
