package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

	public static String demonstrateLengthAddition(Length length1, Length length2) {
		Length result = length1.add(length2);

		return String.format("Adding: %s + %s = %s", length1, length2, result);
	}

	public static String demonstrateEquality(Length l1, Length l2) {
		return String.format("Comparing: %s and %s -> %s", l1, l2, l1.equals(l2));
	}

	public static String demonstrateConversion(Length length, Length.LengthUnit toUnit) {
		Length converted = length.convertTo(toUnit);

		return String.format("Converting: %s -> %s", length, converted);
	}

	public static void main(String[] args) {

		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length l3 = new Length(2.0, Length.LengthUnit.YARDS);
		Length l4 = new Length(24.0, Length.LengthUnit.INCHES);

		Length l5 = new Length(100.0, Length.LengthUnit.CENTIMETERS);
		Length l6 = new Length(1.0, Length.LengthUnit.FEET);

		Length l7 = new Length(3.0, Length.LengthUnit.FEET);
		Length l8 = new Length(36.0, Length.LengthUnit.INCHES);

		Length l9 = new Length(5.0, Length.LengthUnit.FEET);

		System.out.println(demonstrateLengthAddition(l1, l2));
		System.out.println(demonstrateLengthAddition(l3, l4));
		System.out.println(demonstrateLengthAddition(l5, l6));

		System.out.println(demonstrateEquality(l7, l8));

		System.out.println(demonstrateConversion(l9, Length.LengthUnit.CENTIMETERS));
	}
}