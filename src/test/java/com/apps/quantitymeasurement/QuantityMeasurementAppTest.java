package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementAppTest {

	private IQuantityMeasurementService service;
	private QuantityMeasurementController controller;

	@BeforeEach
	void setup() {

		QuantityMeasurementCacheRepository repository = QuantityMeasurementCacheRepository.getInstance();

		service = new QuantityMeasurementServiceImpl(repository);

		controller = new QuantityMeasurementController(service);
	}

	@Test
	void testAddition_Success() {

		QuantityDTO q1 = new QuantityDTO(10, "METER");
		QuantityDTO q2 = new QuantityDTO(5, "METER");

		double result = service.add(q1, q2);

		assertEquals(15, result);
	}

	@Test
	void testSubtraction_Success() {

		QuantityDTO q1 = new QuantityDTO(10, "METER");
		QuantityDTO q2 = new QuantityDTO(5, "METER");

		double result = service.subtract(q1, q2);

		assertEquals(5, result);
	}

	@Test
	void testDivision_Success() {

		QuantityDTO q1 = new QuantityDTO(10, "METER");
		QuantityDTO q2 = new QuantityDTO(5, "METER");

		double result = service.divide(q1, q2);

		assertEquals(2, result);
	}

	@Test
	void testDivision_ByZero() {

		QuantityDTO q1 = new QuantityDTO(10, "METER");
		QuantityDTO q2 = new QuantityDTO(0, "METER");

		assertThrows(ArithmeticException.class, () -> {
			service.divide(q1, q2);
		});
	}

	@Test
	void testController_PerformAddition() {

		QuantityDTO q1 = new QuantityDTO(20, "METER");
		QuantityDTO q2 = new QuantityDTO(10, "METER");

		assertDoesNotThrow(() -> controller.performAddition(q1, q2));
	}

	@Test
	void testController_PerformSubtraction() {

		QuantityDTO q1 = new QuantityDTO(20, "METER");
		QuantityDTO q2 = new QuantityDTO(10, "METER");

		assertDoesNotThrow(() -> controller.performSubtraction(q1, q2));
	}

	@Test
	void testController_PerformDivision() {

		QuantityDTO q1 = new QuantityDTO(20, "METER");
		QuantityDTO q2 = new QuantityDTO(10, "METER");

		assertDoesNotThrow(() -> controller.performDivision(q1, q2));
	}
}