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
		// Act
		List<Long> primes = subject.computePrimesEfficiently(2);
		// Arrange
		assertEquals(primes.size(), 1);
		assertTrue(primes.contains(2l));
	}

	@Test
	public void computePrimeNumbers_inputTen_return2and3() {
		// Act
		List<Long> primes = subject.computePrimeNumbersFirstApproach(10);
		// Assert
		assertEquals("Number of found primes not correct.", 4, primes.size());
		assertTrue(primes.contains(2l));
		assertTrue(primes.contains(3l));
		assertTrue(primes.contains(5l));
		assertTrue(primes.contains(7l));
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

	private void testForPrime(long possiblePrime, boolean expectation) {
		// Arrange
		Part2 subject = new Part2();
		// Act
		boolean prime = subject.isPrime(possiblePrime);
		// Assert
		assertEquals(expectation, prime);
	}

	@Test
	public void isPrimeGivenFormerPrimes_10_returnsFalse() {
		List<Long> formerPrimes = Arrays.asList(2l, 3l);
		testForPrimeUsingFormerPrimes(10, formerPrimes, false);
	}

	@Test
	public void isPrimeGivenFormerPrimes_7_returnsTrue() {
		List<Long> formerPrimes = Arrays.asList(2l, 3l);
		testForPrimeUsingFormerPrimes(7, formerPrimes, true);
	}

	private void testForPrimeUsingFormerPrimes(long possiblePrime,
	    List<Long> formerPrimes, boolean expectation) {
		// Act
		boolean prime = subject.isProductOfAnyOfGivenFactors(possiblePrime,
		    formerPrimes);
		// Assert
		assertEquals(expectation, prime);
	}

}
