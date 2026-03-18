package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.*;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import com.apps.quantitymeasurement.util.ApplicationConfig;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		IQuantityMeasurementRepository repo;

		if (ApplicationConfig.getRepoType().equals("database")) {
			repo = new QuantityMeasurementDatabaseRepository();
		} else {
			throw new RuntimeException("Only DB supported now");
		}

		QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(repo);
		QuantityMeasurementController controller = new QuantityMeasurementController(service);

		QuantityDTO q1 = new QuantityDTO("LENGTH", 10);
		QuantityDTO q2 = new QuantityDTO("LENGTH", 5);

		controller.performAddition(q1, q2);
		controller.performSubtraction(q1, q2);
		controller.performDivision(q1, q2);

		controller.showCount();
		controller.showAll();
	}
}