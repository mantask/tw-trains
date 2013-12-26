package com.thoughtworks.trains;

import java.util.Set;

/**
 * Defines walking rules for RailwayWalker.
 */
public interface RailwayWalkingStrategy {
	
	/**
	 * What to do when a destination town is reached. Adjust the strategy? 
	 * Save current route?
	 *  
	 * @param route The route to destination town.
	 * @param distance The length of the route to destination town.
	 * @param routes Accumulated successful routes to destination town so far. 
	 */
	void handleDestination(String route, int distance, Set<String> routes);
	
	/**
	 * Decides if we should continue walking to a new route?
	 * 
	 * @param route The new candidate route.
	 * @param distance The length of the new candidate route.
	 * @return True, if we should continue.
	 */
	boolean continueTo(String route, int distance);
	
}