package com.thoughtworks.trains;

/**
 * Creates a train network object from a string input. 
 */
public class RailwayFactory {
	
	/**
	 * For the test input, the towns are named using the first few letters of 
	 * the alphabet from A to D.  A route between two towns (A to B) with a 
	 * distance of 5 is represented as AB5. Sample input:
	 * AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
	 * 
	 * Space symbols and comma are expected to separate tracks. Otherwise the 
	 * method will fail.
	 * 
	 * @param input String representation of train network.
	 * @return Parsed train network object.
	 * @throws IllegalArgumentException When cannot parse the given string.
	 */
	public static Railway parse(String input) {
		if (input == null) {
			throw new IllegalArgumentException("Not null expected.");
		}
		Railway railway = new Railway();
		String[] tracks = input.toUpperCase().split(",");
		for (String track : tracks) {
			track = track.trim();
			if (track == "") {
				continue;
			}
			if (!track.matches("^[A-Z]{2}[0-9]+$")) {
				throw new IllegalArgumentException("Expecting a list of tracks in format {char}{char}{int} separated by comma.");
			}
			char town1 = track.charAt(0);
			char town2 = track.charAt(1);
			int distance = Integer.parseInt(track.substring(2));
			railway.addTrack(town1, town2, distance);
		}
		return railway;
	}

}