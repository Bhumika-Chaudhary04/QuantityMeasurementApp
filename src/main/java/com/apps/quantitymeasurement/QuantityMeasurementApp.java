package com.apps.quantitymeasurement;

import java.util.Scanner;
import java.util.InputMismatchException;

public class QuantityMeasurementApp {
	// UC1
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
	//UC2
	public static class Inches {
		private final double value;

		public Inches(double value) {
			this.value = value;
		}

		public double getValue() {
			return value;
		}

		@Override
		public boolean equals(Object obj) {

			if (this == obj)
				return true;

			if (obj == null)
				return false;

			if (getClass() != obj.getClass())
				return false;

			Inches other = (Inches) obj;

			return Double.compare(this.value, other.value) == 0;
		}

		@Override
		public int hashCode() {
			return Double.hashCode(value);
		}
	}

	// feet equality check
	public static boolean compareFeet(double value1, double value2) {
		Feet feet1 = new Feet(value1);
		Feet feet2 = new Feet(value2);
		return feet1.equals(feet2);
	}

	// inches equality check
	public static boolean compareInches(double value1, double value2) {
		Inches inch1 = new Inches(value1);
		Inches inch2 = new Inches(value2);
		return inch1.equals(inch2);
	}

	// main method with user input validation
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
	    try {
	        System.out.println("Enter first value in Feet:");
	        double feet1 = Double.parseDouble(sc.nextLine());

	        System.out.println("Enter second value in Feet:");
	        double feet2 = Double.parseDouble(sc.nextLine());

	        boolean feetResult = compareFeet(feet1, feet2);
	        System.out.println("Feet Comparison Result: " + feetResult);

	        System.out.println("\nEnter first value in Inches:");
	        double inch1 = Double.parseDouble(sc.nextLine());

	        System.out.println("Enter second value in Inches:");
	        double inch2 = Double.parseDouble(sc.nextLine());

	        boolean inchResult = compareInches(inch1, inch2);
	        System.out.println("Inches Comparison Result: " + inchResult);

	    } catch (NumberFormatException e) {
	        System.out.println("Invalid input! Please enter numeric values only.");
	    }
	    sc.close();
	}
}