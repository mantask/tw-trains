package com.thoughtworks.trains.walking_strategies;

import java.util.Set;

public interface WalkingStrategy {
	void handleDestination(String route, int distance, Set<String> routes);
	boolean continueTo(String route, int distance);
}