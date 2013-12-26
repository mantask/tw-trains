package com.thoughtworks.trains.walking_strategies;

import java.util.Set;

public class ExactTownCountStrategy implements WalkingStrategy {

	private final int townCount;
	
	public ExactTownCountStrategy(int townCount) {
		this.townCount = townCount;
	}
	
	// --- WalkingStrategy --------------------------------------
	
	public void handleDestination(String route, int distance, Set<String> routes) {
		if (route.length() == townCount) {
			routes.add(route);
		}
	}
	
	public boolean continueTo(String route, int distance) {
		return route.length() <= townCount;
	}

}
