package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

	@Test
	public void testAddition_TargetUnit_Feet() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length result = l1.add(l2, Length.LengthUnit.FEET);

		assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
	}

	@Test
	public void testAddition_TargetUnit_Inches() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length result = l1.add(l2, Length.LengthUnit.INCHES);

		assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
	}

	@Test
	public void testAddition_TargetUnit_Yards() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length result = l1.add(l2, Length.LengthUnit.YARDS);

		assertEquals(new Length(0.67, Length.LengthUnit.YARDS), result);
	}

	@Test
	public void testAddition_TargetUnit_Centimeters() {
		Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
		Length l2 = new Length(1.0, Length.LengthUnit.INCHES);

		Length result = l1.add(l2, Length.LengthUnit.CENTIMETERS);

		assertEquals(new Length(5.08, Length.LengthUnit.CENTIMETERS), result);
	}

	@Test
	public void testAddition_TargetUnit_CommutativeProperty() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length result1 = l1.add(l2, Length.LengthUnit.FEET);
		Length result2 = l2.add(l1, Length.LengthUnit.FEET);

		assertEquals(result1, result2);
	}

	@Test
	public void testAddition_TargetUnit_WithZero() {
		Length l1 = new Length(5.0, Length.LengthUnit.FEET);
		Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

		Length result = l1.add(l2, Length.LengthUnit.YARDS);

		assertEquals(new Length(1.67, Length.LengthUnit.YARDS), result);
	}

	@Test
	public void testAddition_TargetUnit_NegativeValues() {
		Length l1 = new Length(5.0, Length.LengthUnit.FEET);
		Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

		Length result = l1.add(l2, Length.LengthUnit.INCHES);

		assertEquals(new Length(36.0, Length.LengthUnit.INCHES), result);
	}

	@Test
	public void testAddition_TargetUnit_NullTargetUnit() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		assertThrows(IllegalArgumentException.class, () -> {
			l1.add(l2, null);
		});
	}
}