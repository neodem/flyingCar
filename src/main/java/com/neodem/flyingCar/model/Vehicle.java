package com.neodem.flyingCar.model;

import com.neodem.flyingCar.VehicleId;

/**
 * @author vfumo
 * 
 */
public abstract class Vehicle {

	private VehicleId id;

	public static Vehicle getTestVehicle(int id, final double climbRate, final double driveRate) {
		return new Vehicle(new VehicleId(id)) {

			@Override
			public double getClimbRate() {
				return climbRate;
			}

			@Override
			public double getDriveRate() {
				return driveRate;
			}

		};
	}

	/**
	 * 
	 */
	public Vehicle(VehicleId id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "vehicleID = " + id;
	}

	/**
	 * @return
	 */
	public abstract double getClimbRate();

	/**
	 * @return
	 */
	public abstract double getDriveRate();

	/**
	 * @return the id
	 */
	public VehicleId getId() {
		return id;
	}
}
