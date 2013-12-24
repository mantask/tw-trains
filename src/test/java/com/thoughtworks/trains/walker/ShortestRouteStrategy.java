package com.thoughtworks.trains.walker;
import java.util.Set;

import com.thoughtworks.trains.Route;
import com.thoughtworks.trains.WalkingStrategy;


public class ShortestRouteStrategy implements WalkingStrategy {
	
	private int minDistance = Integer.MAX_VALUE;

	public void selectRoute(Route route, int distance, Set<Route> routes) {
		if (distance < minDistance) {
			minDistance = distance;
			routes.clear();
			routes.add(route);
		}
	}

	public boolean shouldContinue(Route route, int distance) {
		return distance < minDistance;
	}

}
