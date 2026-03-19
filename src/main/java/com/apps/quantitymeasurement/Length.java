package com.apps.quantitymeasurement;

import java.util.Objects;

public class Length {

	private final double value;
	private final LengthUnit unit;

	public enum LengthUnit {
		FEET(12.0), INCHES(1.0), YARDS(36.0), CENTIMETERS(0.393701);

		private final double toInches;

		LengthUnit(double toInches) {
			this.toInches = toInches;
		}

		public double toBase(double value) {
			return value * toInches;
		}

		public double fromBase(double baseValue) {
			return baseValue / toInches;
		}
	}

	public Length(double value, LengthUnit unit) {
		if (!Double.isFinite(value))
			throw new IllegalArgumentException("Value must be finite");
		if (unit == null)
			throw new IllegalArgumentException("Unit cannot be null");

		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return this.value;
	}

	public LengthUnit getUnit() {
		return this.unit;
	}

	private double convertToBaseUnit() {
		return unit.toBase(value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Length))
			return false;

		Length that = (Length) o;
		return Double.compare(this.convertToBaseUnit(), that.convertToBaseUnit()) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(convertToBaseUnit());
	}

	public Length convertTo(LengthUnit targetUnit) {
		double baseValue = convertToBaseUnit();
		double convertedValue = targetUnit.fromBase(baseValue);
		return new Length(round(convertedValue), targetUnit);
	}

	public Length add(Length other) {
		return add(other, this.unit);
	}

	public Length add(Length other, LengthUnit targetUnit) {
		if (other == null || targetUnit == null)
			throw new IllegalArgumentException("Length or target unit cannot be null");

		double sumInBase = this.convertToBaseUnit() + other.convertToBaseUnit();
		double result = targetUnit.fromBase(sumInBase);

		return new Length(round(result), targetUnit);
	}

	private double round(double value) {
		return Math.round(value * 100.0) / 100.0;
	}

	@Override
	public String toString() {
		return "Quantity(" + value + ", " + unit + ")";
	}
}