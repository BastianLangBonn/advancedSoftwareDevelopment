package org.brsu.exercise1;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class containing the code to solve part 2 of exercise 1.
 * 
 * @author bastian
 * 
 */
public class Part1 {

	private Scanner scanner;

	public static void main(String[] args) {
		Part1 part1 = new Part1();
		part1.run();
	}

	public Part1() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Contains all the calls to produce the output asked for in part 2 of
	 * exercise 1.
	 */
	public void run() {
		int[] input = getUserInput();
		computeAndPrintInformationForNumbers(input);
	}

	private void computeAndPrintInformationForNumbers(int[] numbersGiven) {
		int sum = computeSumOfArray(numbersGiven);
		int average = (int) computeAverageOfArray(numbersGiven);
		int variance = (int) computeVarianceOfArray(numbersGiven);
		Arrays.sort(numbersGiven);
		int maximum = numbersGiven[numbersGiven.length - 1];
		int minimum = numbersGiven[0];
		String numbers = createNumbersString(numbersGiven);
		System.out.println(numbers);
		System.out.println("sum: " + sum);
		try {
			int product = computeProductOfArray(numbersGiven);
			System.out.println("product: " + product);
		} catch (IllegalStateException exception) {
			System.err.println(exception.getMessage());
		}
		System.out.println("average: " + average);
		System.out.println("variance: " + variance);
		System.out.println("minimum value: " + minimum);
		System.out.println("maximum value: " + maximum);
	}

	private String createNumbersString(int[] numbersGiven) {
		StringBuilder numbers = new StringBuilder();
		numbers.append("User given numbers: {");
		for (int i = 0; i < numbersGiven.length; i++) {
			numbers.append(numbersGiven[i]);
			if (i < (numbersGiven.length - 1)) {
				numbers.append(", ");
			}
		}
		numbers.append("}");
		return numbers.toString();
	}

	private int[] getUserInput() {
		try {
			int userInput = getIntegerFromUser("Please input an integer n ([0,100]) specifying how many numbers you want to type in.");
			return readNIntegers(userInput);
		} finally {
			scanner.close();
		}
	}

	private int[] readNIntegers(int numberOfIntegersToRead) {
		int[] result = new int[numberOfIntegersToRead];
		for (int i = 0; i < numberOfIntegersToRead; i++) {
			result[i] = getIntegerFromUser("Please input an integer of range [0,100]");
			System.out.println((numberOfIntegersToRead - (i + 1)) + " numbers left.");
		}
		return result;
	}

	/**
	 * Gets a user input from the console. Keeps asking for input until a valid
	 * integer has been passed.
	 * 
	 * @return The given user input.
	 */
	public int getIntegerFromUser(String displayMessage) {
		System.out.println(displayMessage);
		int input = 0;
		try {
			input = scanner.nextInt();
			if (input > 100 || input < 0) {
				throw new InputMismatchException("integer is not in range [0,100]");
			}
		} catch (InputMismatchException exception) {
			System.out.println("Input has to be an integer in range [0,100]");
			return getIntegerFromUser(displayMessage);
		}
		System.out.println("Input given was " + input);
		return input;
	}

	/**
	 * Computes the sum of elements of a given array.
	 * 
	 * @param summands
	 *          An array of integers.
	 * @return The sum of all elements in the array.
	 */
	public int computeSumOfArray(int[] summands) {
		int sum = 0;
		for (int summand : summands) {
			sum += summand;
		}
		return sum;
	}

	/**
	 * Computes the product of multiplying all elements in an array.
	 * 
	 * @param factors
	 *          Array containing the factors.
	 * @return The product of all factors.
	 */
	public int computeProductOfArray(int[] factors) {
		if (factors.length == 0) {
			return 0;
		}
		long product = 1;
		for (int factor : factors) {
			product *= factor;
		}
		if (product > Integer.MAX_VALUE || product < Integer.MIN_VALUE) {
			throw new IllegalStateException(
			    "Computation of product has exceeded integer range.\n");
		}
		if (product > 0) {
			return (int) product;
		}
		Arrays.sort(factors);
		if (factors[0] == 0) {
			return 0;
		}
		throw new IllegalStateException(
		    "Computation of product has exceeded integer range.\n");
	}

	/**
	 * Computes the average of a given array.
	 * 
	 * @param elements
	 *          Array containing the integer values.
	 * @return The average of all given elements.
	 */
	public double computeAverageOfArray(int[] elements) {
		return (double) computeSumOfArray(elements) / elements.length;
	}

	/**
	 * Computes the variance of a given array.
	 * 
	 * @param elements
	 *          Array containing the numbers for the computation.
	 * @return The variance of the given array.
	 */
	public double computeVarianceOfArray(int[] elements) {
		double average = computeAverageOfArray(elements);
		double squareSum = 0;
		for (int element : elements) {
			double distanceToAverage = element - average;
			squareSum += Math.pow(distanceToAverage, 2);
		}
		return squareSum / elements.length;
	}

}
