package com.goeuro.challange.http;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.goeuro.challange.data.BusRouteDAO;
import com.goeuro.challange.processors.BusRouteRepository;

@Path("/")
public class RouteResource {
	private final BusRouteRepository repository;

	public RouteResource() {
		repository = new BusRouteRepository(new BusRouteDAO());
	}

	@GET
	@Path("/direct")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBusRouteInfo(@QueryParam("dep_sid") final String depSid,
			@QueryParam("arr_sid") final String arrSid) {
		if (isInvalidQueryParams(depSid, arrSid)) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("departure or arrival station id can not be empty").type(MediaType.APPLICATION_JSON)
					.build();
		}
		return Response.ok(repository.directRouteInfo(Long.parseLong(depSid), Long.parseLong(arrSid))).build();
	}

	private boolean isInvalidQueryParams(String depSid, String arrSid) {
		if (isNotEmpty(depSid) && isNotEmpty(arrSid)) {
			return false;
		}
		return true;
	}

	private boolean isNotEmpty(String s) {
		return (s != null && !s.isEmpty()) ? true : false;
	}

}
