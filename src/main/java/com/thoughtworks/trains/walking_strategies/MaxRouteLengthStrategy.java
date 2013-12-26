package com.thoughtworks.trains.walking_strategies;

import java.util.Set;

import com.thoughtworks.trains.WalkingStrategy;

public class MaxRouteLengthStrategy implements WalkingStrategy {
	
	private final int maxLength;
	
	public MaxRouteLengthStrategy(int maxLength) {
		this.maxLength = maxLength;
	}
	
	// --- WalkingStrategy --------------------------------------
	
	public void handleDestination(String route, int distance, Set<String> routes) {
		routes.add(route);
	}

	public boolean continueTo(String route, int distance) {
		return distance < maxLength;
	}

}
