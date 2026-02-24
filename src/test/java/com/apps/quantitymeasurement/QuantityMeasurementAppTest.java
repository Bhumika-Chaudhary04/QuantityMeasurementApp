package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
	@Test
	public void givenTwoLengthsInFeet_WhenAdded_ShouldReturnSumInFeet() {
		Length length1 = new Length(3.0, Length.LengthUnit.FEET);
		Length length2 = new Length(1.0, Length.LengthUnit.FEET);

		Length result = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2);

		assertEquals(new Length(4.0, Length.LengthUnit.FEET), result);
	}

	@Test
	public void givenFeetAndInches_WhenAdded_ShouldReturnResultInFeet() {
		Length length1 = new Length(1.0, Length.LengthUnit.FEET);
		Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length result = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2);

		assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
	}

	@Test
	public void givenInchesAndFeet_WhenAdded_ShouldReturnResultInInches() {
		Length length1 = new Length(12.0, Length.LengthUnit.INCHES);
		Length length2 = new Length(1.0, Length.LengthUnit.FEET);

		Length result = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2);

		assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
	}

	@Test
	public void givenYardsAndFeet_WhenAdded_ShouldReturnResultInYards() {
		Length length1 = new Length(1.0, Length.LengthUnit.YARDS);
		Length length2 = new Length(3.0, Length.LengthUnit.FEET);

		Length result = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2);

		assertEquals(new Length(2.0, Length.LengthUnit.YARDS), result);
	}

	@Test
	public void givenLengthAndZero_WhenAdded_ShouldReturnSameLength() {
		Length length1 = new Length(5.0, Length.LengthUnit.FEET);
		Length length2 = new Length(0.0, Length.LengthUnit.INCHES);

		Length result = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2);

		assertEquals(new Length(5.0, Length.LengthUnit.FEET), result);
	}
}