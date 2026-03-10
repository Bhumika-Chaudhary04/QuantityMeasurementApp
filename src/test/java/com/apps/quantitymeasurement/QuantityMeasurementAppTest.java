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

		Quantity<VolumeUnit> millilitre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		assertEquals(litre, millilitre);
	}

	@Test
	public void testVolumeEquality_GallonAndLitre() {

		Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);

		Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);

		assertEquals(gallon, litre);
	}

	@Test
	public void testVolumeConversion_LitreToMillilitre() {

		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);

		assertEquals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), result);
	}

	@Test
	public void testVolumeConversion_GallonToLitre() {

		Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);

		Quantity<VolumeUnit> result = gallon.convertTo(VolumeUnit.LITRE);

		assertEquals(new Quantity<>(3.79, VolumeUnit.LITRE), result);
	}

	@Test
	public void testVolumeAddition_LitreAndMillilitre() {

		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> millilitre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> result = litre.add(millilitre, VolumeUnit.LITRE);

		assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), result);
	}

	@Test
	public void testVolumeAddition_LitreAndGallon() {

		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);

		Quantity<VolumeUnit> result = litre.add(gallon, VolumeUnit.LITRE);

		assertEquals(new Quantity<>(4.79, VolumeUnit.LITRE), result);
	}

	@Test
	public void testCrossCategoryComparison_ShouldReturnFalse() {

		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertNotEquals(length, weight);
	}

	@Test
	public void testVolumeVsLength_ShouldReturnFalse() {

		Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);

		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		assertNotEquals(volume, length);
	}

	@Test
	public void testEqualsWithNull() {

		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		assertNotEquals(length, null);
	}

	@Test
	public void testSameReference() {

		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

		assertEquals(length, length);
	}

	@Test
	public void testConstructor_NullUnit_ShouldThrowException() {

		assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
	}

	@Test
	public void testConstructor_InvalidValue_ShouldThrowException() {

		assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
	}

	@Test
	public void testHashCodeConsistency() {

		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);

		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		assertEquals(feet.hashCode(), inches.hashCode());
	}
}