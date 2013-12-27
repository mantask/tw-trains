package com.thoughtworks.trains;

import java.util.Set;

import com.thoughtworks.trains.walking_strategies.ExactTownCountStrategy;
import com.thoughtworks.trains.walking_strategies.MaxRouteLengthStrategy;
import com.thoughtworks.trains.walking_strategies.MaxTownsStrategy;
import com.thoughtworks.trains.walking_strategies.ShortestRouteStrategy;

/**
 * Calculates various metrics of given railway network.
 */
public class RailwayMetrics {
	private final Railway railway;
	private final RailwayWalker walker;

	public RailwayMetrics(String input) {
		this.railway = RailwayFactory.parse(input);
		this.walker = new RailwayWalker(railway);
	}
	
	/**
	 * 1. The distance of the route A-B-C.
	 */
	public Integer distanceABC() {
		return railway.distance("ABC");
	}

	/**
	 * 2. The distance of the route A-D.
	 */
	public Integer distanceAD() {
		return railway.distance("AD");
	}

	/**
	 * 3. The distance of the route A-D-C.
	 */
	public Integer distanceADC() {
		return railway.distance("ADC");
	}

	/**
	 * 4. The distance of the route A-E-B-C-D.
	 */
	public Integer distanceAEBCD() {
		return railway.distance("AEBCD");
	}

	/**
	 * 5. The distance of the route A-E-D.
	 */
	public Integer distanceAED() {
		return railway.distance("AED");
	}
	
	/**
	 * 6. The number of trips starting at C and ending at C with a maximum of 3 stops.  
	 * In the sample data below, there are two such trips: C-D-C (2 stops).
	 * and C-E-B-C (3 stops). 
	 */
	public Integer tripsCtoCWithMax3Stops() {
		RailwayWalkingStrategy strat = new MaxTownsStrategy(4);
		Set<String> routes6 = walker.walk('C', 'C', strat);
		return routes6.size();
	}
	
	/**
	 * 7. The number of trips starting at A and ending at C with exactly 4 stops.  
	 * In the sample data below, there are three such trips: A to C (via B,C,D); 
	 * A to C (via D,C,D); and A to C (via D,E,B).
	 */
	public Integer tripsAtoCWith4Stops() {
		RailwayWalkingStrategy strat = new ExactTownCountStrategy(5);
		Set<String> routes = walker.walk('A', 'C', strat);
		return routes.size();
	}

	/**
	 * 8. The length of the shortest route (in terms of distance to travel) from A to C.
	 */
	public Integer shortestRouteAtoCLength() {
		RailwayWalkingStrategy strat = new ShortestRouteStrategy();
		Set<String> routes = walker.walk('A', 'C', strat);
		if (!routes.isEmpty()) {
			String route = routes.iterator().next();
			return railway.distance(route);
		} else {
			return null;
		}
	}
	
	/**
	 * 9. The length of the shortest route (in terms of distance to travel) from B to B.
	 */
	public Integer shortestRouteBtoBLength() {
		RailwayWalkingStrategy strat = new ShortestRouteStrategy();
		Set<String> routes = walker.walk('B', 'B', strat);
		if (!routes.isEmpty()) {
			String route = routes.iterator().next();
			return railway.distance(route);
		} else {
			return null;
		}
	}
	
	/**
	 * 10. The number of different routes from C to C with a distance of less than 30.
	 * In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC,
	 * CEBCEBCEBC.
	 */
	public Integer routeCountCtoCWithLengthLessThan30() {
		MaxRouteLengthStrategy strat = new MaxRouteLengthStrategy(30);
		Set<String> routes = walker.walk('C', 'C', strat);
		return routes.size();
	}
	
}
