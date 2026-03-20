package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.utils.DatabaseConfig;

import java.sql.*;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

	private final Connection connection;

	public QuantityMeasurementDatabaseRepository() {
		this.connection = DatabaseConfig.getConnection();
		initializeTable();
	}

	private void initializeTable() {
		String query = """
				    CREATE TABLE IF NOT EXISTS quantity_history (
				        id INT AUTO_INCREMENT PRIMARY KEY,
				        type VARCHAR(50),
				        operation VARCHAR(50),
				        value1 DOUBLE,
				        unit1 VARCHAR(50),
				        value2 DOUBLE,
				        unit2 VARCHAR(50),
				        result DOUBLE,
				        result_unit VARCHAR(50)
				    )
				""";

		try (Statement stmt = connection.createStatement()) {
			stmt.execute(query);
			System.out.println("Table initialized");
		} catch (SQLException e) {
			throw new DatabaseException("Table creation failed", e);
		}
	}

	@Override
	public void save(String type, String operation, double value1, String unit1, double value2, String unit2,
			double result, String resultUnit) {

		String checkQuery = """
				    SELECT COUNT(*) FROM quantity_history
				    WHERE type=? AND operation=? AND value1=? AND unit1=?
				    AND value2=? AND unit2=? AND result=? AND result_unit=?
				""";

		String insertQuery = """
				    INSERT INTO quantity_history
				    (type, operation, value1, unit1, value2, unit2, result, result_unit)
				    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
				""";

		try {
			PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
			checkStmt.setString(1, type);
			checkStmt.setString(2, operation);
			checkStmt.setDouble(3, value1);
			checkStmt.setString(4, unit1);
			checkStmt.setDouble(5, value2);
			checkStmt.setString(6, unit2);
			checkStmt.setDouble(7, result);
			checkStmt.setString(8, resultUnit);

			ResultSet rs = checkStmt.executeQuery();
			rs.next();

			if (rs.getInt(1) > 0) {
				System.out.println("Duplicate ignored");
				return;
			}

			PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
			insertStmt.setString(1, type);
			insertStmt.setString(2, operation);
			insertStmt.setDouble(3, value1);
			insertStmt.setString(4, unit1);
			insertStmt.setDouble(5, value2);
			insertStmt.setString(6, unit2);
			insertStmt.setDouble(7, result);
			insertStmt.setString(8, resultUnit);

			insertStmt.executeUpdate();

			System.out.println("Data saved to DB");

		} catch (SQLException e) {
			throw new DatabaseException("Insert failed", e);
		}
	}
}