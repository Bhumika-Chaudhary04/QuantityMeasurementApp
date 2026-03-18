package com.apps.quantitymeasurement.util;

import com.apps.quantitymeasurement.unit.LengthUnit;

public class QuantityConverter {

	public static double convert(double value, LengthUnit from, LengthUnit to) {

		double base = from.toBase(value);

		return base / to.toBase(1);
	}
}