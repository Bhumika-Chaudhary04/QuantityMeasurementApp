package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

		System.out.println("---- Addition ----");

		System.out.println("Sum in FEET   : " + l1 + " + " + l2 + " = " + l1.add(l2, Length.LengthUnit.FEET));

		System.out.println("Sum in INCHES : " + l1 + " + " + l2 + " = " + l1.add(l2, Length.LengthUnit.INCHES));

		System.out.println("Sum in YARDS  : " + l1 + " + " + l2 + " = " + l1.add(l2, Length.LengthUnit.YARDS));

		System.out.println();

		System.out.println("---- Conversion ----");

		Length l3 = new Length(5.0, Length.LengthUnit.FEET);
		Length l4 = new Length(100.0, Length.LengthUnit.CENTIMETERS);

		System.out.println("Convert: " + l3 + " -> " + l3.convertTo(Length.LengthUnit.INCHES));
		System.out.println("Convert: " + l3 + " -> " + l3.convertTo(Length.LengthUnit.CENTIMETERS));
		System.out.println("Convert: " + l4 + " -> " + l4.convertTo(Length.LengthUnit.FEET));

		System.out.println();

		System.out.println("---- Comparison ----");

		System.out.println(l1 + " and " + l2 + " -> " + l1.equals(l2));
	}
}