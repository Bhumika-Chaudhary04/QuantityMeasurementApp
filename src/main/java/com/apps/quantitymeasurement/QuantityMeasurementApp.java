package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

	// Demonstrate equality
	public static boolean demonstrateLengthEquality(Length length1, Length length2) {
		if (length1 == null || length2 == null)
			return false;

		boolean result = length1.equals(length2);

		System.out.println("\n--- Equality Check ---");
		System.out.println("Comparing: " + length1 + " and " + length2);
		System.out.println("Result: They are " + (result ? "EQUAL" : "NOT EQUAL"));

		return result;
	}

	// Demonstrate equality using raw values
	public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1, double value2,
			LengthUnit unit2) {

		Length length1 = new Length(value1, unit1);
		Length length2 = new Length(value2, unit2);

		return demonstrateLengthEquality(length1, length2);
	}

	// Demonstrate conversion (value + units)
	public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {

		Length length = new Length(value, fromUnit);
		Length converted = length.convertTo(toUnit);

		System.out.println("\n--- Conversion ---");
		System.out.println("Converting: " + length + " → " + toUnit);
		System.out.println("Result: " + converted);

		return converted;
	}

	// Demonstrate conversion (Length object)
	public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {

		Length converted = length.convertTo(toUnit);

		System.out.println("\n--- Conversion ---");
		System.out.println("Converting: " + length + " → " + toUnit);
		System.out.println("Result: " + converted);

		return converted;
	}

	// Demonstrate addition (default unit)
	public static Length demonstrateLengthAddition(Length length1, Length length2) {

		Length result = length1.add(length2);

		System.out.println("\n--- Addition ---");
		System.out.println("Adding: " + length1 + " + " + length2);
		System.out.println("Result: " + result);

		return result;
	}

	// Demonstrate addition with target unit
	public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit targetUnit) {

		Length result = length1.add(length2, targetUnit);

		System.out.println("\n--- Addition ---");
		System.out.println("Adding: " + length1 + " + " + length2 + " (in " + targetUnit + ")");
		System.out.println("Final Result: " + result);

		return result;
	}

	public static void main(String[] args) {

		System.out.println("===== QUANTITY MEASUREMENT APP =====");

		// Equality Test
		demonstrateLengthComparison(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES);
		demonstrateLengthComparison(36.0, LengthUnit.INCHES, 1.0, LengthUnit.YARDS);

		// Conversion Test
		demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);

		// Addition Test
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);

		demonstrateLengthAddition(l1, l2, LengthUnit.FEET);

		// Centimeter conversion
		demonstrateLengthConversion(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
	}
}