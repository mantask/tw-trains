package com.thoughtworks.trains;

import java.io.Console;
import java.io.PrintWriter;
import java.util.Set;

import com.thoughtworks.trains.walking_strategies.ExactTownCountStrategy;
import com.thoughtworks.trains.walking_strategies.MaxRouteLengthStrategy;
import com.thoughtworks.trains.walking_strategies.MaxTownsStrategy;
import com.thoughtworks.trains.walking_strategies.ShortestRouteStrategy;

public class App {
	private static Console console = System.console();
	private static PrintWriter writer = console.writer();

	public static void main(String[] args) {
		try {
			Railway railway = RailwayFactory.parse(console.readLine());
			RailwayWalker walker = new RailwayWalker(railway);
		
			// 1. The distance of the route A-B-C.
			writer.print(" 1: ");
			println(railway.distance("ABC"));
			
			// 2. The distance of the route A-D.
			writer.print(" 2: ");
			println(railway.distance("AD"));
	
			// 3. The distance of the route A-D-C.
			writer.print(" 3: ");
			println(railway.distance("ADC"));
	
			// 4. The distance of the route A-E-B-C-D.
			writer.print(" 4: ");
			println(railway.distance("AEBCD"));
	
			// 5. The distance of the route A-E-D.
			writer.print(" 5: ");
			println(railway.distance("AED"));
			
			// 6. The number of trips starting at C and ending at C with a maximum of 3 stops.  
			// In the sample data below, there are two such trips: C-D-C (2 stops). 
			// and C-E-B-C (3 stops).
			writer.print(" 6: ");
			Set<String> routes6 = walker.walk('C', 'C', new MaxTownsStrategy(4));
			println(routes6.size());
			
			// 7. The number of trips starting at A and ending at C with exactly 4 stops.  
			// In the sample data below, there are three such trips: A to C (via B,C,D); 
			// A to C (via D,C,D); and A to C (via D,E,B).
			writer.print(" 7: ");
			Set<String> routes7 = walker.walk('A', 'C', new ExactTownCountStrategy(5));
			println(routes7.size());
	
			// 8. The length of the shortest route (in terms of distance to travel) from A to C.
			writer.print(" 8: ");
			Set<String> routes8 = walker.walk('A', 'C', new ShortestRouteStrategy());
			if (!routes8.isEmpty()) {
				String route8 = routes8.iterator().next();
				println(railway.distance(route8));
			} else {
				println(null);
			}
			
			// 9. The length of the shortest route (in terms of distance to travel) from B to B.
			writer.print(" 9: ");
			Set<String> routes9 = walker.walk('B', 'B', new ShortestRouteStrategy());
			if (!routes9.isEmpty()) {
				String route9 = routes9.iterator().next();
				println(railway.distance(route9));
			} else {
				println(null);
			}
			
			// 10. The number of different routes from C to C with a distance of less than 30.
			// In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC,
			// CEBCEBCEBC.
			writer.print("10: ");
			MaxRouteLengthStrategy acc10 = new MaxRouteLengthStrategy(30);
			Set<String> routes10 = walker.walk('C', 'C', acc10);
			println(routes10.size());
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
	}
	
	private static void println(Object result) {
		writer.println((result != null) ? result.toString() : "NO SUCH ROUTE");
	}
}
