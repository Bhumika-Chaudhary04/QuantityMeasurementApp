package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import com.apps.quantitymeasurement.units.LengthUnit;
import com.apps.quantitymeasurement.units.TemperatureUnit;
import com.apps.quantitymeasurement.units.VolumeUnit;
import com.apps.quantitymeasurement.units.WeightUnit;

public class QuantityMeasurementAppTest {

	private QuantityMeasurementController controller;

	@BeforeEach
	void setUp() {
		QuantityMeasurementCacheRepository repository = new QuantityMeasurementCacheRepository();
		IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
		controller = new QuantityMeasurementController(service, repository);
	}

	@Test
	void givenFeetAndInches_whenEqual_thenReturnTrue() {
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		assertTrue(controller.checkEquality(feet, inches));
	}

	@Test
	void givenFeet_whenConvertedToInches_thenCorrectValue() {
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = controller.convert(feet, LengthUnit.INCHES);

		assertEquals(12.0, result.getValue());
	}

	@Test
	void givenLength_whenAdded_thenCorrectSum() {
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = controller.add(feet, inches, LengthUnit.FEET);

		assertEquals(2.0, result.getValue());
	}

	@Test
	void givenKgAndGram_whenEqual_thenReturnTrue() {
		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

		assertTrue(controller.checkEquality(kg, gram));
	}

	@Test
	void givenKg_whenConvertedToGram_thenCorrectValue() {
		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> result = controller.convert(kg, WeightUnit.GRAM);

		assertEquals(1000.0, result.getValue());
	}

	// ================= VOLUME =================

	@Test
	void givenLitreAndMillilitre_whenEqual_thenReturnTrue() {
		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		assertTrue(controller.checkEquality(litre, ml));
	}

	@Test
	void givenVolume_whenConverted_thenCorrectValue() {
		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = controller.convert(litre, VolumeUnit.MILLILITRE);

		assertEquals(1000.0, result.getValue());
	}

	@Test
	void givenCelsiusAndFahrenheit_whenEqual_thenReturnTrue() {
		Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
		Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

		assertTrue(controller.checkEquality(c, f));
	}

	@Test
	void givenTemperature_whenConverted_thenCorrectValue() {
		Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);

		Quantity<TemperatureUnit> result = controller.convert(c, TemperatureUnit.FAHRENHEIT);

		assertEquals(32.0, result.getValue());
	}
}