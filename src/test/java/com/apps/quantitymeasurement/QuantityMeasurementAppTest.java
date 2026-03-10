package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 0.01;

	@Test
	void testLengthEquality_FeetInches() {
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
		assertEquals(feet, inches);
	}

	@Test
	void testLengthAddition() {
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> sum = feet.add(inches, LengthUnit.FEET);
		assertEquals(2.0, sum.getValue(), EPSILON);
	}

	@Test
	void testLengthConversion() {
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = feet.convertTo(LengthUnit.INCHES);
		assertEquals(12.0, inches.getValue(), EPSILON);
	}

	@Test
	void testWeightEquality_KgGram() {
		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);
		assertEquals(kg, gram);
	}

	@Test
	void testWeightAddition() {
		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(500.0, WeightUnit.GRAM);
		Quantity<WeightUnit> sum = kg.add(gram, WeightUnit.KILOGRAM);
		assertEquals(1.5, sum.getValue(), EPSILON);
	}

	@Test
	void testVolumeEquality_LitreMillilitre() {
		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
		assertEquals(litre, ml);
	}

	@Test
	void testVolumeAddition() {
		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> ml = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> sum = litre.add(ml, VolumeUnit.LITRE);
		assertEquals(1.5, sum.getValue(), EPSILON);
	}

	@Test
	void testTemperatureEquality_CelsiusFahrenheit() {
		Quantity<TemperatureUnit> celsius = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
		Quantity<TemperatureUnit> fahrenheit = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
		assertEquals(celsius, fahrenheit);
	}

	@Test
	void testTemperatureConversion_CelsiusFahrenheit() {
		Quantity<TemperatureUnit> celsius = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
		Quantity<TemperatureUnit> fahrenheit = celsius.convertTo(TemperatureUnit.FAHRENHEIT);
		assertEquals(212.0, fahrenheit.getValue(), EPSILON);
	}

	@Test
	void testTemperatureConversion_CelsiusKelvin() {
		Quantity<TemperatureUnit> celsius = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
		Quantity<TemperatureUnit> kelvin = celsius.convertTo(TemperatureUnit.KELVIN);
		assertEquals(273.15, kelvin.getValue(), EPSILON);
	}

	@Test
	void testTemperatureUnsupportedAddition() {
		Quantity<TemperatureUnit> celsius1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
		Quantity<TemperatureUnit> celsius2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
		Exception ex = assertThrows(UnsupportedOperationException.class, () -> celsius1.add(celsius2));
		assertTrue(ex.getMessage().contains("addition"));
	}

	@Test
	void testTemperatureUnsupportedSubtraction() {
		Quantity<TemperatureUnit> celsius1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
		Quantity<TemperatureUnit> celsius2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
		Exception ex = assertThrows(UnsupportedOperationException.class, () -> celsius1.subtract(celsius2));
		assertTrue(ex.getMessage().contains("subtraction"));
	}

	@Test
	void testTemperatureUnsupportedDivision() {
		Quantity<TemperatureUnit> celsius1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
		Quantity<TemperatureUnit> celsius2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
		Exception ex = assertThrows(UnsupportedOperationException.class, () -> celsius1.divide(celsius2));
		assertTrue(ex.getMessage().contains("division"));
	}

	@Test
	void testCrossCategoryEquality() {
		Quantity<TemperatureUnit> temp = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
		Quantity<LengthUnit> length = new Quantity<>(50.0, LengthUnit.FEET);
		assertNotEquals(temp, length);
	}
}