package com.neodem.flyingCar.model.time;

public class Duration {

	private double startTime;
	private double endTime;
	private double duration = -1;

	/**
	 * 
	 */
	public Duration(Time start, Time end) {
		if (start == null || end == null) {
			throw new IllegalArgumentException("start and end must both be non null");
		}

		if (start.earlierThan(end) || start.equals(end)) {
			startTime = start.getActualTime();
			endTime = end.getActualTime();
			duration = endTime - startTime;
		} else {
			throw new IllegalArgumentException("start needs to be before or equal to end");
		}
	}

	public boolean contains(Time time) {
		double t = time.getActualTime();
		return (t >= startTime && t <= endTime);
	}

	public double getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		return String.format("startTime=%s, endTime=%s, duration=%s", Double.toString(startTime), Double
				.toString(endTime), Double.toString(duration));
	}
}
