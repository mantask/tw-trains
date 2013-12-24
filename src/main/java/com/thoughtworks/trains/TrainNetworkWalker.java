package com.thoughtworks.trains;

import java.util.HashSet;
import java.util.Set;


public class TrainNetworkWalker {
	
	private final TrainNetwork network;
	private final WalkingStrategy strategy;

	/**
	 * @param network
	 * @param strategy
	 */
	public TrainNetworkWalker(TrainNetwork network, WalkingStrategy strategy) {
		this.network = network;
		this.strategy = strategy;
	}
	
	/**
	 * Walk the train network and record routes according to strategy provided.
	 * 
	 * @param startTown
	 * @param strategy
	 * @return
	 */
	public Set<Route> walk(char startTown, char destTown) {
		Set<Route> routes = new HashSet<Route>();
		walk(new Route(startTown), 0, destTown, routes);
		return routes;
	}
	
	/**
	 * Walk the tree by Depth First Search by recursion.
	 * 
	 * @param route
	 * @param distance
	 * @param destTown
	 * @param routes
	 */
	private void walk(Route route, int distance, char destTown, Set<Route> routes) {
		if (route.lastTown() == destTown && route.townCount() > 1) { 
			strategy.selectRoute(route, distance, routes);
		}
		if (strategy.shouldContinue(route, distance)) {
			char lastTown = route.lastTown();
			for (char nextTown : network.neighbours(lastTown)) {
				walk(route.add(nextTown), distance + 
						network.distance(lastTown, nextTown), destTown, routes);
			}
		}
	}

}
