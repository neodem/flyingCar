package com.neodem.flyingCar.model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.neodem.flyingCar.Reserver;
import com.neodem.flyingCar.ReserverImpl;
import com.neodem.flyingCar.Route;
import com.neodem.flyingCar.model.Vehicle;
import com.neodem.flyingCar.model.time.Time;

/**
 * @author vfumo
 * 
 */
public class ReserverTest {

	private Reserver reserver;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		reserver = new ReserverImpl();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		reserver = null;
	}

	@Test
	public void reserverShouldReserveOnInitialStart() {

		Time time = new Time(0);
		Location from = new Location(0, 0);
		Location to = new Location(10, 10);

		Route route = new Route(from, to, time);

		Vehicle v = Vehicle.getTestVehicle(1, 1000, 1);

		Reservation r = reserver.reserve(v, route);
		assertNotNull(r);
	}
}
