package com.thoughtworks.trains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main application. Reads the railway network from standard input, 
 * calculates and prints results to standard output.
 */
public class App {
	
	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			
			RailwayMetrics metrics = new RailwayMetrics(reader.readLine());
		
			System.out.print(" 1: ");
			println(metrics.distanceABC());
			
			System.out.print(" 2: ");
			println(metrics.distanceAD());
			
			System.out.print(" 3: ");
			println(metrics.distanceADC());
			
			System.out.print(" 4: ");
			println(metrics.distanceAEBCD());
			
			System.out.print(" 5: ");
			println(metrics.distanceAED());
			
			System.out.print(" 6: ");
			println(metrics.tripsCtoCWithMax3Stops());
			
			System.out.print(" 7: ");
			println(metrics.tripsAtoCWith4Stops());
			
			System.out.print(" 8: ");
			println(metrics.shortestRouteAtoCLength());
			
			System.out.print(" 9: ");
			println(metrics.shortestRouteBtoBLength());
			
			System.out.print("10: ");
			println(metrics.routeCountCtoCWithLengthLessThan30());
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		} finally {
			try {
				if (reader != null) reader.close();
			} catch (IOException e) { /* Silently ignore */ }
		}
	}
	
	private static void println(Object result) {
		System.out.println((result != null) ? result.toString() : "NO SUCH ROUTE");
	}
}
