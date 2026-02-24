package com.apps.quantitymeasurement;

public class Length {

	private final double value;
	private final LengthUnit unit;

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

	// Convert to another unit
	public Length convertTo(LengthUnit targetUnit) {
		if (targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");

		double baseValue = unit.convertToBaseUnit(value);
		double converted = targetUnit.convertFromBaseUnit(baseValue);

		return new Length(converted, targetUnit);
	}

	// Add (default result in current unit)
	public Length add(Length other) {
		return add(other, this.unit);
	}

	// Add with target unit
	public Length add(Length other, LengthUnit targetUnit) {
		if (other == null || targetUnit == null)
			throw new IllegalArgumentException("Invalid input");

		double baseSum = this.unit.convertToBaseUnit(this.value) + other.unit.convertToBaseUnit(other.value);

		double finalValue = targetUnit.convertFromBaseUnit(baseSum);

		return new Length(finalValue, targetUnit);
	}

	// Equality based on base unit (INCHES)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Length))
			return false;

		Length other = (Length) obj;

		double thisBase = this.unit.convertToBaseUnit(this.value);
		double otherBase = other.unit.convertToBaseUnit(other.value);

		return Double.compare(thisBase, otherBase) == 0;
	}

	@Override
	public String toString() {
		return "Quantity(" + value + ", " + unit + ")";
	}
}