package com.neodem.flyingCar.model.time;

/**
 * @author vfumo
 * 
 */
public class Time {

	public static final double MAXTIME = 1000d;
	public static final double MINTIME = 0d;

	private double time;

	public Time(double time) {
		if(time > MAXTIME || time < MINTIME) {
			throw new IllegalArgumentException("time value needs to be within boundaries");
		}
		this.time = time;
	}

	private void validate() {
		if (time < MINTIME) {
			time = MINTIME;
		}
		if (time > MAXTIME) {
			time = MAXTIME;
		}
	}

	/**
	 * copy constructor
	 * 
	 * @param desiredGoTime
	 */
	public Time(Time time) {
		this(time.getActualTime());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Time)) {
			return false;
		}

		Time other = (Time) obj;
		return other.getActualTime() == this.getActualTime();
	}

	/**
	 * 
	 */
	public Time inc() {
		time++;
		validate();
		return this;
	}

	/**
	 * 
	 */
	public Time dec() {
		time--;
		validate();
		return this;
	}

	/**
	 * @param travelTimeToPlane
	 */
	public Time add(double units) {
		time += units;
		validate();
		return this;
	}

	/**
	 * return true iff this time is earlier than the other
	 * 
	 * @param other
	 * @return
	 */
	public boolean earlierThan(Time other) {
		double otherTime = other.getActualTime();
		return this.time < otherTime;
	}

	/**
	 * @return
	 */
	protected double getActualTime() {
		return time;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Double.toString(time);
	}

}
