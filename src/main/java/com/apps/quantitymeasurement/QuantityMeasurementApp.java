package com.apps.quantitymeasurement;

import java.util.Scanner;
import java.util.InputMismatchException;

public class QuantityMeasurementApp {
	//UC1
	// inner Class
	public static class Feet {
		private final double value;

		// constructor
		public Feet(double value) {
			this.value = value;
		}

		public double getValue() {
			return value;
		}

		// overriding equals() method
		@Override
		public boolean equals(Object obj) {

			if (this == obj)
				return true;

			if (obj == null)
				return false;

			if (getClass() != obj.getClass())
				return false;

			Feet other = (Feet) obj;

			return Double.compare(this.value, other.value) == 0;
		}
		@Override
		public int hashCode() {
		    return Double.hashCode(value);
		}
	}

	// main method with user input validation
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Enter first value in feet: ");
			double value1 = scanner.nextDouble();

			System.out.print("Enter second value in feet: ");
			double value2 = scanner.nextDouble();

			Feet feet1 = new Feet(value1);
			Feet feet2 = new Feet(value2);

			System.out.println("Are they equal? " + feet1.equals(feet2));

		} 
		catch (InputMismatchException e) {
			System.out.println("Invalid input! Please enter numeric values only.");
		} 
		finally {
			scanner.close();
		}
	}
}