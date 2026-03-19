package com.apps.quantitymeasurement;

public class Quantity<U extends IMeasurable> {

	private final double value;
	private final U unit;

	public Quantity(double value, U unit) {
		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public U getUnit() {
		return unit;
	}

	public Quantity<U> convertTo(U targetUnit) {

		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

		if (unit.getClass() != targetUnit.getClass()) {
			throw new IllegalArgumentException("Incompatible unit conversion");
		}

		double baseValue = unit.convertToBaseUnit(value);
		double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

		return new Quantity<>(roundToTwoDecimals(convertedValue), targetUnit);
	}

	private enum ArithmeticOperation {

		ADD((a, b) -> a + b),

		SUBTRACT((a, b) -> a - b),

		DIVIDE((a, b) -> {
			if (b == 0) {
				throw new ArithmeticException("Division by zero");
			}
			return a / b;
		});

		private final java.util.function.DoubleBinaryOperator operator;

		ArithmeticOperation(java.util.function.DoubleBinaryOperator operator) {
			this.operator = operator;
		}

		public double compute(double a, double b) {
			return operator.applyAsDouble(a, b);
		}
	}

	private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetRequired) {

		if (other == null) {
			throw new IllegalArgumentException("Operand cannot be null");
		}

		if (unit.getClass() != other.unit.getClass()) {
			throw new IllegalArgumentException("Cross-category arithmetic not allowed");
		}

		if (!Double.isFinite(value) || !Double.isFinite(other.value)) {
			throw new IllegalArgumentException("Values must be finite numbers");
		}

		if (targetRequired && targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}
	}

	private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {

		double base1 = unit.convertToBaseUnit(value);
		double base2 = other.unit.convertToBaseUnit(other.value);

		return operation.compute(base1, base2);
	}

	public Quantity<U> add(Quantity<U> other) {
		return add(other, this.unit);
	}

	public Quantity<U> add(Quantity<U> other, U targetUnit) {

		validateArithmeticOperands(other, targetUnit, true);

		double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

		double result = targetUnit.convertFromBaseUnit(baseResult);

		return new Quantity<>(roundToTwoDecimals(result), targetUnit);
	}

	public Quantity<U> subtract(Quantity<U> other) {
		return subtract(other, this.unit);
	}

	public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

		validateArithmeticOperands(other, targetUnit, true);

		double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

		double result = targetUnit.convertFromBaseUnit(baseResult);

		return new Quantity<>(roundToTwoDecimals(result), targetUnit);
	}

	public double divide(Quantity<U> other) {

		validateArithmeticOperands(other, null, false);

		return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof Quantity))
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
		double base = unit.convertToBaseUnit(value);
		return Double.hashCode(base);
	}

	private double roundToTwoDecimals(double value) {
		return Math.round(value * 100.0) / 100.0;
	}

	@Override
	public String toString() {
		return "Quantity{" + "value=" + value + ", unit=" + unit + '}';
	}
}