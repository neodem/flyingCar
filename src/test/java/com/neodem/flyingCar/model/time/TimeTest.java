package com.neodem.flyingCar.model.time;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TimeTest {

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAllowConstructionBelowMin() {
		new Time(Time.MINTIME - 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAllowConstructionAboveMax() {
		new Time(Time.MAXTIME + 1);
	}

	@Test
	public void incShouldAddOneToTime() {
		Time time = new Time(0).inc();
		assertThat(time.getActualTime(), equalTo(1d));
	}

	@Test
	public void incShouldAddOneToTimeUnlessWeAreAtMaxTime() {
		Time time = new Time(Time.MAXTIME).inc();
		assertThat(time.getActualTime(), equalTo(Time.MAXTIME));
	}

	@Test
	public void addShouldAddToTime() {
		Time time = new Time(0).add(10);
		assertThat(time.getActualTime(), equalTo(10d));
	}

	@Test
	public void addShouldAddToTimeUnlessWeGoPast() {
		Time time = new Time(Time.MAXTIME - 2);

		time.add(1);
		assertThat(time.getActualTime(), equalTo(Time.MAXTIME - 1));

		time.add(10);
		assertThat(time.getActualTime(), equalTo(Time.MAXTIME));
	}

	@Test
	public void decShouldRemoveOneFromTime() {
		Time time = new Time(1).dec();
		assertThat(time.getActualTime(), equalTo(0d));
	}

	@Test
	public void decShouldRemoveOneFromTimeUnlessWeAreAtMinTime() {
		Time time = new Time(Time.MINTIME).dec();
		assertThat(time.getActualTime(), equalTo(Time.MINTIME));
	}

	@Test
	public void earlierThanSholdReturnFalseWhenTimesAreEqual() {
		Time start = new Time(0);
		Time end = new Time(0);
		assertFalse(start.earlierThan(end));
	}

	@Test
	public void earlierThanShouldWorkWhenStartBeforeEnd() {
		Time start = new Time(0);
		Time end = new Time(1);
		assertTrue(start.earlierThan(end));
		assertFalse(end.earlierThan(start));
	}

	@Test
	public void equalsShouldNotCareAboutAnythingButActualTime() {
		Time one = new Time(100);
		Time two = new Time(100);

		assertTrue(one.equals(two));
	}

	@Test
	public void equalsShouldWork() {
		Time one = new Time(100);
		Time two = new Time(10);

		assertFalse(one.equals(two));
	}

}
