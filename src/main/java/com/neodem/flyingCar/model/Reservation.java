package com.neodem.flyingCar.model;

import com.neodem.flyingCar.Route;
import com.neodem.flyingCar.model.time.Time;

/**
 * @author vfumo
 *
 */
public class Reservation {

	private FlightLevel flightLevel;
	private Route route;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("FlightLevel : %s, Route : %s", flightLevel, route);
	}
	
	/**
	 * @param travelPlane
	 * @param route
	 */
	public Reservation(FlightLevel flightLevel, Route route) {
		this.flightLevel = flightLevel;
		this.route = route;
	}

	/**
	 * @return
	 */
	public Time getStartTime() {
		return route.getStartTime();
	}

}
