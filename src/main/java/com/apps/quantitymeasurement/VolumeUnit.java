package com.apps.quantitymeasurement;

public enum VolumeUnit implements IMeasurable {
	LITRE(1000.0), MILLILITRE(1.0), GALLON(3785.41);

	private final double toMillilitres;

	VolumeUnit(double toMillilitres) {
		this.toMillilitres = toMillilitres;
	}

	@Override
	public double convertToBaseUnit(double value) {
		return value * toMillilitres;
	}

	@Override
	public double convertFromBaseUnit(double value) {
		return value / toMillilitres;
	}
}