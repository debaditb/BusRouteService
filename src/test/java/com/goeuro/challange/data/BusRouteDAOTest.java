package com.goeuro.challange.data;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BusRouteDAOTest {
	public BusRouteDAO daoToTest;

	@Before
	public void setUp() {
		daoToTest = new BusRouteDAO();
	}

	@Test
	public void shouldAppendRoutesForAnExistingStation() {
		daoToTest.addStationWithRoutes(1l, 2l);
		daoToTest.addStationWithRoutes(1l, 3l);
		final Set<Long> expected = new HashSet<>();
		expected.add(2l);
		expected.add(3l);
		assertEquals(expected, daoToTest.getRoutesFor(1l));
	}

	@Test
	public void shouldCreateEntryForANewStationWithRoutes() {
		daoToTest.addStationWithRoutes(1l, 2l);
		final Set<Long> expected = new HashSet<>();
		expected.add(2l);
		assertEquals(expected, daoToTest.getRoutesFor(1l));
	}

	@After
	public void tearDown() {
		BusRouteDB.INSTANCE.removeAllRoutes();
	}
}
