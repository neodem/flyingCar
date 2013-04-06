package com.neodem.flyingCar.model;

import com.neodem.flyingCar.VehicleId;

/**
 * this car can travel at one unit per second (climbing and cruising)
 * 
 * @author vfumo
 *
 */
public class BasicCar extends Vehicle {
	
	private static final double CLIMB_RATE = 1.0d;
	private static final double DRIVE_RATE = 1.0d;
	
	
	public BasicCar(int id) {
		super(new VehicleId(id));
	}

	@Override
	public double getClimbRate() {
		return CLIMB_RATE;
	}

	@Override
	public double getDriveRate() {
		return DRIVE_RATE;
	}

}
