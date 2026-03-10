package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

	@Test
	public void testLengthEquality_FeetAndInches() {

		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		assertEquals(feet, inches);
	}

	@Test
	public void testLengthConversion_FeetToInches() {

		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = feet.convertTo(LengthUnit.INCHES);

		assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), result);
	}

	@Test
	public void testLengthAddition() {

		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = feet.add(inches, LengthUnit.FEET);

		assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
	}

	@Test
	public void testWeightEquality_KgAndGram() {

		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

		assertEquals(kg, gram);
	}

	@Test
	public void testWeightConversion_KgToGram() {

		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> result = kg.convertTo(WeightUnit.GRAM);

		assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM), result);
	}

	@Test
	public void testWeightAddition() {

		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

		Quantity<WeightUnit> result = kg.add(gram, WeightUnit.KILOGRAM);

		assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), result);
	}

	@Test
	public void testVolumeEquality_LitreAndMillilitre() {

		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		assertEquals(litre, ml);
	}

	@Test
	public void testVolumeConversion_LitreToMillilitre() {

		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);

		assertEquals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), result);
	}

	@Test
	public void testVolumeAddition() {

		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> result = litre.add(ml, VolumeUnit.LITRE);

		assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), result);
	}

	// ======================
	// UC12 SUBTRACTION TESTS
	// ======================

	@Test
	public void testSubtraction_FeetMinusInches() {

		Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<LengthUnit> inches = new Quantity<>(6.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = feet.subtract(inches);

		assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
	}

	@Test
	public void testSubtraction_ExplicitTargetUnit() {

		Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<LengthUnit> inches = new Quantity<>(6.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = feet.subtract(inches, LengthUnit.INCHES);

		assertEquals(new Quantity<>(114.0, LengthUnit.INCHES), result);
	}

	@Test
	public void testSubtraction_ResultingNegative() {

		Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);

		Quantity<LengthUnit> b = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<LengthUnit> result = a.subtract(b);

		assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), result);
	}

	@Test
	public void testSubtraction_ResultZero() {

		Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<LengthUnit> b = new Quantity<>(120.0, LengthUnit.INCHES);

		Quantity<LengthUnit> result = a.subtract(b);

		assertEquals(new Quantity<>(0.0, LengthUnit.FEET), result);
	}

	@Test
	public void testDivision_SameUnit() {

		Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

		double result = a.divide(b);

		assertEquals(5.0, result);
	}

	@Test
	public void testDivision_CrossUnit() {

		Quantity<LengthUnit> inches = new Quantity<>(24.0, LengthUnit.INCHES);

		Quantity<LengthUnit> feet = new Quantity<>(2.0, LengthUnit.FEET);

		double result = inches.divide(feet);

		assertEquals(1.0, result);
	}

	@Test
	public void testDivision_LessThanOne() {

		Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);

		Quantity<LengthUnit> b = new Quantity<>(10.0, LengthUnit.FEET);

		double result = a.divide(b);

		assertEquals(0.5, result);
	}

	@Test
	public void testDivision_ByZero() {

		Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);

		Quantity<LengthUnit> b = new Quantity<>(0.0, LengthUnit.FEET);

		assertThrows(ArithmeticException.class, () -> a.divide(b));
	}

	@Test
	public void testCrossCategoryComparison_ShouldReturnFalse() {

		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertNotEquals(length, weight);
	}

	@Test
	public void testEqualsWithNull() {

		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		assertNotEquals(length, null);
	}

	@Test
	public void testHashCodeConsistency() {

		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		assertEquals(feet.hashCode(), inches.hashCode());
	}

}