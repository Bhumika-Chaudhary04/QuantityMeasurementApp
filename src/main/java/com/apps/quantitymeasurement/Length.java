package com.apps.quantitymeasurement;

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

		public double toBaseUnit(double value) {
			return value * toInches;
		}

		public double fromBaseUnit(double baseValue) {
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
		return value;
	}

	public LengthUnit getUnit() {
		return unit;
	}

	public Length add(Length other) {
		return add(other, this.unit);
	}

	public Length add(Length other, LengthUnit targetUnit) {
		if (other == null || targetUnit == null) {
			throw new IllegalArgumentException("Length or target unit cannot be null");
		}
		double resultInTarget = addInTargetUnit(this, other, targetUnit);

		return new Length(resultInTarget, targetUnit);
	}

	private static double addInTargetUnit(Length l1, Length l2, LengthUnit targetUnit) {

		double baseSum = l1.unit.toBaseUnit(l1.value) + l2.unit.toBaseUnit(l2.value);

		double result = targetUnit.fromBaseUnit(baseSum);

		return round(result);
	}

	private static double round(double value) {
		return Math.round(value * 100.0) / 100.0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Length))
			return false;
		Length other = (Length) obj;
		return Double.compare(value, other.value) == 0 && unit == other.unit;
	}

	@Override
	public String toString() {
		return "Quantity(" + value + ", " + unit + ")";
	}
}