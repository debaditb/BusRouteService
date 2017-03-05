package com.goeuro.challange.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class BusRouteServer {

	public void start() {
		final ResourceConfig config = new ResourceConfig().packages("com.goeuro.challange")
				.register(JacksonFeature.class);
		final ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));
		final Server server = new Server(8088);
		final ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/api");
		context.addServlet(jerseyServlet, "/*");
		server.setHandler(context);
		try {
			server.start();
			server.join();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			server.destroy();
		}
	}

}
