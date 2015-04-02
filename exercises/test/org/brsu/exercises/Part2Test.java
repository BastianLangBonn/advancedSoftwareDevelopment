package org.brsu.exercises;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Part2.
 * 
 * @author bastian
 *
 */
public class Part2Test {

	private Part2 subject;

	@Before
	public void setUp() {
		subject = new Part2();
	}

	@Test
	public void computePrimeNumbersEfficiently_2_return2() {
		// Arrange
		List<Integer> formerPrimes = Arrays.asList();
		// Act
		List<Integer> primes = subject.computePrimesEfficiently(2);
		// Arrange
		assertEquals(primes.size(), 1);
		assertTrue(primes.contains(2));
	}

	@Test
	public void computePrimeNumbers_inputTen_return2and3() {
		// Act
		List<Integer> primes = subject.computePrimeNumbersFirstApproach(10);
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
		Part2 subject = new Part2();
		// Act
		boolean prime = subject.isPrime(possiblePrime);
		// Assert
		assertEquals(expectation, prime);
	}

	@Test
	public void isPrimeGivenFormerPrimes_10_returnsFalse() {
		List<Integer> formerPrimes = Arrays.asList(2, 3);
		testForPrimeUsingFormerPrimes(10, formerPrimes, false);
	}

	@Test
	public void isPrimeGivenFormerPrimes_7_returnsTrue() {
		List<Integer> formerPrimes = Arrays.asList(2, 3);
		testForPrimeUsingFormerPrimes(7, formerPrimes, true);
	}

	private void testForPrimeUsingFormerPrimes(int possiblePrime,
			List<Integer> formerPrimes, boolean expectation) {
		// Act
		boolean prime = subject.isProductOfAnyOfGivenFactors(possiblePrime,
				formerPrimes);
		// Assert
		assertEquals(expectation, prime);
	}

}
