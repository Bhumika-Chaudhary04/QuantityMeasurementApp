CREATE TABLE IF NOT EXISTS quantity_measurement_entity (
    id INT AUTO_INCREMENT PRIMARY KEY,
    measurement_type VARCHAR(50) NOT NULL,
    operation_type VARCHAR(50) NOT NULL,
    value1 DOUBLE NOT NULL,
    value2 DOUBLE NOT NULL,
    result BOOLEAN NOT NULL
);