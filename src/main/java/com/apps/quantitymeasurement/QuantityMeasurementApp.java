package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

	public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {

		System.out.println("\n--- Equality Check ---");
		System.out.println("Comparing: " + q1 + " and " + q2);
		System.out.println("Result: " + (q1.equals(q2) ? "EQUAL" : "NOT EQUAL"));
	}

	public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> quantity, U targetUnit) {

		System.out.println("\n--- Conversion ---");
		System.out.println("Converting: " + quantity + " → " + targetUnit);
		System.out.println("Result: " + quantity.convertTo(targetUnit));
	}

	public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {

		System.out.println("\n--- Addition ---");
		System.out.println("Adding: " + q1 + " + " + q2 + " (in " + targetUnit + ")");
		System.out.println("Result: " + q1.add(q2, targetUnit));
	}

	public static void main(String[] args) {

		System.out.println("========== QUANTITY MEASUREMENT APP ==========");

		// ================= LENGTH =================
		System.out.println("\n===== LENGTH MEASUREMENT =====");

		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		demonstrateEquality(feet, inches);
		demonstrateConversion(feet, LengthUnit.INCHES);
		demonstrateAddition(feet, inches, LengthUnit.FEET);

		// ================= WEIGHT =================
		System.out.println("\n===== WEIGHT MEASUREMENT =====");

		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

		demonstrateEquality(kg, gram);
		demonstrateConversion(kg, WeightUnit.GRAM);
		demonstrateAddition(kg, gram, WeightUnit.KILOGRAM);

	}
}