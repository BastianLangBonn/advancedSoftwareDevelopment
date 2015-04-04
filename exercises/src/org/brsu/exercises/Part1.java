package org.brsu.exercises;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Class containing the code to solve part 2 of exercise 1.
 * 
 * @author bastian
 * 
 */
public class Part1 {

	private static final int MAX_RANDOM_INTEGER = 100;
	private Random random;

	public static void main(String[] args) {
		Part1 part1 = new Part1();
		part1.run();
	}

	public Part1() {
		random = new Random();
	}

	/**
	 * Contains all the calls to produce the output asked for in part 2 of
	 * exercise 1.
	 */
	public void run() {
		int userInput = getUserInput();
		int[] randomNumbers = readNIntegers(userInput);
		for (int i = 0; i < randomNumbers.length; i++) {
			System.out.println("random number " + (i + 1) + ": " + randomNumbers[i]);
		}
		System.out.println("sum: " + computeSumOfArray(randomNumbers));
		try {
			System.out.println("product: " + computeProductOfArray(randomNumbers));
		} catch (IllegalStateException exception) {
			System.err.println(exception.getMessage());
		}
		System.out
		    .println("average: " + (int) computeAverageOfArray(randomNumbers));
		System.out.println("variance: "
		    + (int) computeVarianceOfArray(randomNumbers));
		Arrays.sort(randomNumbers);
		System.out.println("maximum value: "
		    + randomNumbers[randomNumbers.length - 1]);
		System.out.println("minimum value: " + randomNumbers[0]);
	}

	/**
	 * Gets a user input from the console. Keeps asking for input until a valid
	 * integer has been passed.
	 * 
	 * @return The given user input.
	 */
	public int getUserInput() {
		System.out.println("Please input an integer n");
		Scanner scanner = new Scanner(System.in);
		int input = 0;
		try {
			input = scanner.nextInt();
			if (input > 100 || input < 0) {
				throw new InputMismatchException("integer is not in range [0,100]");
			}
		} catch (InputMismatchException exception) {
			System.out.println("Input has to be an integer in range [0,100]");
			return getUserInput();
		} finally {
			scanner.close();
		}
		System.out.println("Input given was " + input);
		return input;
	}

	/**
	 * Creates n random integers.
	 * 
	 * @param n
	 *          Number of integers to be created.
	 * @return An array of integers.
	 */
	public int[] readNIntegers(int n) {
		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			result[i] = random.nextInt(MAX_RANDOM_INTEGER);
		}
		return result;
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
