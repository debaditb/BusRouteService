package com.goeuro.challange;

/**
 * Bus Route Service
 *
 */
public class App {
	public static void main(String[] args) {
		final BusRouteService service = new BusRouteService();
		service.start(args[0]);
	}
}
