package com.apps.quantitymeasurement.application;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import com.apps.quantitymeasurement.units.LengthUnit;
import com.apps.quantitymeasurement.units.TemperatureUnit;
import com.apps.quantitymeasurement.units.VolumeUnit;
import com.apps.quantitymeasurement.units.WeightUnit;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		QuantityMeasurementCacheRepository repository = new QuantityMeasurementCacheRepository();
		IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
		QuantityMeasurementController controller = new QuantityMeasurementController(service, repository);

		// ================= LENGTH =================
		System.out.println("\n========== LENGTH MEASUREMENT ==========");

		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

		System.out.println("\nComparing: " + feet + " and " + inches);
		System.out.println("Result: " + (controller.checkEquality(feet, inches) ? "EQUAL" : "NOT EQUAL"));

		System.out.println("\nConverting: " + feet + " → INCHES");
		System.out.println("Result: " + controller.convert(feet, LengthUnit.INCHES));

		System.out.println("\nAdding: " + feet + " + " + inches + " (in FEET)");
		System.out.println("Result: " + controller.add(feet, inches, LengthUnit.FEET));

		System.out.println("\nSubtracting: " + feet + " - " + inches + " (in FEET)");
		System.out.println("Result: " + controller.subtract(feet, inches, LengthUnit.FEET));

		System.out.println("\nDividing: " + feet + " / " + inches);
		System.out.println("Result: " + controller.divide(feet, inches));

		// ================= WEIGHT =================
		System.out.println("\n========== WEIGHT MEASUREMENT ==========");

		Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

		System.out.println("\nComparing: " + kg + " and " + gram);
		System.out.println("Result: " + (controller.checkEquality(kg, gram) ? "EQUAL" : "NOT EQUAL"));

		System.out.println("\nConverting: " + kg + " → GRAM");
		System.out.println("Result: " + controller.convert(kg, WeightUnit.GRAM));

		System.out.println("\nAdding: " + kg + " + " + gram + " (in KILOGRAM)");
		System.out.println("Result: " + controller.add(kg, gram, WeightUnit.KILOGRAM));

		System.out.println("\nSubtracting: " + kg + " - " + gram + " (in KILOGRAM)");
		System.out.println("Result: " + controller.subtract(kg, gram, WeightUnit.KILOGRAM));

		System.out.println("\nDividing: " + kg + " / " + gram);
		System.out.println("Result: " + controller.divide(kg, gram));

		// ================= VOLUME =================
		System.out.println("\n========== VOLUME MEASUREMENT ==========");

		Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

		System.out.println("\nComparing: " + litre + " and " + ml);
		System.out.println("Result: " + (controller.checkEquality(litre, ml) ? "EQUAL" : "NOT EQUAL"));

		System.out.println("\nConverting: " + litre + " → MILLILITRE");
		System.out.println("Result: " + controller.convert(litre, VolumeUnit.MILLILITRE));

		System.out.println("\nAdding: " + litre + " + " + ml + " (in LITRE)");
		System.out.println("Result: " + controller.add(litre, ml, VolumeUnit.LITRE));

		System.out.println("\nSubtracting: " + litre + " - " + ml + " (in LITRE)");
		System.out.println("Result: " + controller.subtract(litre, ml, VolumeUnit.LITRE));

		System.out.println("\nDividing: " + litre + " / " + ml);
		System.out.println("Result: " + controller.divide(litre, ml));

		// ================= TEMPERATURE =================
		System.out.println("\n========== TEMPERATURE MEASUREMENT ==========");

		Quantity<TemperatureUnit> c = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
		Quantity<TemperatureUnit> f = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

		System.out.println("\nComparing: " + c + " and " + f);
		System.out.println("Result: " + (controller.checkEquality(c, f) ? "EQUAL" : "NOT EQUAL"));

		System.out.println("\nConverting: " + c + " → FAHRENHEIT");
		System.out.println("Result: " + controller.convert(c, TemperatureUnit.FAHRENHEIT));

		System.out.println("\nTrying Addition: " + c + " + " + f);
		try {
			System.out.println("Result: " + controller.add(c, f));
		} catch (Exception e) {
			System.out.println("Not Allowed: " + e.getMessage());
		}

		System.out.println("\n========== OPERATION HISTORY ==========");
		controller.getHistory().forEach(System.out::println);
	}
}