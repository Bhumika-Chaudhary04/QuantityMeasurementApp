package com.apps.quantitymeasurement;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

	private final double value;
	private final U unit;

	public Quantity(double value, U unit) {
		if (unit == null)
			throw new IllegalArgumentException("Unit cannot be null");
		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public U getUnit() {
		return unit;
	}

	// ------------------ Conversion ------------------
	public Quantity<U> convertTo(U targetUnit) {
		if (!(unit instanceof Enum && targetUnit instanceof Enum)
				|| ((Enum<?>) unit).getDeclaringClass() != ((Enum<?>) targetUnit).getDeclaringClass()) {
			throw new IllegalArgumentException("Incompatible unit conversion");
		}
		double baseValue = unit.convertToBaseUnit(value);
		double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
		return new Quantity<>(roundToTwoDecimals(convertedValue), targetUnit);
	}

	// ------------------ Arithmetic ------------------
	private enum ArithmeticOperation {
		ADD((a, b) -> a + b), SUBTRACT((a, b) -> a - b), DIVIDE((a, b) -> {
			if (b == 0)
				throw new ArithmeticException("Division by zero");
			return a / b;
		});

		private final DoubleBinaryOperator op;

		ArithmeticOperation(DoubleBinaryOperator op) {
			this.op = op;
		}

		public double compute(double a, double b) {
			return op.applyAsDouble(a, b);
		}
	}

	private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetRequired, String operation) {
		if (other == null)
			throw new IllegalArgumentException("Operand cannot be null");
		if (!(unit instanceof Enum && other.unit instanceof Enum)
				|| ((Enum<?>) unit).getDeclaringClass() != ((Enum<?>) other.unit).getDeclaringClass()) {
			throw new IllegalArgumentException("Cross-category arithmetic not allowed");
		}
		if (targetRequired && targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");
		unit.validateOperationSupport(operation);
		other.unit.validateOperationSupport(operation);
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
		validateArithmeticOperands(other, targetUnit, true, "addition");
		double result = targetUnit.convertFromBaseUnit(performBaseArithmetic(other, ArithmeticOperation.ADD));
		return new Quantity<>(roundToTwoDecimals(result), targetUnit);
	}

	public Quantity<U> subtract(Quantity<U> other) {
		return subtract(other, this.unit);
	}

	public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
		validateArithmeticOperands(other, targetUnit, true, "subtraction");
		double result = targetUnit.convertFromBaseUnit(performBaseArithmetic(other, ArithmeticOperation.SUBTRACT));
		return new Quantity<>(roundToTwoDecimals(result), targetUnit);
	}

	public double divide(Quantity<U> other) {
		validateArithmeticOperands(other, null, false, "division");
		return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
	}

	// ------------------ Equality ------------------
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Quantity))
			return false;
		Quantity<?> other = (Quantity<?>) obj;

		if (!(unit instanceof Enum && other.unit instanceof Enum)
				|| ((Enum<?>) unit).getDeclaringClass() != ((Enum<?>) other.unit).getDeclaringClass()) {
			return false;
		}

		double base1 = unit.convertToBaseUnit(value);
		double base2 = other.unit.convertToBaseUnit(other.value);
		return Double.compare(base1, base2) == 0;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(unit.convertToBaseUnit(value));
	}

	private double roundToTwoDecimals(double val) {
		return Math.round(val * 100.0) / 100.0;
	}

	@Override
	public String toString() {
		return "Quantity{" + "value=" + value + ", unit=" + unit + '}';
	}
}