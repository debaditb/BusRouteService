package com.goeuro.challange.processors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.goeuro.challange.data.BusRouteDAO;
import com.goeuro.challange.http.RouteInfoDTO;

public class BusRouteRepository {
	private final BusRouteDAO routeDao;

	public BusRouteRepository(BusRouteDAO routeDao) {
		this.routeDao = routeDao;
	}

	public RouteInfoDTO directRouteInfo(Long departureStation, Long arrivalStation) {
		final Map<String, Set<Long>> results = shorterAndLonger(routeDao.getRoutesFor(departureStation),
				routeDao.getRoutesFor(arrivalStation));
		final Boolean result = results.get("shorter").stream().anyMatch(results.get("longer")::contains);
		return new RouteInfoDTO(departureStation, arrivalStation, result);
	}

	public void importRoutesToDB(String line) {
		final String[] routeWithStations = line.split(" ");
		final Long routeIdLong = Long.parseLong(routeWithStations[0]);
		Arrays.stream(routeWithStations, 1, routeWithStations.length)
				.forEach((stationId) -> routeDao.addStationWithRoutes(Long.parseLong(stationId), routeIdLong));

	}

	private Map<String, Set<Long>> shorterAndLonger(Set<Long> set1, Set<Long> set2) {
		final Map<String, Set<Long>> resultMap = new HashMap<>();
		if (set1.size() > set2.size()) {
			resultMap.put("shorter", set2);
			resultMap.put("longer", set1);
		} else {
			resultMap.put("shorter", set1);
			resultMap.put("longer", set2);
		}
		return resultMap;
	}

}
