package com.apps.quantitymeasurement.application;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import com.apps.quantitymeasurement.units.*;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		IQuantityMeasurementRepository repository = new QuantityMeasurementDatabaseRepository();
		IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
		QuantityMeasurementController controller = new QuantityMeasurementController(service, repository);

		System.out.println("\n========== LENGTH ==========");

		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		System.out.println("\nFirst ADD:");
		System.out.println(controller.add(feet, inches, LengthUnit.FEET));

		System.out.println("\nDuplicate ADD:");
		System.out.println(controller.add(feet, inches, LengthUnit.FEET));

		System.out.println("\nFirst SUBTRACT:");
		System.out.println(controller.subtract(feet, inches, LengthUnit.FEET));

		System.out.println("\nDuplicate SUBTRACT:");
		System.out.println(controller.subtract(feet, inches, LengthUnit.FEET));

		System.out.println("\n========== WEIGHT ==========");

		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

		System.out.println("\nFirst ADD:");
		System.out.println(controller.add(kg, gram, WeightUnit.KILOGRAM));

		System.out.println("\nDuplicate ADD:");
		System.out.println(controller.add(kg, gram, WeightUnit.KILOGRAM));

		// ================= VOLUME =================
		System.out.println("\n========== VOLUME ==========");

		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		System.out.println("\nFirst ADD:");
		System.out.println(controller.add(litre, ml, VolumeUnit.LITRE));

		System.out.println("\nDuplicate ADD:");
		System.out.println(controller.add(litre, ml, VolumeUnit.LITRE));

		System.out.println("\n========== TEMPERATURE ==========");

		Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
		Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

		System.out.println("\nTrying ADD (should fail):");
		try {
			System.out.println(controller.add(c, f, TemperatureUnit.CELSIUS));
		} catch (Exception e) {
			System.out.println("Not Allowed: " + e.getMessage());
		}
	}
}