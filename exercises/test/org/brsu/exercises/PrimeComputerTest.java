package org.brsu.exercises;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class PrimeComputerTest {

	@Test
	public void computePrimeNumbers_inputTen_return2and3() {
		// Arrange
		PrimeComputer subject = new PrimeComputer();
		// Act
		List<Integer> primes = subject.computePrimeNumbers(10);
		// Assert
		assertEquals("Number of found primes not correct.", 4, primes.size());
		assertTrue(primes.contains(2));
		assertTrue(primes.contains(3));
		assertTrue(primes.contains(5));
		assertTrue(primes.contains(7));
	}

	@Test
	public void isPrime_1_false() {
		testForPrime(1, false);
	}

	@Test
	public void isPrime_2_true() {
		testForPrime(2, true);
	}

	@Test
	public void isPrime_3_true() {
		testForPrime(3, true);
	}

	@Test
	public void isPrime_4_false() {
		testForPrime(4, false);
	}

	private void testForPrime(int possiblePrime, boolean expectation) {
		// Arrange
		PrimeComputer subject = new PrimeComputer();
		// Act
		boolean prime = subject.isPrime(possiblePrime);
		// Assert
		assertEquals(expectation, prime);
	}

	private void testForPrimeUsingArray() {
		// Arrange
		PrimeComputer subject = new PrimeComputer();
	}

}
