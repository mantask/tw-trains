package com.thoughtworks.trains.walker;

import java.util.Set;

import com.thoughtworks.trains.Route;
import com.thoughtworks.trains.WalkingStrategy;

public class MaxRouteLengthStrategy implements WalkingStrategy {
	
	private final int maxLength;
	
	public MaxRouteLengthStrategy(int maxLength) {
		this.maxLength = maxLength;
	}
	
	public void selectRoute(Route route, int distance, Set<Route> routes) {
		if (distance < maxLength) {
			routes.add(route);
		}
	}

	public boolean shouldContinue(Route route, int distance) {
		return distance < maxLength;
	}

}
