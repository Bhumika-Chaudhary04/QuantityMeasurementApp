package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

	@Test
	public void testFeetEquality() {
		Length length1 = new Length(1, LengthUnit.FEET);
		Length length2 = new Length(1, LengthUnit.FEET);
		assertEquals(length1, length2);
	}

	@Test
	public void testInchesEquality() {
		Length length1 = new Length(5, LengthUnit.INCHES);
		Length length2 = new Length(5, LengthUnit.INCHES);
		assertEquals(length1, length2);
	}

	@Test
	public void testFeetInchesComparison() {
		Length feet = new Length(1, LengthUnit.FEET);
		Length inches = new Length(12, LengthUnit.INCHES);
		assertEquals(feet, inches);
	}

	@Test
	public void testFeetInequality() {
		Length length1 = new Length(1, LengthUnit.FEET);
		Length length2 = new Length(2, LengthUnit.FEET);
		assertNotEquals(length1, length2);
	}

	@Test
	public void testInchesInequality() {
		Length length1 = new Length(5, LengthUnit.INCHES);
		Length length2 = new Length(6, LengthUnit.INCHES);
		assertNotEquals(length1, length2);
	}

	@Test
	public void testCrossUnitInequality() {
		Length feet = new Length(1, LengthUnit.FEET);
		Length inches = new Length(13, LengthUnit.INCHES);
		assertNotEquals(feet, inches);
	}

	@Test
	public void testMultipleFeetComparison() {
		Length length1 = new Length(2, LengthUnit.FEET);
		Length length2 = new Length(24, LengthUnit.INCHES);
		assertEquals(length1, length2);
	}

	@Test
	public void yardEquals36Inches() {
		Length yard = new Length(1, LengthUnit.YARDS);
		Length inches = new Length(36, LengthUnit.INCHES);
		assertEquals(yard, inches);
	}

	@Test
	public void centimeterEquals39Point3701Inches() {
		Length cm = new Length(100, LengthUnit.CENTIMETERS);
		Length inches = new Length(39.3701, LengthUnit.INCHES);
		assertEquals(cm, inches);
	}

	@Test
	public void threeFeetEqualsOneYard() {
		Length feet = new Length(3, LengthUnit.FEET);
		Length yard = new Length(1, LengthUnit.YARDS);
		assertEquals(feet, yard);
	}

	@Test
	public void thirtyPoint48CmEqualsOneFoot() {
		Length cm = new Length(30.48, LengthUnit.CENTIMETERS);
		Length foot = new Length(1, LengthUnit.FEET);
		assertEquals(cm, foot);
	}

	@Test
	public void yardNotEqualToInches() {
		Length yard = new Length(1, LengthUnit.YARDS);
		Length inches = new Length(35, LengthUnit.INCHES);
		assertNotEquals(yard, inches);
	}

	@Test
	public void referenceEqualitySameObject() {
		Length length = new Length(1, LengthUnit.FEET);
		assertEquals(length, length);
	}

	@Test
	public void equalsReturnsFalseForNull() {
		Length length = new Length(1, LengthUnit.FEET);
		assertNotEquals(length, null);
	}

	@Test
	public void differentValuesSameUnitNotEqual() {
		Length a = new Length(5, LengthUnit.INCHES);
		Length b = new Length(6, LengthUnit.INCHES);
		assertNotEquals(a, b);
	}

	@Test
	public void convertFeetToInches() {
		Length feet = new Length(1, LengthUnit.FEET);
		Length result = feet.add(new Length(0, LengthUnit.INCHES), LengthUnit.INCHES);
		assertEquals(12, result.getValue());
	}

	@Test
	public void convertYardsToInchesUsingOverloadedMethod() {
		Length yard = new Length(1, LengthUnit.YARDS);
		Length result = yard.add(new Length(0, LengthUnit.INCHES), LengthUnit.INCHES);
		assertEquals(36, result.getValue());
	}

	@Test
	public void addFeetAndInches() {
		Length feet = new Length(1, LengthUnit.FEET);
		Length inches = new Length(2, LengthUnit.INCHES);

		Length result = feet.add(inches, LengthUnit.INCHES);
		assertEquals(14, result.getValue());
	}

	@Test
	public void addFeetAndInchesWithTargetUnitInches() {
		Length feet = new Length(2, LengthUnit.FEET);
		Length inches = new Length(6, LengthUnit.INCHES);

		Length result = feet.add(inches, LengthUnit.INCHES);
		assertEquals(30, result.getValue());
	}
}