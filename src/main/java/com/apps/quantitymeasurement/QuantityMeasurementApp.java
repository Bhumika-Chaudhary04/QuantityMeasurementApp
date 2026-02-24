package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

	// generic method to demonstrate Length equality check
	public static boolean demonstrateLengthEquality(Length length1, Length length2) {
		if (length1 == null || length2 == null) {
			throw new IllegalArgumentException("Length cannot be null.");
		}

		boolean result = length1.equals(length2);
		System.out.println("Comparing " + length1 + " and " + length2);
		System.out.println("Are Equal? " + result);
		System.out.println("-----------------------------------");
		return result;
	}

	// overloaded method to accept raw values and units
	public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1, double value2,
			Length.LengthUnit unit2) {

		Length length1 = new Length(value1, unit1);
		Length length2 = new Length(value2, unit2);

		return demonstrateLengthEquality(length1, length2);
	}

	public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit,
			Length.LengthUnit toUnit) {

		Length length = new Length(value, fromUnit);
		Length converted = length.convertTo(toUnit);

		System.out.println("Convert(" + value + ", " + fromUnit + ", " + toUnit + ") = " + converted);
		System.out.println("----------------------------------");

		return converted;
	}

	public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {

		Length converted = length.convertTo(toUnit);

		System.out.println("Convert(" + length + " → " + toUnit + ") = " + converted);
		System.out.println("----------------------------------");

		return converted;
	}

	public static void main(String[] args) {
		demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
		demonstrateLengthConversion(3.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET);
		demonstrateLengthConversion(36.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS);
		demonstrateLengthConversion(1.0, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES);
		demonstrateLengthConversion(0.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);

		demonstrateLengthComparison(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES);
	}
}