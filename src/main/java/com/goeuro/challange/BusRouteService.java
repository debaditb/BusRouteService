package com.goeuro.challange;

import com.goeuro.challange.data.BusRouteDAO;
import com.goeuro.challange.http.BusRouteServer;
import com.goeuro.challange.processors.BusRouteRepository;
import com.goeuro.challange.processors.InputProcessor;

public class BusRouteService {

	public void start(String filepath) {
		final BusRouteDAO dao = new BusRouteDAO();
		final BusRouteRepository importer = new BusRouteRepository(dao);
		final InputProcessor processor = new InputProcessor(filepath, importer);
		processor.readFromFile();
		System.out.println("Reading completed from : " + filepath);
		final BusRouteServer server = new BusRouteServer();
		server.start();
	}
}
