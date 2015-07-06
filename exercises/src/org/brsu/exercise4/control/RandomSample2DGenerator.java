package org.brsu.exercise4.control;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.brsu.exercise4.model.Sample2D;
import org.brsu.exercise4.model.SamplingConstants;

public class RandomSample2DGenerator {

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
		weight = clampValueWithinBounds(weight, SamplingConstants.WEIGHT_MIN, SamplingConstants.WEIGHT_MAX);
		double x = getNewRandomWithinBounds(SamplingConstants.X_MIN, SamplingConstants.X_MAX);
		double y = getNewRandomWithinBounds(SamplingConstants.Y_MIN, SamplingConstants.Y_MAX);
		double theta = getNewRandomWithinBounds(SamplingConstants.THETA_MIN, SamplingConstants.THETA_MAX
		    - SamplingConstants.CLAMP_PRECISION);
		return new Sample2D(x, y, theta, weight);
	}

	/**
	 * Creates a new {@link Sample2D} out of an overlay of multiple gaussians.
	 * 
	 * @param xMeans
	 * @param yMeans
	 * @param thetaMeans
	 * @param weight
	 * @return
	 */
	public Sample2D generateSampleAroundDistributions(double weight) {
		int numberOfDistributions = SamplingConstants.X_MEANS.length;
		int distributionIndex = random.nextInt(numberOfDistributions);
		double x = (random.nextGaussian() * SamplingConstants.SIGMA_X) + SamplingConstants.X_MEANS[distributionIndex];
		double y = (random.nextGaussian() * SamplingConstants.SIGMA_Y) + SamplingConstants.Y_MEANS[distributionIndex];
		double theta = (random.nextGaussian() * SamplingConstants.SIGMA_THETA)
		    + SamplingConstants.THETA_MEANS[distributionIndex];

		x = clampValueWithinBounds(x, SamplingConstants.X_MIN, SamplingConstants.X_MAX);
		y = clampValueWithinBounds(y, SamplingConstants.Y_MIN, SamplingConstants.Y_MAX);
		theta = clampValueWithinBounds(theta, SamplingConstants.THETA_MIN, SamplingConstants.THETA_MAX);

		return new Sample2D(x, y, theta, weight);
	}

	/**
	 * Creates a set of {@link Sample2D} around an overlay of given distributions.
	 * 
	 * @param xMeans
	 * @param yMeans
	 * @param thetaMeans
	 * @param numberOfSamples
	 * @return
	 */
	public Set<Sample2D> generateSamplesAroundDistributions(int numberOfSamples) {
		Set<Sample2D> result = new HashSet<Sample2D>(numberOfSamples);
		double weight = 1.0 / numberOfSamples;
		for (int i = 0; i < numberOfSamples; i++) {
			result.add(generateSampleAroundDistributions(weight));
		}
		return result;
	}

	/**
	 * Creates a set of {@link Sample2D} that is uniformly distributed within the
	 * specified ranges.
	 * 
	 * @param numberOfSamples
	 * @return
	 */
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

	/**
	 * Generates a new set weighted according to a random pose.
	 * 
	 * @param originalSamples
	 * @return
	 */
	public Set<Sample2D> generateWeightedSet(Set<Sample2D> originalSamples) {
		Set<Sample2D> result = new HashSet<Sample2D>(originalSamples.size());
		// compute real pose - weight is not important here
		Sample2D realPose = generateSampleAroundDistributions(0);
		double realX = realPose.getX();
		double realY = realPose.getY();
		double realTheta = realPose.getTheta();

		for (Sample2D sample : originalSamples) {
			double sampleX = sample.getX();
			double sampleY = sample.getY();
			double sampleTheta = sample.getTheta();

			double xDensity = computeDensity(sampleX, realX, SamplingConstants.SIGMA_X);
			double yDensity = computeDensity(sampleY, realY, SamplingConstants.SIGMA_Y);
			double thetaDensity = computeDensity(sampleTheta, realTheta, SamplingConstants.SIGMA_THETA);
			double weight = xDensity * yDensity * thetaDensity;
			result.add(new Sample2D(sampleX, sampleY, sampleTheta, weight));
		}
		return result;
	}

	/**
	 * Method to resample a given set of samples based on the samples' weights.
	 * 
	 * @param originalSamples
	 *          A {@link Set} of {@link Sample2D}s.
	 * @return A new resampled {@link Set} of {@link Sample2D}s.
	 */
	public Set<Sample2D> resampleSet(Set<Sample2D> originalSamples) {
		Set<Sample2D> result = new HashSet<Sample2D>(originalSamples.size());

		// Create a new sample for every sample
		for (int i = 0; i < originalSamples.size(); i++) {
			double randomNumber = random.nextDouble();
			// compute sample to use for resampling
			double currentWeight = 0;
			for (Sample2D sample : originalSamples) {
				currentWeight += sample.getWeight();
				if (randomNumber < currentWeight) {
					// Create new sample
					double x = random.nextGaussian() * SamplingConstants.SIGMA_X + sample.getX();
					double y = random.nextGaussian() * SamplingConstants.SIGMA_Y + sample.getY();
					double theta = random.nextGaussian() * SamplingConstants.SIGMA_THETA + sample.getTheta();
					double weight = 1.0 / originalSamples.size();
					result.add(new Sample2D(x, y, theta, weight));
					break;
				}
			}
		}
		return result;
	}

	private double computeDensity(double value, double mean, double sigma) {
		double base = 1.0 / (Math.sqrt(2 * Math.PI * sigma * sigma));
		double exponent = -Math.pow(value - mean, 2) / (2 * sigma * sigma);
		return base * Math.exp(exponent);
	}

	public static void main(String[] args) {
		RandomSample2DGenerator generator = new RandomSample2DGenerator();
		Set<Sample2D> samples = generator.generateUniformlyDistributedSamples(100);
		for (Sample2D sample : samples) {
			System.out.println(sample.toString());
		}
	}
}
