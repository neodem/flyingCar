package com.neodem.flyingCar.model.time;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DurationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void durationConstructionShouldNowBeAllowedWhenStartIsAfterEnd() {
		Time start = new Time(10);
		Time end = new Time(0);
		new Duration(start, end);
	}

	@Test(expected = IllegalArgumentException.class)
	public void durationConstructionShouldNowBeAllowedWhenStartAndEndAreNull() {
		new Duration(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void durationConstructionShouldNowBeAllowedWhenEndIsNull() {
		new Duration(new Time(10), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void durationConstructionShouldNowBeAllowedWhenStartIsNull() {
		new Duration(null, new Time(10));
	}

	@Test
	public void durationConstructionShouldBeAllowedWhenStartIsTheSameAsEnd() {
		Time start = new Time(10);
		assertNotNull(new Duration(start, start));
	}

	@Test
	public void durationShouldBeZeroWhenStartAndEndAreTheSame() {
		Time time = new Time(10);
		Duration duration = new Duration(time, time);
		assertThat(duration.getDuration(), equalTo(0d));

	}

	@Test
	public void containsShouldReturnTrueForEdgeCases() {
		Time start = new Time(0);
		Time end = new Time(10);
		Duration d = new Duration(start, end);
		assertTrue(d.contains(new Time(0)));
		assertTrue(d.contains(new Time(10)));
	}

	@Test
	public void containsReturnsTrueWhenTimeInside() {
		Time start = new Time(0);
		Time end = new Time(10);
		Duration d = new Duration(start, end);
		assertTrue(d.contains(new Time(1)));
		assertTrue(d.contains(new Time(9)));
	}

	@Test
	public void containsReturnsFalseWhenTimeOutside() {
		Time start = new Time(5);
		Time end = new Time(10);
		Duration d = new Duration(start, end);
		assertFalse(d.contains(new Time(4)));
		assertFalse(d.contains(new Time(11)));
	}

}
