package org.brsu.exercise4.control;

import java.util.Random;

/**
 * Class providing static utility methods for sampling.
 * 
 * @author Bastian Lang
 * 
 */
public class SamplingUtils {

	private static Random random = new Random();

	/**
	 * Clamps a value between the given bounds.
	 * 
	 * @param value
	 *          The value to be clamped.
	 * @param lowerBound
	 *          The lower bound.
	 * @param upperBound
	 *          The upper bound.
	 * @return The given value or one of the given bounds if the value exceeds
	 *         this bound.
	 */
	public static double clampValueWithinBounds(double value, double lowerBound, double upperBound) {
		double result = value;
		if (value < lowerBound) {
			result = lowerBound;
		} else if (value > upperBound) {
			result = upperBound;
		}
		return result;
	}

	/**
	 * Generates a new random value within the given values.
	 * 
	 * @param minValue
	 *          Minimum value.
	 * @param maxValue
	 *          Maximum value
	 * @return A random value within the given range.
	 */
	public static double getNewRandomWithinBounds(double minValue, double maxValue) {
		return random.nextDouble() * (maxValue - minValue) + minValue;
	}

	/**
	 * Computes the density of a given value wrt the given mean and sigma.
	 * 
	 * @param value
	 * @param mean
	 * @param sigma
	 * @return
	 */
	public static double computeDensity(double value, double mean, double sigma) {
		double base = 1.0 / (Math.sqrt(2 * Math.PI * sigma * sigma));
		double exponent = -Math.pow(value - mean, 2) / (2 * sigma * sigma);
		return base * Math.exp(exponent);
	}

}
