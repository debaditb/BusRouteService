package com.goeuro.challange.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public enum BusRouteDB {
	INSTANCE;

	private final Map<Long, Set<Long>> busRouteProvider = new HashMap<>();

	private BusRouteDB() {
	}

	protected void addRoutesTo(Long StationId, Long route) {
		final Set<Long> existingRoutes = getExistingRoutes(StationId);
		existingRoutes.add(route);
		busRouteProvider.put(StationId, existingRoutes);
	}

	private Set<Long> getExistingRoutes(Long StationId) {
		final Optional<Set<Long>> result = Optional.ofNullable(busRouteProvider.get(StationId));
		return result.orElse(new HashSet<Long>());
	}

	protected Set<Long> getRoutesFor(Long StationId) {
		return Collections.unmodifiableSet(getExistingRoutes(StationId));
	}

	protected void removeAllRoutes() {
		busRouteProvider.clear();
	}
}
