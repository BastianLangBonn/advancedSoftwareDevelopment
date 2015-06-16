package org.brsu.exercise4.control;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.brsu.exercise4.model.Sample2D;

public class RandomSample2DGenerator {

	private static final double X_MIN = -200.0;
	private static final double X_MAX = 200.0;
	private static final double Y_MIN = -200.0;
	private static final double Y_MAX = 200.0;
	private static final double THETA_MIN = 0.0;
	private static final double THETA_MAX = 360.0;
	private static final double WEIGHT_MIN = 0.0;
	private static final double WEIGHT_MAX = 1.0;
	private static final double CLAMP_PRECISION = 0.001;

	private static final double SIGMA_X = 20.0;
	private static final double SIGMA_Y = 20.0;
	private static final double SIGMA_THETA = 10.0;
	private Random random;

	public RandomSample2DGenerator() {
		random = new Random();
	}

	/**
	 * Creates a new {@link Sample2D} within the given bounds and the given
	 * weight.
	 * 
	 * @param weight
	 * @return
	 */
	public Sample2D generateUniformlyDistributedSample(double weight) {
		weight = clampValueWithinBounds(weight, WEIGHT_MIN, WEIGHT_MAX);
		double x = getNewRandomWithinBounds(X_MIN, X_MAX);
		double y = getNewRandomWithinBounds(Y_MIN, Y_MAX);
		double theta = getNewRandomWithinBounds(THETA_MIN, THETA_MAX);
		return new Sample2D(x, y, theta, weight);
	}

	public Set<Sample2D> generateUniformlyDistributedSamples(int numberOfSamples) {
		Set<Sample2D> result = new HashSet<Sample2D>(numberOfSamples);
		for (int i = 0; i < numberOfSamples; i++) {
			result.add(generateUniformlyDistributedSample(1.0 / numberOfSamples));
		}
		return result;
	}

	private double getNewRandomWithinBounds(double minValue, double maxValue) {
		return random.nextDouble() * (maxValue - minValue) + minValue;
	}

	private double clampValueWithinBounds(double value, double lowerBound, double upperBound) {
		double result = value;
		if (value < lowerBound) {
			result = lowerBound;
		} else if (value > upperBound) {
			result = upperBound;
		}
		return result;
	}

	public static void main(String[] args) {
		RandomSample2DGenerator generator = new RandomSample2DGenerator();
		Set<Sample2D> samples = generator.generateUniformlyDistributedSamples(100);
		for (Sample2D sample : samples) {
			System.out.println(sample.toString());
		}
	}
}
