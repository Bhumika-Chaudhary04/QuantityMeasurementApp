package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementAppTest {

	private QuantityMeasurementServiceImpl service;

	@BeforeEach
	void setUp() {
		IQuantityMeasurementRepository repo = new QuantityMeasurementDatabaseRepository();
		service = new QuantityMeasurementServiceImpl(repo);
	}

	@Test
	void testAddition() {
		double result = service.add(new QuantityDTO("LENGTH", 10), new QuantityDTO("LENGTH", 5));
		assertEquals(15.0, result);
	}

	@Test
	void testSubtraction() {
		double result = service.subtract(new QuantityDTO("LENGTH", 10), new QuantityDTO("LENGTH", 5));
		assertEquals(5.0, result);
	}

	@Test
	void testDivision() {
		double result = service.divide(new QuantityDTO("LENGTH", 10), new QuantityDTO("LENGTH", 5));
		assertEquals(2.0, result);
	}

	@Test
	void testNegativeValues() {
		double result = service.add(new QuantityDTO("LENGTH", -10), new QuantityDTO("LENGTH", 5));
		assertEquals(-5.0, result);
	}

	@Test
	void testMultipleOperationsCount() {
		service.add(new QuantityDTO("LENGTH", 10), new QuantityDTO("LENGTH", 5));
		service.subtract(new QuantityDTO("LENGTH", 20), new QuantityDTO("LENGTH", 10));
		service.divide(new QuantityDTO("LENGTH", 50), new QuantityDTO("LENGTH", 5));

		assertEquals(3, service.count());
	}

	@Test
	void testGetAll() {
		service.add(new QuantityDTO("LENGTH", 10), new QuantityDTO("LENGTH", 5));

		List<QuantityMeasurementEntity> list = service.getAll();

		assertNotNull(list);
		assertTrue(list.size() > 0);
	}

	@Test
	void testDeleteAll() {
		service.add(new QuantityDTO("LENGTH", 10), new QuantityDTO("LENGTH", 5));

		service.deleteAll();

		assertEquals(0, service.count());
	}

	@Test
	void testStoredEntityData() {
		service.add(new QuantityDTO("LENGTH", 10), new QuantityDTO("LENGTH", 5));

		List<QuantityMeasurementEntity> list = service.getAll();

		QuantityMeasurementEntity e = list.get(0);

		assertEquals("LENGTH", e.getMeasurementType());
		assertEquals("ADD", e.getOperationType());
		assertEquals(10.0, e.getValue1());
		assertEquals(5.0, e.getValue2());
		assertTrue(e.isResult());
	}

	@Test
	void testAdditionWithZero() {
		double result = service.add(new QuantityDTO("LENGTH", 0), new QuantityDTO("LENGTH", 25));
		assertEquals(25.0, result);
	}

	@Test
	void testSubtractSameNumbers() {
		double result = service.subtract(new QuantityDTO("LENGTH", 10), new QuantityDTO("LENGTH", 10));
		assertEquals(0.0, result);
	}

	@Test
	void testDivisionDecimal() {
		double result = service.divide(new QuantityDTO("LENGTH", 10), new QuantityDTO("LENGTH", 4));
		assertEquals(2.5, result);
	}

	@Test
	void testLargeValues() {
		double result = service.add(new QuantityDTO("LENGTH", 1_000_000), new QuantityDTO("LENGTH", 2_000_000));
		assertEquals(3_000_000, result);
	}

	@Test
	void testGetAllAfterDelete() {
		service.add(new QuantityDTO("LENGTH", 10), new QuantityDTO("LENGTH", 5));

		service.deleteAll();

		List<QuantityMeasurementEntity> list = service.getAll();

		assertTrue(list.isEmpty());
	}

	@Test
	void testAdditionCommutative() {
		double r1 = service.add(new QuantityDTO("LENGTH", 10), new QuantityDTO("LENGTH", 5));

		double r2 = service.add(new QuantityDTO("LENGTH", 5), new QuantityDTO("LENGTH", 10));

		assertEquals(r1, r2);
	}
}