package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.utils.DatabaseConfig;

public class QuantityMeasurementAppTest {

	private Connection connection;
	private QuantityMeasurementDatabaseRepository repository;

	@BeforeEach
	void setup() throws Exception {
		connection = DatabaseConfig.getConnection();
		repository = new QuantityMeasurementDatabaseRepository();

		connection.createStatement().execute("DELETE FROM quantity_history");
	}

	@Test
	void givenValidData_whenSaved_thenStoredInDB() throws Exception {

		repository.save("LengthUnit", "ADD", 1.0, "FEET", 12.0, "INCHES", 2.0, "FEET");

		ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM quantity_history");

		rs.next();
		int count = rs.getInt(1);

		assertTrue(count == 1);
	}

	@Test
	void givenDuplicateData_whenSaved_thenOnlyOneStored() throws Exception {

		repository.save("LengthUnit", "ADD", 1.0, "FEET", 12.0, "INCHES", 2.0, "FEET");

		repository.save("LengthUnit", "ADD", 1.0, "FEET", 12.0, "INCHES", 2.0, "FEET");

		ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM quantity_history");

		rs.next();
		int count = rs.getInt(1);

		assertTrue(count == 1);
	}

	@Test
	void givenDifferentOperations_whenSaved_thenStoredSeparately() throws Exception {

		repository.save("LengthUnit", "ADD", 1.0, "FEET", 12.0, "INCHES", 2.0, "FEET");

		repository.save("LengthUnit", "SUBTRACT", 1.0, "FEET", 12.0, "INCHES", 0.0, "FEET");

		ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM quantity_history");

		rs.next();
		int count = rs.getInt(1);

		assertTrue(count == 2);
	}

	@Test
	void givenDifferentTypes_whenSaved_thenStoredIndependently() throws Exception {

		repository.save("LengthUnit", "ADD", 1.0, "FEET", 12.0, "INCHES", 2.0, "FEET");

		repository.save("WeightUnit", "ADD", 1.0, "KILOGRAM", 1000.0, "GRAM", 2.0, "KILOGRAM");

		ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM quantity_history");

		rs.next();
		int count = rs.getInt(1);

		assertTrue(count == 2);
	}

	@Test
	void givenValidData_whenSaved_thenCorrectResultStored() throws Exception {

		repository.save("LengthUnit", "ADD", 1.0, "FEET", 12.0, "INCHES", 2.0, "FEET");

		ResultSet rs = connection.createStatement().executeQuery("SELECT result FROM quantity_history");

		rs.next();
		double result = rs.getDouble("result");

		assertTrue(result == 2.0);
	}

	@Test
	void givenValidData_whenSaved_thenCorrectUnitsStored() throws Exception {

		repository.save("LengthUnit", "ADD", 1.0, "FEET", 12.0, "INCHES", 2.0, "FEET");

		ResultSet rs = connection.createStatement()
				.executeQuery("SELECT unit1, unit2, result_unit FROM quantity_history");

		rs.next();

		String unit1 = rs.getString("unit1");
		String unit2 = rs.getString("unit2");
		String resultUnit = rs.getString("result_unit");

		assertTrue(unit1.equals("FEET"));
		assertTrue(unit2.equals("INCHES"));
		assertTrue(resultUnit.equals("FEET"));
	}

	@Test
	void givenEmptyDatabase_thenNoRecordsFound() throws Exception {

		ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM quantity_history");

		rs.next();
		int count = rs.getInt(1);

		assertTrue(count == 0);
	}

	@Test
	void givenMultipleValidEntries_whenSaved_thenAllStored() throws Exception {

		repository.save("LengthUnit", "ADD", 1.0, "FEET", 12.0, "INCHES", 2.0, "FEET");
		repository.save("VolumeUnit", "ADD", 1.0, "LITRE", 1000.0, "MILLILITRE", 2.0, "LITRE");
		repository.save("WeightUnit", "ADD", 1.0, "KILOGRAM", 1000.0, "GRAM", 2.0, "KILOGRAM");

		ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM quantity_history");

		rs.next();
		int count = rs.getInt(1);

		assertTrue(count == 3);
	}
}