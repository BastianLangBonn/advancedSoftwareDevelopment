package org.brsu.exercises;

import java.util.LinkedList;
import java.util.List;

public class PrimeComputer {

	public static void main(String[] args) {
		PrimeComputer primeComputer = new PrimeComputer();
		primeComputer.computePrimesUntilMaxInteger();
	}

	public void computePrimesUntilMaxInteger() {
		long startTime = System.currentTimeMillis();
		List<Integer> primeNumbers = computePrimeNumbers(Integer.MAX_VALUE);
		long endTime = System.currentTimeMillis();

		long timeTaken = endTime - startTime;
		for (Integer prime : primeNumbers) {
			System.out.println(prime);
		}
		System.out.println(primeNumbers.size() + "primes found");
		System.out.println("Time taken for computation: " + timeTaken + "ms");
	}

	/**
	 * Computes all primes between 0 and a given max.
	 * 
	 * @param maxPrime
	 *            The maximum prime value to be found.
	 * @return A {@link List} of primes found.
	 */
	public List<Integer> computePrimeNumbers(int maxPrime) {
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

}
