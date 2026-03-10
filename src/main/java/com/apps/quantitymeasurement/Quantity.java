package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

	private final double value;
	private final U unit;

	public Quantity(double value, U unit) {

		if (unit == null)
			throw new IllegalArgumentException("Unit cannot be null");

		if (Double.isNaN(value) || Double.isInfinite(value))
			throw new IllegalArgumentException("Invalid value");

		this.value = value;
		this.unit = unit;
	}

	public Quantity<U> convertTo(U targetUnit) {

		double baseValue = unit.convertToBaseUnit(value);

		double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

		convertedValue = Math.round(convertedValue * 100.0) / 100.0;

		return new Quantity<>(convertedValue, targetUnit);
	}

	public Quantity<U> add(Quantity<U> other) {

		return add(other, this.unit);
	}

	public Quantity<U> add(Quantity<U> other, U targetUnit) {

		double base1 = unit.convertToBaseUnit(value);
		double base2 = other.unit.convertToBaseUnit(other.value);

		double sum = base1 + base2;

		double result = targetUnit.convertFromBaseUnit(sum);

		result = Math.round(result * 100.0) / 100.0;

		return new Quantity<>(result, targetUnit);
	}

	public Quantity<U> subtract(Quantity<U> other) {
		return subtract(other, this.unit);
	}

	public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

		if (other == null)
			throw new IllegalArgumentException("Quantity cannot be null");

		if (targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");

		if (!this.unit.getClass().equals(other.unit.getClass()))
			throw new IllegalArgumentException("Cross category subtraction not allowed");

		double baseValue1 = this.unit.convertToBaseUnit(this.value);
		double baseValue2 = other.unit.convertToBaseUnit(other.value);

		double resultBase = baseValue1 - baseValue2;

		double convertedResult = targetUnit.convertFromBaseUnit(resultBase);

		double rounded = Math.round(convertedResult * 100.0) / 100.0;

		return new Quantity<>(rounded, targetUnit);
	}

	public double divide(Quantity<U> other) {

		if (other == null)
			throw new IllegalArgumentException("Quantity cannot be null");

		if (!this.unit.getClass().equals(other.unit.getClass()))
			throw new IllegalArgumentException("Cross category division not allowed");

		double baseValue1 = this.unit.convertToBaseUnit(this.value);
		double baseValue2 = other.unit.convertToBaseUnit(other.value);

		if (baseValue2 == 0)
			throw new ArithmeticException("Division by zero");

		return baseValue1 / baseValue2;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		Quantity<?> other = (Quantity<?>) obj;

		if (unit.getClass() != other.unit.getClass())
			return false;

		double base1 = unit.convertToBaseUnit(value);
		double base2 = other.unit.convertToBaseUnit(other.value);

		return Double.compare(base1, base2) == 0;
	}

	@Override
	public int hashCode() {

		return Objects.hash(unit.convertToBaseUnit(value));
	}

	@Override
	public String toString() {

		return value + " " + unit.getUnitName();
	}
}
