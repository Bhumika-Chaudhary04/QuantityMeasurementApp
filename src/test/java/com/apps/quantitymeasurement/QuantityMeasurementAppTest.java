package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;

public class QuantityMeasurementAppTest {
	//UC1
	@Test
	public void testFeetEquality_SameValue() {
		Feet feet1 = new Feet(1.0);
		Feet feet2 = new Feet(1.0);

		assertTrue(feet1.equals(feet2));
	}

	@Test
	public void testFeetEquality_DifferentValue() {
		Feet feet1 = new Feet(1.0);
		Feet feet2 = new Feet(2.0);

		assertFalse(feet1.equals(feet2));
	}

	@Test
	public void testFeetEquality_NullComparison() {
		Feet feet = new Feet(1.0);

		assertFalse(feet.equals(null));
	}

	@Test
	public void testFeetEquality_DifferentClass() {
		Feet feet = new Feet(1.0);
		String str = "1.0";

		assertFalse(feet.equals(str));
	}

	@Test
	public void testFeetEquality_SameReference() {
		Feet feet = new Feet(1.0);

		assertTrue(feet.equals(feet));
	}
}