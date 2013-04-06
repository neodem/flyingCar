package com.neodem.flyingCar.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.neodem.flyingCar.Route;
import com.neodem.flyingCar.model.FlightLevel;
import com.neodem.flyingCar.model.Vehicle;
import com.neodem.flyingCar.model.FlightLevel.FlightLevelRegistration;
import com.neodem.flyingCar.model.time.Time;

public class FlightLevelTest {
	
	private FlightLevel fl;
	
	// this vehicle takes 1 time to go 1 unit and 1 time to climb 10 in altitude
	private static final Vehicle testVehicle = Vehicle.getTestVehicle(0, 10, 1);

	@Before
	public void setUp() throws Exception {
		fl = new FlightLevel(0);
	}

	@After
	public void tearDown() throws Exception {
		fl = null;
	}
	
	@Test
	public void getTravelTimeShouldReturnCorrectValues() {
		assertThat(fl.getTravelTime(testVehicle), closeTo(100, 0.01));
	}
	
	@Test
	public void reserveShouldReserveAnyFirstRoute() {
		// for this vehicle, this trip should take 4
		Location from = new Location(1, 1);
		Location to = new Location(5, 1);
		
		Route route = new Route(from, to, new Time(0));
		
		fl.reserve(testVehicle, route);
		
		Set<FlightLevelRegistration> registeredRoutes = fl.getRegisteredRoutes();
		assertThat(registeredRoutes.size(), equalTo(1));
		
		FlightLevelRegistration flr = registeredRoutes.iterator().next();
		assertThat(flr.v, equalTo(testVehicle));
	}
	
	/**
	 * 
	 */
	@Test
	public void intersectingRoutesHappeningAtDifferentTimesShouldBeAlowed() {
		// for this vehicle, this trip should take 4
		Location from = new Location(1, 1);
		Location to = new Location(5, 1);
		
		Route route1 = new Route(from, to, new Time(0));
		Route route2 = new Route(from, to, new Time(5));
		
		assertTrue(fl.reserve(testVehicle, route1));
		assertTrue(fl.reserve(testVehicle, route2));
	}

}
