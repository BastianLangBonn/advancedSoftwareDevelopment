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
		// part2.computeAndPrintPrimesFirstApproach(1000000);
		part2.computeAndPrintPrimesEfficiently(Integer.MAX_VALUE);
		part2.computeAndPrintPrimesEfficiently(Long.MAX_VALUE);
		// part2.computeAndPrintPrimesFirstApproach(Integer.MAX_VALUE);

	}

	public void computeAndPrintPrimesEfficiently(long maxValue) {
		long startTime = System.currentTimeMillis();
		List<Long> primes = computePrimesEfficiently(maxValue);
		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;
		printOutPrimesAndTimeTaken(primes, timeTaken);
	}

	/**
	 * Computes all primes up to MAX_INTEGER using the first approach.
	 */
	public void computeAndPrintPrimesFirstApproach(long upperBound) {
		long startTime = System.currentTimeMillis();
		List<Long> primeNumbers = computePrimeNumbersFirstApproach(upperBound);
		long endTime = System.currentTimeMillis();
		long timeTaken = endTime - startTime;
		printOutPrimesAndTimeTaken(primeNumbers, timeTaken);
	}

	private void printOutPrimesAndTimeTaken(List<Long> primes, long timeTaken) {
		for (long prime : primes) {
			System.out.println(prime);
		}
		System.out.println(primes.size() + " primes found");
		System.out.println("Time taken for computation: " + timeTaken + "ms");
	}

	/**
	 * Computes all primes between 0 and a given max.
	 * 
	 * @param upperBound
	 *          The maximum prime value to be found.
	 * @return A {@link List} of primes found.
	 */
	public List<Long> computePrimeNumbersFirstApproach(long upperBound) {
		LinkedList<Long> primes = new LinkedList<Long>();
		for (long i = 2; i <= upperBound; i++) {
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
	 *          The integer to be checked.
	 * @return <code>true</code> if integer is prime.
	 */
	public boolean isPrime(long possiblePrime) {
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
	 *          The number to check.
	 * @param result
	 *          List of possible factors.
	 * @return
	 */
	public boolean isProductOfAnyOfGivenFactors(long possibleProduct,
	    List<Long> result) {
		double maxFactor = Math.sqrt(possibleProduct);
		for (long factor : result) {
			if (factor > maxFactor) {
				return true;
			}
			if (possibleProduct % factor == 0) {
				return false;
			}
		}
		return true;
	}

	private List<Long> computePrimesUpToBoundEfficiently(long maxValue,
	    List<Long> formerPrimes) {
		List<Long> result = new LinkedList<Long>(formerPrimes);
		for (long i = 2; i <= maxValue; i++) {
			if (isProductOfAnyOfGivenFactors(i, result)) {
				result.add(i);
			}
		}
		return result;
	}

	/**
	 * Computes all primes up to a given border.
	 * 
	 * @param maxValue
	 *          The maximum number to check for prime.
	 * @return A @{List} of all the primes found.
	 */
	public List<Long> computePrimesEfficiently(long maxValue) {
		return computePrimesUpToBoundEfficiently(maxValue, new LinkedList<Long>());
	}

}
