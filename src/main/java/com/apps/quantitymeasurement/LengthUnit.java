package com.apps.quantitymeasurement;

public enum LengthUnit {

	FEET(12.0), // 1 foot = 12 inches
	INCHES(1.0), // Base unit
	YARDS(36.0), // 1 yard = 36 inches
	CENTIMETERS(0.393701); // 1 cm = 0.393701 inches

	private final double conversionFactor; // To INCHES

	LengthUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public double getConversionFactor() {
		return conversionFactor;
	}

	// Convert THIS unit → INCHES (base unit)
	public double convertToBaseUnit(double value) {
		return round(value * conversionFactor);
	}

	// Convert INCHES → THIS unit
	public double convertFromBaseUnit(double baseValue) {
		return round(baseValue / conversionFactor);
	}

	private static double round(double value) {
		return Math.round(value * 100.0) / 100.0;
	}
}