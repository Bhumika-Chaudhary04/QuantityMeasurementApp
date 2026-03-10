package com.apps.quantitymeasurement;

public enum LengthUnit implements IMeasurable {
    FEET(12.0), INCHES(1.0), YARD(36.0), CENTIMETER(0.393701);

    private final double toInches;

    LengthUnit(double toInches) {
        this.toInches = toInches;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * toInches;
    }

    @Override
    public double convertFromBaseUnit(double value) {
        return value / toInches;
    }
}