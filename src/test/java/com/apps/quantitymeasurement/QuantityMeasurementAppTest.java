package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

	// Basic Equality Tests
	@Test
	public void testFeetEquality() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(1.0, Length.LengthUnit.FEET);

		assertTrue(l1.equals(l2));
	}

	@Test
	public void testInchesEquality() {
		Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		assertTrue(l1.equals(l2));
	}

	// Cross Unit Equality
	@Test
	public void testFeetInchesComparison() {
		Length feet = new Length(1.0, Length.LengthUnit.FEET);
		Length inches = new Length(12.0, Length.LengthUnit.INCHES);

		assertTrue(feet.equals(inches));
	}

	@Test
	public void yardEquals36Inches() {
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
		Length inches = new Length(36.0, Length.LengthUnit.INCHES);

		assertTrue(yard.equals(inches));
	}

	@Test
	public void centimeterEqualsPoint39Inches() {
		Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
		Length inches = new Length(0.39, Length.LengthUnit.INCHES);

		assertTrue(cm.equals(inches));
	}

	// Inequality Tests
	@Test
	public void testFeetInequality() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(2.0, Length.LengthUnit.FEET);

		assertFalse(l1.equals(l2));
	}

	@Test
	public void testInchesInequality() {
		Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
		Length l2 = new Length(13.0, Length.LengthUnit.INCHES);

		assertFalse(l1.equals(l2));
	}

	@Test
	public void testCrossUnitInequality() {
		Length feet = new Length(1.0, Length.LengthUnit.FEET);
		Length inches = new Length(13.0, Length.LengthUnit.INCHES);

		assertFalse(feet.equals(inches));
	}

	// Equality Contract Tests
	@Test
	public void referenceEqualitySameObject() {
		Length length = new Length(1.0, Length.LengthUnit.FEET);

		assertTrue(length.equals(length));
	}

	@Test
	public void equalsReturnsFalseForNull() {
		Length length = new Length(1.0, Length.LengthUnit.FEET);

		assertFalse(length.equals(null));
	}

	@Test
	public void reflexiveSymmetricAndTransitiveProperty() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET); // 12 inches
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES); // 12 inches
		Length l3 = new Length(12.0, Length.LengthUnit.INCHES); // 12 inches

		// Reflexive
		assertTrue(l1.equals(l1));

		// Symmetric
		assertTrue(l1.equals(l2));
		assertTrue(l2.equals(l1));

		// Transitive
		assertTrue(l1.equals(l2));
		assertTrue(l2.equals(l3));
		assertTrue(l1.equals(l3));
	}

	// Conversion Tests (App Layer)
	@Test
	public void convertFeetToInches() {
		Length result = QuantityMeasurementApp.demonstrateLengthConversion(3.0, Length.LengthUnit.FEET,
				Length.LengthUnit.INCHES);

		Length expected = new Length(36.0, Length.LengthUnit.INCHES);

		assertTrue(result.equals(expected));
	}

	@Test
	public void convertYardsToInchesUsingOverloadedMethod() {
		Length yards = new Length(2.0, Length.LengthUnit.YARDS);

		Length result = QuantityMeasurementApp.demonstrateLengthConversion(yards, Length.LengthUnit.INCHES);

		Length expected = new Length(72.0, Length.LengthUnit.INCHES);

		assertTrue(result.equals(expected));
	}
}