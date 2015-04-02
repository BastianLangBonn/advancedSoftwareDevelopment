package org.brsu.exercises;

import java.util.LinkedList;
import java.util.List;

/**
 * Class containing the code to solve part 2 of exercise 1.
 * 
 * @author bastian
 *
 */
public class Part2 {

	public static void main(String[] args) {
		Part2 part2 = new Part2();
		// part2.computeAndPrintPrimesEfficiently(1000000);
		part2.computeAndPrintPrimesFirstApproach(1000000);

	}

	public void computeAndPrintPrimesEfficiently(int upperBound) {
		long startTime = System.currentTimeMillis();
		List<Integer> primes = computePrimesEfficiently(upperBound);
		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;
		printOutPrimesAndTimeTaken(primes, timeTaken);
	}

	/**
	 * Computes all primes up to MAX_INTEGER using the first approach.
	 */
	public void computeAndPrintPrimesFirstApproach(int upperBound) {
		long startTime = System.currentTimeMillis();
		List<Integer> primeNumbers = computePrimeNumbersFirstApproach(upperBound);
		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;
		printOutPrimesAndTimeTaken(primeNumbers, timeTaken);
	}

	private void printOutPrimesAndTimeTaken(List<Integer> primeNumbers,
			long timeTaken) {
		for (Integer prime : primeNumbers) {
			// System.out.println(prime);
		}
		System.out.println(primeNumbers.size() + " primes found");
		System.out.println("Time taken for computation: " + timeTaken + "ms");
	}

	/**
	 * Computes all primes between 0 and a given max.
	 * 
	 * @param maxPrime
	 *            The maximum prime value to be found.
	 * @return A {@link List} of primes found.
	 */
	public List<Integer> computePrimeNumbersFirstApproach(int maxPrime) {
		LinkedList<Integer> primes = new LinkedList<Integer>();
		for (int i = 2; i <= maxPrime; i++) {
			if (isPrime(i)) {
				primes.add(i);
			}
		}
		return primes;
	}

	/**
	 * Checks if an integer is prime.
	 * 
	 * @param possiblePrime
	 *            The integer to be checked.
	 * @return <code>true</code> if integer is prime.
	 */
	public boolean isPrime(int possiblePrime) {
		double maxDivisor = Math.sqrt(possiblePrime);
		if (possiblePrime < 2) {
			return false;
		}
		for (int i = 2; i <= maxDivisor; i++) {
			if (possiblePrime % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if a given integer is the product of at least one of the given
	 * factors.
	 * 
	 * @param possibleProduct
	 *            The number to check.
	 * @param factors
	 *            List of possible factors.
	 * @return
	 */
	public boolean isProductOfAnyOfGivenFactors(int possibleProduct,
			List<Integer> factors) {
		for (int factor : factors) {
			if (possibleProduct % factor == 0) {
				return false;
			}
		}
		return true;
	}

	private List<Integer> computePrimesUpToBoundEfficiently(int upperBound,
			List<Integer> formerPrimes) {
		List<Integer> result = new LinkedList<Integer>(formerPrimes);
		for (int i = 2; i <= upperBound; i++) {
			if (isProductOfAnyOfGivenFactors(i, result)) {
				result.add(i);
			}
		}
		return result;
	}

	/**
	 * Computes all primes up to a given border.
	 * 
	 * @param upperBorder
	 *            The maximum number to check for prime.
	 * @return A @{List} of all the primes found.
	 */
	public List<Integer> computePrimesEfficiently(int upperBorder) {
		return computePrimesUpToBoundEfficiently(upperBorder,
				new LinkedList<Integer>());
	}

}
