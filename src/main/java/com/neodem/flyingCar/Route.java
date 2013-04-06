package com.neodem.flyingCar;

import com.neodem.flyingCar.model.Location;
import com.neodem.flyingCar.model.Vehicle;
import com.neodem.flyingCar.model.time.Time;

/**
 * a Route encapsulates a trip from one location to another at a given time.
 * 
 * Most of the math involved in intersections/distance are here.
 * 
 * @author vfumo
 * 
 */
public class Route {
	private Location from;

	private Location to;

	private Time startTime;

	private Double distance = null;

	public Route() {
	}

	/**
	 * @param start
	 *            the starting location
	 * @param end
	 *            the ending location
	 * @param startTime
	 *            the time the trip begins
	 */
	public Route(Location from, Location to, Time startTime) {
		super();
		this.from = from;
		this.to = to;
		this.startTime = startTime;
	}

	/**
	 * copy constructor
	 * 
	 * @param route
	 */
	public Route(Route route) {
		this.from = new Location(route.getFrom());
		this.to = new Location(route.getTo());
		this.startTime = new Time(route.getStartTime());
	}

	/**
	 * determine if the planned route intersects with this route
	 * 
	 * @param plannedRoute
	 * @return
	 */
	public boolean collidesWith(Route plannedRoute) {
		Location l11 = this.getFrom();
		Location l12 = this.getTo();
		Location l21 = plannedRoute.getFrom();
		Location l22 = plannedRoute.getTo();

		return getIntersection(l11, l12, l21, l22) != null;
	}

	/**
	 * determine if two routes intesrsect
	 * 
	 * @param from1
	 *            starting point for route1
	 * @param to1
	 *            ending point for route1
	 * @param from2
	 *            starting point for route2
	 * @param to2
	 *            ending point from route2
	 * @param intersection
	 * @return the intersection or null if none
	 */
	protected Location getIntersection(Location from1, Location to1, Location from2, Location to2) {
		// Based on the 2d line intersection method from
		// "comp.graphics.algorithms

		/*
		 * (Ay-Cy)(Dx-Cx)-(Ax-Cx)(Dy-Cy) r = ----------------------------- (eqn
		 * 1) (Bx-Ax)(Dy-Cy)-(By-Ay)(Dx-Cx)
		 */

		double ay = from1.getY();
		double by = to1.getY();
		double cy = from2.getY();
		double dy = to2.getY();

		double ax = from1.getX();
		double bx = to1.getX();
		double cx = from2.getX();
		double dx = to2.getX();

		double q = (ay - cy) * (dx - cx) - (ax - cx) * (dy - cy);
		double d = (bx - ax) * (dy - cy) - (by - ay) * (dx - cx);

		if (d == 0) // parallel lines so no intersection anywhere in space
		{
			return null;
		}

		double r = q / d;

		/*
		 * (Ay-Cy)(Bx-Ax)-(Ax-Cx)(By-Ay) s = ----------------------------- (eqn
		 * 2) (Bx-Ax)(Dy-Cy)-(By-Ay)(Dx-Cx)
		 */

		q = (ay - cy) * (bx - ax) - (ax - cx) * (by - ay);
		double s = q / d;

		/*
		 * If r>1, P is located on extension of AB If r<0, P is located on
		 * extension of BA If s>1, P is located on extension of CD If s<0, P is
		 * located on extension of DC
		 * 
		 * The above basically checks if the intersection is located at an
		 * extrapolated point outside of the line segments. To ensure the
		 * intersection is only within the line segments then the above must all
		 * be false, ie r between 0 and 1 and s between 0 and 1.
		 */

		if (r < 0 || r > 1 || s < 0 || s > 1) {
			return null;
		}

		/*
		 * Px=Ax+r(Bx-Ax) Py=Ay+r(By-Ay)
		 */

		double intersectionX = ax + (0.5f + r * (bx - ax));
		double intersectionY = ay + (0.5f + r * (by - ay));

		Location intersection = new Location(intersectionX, intersectionY);

		return intersection;
	}

	protected double getDistance() {
		if (distance == null) {
			// d = sqrrt(((x1-x2)sq) + ((y1-y2)sq))
			double x1 = this.getFrom().getX();
			double x2 = this.getTo().getX();
			double y1 = this.getFrom().getY();
			double y2 = this.getTo().getY();

			double x = x1 - x2;
			double y = y1 - y2;

			x = x * x;
			y = y * y;

			distance = new Double(Math.sqrt(x + y));
		}
		return distance;
	}

	/**
	 * determine the endTime (not counting going up or down) of this route for a given vehicle
	 * 
	 * @return
	 */
	public Time getEndTime(Time startTime, Vehicle v) {
		Time endTime = new Time(startTime);
		double time = getDistance()/ v.getDriveRate();
		endTime.add(time);
		return endTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("from %s to %s at : %s", from, to, startTime);
	}

	/**
	 * @return the from
	 */
	public Location getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public Location getTo() {
		return to;
	}

	/**
	 * @return the startTime
	 */
	public Time getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(Location from) {
		this.from = from;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(Location to) {
		this.to = to;
	}

}
