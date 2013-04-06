package com.neodem.flyingCar.model;

import java.util.HashSet;
import java.util.Set;

import com.neodem.flyingCar.Route;
import com.neodem.flyingCar.model.time.Duration;
import com.neodem.flyingCar.model.time.Time;

/**
 * a flight level is an altitude that is available for travel. Each FlightLevel
 * holds a set of reserved routes that are occuring.
 * 
 * Current Rules for FlightLevel
 * <ul>
 * <li>empty FL accepts any Route
 * <li>if new Route starts when no other routes are happening, it's accepted
 * <li>if new Route starts during the duration of a registered route it must not
 * collide (note, sure a collision may only occur temporally (eg. both planes at
 * intersection point at same time), but this is not considered)
 * 
 * note that the FlightLevel will allow any reservation. It's up to the caller
 * to make sure that they call canAccept() first
 * 
 * TODO : fix rule 3 (above), add registerd route cleanup,
 * 
 * @author vfumo
 * 
 */
public class FlightLevel {

	public static final int BASE_ALITITUDE = 1000;

	public static final int LEVEL_SIZE = 10;

	/**
	 * this is the actual altitude of the flight level
	 */
	private int flightLevel;

	/**
	 * current reserved routess
	 */
	private Set<FlightLevelRegistration> registeredRoutes;

	protected class FlightLevelRegistration {
		Vehicle v;

		// route, including the actual start time on the ground
		Route r;

		// duration on the flight level
		Duration d;
	}

	/**
	 * all flight levels begin at some Base Altitude. The 'key' value here is
	 * the altitude of this flight level on top of the base altitude.
	 * 
	 * @param key
	 */
	public FlightLevel(int key) {
		flightLevel = (key * LEVEL_SIZE) + BASE_ALITITUDE;
		registeredRoutes = new HashSet<FlightLevelRegistration>();
	}

	public double getLevel() {
		return flightLevel;
	}

	/**
	 * This is the amount of time it takes a vehicle to get to this flight
	 * level.
	 * 
	 * @TODO refactor and possibly remove this
	 * 
	 * @return
	 */
	public double getTravelTime(Vehicle v) {
		return getLevel() / v.getClimbRate();
	}

	/**
	 * return true if a given route can occur on this flight level at the given
	 * startTime
	 * 
	 * @param route
	 * @return
	 */
	protected boolean canAccept(Vehicle v, Route route, Time startTime) {
		if (registeredRoutes.size() == 0) {
			return true;
		}

		for (FlightLevelRegistration pr : registeredRoutes) {
			Duration d = pr.d;

			// this route will start during a trip reserved already
			// will they crash?
			if (d.contains(startTime)) {
				Route r = pr.r;
				if (r.collidesWith(route)) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * determine if we can fly on this level at the given start time. Also
	 * include the time to get to this level
	 * 
	 * @param v
	 * @param route
	 *            should have start and end times for the route only. (not
	 *            including time to or from the FL)
	 * @return true if reservation is made
	 */
	public boolean reserve(Vehicle v, final Route route) {
		Time routeStart = route.getStartTime();

		// the time we'll be arriving at the level
		Time levelStart = new Time(routeStart).add(getTravelTime(v));
		
		// the time we'll be done on the level
		Time levelEnd = route.getEndTime(levelStart, v);

		if (canAccept(v, route, levelStart)) {
			// this is the duration on the flight level.
			// it does not include time up or down
			Duration d = new Duration(levelStart, levelEnd);
			FlightLevelRegistration pr = new FlightLevelRegistration();
			pr.d = d;
			pr.v = v;
			pr.r = route;

			registeredRoutes.add(pr);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Integer.toString(flightLevel);
	}

	/**
	 * @param registeredRoutes
	 *            the registeredRoutes to set
	 */
	public void setRegisteredRoutes(Set<FlightLevelRegistration> registeredRoutes) {
		this.registeredRoutes = registeredRoutes;
	}

	/**
	 * @return the registeredRoutes
	 */
	public Set<FlightLevelRegistration> getRegisteredRoutes() {
		return registeredRoutes;
	}
}
