package com.thoughtworks.trains;

import java.util.Set;



public interface WalkingStrategy {
	void selectRoute(Route route, int distance, Set<Route> routes);
	boolean shouldContinue(Route route, int distance);
}
