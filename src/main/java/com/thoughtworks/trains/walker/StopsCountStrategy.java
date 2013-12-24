package com.thoughtworks.trains.walker;

import java.util.Set;

import com.thoughtworks.trains.Route;
import com.thoughtworks.trains.WalkingStrategy;

public class StopsCountStrategy implements WalkingStrategy {

	private final int routeSize;
	
	public StopsCountStrategy(int exactStopsCount) {
		this.routeSize = exactStopsCount + 1;
	}
	
	public void selectRoute(Route route, int distance, Set<Route> routes) {
		if (route.townCount() == routeSize) {
			routes.add(route);
		}
	}
	
	public boolean shouldContinue(Route route, int distance) {
		return route.townCount() < routeSize;
	}
}
