package com.goeuro.challange.processors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.goeuro.challange.data.BusRouteDAO;

@RunWith(MockitoJUnitRunner.class)
public class BusRouteRepositoryTest {
	private BusRouteRepository importer;
	@Mock
	private BusRouteDAO dao;

	@Before
	public void setUp() {
		importer = new BusRouteRepository(dao);
	}

	@Test
	public void shouldCallDaoToUpdateTheStationWithRoutes() {
		final String line = "0 1 2";
		importer.importRoutesToDB(line);
		verify(dao, times(1)).addStationWithRoutes(1l, 0l);
	}

	@Test
	public void shouldReturnFalseIfArrivalStationDoesNotHaveAnyRoutes() {
		final Set<Long> routes1 = new HashSet<>();
		routes1.add(2l);
		when(dao.getRoutesFor(1l)).thenReturn(routes1);
		when(dao.getRoutesFor(2l)).thenReturn(new HashSet<Long>());
		assertFalse(importer.directRouteInfo(1l, 2l).getHasDirectBusRoute());
	}

	@Test
	public void shouldReturnFalseIfDepStationDoesNotHaveAnyRoutes() {
		final Set<Long> routes2 = new HashSet<>();
		routes2.add(2l);
		when(dao.getRoutesFor(1l)).thenReturn(new HashSet<Long>());
		when(dao.getRoutesFor(2l)).thenReturn(routes2);
		assertFalse(importer.directRouteInfo(1l, 2l).getHasDirectBusRoute());
	}

	@Test
	public void shouldReturnFalseIfDirectRouteDoesNotExist() {
		final Set<Long> routes1 = new HashSet<>();
		routes1.add(1l);
		final Set<Long> routes2 = new HashSet<>();
		routes2.add(2l);
		when(dao.getRoutesFor(1l)).thenReturn(routes1);
		when(dao.getRoutesFor(2l)).thenReturn(routes2);
		assertFalse(importer.directRouteInfo(1l, 2l).getHasDirectBusRoute());
	}

	@Test
	public void shouldReturnTrueIfDirectRouteExists() {
		final Set<Long> routes1 = new HashSet<>();
		routes1.add(1l);
		routes1.add(2l);
		final Set<Long> routes2 = new HashSet<>();
		routes2.add(2l);
		routes2.add(3l);
		when(dao.getRoutesFor(1l)).thenReturn(routes1);
		when(dao.getRoutesFor(2l)).thenReturn(routes2);
		assertTrue(importer.directRouteInfo(1l, 2l).getHasDirectBusRoute());
	}

	@Test
	public void shouldSetFirstElementAsRouteIdForAllStations() {
		final String line = "0 1 2 0";
		importer.importRoutesToDB(line);
		verify(dao, times(3)).addStationWithRoutes(anyLong(), anyLong());
	}
}
