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

		System.out.println("========== QUANTITY MEASUREMENT APP ==========");

		// ===== LENGTH =====
		System.out.println("\n===== LENGTH =====");

		Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		demonstrateEquality(feet, inches);
		demonstrateConversion(feet, LengthUnit.INCHES);
		demonstrateAddition(feet, inches, LengthUnit.FEET);
		demonstrateSubtraction(feet, inches, LengthUnit.FEET);
		demonstrateDivision(feet, new Quantity<>(2.0, LengthUnit.FEET));

		// ===== WEIGHT =====
		System.out.println("\n===== WEIGHT =====");

		Quantity<WeightUnit> kg = new Quantity<>(5.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(500.0, WeightUnit.GRAM);

		demonstrateEquality(kg, gram);
		demonstrateConversion(kg, WeightUnit.GRAM);
		demonstrateAddition(kg, gram, WeightUnit.KILOGRAM);
		demonstrateSubtraction(kg, gram, WeightUnit.KILOGRAM);
		demonstrateDivision(kg, new Quantity<>(2.0, WeightUnit.KILOGRAM));

		// ===== VOLUME =====
		System.out.println("\n===== VOLUME =====");

		Quantity<VolumeUnit> litre = new Quantity<>(5.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> ml = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);

		demonstrateEquality(litre, ml);
		demonstrateConversion(litre, VolumeUnit.MILLILITRE);
		demonstrateConversion(gallon, VolumeUnit.LITRE);
		demonstrateAddition(litre, ml, VolumeUnit.LITRE);
		demonstrateSubtraction(litre, ml, VolumeUnit.LITRE);
		demonstrateDivision(litre, new Quantity<>(2.0, VolumeUnit.LITRE));

		// Cross-unit volume operation
		demonstrateAddition(litre, gallon, VolumeUnit.MILLILITRE);

		// ===== TEMPERATURE =====
		System.out.println("\n===== TEMPERATURE =====");

		Quantity<TemperatureUnit> tempC = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
		Quantity<TemperatureUnit> tempF = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

		demonstrateEquality(tempC, tempF);
		demonstrateConversion(tempC, TemperatureUnit.FAHRENHEIT);

		try {
			demonstrateAddition(tempC, new Quantity<>(10.0, TemperatureUnit.CELSIUS), TemperatureUnit.CELSIUS);
		} catch (UnsupportedOperationException e) {
			System.out.println("Error: " + e.getMessage());
		}

		try {
			demonstrateSubtraction(tempC, new Quantity<>(10.0, TemperatureUnit.CELSIUS), TemperatureUnit.CELSIUS);
		} catch (UnsupportedOperationException e) {
			System.out.println("Error: " + e.getMessage());
		}

		try {
			demonstrateDivision(tempC, new Quantity<>(2.0, TemperatureUnit.CELSIUS));
		} catch (UnsupportedOperationException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}
}