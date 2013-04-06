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
public class ReserverLoadTest {

	private static final double MAXX = 100;
	private static final double MAXY = 100;
	private Reserver reserver;
	private Random randomGenerator;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		reserver = new ReserverImpl();
		randomGenerator = new Random();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		reserver = null;
		randomGenerator = null;
	}

	@Test
	public void testRandomLoad() {
		int i = 1;
		for (; i < 100000; i++) {
			Vehicle v =  Vehicle.getTestVehicle(i, 10, 1);
			Time time = new Time(0);
			Location from = randomLocation();
			Location to = randomLocation();

			Route route = new Route(from, to, time);
			Reservation r = reserver.reserve(v, route);
			if (r == null) {
				break;
			}
			System.out.println(r);

			Time reservedStartTime = r.getStartTime();
			if (!reservedStartTime.equals(time)) {
				System.out.println("time difference");
				break;
			}
		}
		System.out.println("" + i);
	}

	/**
	 * @return
	 */
	private Location randomLocation() {
		double x = randomGenerator.nextDouble() * MAXX;
		double y = randomGenerator.nextDouble() * MAXY;
		return new Location(x, y);
	}

}
