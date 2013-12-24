package com.thoughtworks.trains.walker;

import java.util.Set;

import com.thoughtworks.trains.Route;
import com.thoughtworks.trains.WalkingStrategy;

public class MaxStopsStrategy implements WalkingStrategy {
	private final int maxRouteSize; 
	
	public MaxStopsStrategy(int stopsLimit) {
		this.maxRouteSize = stopsLimit + 1;
	}
	
	public void selectRoute(Route route, int distance, Set<Route> routes) {
		if (route.townCount() <= maxRouteSize) {
			routes.add(route);
		}
	}
	
	public boolean shouldContinue(Route route, int distance) {
		// TODO what about dist=0 for next edge?
		return route.townCount() <= maxRouteSize;
	}
}
