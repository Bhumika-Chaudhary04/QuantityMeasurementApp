package com.apps.quantitymeasurement;

public interface IMeasurable {

	// Convert to base unit
	double convertToBaseUnit(double value);

	// Convert from base unit
	double convertFromBaseUnit(double value);

	// Lambda to indicate if arithmetic is supported
	SupportsArithmetic supportsArithmetic = () -> true;

	default boolean supportsArithmetic() {
		return supportsArithmetic.isSupported();
	}

	default void validateOperationSupport(String operation) {
		// Default: do nothing (all operations supported)
	}
}