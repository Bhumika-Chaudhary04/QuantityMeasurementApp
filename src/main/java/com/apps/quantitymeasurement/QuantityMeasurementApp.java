package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {
	public static boolean demonstrateLengthEquality(Length length1, Length length2) {
		return length1.equals(length2);
	}

	public static boolean demonstrateLengthComparison(Length length1, Length length2) {
		return length1.equals(length2);
	}

	public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
		return length.convertTo(toUnit);
	}

	public static Length demonstrateLengthAddition(Length length1, Length length2) {
		return length1.add(length2);
	}

	public static void main(String[] args) {

		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		Length result = demonstrateLengthAddition(l1, l2);
		System.out.println("Addition Result: " + result); // 2.0 FEET
	}
}