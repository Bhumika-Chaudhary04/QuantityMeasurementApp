package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {


	public static boolean demonstrateLengthEquality(Length length1, Length length2) {
		if (length1 == null || length2 == null) {
			return false;
		}

		boolean result = length1.equals(length2);

		System.out.println("\n--- Length Equality Check ---");
		System.out.println("Comparing: " + length1 + " and " + length2);
		System.out.println("Result: " + (result ? "EQUAL" : "NOT EQUAL"));

		return result;
	}

	public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1, double value2,
			LengthUnit unit2) {

		Length length1 = new Length(value1, unit1);
		Length length2 = new Length(value2, unit2);

		return demonstrateLengthEquality(length1, length2);
	}

	public static void demonstrateLength() {

		System.out.println("\n===== LENGTH MEASUREMENT =====");

		demonstrateLengthComparison(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES);

		System.out.println("\n--- Conversion ---");
		Length l = new Length(1.0, LengthUnit.FEET);
		System.out.println("Converting: " + l + " → " + LengthUnit.INCHES);
		System.out.println("Result: " + l.convertTo(LengthUnit.INCHES));

		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);

		System.out.println("\n--- Addition ---");
		System.out.println("Adding: " + l1 + " + " + l2);
		System.out.println("Result: " + l1.add(l2));

		System.out.println("\n--- Addition (Target Unit: INCHES) ---");
		System.out.println("Result: " + l1.add(l2, LengthUnit.INCHES));

		demonstrateLengthComparison(36.0, LengthUnit.INCHES, 1.0, LengthUnit.YARDS);

		System.out.println("\n--- Conversion ---");
		Length cm = new Length(2.54, LengthUnit.CENTIMETERS);
		System.out.println("Converting: " + cm + " → " + LengthUnit.INCHES);
		System.out.println("Result: " + cm.convertTo(LengthUnit.INCHES));
	}


	public static void demonstrateWeight() {

		System.out.println("\n===== WEIGHT MEASUREMENT =====");

		QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

		// Equality
		System.out.println("\n--- Equality Check ---");
		System.out.println("Comparing: " + w1 + " and " + w2);
		System.out.println("Result: " + (w1.equals(w2) ? "EQUAL" : "NOT EQUAL"));

		// Conversion
		System.out.println("\n--- Conversion ---");
		System.out.println("Converting: " + w1 + " → " + WeightUnit.POUND);
		QuantityWeight converted = w1.convertTo(WeightUnit.POUND);
		System.out.println("Result: " + converted);

		// Addition
		System.out.println("\n--- Addition ---");
		System.out.println("Adding: " + w1 + " + " + w2);
		System.out.println("Result: " + w1.add(w2));

		// Addition with target unit
		System.out.println("\n--- Addition (Target Unit: GRAM) ---");
		System.out.println("Adding: " + w1 + " + " + w2);
		System.out.println("Result: " + w1.add(w2, WeightUnit.GRAM));

	}


	public static void main(String[] args) {

		System.out.println("---------- QUANTITY MEASUREMENT APP ---------");

		demonstrateLength();
		demonstrateWeight();

	}
}