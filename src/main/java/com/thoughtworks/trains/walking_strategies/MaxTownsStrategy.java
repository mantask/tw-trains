package com.thoughtworks.trains.walking_strategies;

import java.util.Set;


public class MaxTownsStrategy implements WalkingStrategy {
	
	private final int townLimit; 
	
	public MaxTownsStrategy(int townLimit) {
		this.townLimit = townLimit;
	}
	
	// --- RouteAccumulator --------------------------------------
	
	public void handleDestination(String route, int distance, Set<String> routes) {
		routes.add(route);
	}
	
	public boolean continueTo(String route, int distance) {
		return route.length() <= townLimit;
	}

}
