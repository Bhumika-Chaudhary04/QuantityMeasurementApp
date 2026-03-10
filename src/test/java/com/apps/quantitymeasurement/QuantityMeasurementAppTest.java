package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

	@Test
	void testEquality_Length_FeetAndInches() {
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		assertEquals(feet, inches);
	}

	@Test
	void testEquality_Weight_KgAndGram() {
		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

		assertEquals(kg, gram);
	}

	@Test
	void testEquality_Volume_LitreAndMillilitre() {
		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		assertEquals(litre, ml);
	}



	@Test
	void testConversion_FeetToInches() {
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = feet.convertTo(LengthUnit.INCHES);

		assertEquals(12.0, result.getValue());
		assertEquals(LengthUnit.INCHES, result.getUnit());
	}

	@Test
	void testConversion_KgToGram() {
		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> result = kg.convertTo(WeightUnit.GRAM);

		assertEquals(1000.0, result.getValue());
	}

	@Test
	void testConversion_LitreToMillilitre() {
		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);

		assertEquals(1000.0, result.getValue());
	}

	@Test
	void testAddition_Length() {
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = feet.add(inches, LengthUnit.FEET);

		assertEquals(2.0, result.getValue());
	}

	@Test
	void testAddition_Weight() {
		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

		Quantity<WeightUnit> result = kg.add(gram, WeightUnit.KILOGRAM);

		assertEquals(2.0, result.getValue());
	}

	@Test
	void testAddition_Volume() {
		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> result = litre.add(ml, VolumeUnit.LITRE);

		assertEquals(2.0, result.getValue());
	}


	@Test
	void testSubtraction_Length() {
		Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(6.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = length1.subtract(length2, LengthUnit.FEET);

		assertEquals(9.5, result.getValue());
	}

	@Test
	void testDivision_Length() {
		Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(2.0, LengthUnit.FEET);

		double result = length1.divide(length2);

		assertEquals(5.0, result);
	}


	@Test
	void testAdd_NullOperand_ShouldThrowException() {
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

		assertThrows(IllegalArgumentException.class, () -> {
			feet.add(null);
		});
	}

	@Test
	void testCrossCategory_Addition_ShouldThrowException() {

		Quantity length = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertThrows(IllegalArgumentException.class, () -> {
			length.add(weight);
		});
	}

	@Test
	void testDivision_ByZero_ShouldThrowException() {

		Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> zero = new Quantity<>(0.0, LengthUnit.FEET);

		assertThrows(ArithmeticException.class, () -> {
			length.divide(zero);
		});
	}


	@Test
	void testImmutability_AfterAdd() {

		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		feet.add(inches);

		assertEquals(1.0, feet.getValue());
	}


	@Test
	void testRounding_Addition() {

		Quantity<VolumeUnit> litre = new Quantity<>(1.234, VolumeUnit.LITRE);
		Quantity<VolumeUnit> ml = new Quantity<>(100.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> result = litre.add(ml, VolumeUnit.LITRE);

		assertTrue(result.getValue() > 1.0);
	}
}