package org.brsu.exercises;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class containing the code to solve part 4 of exercise 1.
 * 
 * @author bastian
 * 
 */
public class Part4 {

	private static final int MAX_PRECISION = 15;
	private static final int MIN_PRECISION = 0;
	private Scanner scanner;

	public static void main(String[] args) {
		Part4 part4 = new Part4();
		part4.run();
	}

	public Part4() {
		scanner = new Scanner(System.in);
	}

	private void run() {
		double diameter = getDoubleFromUser("Please input the diameter (type double).");
		int precision = getIntegerFromUser("Please give the maximum precision (["
		    + MIN_PRECISION + "," + MAX_PRECISION + "]).");
		double previousCircumference = 0.0;
		double previousArea = 0.0;
		System.out
		    .println("Precision\t|\tPi\t\t\t|\tCircumference\t\t|\tArea\t\t\t|\tPercentage increase Cirumference\t|\tPercentage increase Area");
		for (int i = MIN_PRECISION; i <= precision; i++) {
			System.out.print(i);
			System.out.print("\t\t|\t");
			double approximatedPi = approximatePiWithPrecision(i);
			System.out.print(String.format("%.15f", approximatedPi));
			System.out.print("\t|\t");
			double circumference = 2 * approximatedPi * diameter;
			double area = approximatedPi * Math.pow(diameter, 2.0);
			System.out.print(String.format("%.15f", circumference));
			System.out.print("\t|\t");
			System.out.print(String.format("%.15f", area));
			System.out.print("\t|\t");
			double percentageIncreaseOfCircumference = computePercentageIncreaseOfCircumference(
			    previousCircumference, circumference);
			System.out.print(percentageIncreaseOfCircumference);
			System.out.print("\t\t\t|\t");
			double percentageIncreaseOfArea = computePercentageIncreaseOfArea(
			    previousArea, area);
			System.out.print(percentageIncreaseOfArea);
			System.out.print("\n");
			previousCircumference = circumference;
			previousArea = area;
		}
	}

	private double computePercentageIncreaseOfArea(double previousArea,
	    double area) {
		double differenceInArea = area - previousArea;
		double percentageIncreaseOfArea;
		if (differenceInArea == 0) {
			percentageIncreaseOfArea = 0;
		} else {
			percentageIncreaseOfArea = differenceInArea * 100 / previousArea;
		}
		return percentageIncreaseOfArea;
	}

	private double computePercentageIncreaseOfCircumference(
	    double previousCircumference, double circumference) {
		double percentageIncreaseOfCircumference = computePercentageIncreaseOfArea(
		    previousCircumference, circumference);
		return percentageIncreaseOfCircumference;
	}

	private double approximatePiWithPrecision(int precision) {
		String stringRepresentation = String.valueOf(Math.PI);
		stringRepresentation = stringRepresentation.substring(0, 2 + precision);
		return Double.valueOf(stringRepresentation);
	}

	private int getIntegerFromUser(String message) {
		int result = 0;
		scanner = new Scanner(System.in);
		try {
			System.out.println(message);
			result = scanner.nextInt();
		} catch (InputMismatchException exception) {
			System.out.println("Input has to be of type int.");
			result = getIntegerFromUser(message);
		}
		if (result < MIN_PRECISION || result > MAX_PRECISION) {
			System.out.println("Input out of range [" + MIN_PRECISION + ","
			    + MAX_PRECISION + "]. Taking " + MAX_PRECISION + " instead.");
			result = MAX_PRECISION;
		}
		System.out.println("Precision specified: " + result);
		return result;
	}

	private double getDoubleFromUser(String message) {
		double result = 0.0;
		scanner = new Scanner(System.in);
		try {
			System.out.println(message);
			result = scanner.nextDouble();
		} catch (InputMismatchException exception) {
			System.out.println("Input has to be of type double.");
			return getDoubleFromUser(message);
		}
		System.out.println("Diameter given: " + result);
		return result;
	}
}
