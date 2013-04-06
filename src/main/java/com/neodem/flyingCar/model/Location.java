/**
 * 
 */
package com.neodem.flyingCar.model;

/**
 * @author vfumo
 * 
 */
public class Location {
	private double x;

	private double y;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("[%05.2f, %05.2f]", new Double(x), new Double(y));
	}

	/**
	 * @param x
	 * @param y
	 */
	public Location(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 */
	public Location() {
	}

	/**
	 * @param from
	 */
	public Location(Location from) {
		this(from.getX(), from.getY());
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

}
