package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

	public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
		System.out.println(q1 + " equals " + q2 + " : " + q1.equals(q2));
	}

	public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> q, U targetUnit) {
		System.out.println(q + " = " + q.convertTo(targetUnit));
	}

	public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
		System.out.println("Sum = " + q1.add(q2, targetUnit));
	}

	public static <U extends IMeasurable> void demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
		System.out.println("Difference = " + q1.subtract(q2, targetUnit));
	}

	public static <U extends IMeasurable> void demonstrateDivision(Quantity<U> q1, Quantity<U> q2) {
		System.out.println("Ratio = " + q1.divide(q2));
	}

	public static void main(String[] args) {

		// Length
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
		demonstrateEquality(feet, inches);
		demonstrateConversion(feet, LengthUnit.INCHES);
		demonstrateAddition(feet, inches, LengthUnit.FEET);

		// Weight
		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);
		demonstrateEquality(kg, gram);
		demonstrateConversion(kg, WeightUnit.GRAM);
		demonstrateAddition(kg, gram, WeightUnit.KILOGRAM);

		// Volume
		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
		demonstrateEquality(litre, ml);
		demonstrateConversion(litre, VolumeUnit.MILLILITRE);
		demonstrateConversion(gallon, VolumeUnit.LITRE);
		demonstrateAddition(litre, ml, VolumeUnit.LITRE);
		demonstrateAddition(litre, gallon, VolumeUnit.MILLILITRE);

		// Temperature
		Quantity<TemperatureUnit> tempC = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
		Quantity<TemperatureUnit> tempF = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
		demonstrateEquality(tempC, tempF);
		demonstrateConversion(tempC, TemperatureUnit.FAHRENHEIT);

		// Attempt unsupported arithmetic
		try {
			tempC.add(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
		} catch (UnsupportedOperationException e) {
			System.out.println(e.getMessage());
		}
	}
}