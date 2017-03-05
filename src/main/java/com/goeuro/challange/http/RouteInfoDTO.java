package com.goeuro.challange.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RouteInfoDTO {
	@JsonProperty(value = "dep_sid")
	private final Long detartureStationId;
	@JsonProperty(value = "arr_sid")
	private final Long arrivalStationId;
	@JsonProperty(value = "direct_bus_route")
	private final Boolean hasDirectBusRoute;

	public RouteInfoDTO(Long detartureStationId, Long arrivalStationId, Boolean hasDirectBusRoute) {
		this.arrivalStationId = arrivalStationId;
		this.detartureStationId = detartureStationId;
		this.hasDirectBusRoute = hasDirectBusRoute;
	}

	public Long getArrivalStationId() {
		return arrivalStationId;
	}

	public Long getDetartureStationId() {
		return detartureStationId;
	}

	public Boolean getHasDirectBusRoute() {
		return hasDirectBusRoute;
	}

}
