package com.goeuro.challange.processors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class InputProcessor {
	private final String filePath;
	private final BusRouteRepository routeImporter;

	public InputProcessor(String location, BusRouteRepository importer) {
		if (null == location || location.isEmpty()) {
			throw new IllegalArgumentException("Input file location is null or empty");
		}
		this.filePath = location;
		this.routeImporter = importer;
	}

	public void readFromFile() {
		try (final Stream<String> lines = Files.lines(Paths.get(filePath))) {
			lines.skip(1).forEach(routeImporter::importRoutesToDB);
		} catch (final NoSuchFileException e) {
			final String message = "No such File as : " + e.getMessage();
			System.out.println(message);
			throw new RuntimeException(message);
		} catch (final IOException e) {
			final String message = "I/O error occured : " + e.getMessage();
			System.out.println(message);
			throw new RuntimeException(message);
		}
	}

}
