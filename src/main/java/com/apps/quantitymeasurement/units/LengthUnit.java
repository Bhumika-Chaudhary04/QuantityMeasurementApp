package com.apps.quantitymeasurement.units;

public enum LengthUnit {

	METER(1), FEET(0.3048), INCH(0.0254);

	private double conversionFactor;

	LengthUnit(double factor) {
		this.conversionFactor = factor;
	}

	public double toBase(double value) {
		return value * conversionFactor;
	}
}
