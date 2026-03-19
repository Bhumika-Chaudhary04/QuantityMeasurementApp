package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 1e-6;

	// ================= LENGTH TESTS =================

	@Test
	public void testLengthEqualityFeetAndInches() {
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);

		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
	}

	@Test
	public void testLengthEqualityInchesAndYards() {
		assertTrue(QuantityMeasurementApp.demonstrateLengthComparison(36.0, LengthUnit.INCHES, 1.0, LengthUnit.YARDS));
	}

	@Test
	public void testLengthNotEqual() {
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(10.0, LengthUnit.INCHES);

		assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, l2));
	}

	@Test
	public void testLengthConversion() {
		Length l = new Length(1.0, LengthUnit.FEET);
		Length result = l.convertTo(LengthUnit.INCHES);

		assertEquals(12.0, result.getValue(), EPSILON);
	}

	@Test
	public void testLengthAddition() {
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);

		Length result = l1.add(l2);

		assertEquals(2.0, result.getValue(), EPSILON);
	}

	@Test
	public void testLengthAdditionWithTargetUnit() {
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);

		Length result = l1.add(l2, LengthUnit.INCHES);

		assertEquals(24.0, result.getValue(), EPSILON);
	}

	@Test
	public void testLengthNullHandling() {
		Length l1 = new Length(1.0, LengthUnit.FEET);

		assertFalse(QuantityMeasurementApp.demonstrateLengthEquality(l1, null));
	}

	// ================= WEIGHT TESTS =================

	@Test
	public void testWeightEqualityKgAndGram() {
		QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

		assertEquals(w1, w2);
	}

	@Test
	public void testWeightConversionKgToPound() {
		QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

		QuantityWeight pound = kg.convertTo(WeightUnit.POUND);

		assertEquals(2.20462, pound.getValue(), 0.001);
	}

	@Test
	public void testWeightAddition() {
		QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

		QuantityWeight result = w1.add(w2);

		assertEquals(2.0, result.getValue(), EPSILON);
	}

	@Test
	public void testWeightAdditionWithTargetUnit() {
		QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

		QuantityWeight result = w1.add(w2, WeightUnit.GRAM);

		assertEquals(2000.0, result.getValue(), EPSILON);
	}

	@Test
	public void testWeightNegativeValues() {
		QuantityWeight w1 = new QuantityWeight(-1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(-1000.0, WeightUnit.GRAM);

		assertEquals(w1, w2);
	}

	@Test
	public void testWeightZero() {
		QuantityWeight w = new QuantityWeight(0.0, WeightUnit.KILOGRAM);

		QuantityWeight result = w.convertTo(WeightUnit.GRAM);

		assertEquals(0.0, result.getValue(), EPSILON);
	}

	@Test
	public void testWeightNullAdditionThrowsException() {
		QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

		assertThrows(IllegalArgumentException.class, () -> w.add(null));
	}
}