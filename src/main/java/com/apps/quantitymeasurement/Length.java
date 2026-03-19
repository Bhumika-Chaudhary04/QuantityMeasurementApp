package com.apps.quantitymeasurement;

import java.util.Objects;

public class Length {

	private final double value;
	private final LengthUnit unit;

	public enum LengthUnit {
		FEET(12.0), // 1 foot = 12 inches
		INCHES(1.0), // base unit
		YARDS(36.0), // 1 yard = 36 inches
		CENTIMETERS(0.393701); // 1 cm = 0.393701 inches

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
		if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return this.value;
	}

	private double convertToBaseUnit() {
		return unit.toBase(value);
	}

	private boolean compare(Length thatLength) {
		return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Length))
			return false;
		Length that = (Length) o;
		return compare(that);
	}

	@Override
	public int hashCode() {
		return Objects.hash(convertToBaseUnit());
	}

	public Length convertTo(LengthUnit targetUnit) {
		double baseValue = convertToBaseUnit();
		double convertedValue = convertFromBaseToTargetUnit(baseValue, targetUnit);
		return new Length(convertedValue, targetUnit);
	}

	public Length add(Length thatLength) {
		if (thatLength == null) {
			throw new IllegalArgumentException("Length to add cannot be null");
		}

		double sumInInches = this.convertToBaseUnit() + thatLength.convertToBaseUnit();

		double resultValue = convertFromBaseToTargetUnit(sumInInches, this.unit);

		return new Length(resultValue, this.unit);
	}

	private double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit) {
		double converted = targetUnit.fromBase(lengthInInches);
		return Math.round(converted * 100.0) / 100.0;
	}

	@Override
	public String toString() {
		return value + " " + unit;
	}
}