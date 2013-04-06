package com.neodem.flyingCar;

import java.util.ArrayList;
import java.util.List;

import com.neodem.flyingCar.model.FlightLevel;
import com.neodem.flyingCar.model.Reservation;
import com.neodem.flyingCar.model.Vehicle;
import com.neodem.flyingCar.model.time.Time;

/**
 * @author vfumo
 * 
 */
public class ReserverImpl implements Reserver {

	private static final int MAX_LEVELS = 10;

	/**
	 * the planes we have available for travel (note these are flight levels)
	 */
	private List<FlightLevel> flightLevels;

	/**
	 * 
	 */
	public ReserverImpl() {
		this(MAX_LEVELS);
	}

	/**
	 * 
	 */
	public ReserverImpl(int numLevels) {
		flightLevels = new ArrayList<FlightLevel>(numLevels);
		for (int i = 0; i < numLevels; i++) {
			flightLevels.add(new FlightLevel(i));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neodem.flyingCar.Reserver#reserve(com.neodem.flyingCar.Vehicle,
	 * com.neodem.flyingCar.Route)
	 */
	public Reservation reserve(Vehicle v, Route route) {
		Time desiredGoTime = route.getStartTime();
		Time actualGoTime = new Time(desiredGoTime);

		// see if there is a flight level we can go to
		// (first at the desiredGoTime, then inc)
		do {
			route.setStartTime(actualGoTime);
			for (FlightLevel level : flightLevels) {
				if (level.reserve(v, route)) {
					return new Reservation(level, route);
				}
			}
			actualGoTime.inc();
		} while (!actualGoTime.equals(Time.MAXTIME));

		return null;
	}
}
