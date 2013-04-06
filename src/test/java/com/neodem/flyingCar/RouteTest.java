package com.neodem.flyingCar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.neodem.flyingCar.Route;
import com.neodem.flyingCar.model.Location;
import com.neodem.flyingCar.model.Vehicle;
import com.neodem.flyingCar.model.time.Time;

/**
 * @author vfumo
 * 
 */
public class RouteTest {

	private Route r = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		r = new Route();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		r = null;
	}

	@Test
	public void routeShouldReturnCorrectEndTime() {
		Vehicle v = Vehicle.getTestVehicle(1, 1000, 1);

		// for this vehicle, this trip should take 4 
		Location from = new Location(1, 1);
		Location to = new Location(5, 1);
		
		Time startTime = new Time(0);

		Route route = new Route(from, to, startTime);
		
		Time endTime = route.getEndTime(startTime, v);

		assertThat(endTime, equalTo(new Time(4)));
	}

	@Test
	public void routeShouldBeAbleToComputeLength() {
		Location from1 = new Location(1, 1);
		Location to1 = new Location(5, 1);
		Route thisRoute = new Route(from1, to1, null);
		double distance = thisRoute.getDistance();
		
		assertThat(distance, is(closeTo(4, .01)));
	}

	@Test
	public void routeShouldBeAbleToComputeLength2() {
		Location from1 = new Location(1, 1);
		Location to1 = new Location(5, -1);
		Route thisRoute = new Route(from1, to1, null);
		double distance = thisRoute.getDistance();
		
		assertThat(distance, is(closeTo(4.47, .01)));
	}

	@Test
	public void collidesWithShouldWorkFine() {
		Location from1 = new Location(1, 1);
		Location to1 = new Location(5, -1);
		Route thisRoute = new Route(from1, to1, null);

		Location from2 = new Location(2, 1);
		Location to2 = new Location(3, -3);
		Route plannedRoute = new Route(from2, to2, null);

		assertTrue(thisRoute.collidesWith(plannedRoute));
	}

	@Test
	public void goodIntersectionShouldComeBackFine() {
		Location from1 = new Location(1, 1);
		Location to1 = new Location(5, -1);
		Location from2 = new Location(2, 1);
		Location to2 = new Location(3, -3);

		Location intersection = r.getIntersection(from1, to1, from2, to2);
		assertNotNull(intersection);
		
		assertThat(intersection.getX(), is(closeTo(2.64, .01)));
		assertThat(intersection.getY(), is(closeTo(0.92, .01)));
	}

	@Test
	public void twoNonIntersectingLinesShouldNotIntersect() {
		Location from1 = new Location(1, 1);
		Location to1 = new Location(2, 4);
		Location from2 = new Location(2, 1);
		Location to2 = new Location(3, -3);

		Location intersection = r.getIntersection(from1, to1, from2, to2);
		assertNull(intersection);
	}

}
