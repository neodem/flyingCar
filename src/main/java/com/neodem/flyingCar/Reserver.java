package com.neodem.flyingCar;

import com.neodem.flyingCar.model.Reservation;
import com.neodem.flyingCar.model.Vehicle;

/**
 * @author vfumo
 *
 */
public interface Reserver {
	
	/**
	 * attempt to reserve a route
	 * 
	 * 
	 * @return a {@link Reservation} object containg info on the route
	 */
	public Reservation reserve(Vehicle v, Route route);
	
}
