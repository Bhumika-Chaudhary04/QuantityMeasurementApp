package com.apps.quantitymeasurement;

public enum WeightUnit implements IMeasurable {
    KILOGRAM(1000.0), GRAM(1.0), POUND(453.592);

    private final double toGrams;

    WeightUnit(double toGrams) {
        this.toGrams = toGrams;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * toGrams;
    }

    @Override
    public double convertFromBaseUnit(double value) {
        return value / toGrams;
    }
}