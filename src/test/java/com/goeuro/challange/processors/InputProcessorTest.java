package com.goeuro.challange.processors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InputProcessorTest {
	@Mock
	private BusRouteRepository importer;
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void shouldCallImporterForEachLineApartFromTheFirst() {
		final InputProcessor processor = new InputProcessor("src/test/resources/sampleBusRouteFile.txt", importer);
		processor.readFromFile();
		verify(importer, times(3)).importRoutesToDB(anyString());
	}

	@Test
	public void shouldThrowIllegarArgumentExceptionIfLocationIsEmpty() throws Exception {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Input file location is null or empty");
		final InputProcessor processor = new InputProcessor("", importer);
	}

	@Test
	public void shouldThrowIllegarArgumentExceptionIfLocationNotPresent() throws Exception {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Input file location is null or empty");
		final InputProcessor processor = new InputProcessor(null, importer);
	}

	@Test
	public void shouldThrowRuntimeExceptionForBadLocation() throws Exception {
		final InputProcessor processor = new InputProcessor("abcd/xyz.txt", importer);
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("No such File as : abcd/xyz.txt");
		processor.readFromFile();
	}
}
