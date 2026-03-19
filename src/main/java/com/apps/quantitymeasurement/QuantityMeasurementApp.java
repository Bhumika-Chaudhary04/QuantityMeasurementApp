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

	public static <U extends IMeasurable> void demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
		System.out.println("\n--- Subtraction ---");
		System.out.println("Subtracting: " + q1 + " - " + q2 + " (in " + targetUnit + ")");
		System.out.println("Result: " + q1.subtract(q2, targetUnit));
	}

	public static <U extends IMeasurable> void demonstrateDivision(Quantity<U> q1, Quantity<U> q2) {
		System.out.println("\n--- Division ---");
		System.out.println("Dividing: " + q1 + " / " + q2);
		System.out.println("Result (Ratio): " + q1.divide(q2));
	}

	public static void main(String[] args) {

		System.out.println("========== QUANTITY MEASUREMENT APP ==========");

		System.out.println("\n===== LENGTH MEASUREMENT =====");

		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		demonstrateEquality(feet, inches);
		demonstrateConversion(feet, LengthUnit.INCHES);
		demonstrateAddition(feet, inches, LengthUnit.FEET);

		Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(6.0, LengthUnit.INCHES);

		demonstrateSubtraction(length1, length2, LengthUnit.FEET);
		demonstrateDivision(length1, new Quantity<>(2.0, LengthUnit.FEET));

		System.out.println("\n===== WEIGHT MEASUREMENT =====");

		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

		demonstrateEquality(kg, gram);
		demonstrateConversion(kg, WeightUnit.GRAM);
		demonstrateAddition(kg, gram, WeightUnit.KILOGRAM);

		Quantity<WeightUnit> w1 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> w2 = new Quantity<>(500.0, WeightUnit.GRAM);

		demonstrateSubtraction(w1, w2, WeightUnit.KILOGRAM);
		demonstrateDivision(w1, new Quantity<>(2.0, WeightUnit.KILOGRAM));

		System.out.println("\n===== VOLUME MEASUREMENT =====");

		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> millilitre = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);

		demonstrateEquality(litre, millilitre);
		demonstrateConversion(litre, VolumeUnit.MILLILITRE);
		demonstrateConversion(gallon, VolumeUnit.LITRE);

		demonstrateAddition(litre, millilitre, VolumeUnit.LITRE);
		demonstrateAddition(litre, gallon, VolumeUnit.MILLILITRE);

		Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

		demonstrateSubtraction(v1, v2, VolumeUnit.LITRE);
		demonstrateDivision(v1, new Quantity<>(2.0, VolumeUnit.LITRE));

	}
}