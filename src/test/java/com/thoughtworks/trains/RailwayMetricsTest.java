package com.thoughtworks.trains;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class RailwayMetricsTest {

	@Test
	public void shouldHandleSampleRailwayMetrics() throws Exception {
		Railway railway = RailwayFactory.parse("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		RailwayMetrics metrics = new RailwayMetrics(railway);
		assertEquals(new Integer(9), metrics.distanceABC());
		assertEquals(new Integer(5), metrics.distanceAD());
		assertEquals(new Integer(13), metrics.distanceADC());
		assertEquals(new Integer(22), metrics.distanceAEBCD());
		assertNull(metrics.distanceAED());
		assertEquals(new Integer(2), metrics.tripsCtoCWithMax3Stops());
		assertEquals(new Integer(3), metrics.tripsAtoCWith4Stops());
		assertEquals(new Integer(9), metrics.shortestRouteAtoCLength());
		assertEquals(new Integer(9), metrics.shortestRouteBtoBLength());
		assertEquals(new Integer(7), metrics.routeCountCtoCWithLengthLessThan30());
	}
	
	@Test
	public void shouldHandleEmptyRailway() throws Exception {
		Railway railway = RailwayFactory.parse("");
		RailwayMetrics metrics = new RailwayMetrics(railway);
		assertNull(metrics.distanceABC());
		assertNull(metrics.distanceAD());
		assertNull(metrics.distanceADC());
		assertNull(metrics.distanceAEBCD());
		assertNull(metrics.distanceAED());
		assertEquals(new Integer(0), metrics.tripsCtoCWithMax3Stops());
		assertEquals(new Integer(0), metrics.tripsAtoCWith4Stops());
		assertNull(metrics.shortestRouteAtoCLength());
		assertNull(metrics.shortestRouteBtoBLength());
		assertEquals(new Integer(0), metrics.routeCountCtoCWithLengthLessThan30());
	}

	@Test
	public void shouldHandleSparseRailway() throws Exception {
		Railway railway = RailwayFactory.parse("AB1, CD1, DE1, EF1");
		RailwayMetrics metrics = new RailwayMetrics(railway);
		assertNull(metrics.distanceABC());
		assertNull(metrics.distanceAD());
		assertNull(metrics.distanceADC());
		assertNull(metrics.distanceAEBCD());
		assertNull(metrics.distanceAED());
		assertEquals(new Integer(0), metrics.tripsCtoCWithMax3Stops());
		assertEquals(new Integer(0), metrics.tripsAtoCWith4Stops());
		assertNull(metrics.shortestRouteAtoCLength());
		assertNull(metrics.shortestRouteBtoBLength());
		assertEquals(new Integer(0), metrics.routeCountCtoCWithLengthLessThan30());
	}

	@Test
	public void shouldHandleRailwayWithLoop() throws Exception {
		Railway railway = RailwayFactory.parse("AB1, BC1, CA1");
		RailwayMetrics metrics = new RailwayMetrics(railway);
		assertEquals(new Integer(2), metrics.distanceABC());
		assertNull(metrics.distanceAD());
		assertNull(metrics.distanceADC());
		assertNull(metrics.distanceAEBCD());
		assertNull(metrics.distanceAED());
		assertEquals(new Integer(1), metrics.tripsCtoCWithMax3Stops());
		assertEquals(new Integer(0), metrics.tripsAtoCWith4Stops());
		assertEquals(new Integer(2), metrics.shortestRouteAtoCLength());
		assertEquals(new Integer(3), metrics.shortestRouteBtoBLength());
		assertEquals(new Integer(9), metrics.routeCountCtoCWithLengthLessThan30());
	}

}
