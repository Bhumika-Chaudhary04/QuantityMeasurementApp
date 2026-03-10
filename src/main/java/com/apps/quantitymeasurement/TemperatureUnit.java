package com.apps.quantitymeasurement;

public enum TemperatureUnit implements IMeasurable {
	CELSIUS {
		@Override
		public double convertToBaseUnit(double value) {
			return value; // base is Celsius
		}

		@Override
		public double convertFromBaseUnit(double value) {
			return value;
		}

		@Override
		public void validateOperationSupport(String operation) {
			throw new UnsupportedOperationException("Temperature does not support " + operation);
		}
	},
	FAHRENHEIT {
		@Override
		public double convertToBaseUnit(double value) {
			return (value - 32) * 5 / 9;
		}

		@Override
		public double convertFromBaseUnit(double value) {
			return value * 9 / 5 + 32;
		}

		@Override
		public void validateOperationSupport(String operation) {
			throw new UnsupportedOperationException("Temperature does not support " + operation);
		}
	},
	KELVIN {
		@Override
		public double convertToBaseUnit(double value) {
			return value - 273.15;
		}

		@Override
		public double convertFromBaseUnit(double value) {
			return value + 273.15;
		}

		@Override
		public void validateOperationSupport(String operation) {
			throw new UnsupportedOperationException("Temperature does not support " + operation);
		}
	};
}