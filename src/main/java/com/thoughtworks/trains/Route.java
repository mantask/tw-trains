package com.thoughtworks.trains;

/**
 * Immutable list. The towns are stored in reverse order: last to first.
 * 
 */
public class Route {
	
	private final char town; // head
	private final Route route; // tail
	private final int count; // element count
	
	// --- ctor ----------------------------------
	
	public Route(char town) {
		this.town = town;
		this.route = null;
		this.count = 1;
	}
	
	public Route(char town, Route trip) {
		this.town = town;
		this.route = trip;
		this.count = 1 + trip.townCount();
	}
	
	// -------------------------------------
	
	public Route add(char town) {
		return new Route(town, this);
	}
	
	public char lastTown() {
		return town;
	}
	
	public Route prevRoute() {
		return route;
	}
	
	public int townCount() {
		return count;
	}
	
	// --- Object ----------------------------------
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Route)) {
			return false;
		}
		Route that = (Route) o;
		return this.town == that.town &&
				(this.route == null && that.route == null ||
				this.route != null && this.route.equals(that.route));
	}
	
	@Override
	public int hashCode() {
		return new Character(town).hashCode() + count;
	}
	
	@Override
	public String toString() {
		return (route != null ? route.toString() : "") + town;
	}
}
