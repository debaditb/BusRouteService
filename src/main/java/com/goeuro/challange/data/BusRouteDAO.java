package com.goeuro.challange.data;

import java.util.Set;

public class BusRouteDAO {

	public void addStationWithRoutes(Long stationId, Long routeId) {
		BusRouteDB.INSTANCE.addRoutesTo(stationId, routeId);
	}

	public Set<Long> getRoutesFor(Long stationId) {
		return BusRouteDB.INSTANCE.getRoutesFor(stationId);
	}
}
