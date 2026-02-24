package com.apps.quantitymeasurement;

public class Length {

	private final double value;
	private final LengthUnit unit;

	public enum LengthUnit {
		FEET(12.0), // 1 foot = 12 inches
		INCHES(1.0), // base unit
		YARDS(36.0), // 1 yard = 36 inches
		CENTIMETERS(0.393701); // 1 cm = 0.393701 inches

		private final double conversionFactor;

		LengthUnit(double conversionFactor) {
			this.conversionFactor = conversionFactor;
		}

		public double getConversionFactor() {
			return conversionFactor;
		}
	}

	public Length(double value, LengthUnit unit) {
		if (!Double.isFinite(value))
			throw new IllegalArgumentException("Value must be finite");
		if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
		this.value = value;
		this.unit = unit;
	}

	private double convertToBaseUnit() {
		double baseValue = value * unit.getConversionFactor();
		return roundToTwoDecimalPlaces(baseValue);
	}

	private double roundToTwoDecimalPlaces(double number) {
		return Math.round(number * 100.0) / 100.0;
	}

	private boolean compare(Length thatLength) {
		if (thatLength == null)
			return false;

		return this.convertToBaseUnit() == thatLength.convertToBaseUnit();
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;

		if (o == null)
			return false;

		if (getClass() != o.getClass())
			return false;

		Length thatLength = (Length) o;

		return compare(thatLength);
	}

	@Override
	public int hashCode() {
		return Double.hashCode(convertToBaseUnit());
	}

	public Length convertTo(LengthUnit targetUnit) {
		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null.");
		}

		double baseValue = convertToBaseUnit();
		double convertedValue = baseValue / targetUnit.getConversionFactor();

		return new Length(roundToTwoDecimalPlaces(convertedValue), targetUnit);
	}

	@Override
	public String toString() {
		return String.format("%.2f %s", value, unit);
	}
}