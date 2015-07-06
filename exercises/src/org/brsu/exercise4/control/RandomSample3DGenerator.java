package org.brsu.exercise4.control;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.brsu.exercise4.model.Sample3D;
import org.brsu.exercise4.model.SamplingConstants;

/**
 * Class to perform sampling actions for {@link Sample3D}s.
 * 
 * @author Bastian Lang
 * 
 */
public class RandomSample3DGenerator {

	private Random random = new Random();

	/**
	 * Generates a {@link Sample3D} uniformly distributed with given weight.
	 * 
	 * @param weight
	 */
	public Sample3D generateUniformlyDistributedSample(double weight) {
		weight = SamplingUtils.clampValueWithinBounds(weight, SamplingConstants.WEIGHT_MIN, SamplingConstants.WEIGHT_MAX);
		double x = SamplingUtils.getNewRandomWithinBounds(SamplingConstants.X_MIN, SamplingConstants.X_MAX);
		double y = SamplingUtils.getNewRandomWithinBounds(SamplingConstants.Y_MIN, SamplingConstants.Y_MAX);
		double z = SamplingUtils.getNewRandomWithinBounds(SamplingConstants.Z_MIN, SamplingConstants.Z_MAX);
		double alpha = SamplingUtils.getNewRandomWithinBounds(SamplingConstants.ANGLE_MIN, SamplingConstants.ANGLE_MAX
		    - SamplingConstants.CLAMP_PRECISION);
		double beta = SamplingUtils.getNewRandomWithinBounds(SamplingConstants.ANGLE_MIN, SamplingConstants.ANGLE_MAX
		    - SamplingConstants.CLAMP_PRECISION);
		double gamma = SamplingUtils.getNewRandomWithinBounds(SamplingConstants.ANGLE_MIN, SamplingConstants.ANGLE_MAX
		    - SamplingConstants.CLAMP_PRECISION);

		Sample3D result = new Sample3D();
		result.setX(x);
		result.setY(y);
		result.setZ(z);
		result.setAlpha(alpha);
		result.setBeta(beta);
		result.setGamma(gamma);
		return result;
	}

	/**
	 * Generates a {@link Set} of {@link Sample3D} uniformly distributed.
	 * 
	 * @param numberOfSamples
	 * @return
	 */
	public Set<Sample3D> generateUniformlyDistributedSamples(int numberOfSamples) {
		Set<Sample3D> result = new HashSet<Sample3D>(numberOfSamples);
		for (int i = 0; i < numberOfSamples; i++) {
			result.add(generateUniformlyDistributedSample(1.0 / numberOfSamples));
		}
		return result;
	}

	/**
	 * Generates a {@link Sample3D} distributed around an overlay of five
	 * gaussians.
	 * 
	 * @param weight
	 * @return
	 */
	public Sample3D generateSampleAroundDistributions(double weight) {
		int numberOfDistributions = SamplingConstants.X_MEANS.length;
		int distributionIndex = random.nextInt(numberOfDistributions);
		double x = (random.nextGaussian() * SamplingConstants.SIGMA_X) + SamplingConstants.X_MEANS[distributionIndex];
		double y = (random.nextGaussian() * SamplingConstants.SIGMA_Y) + SamplingConstants.Y_MEANS[distributionIndex];
		double z = (random.nextGaussian() * SamplingConstants.SIGMA_Z) + SamplingConstants.Z_MEANS[distributionIndex];
		double alpha = (random.nextGaussian() * SamplingConstants.SIGMA_ANGLE)
		    + SamplingConstants.ANGLE_MEANS[distributionIndex];
		double beta = (random.nextGaussian() * SamplingConstants.SIGMA_ANGLE)
		    + SamplingConstants.ANGLE_MEANS[distributionIndex];
		double gamma = (random.nextGaussian() * SamplingConstants.SIGMA_ANGLE)
		    + SamplingConstants.ANGLE_MEANS[distributionIndex];

		x = SamplingUtils.clampValueWithinBounds(x, SamplingConstants.X_MIN, SamplingConstants.X_MAX);
		y = SamplingUtils.clampValueWithinBounds(y, SamplingConstants.Y_MIN, SamplingConstants.Y_MAX);
		z = SamplingUtils.clampValueWithinBounds(z, SamplingConstants.Z_MIN, SamplingConstants.Z_MAX);
		alpha = SamplingUtils.clampValueWithinBounds(alpha, SamplingConstants.ANGLE_MIN, SamplingConstants.ANGLE_MAX);
		beta = SamplingUtils.clampValueWithinBounds(beta, SamplingConstants.ANGLE_MIN, SamplingConstants.ANGLE_MAX);
		gamma = SamplingUtils.clampValueWithinBounds(gamma, SamplingConstants.ANGLE_MIN, SamplingConstants.ANGLE_MAX);

		Sample3D result = new Sample3D();
		result.setX(x);
		result.setY(y);
		result.setZ(z);
		result.setAlpha(alpha);
		result.setBeta(beta);
		result.setGamma(gamma);
		return result;
	}

	/**
	 * Generates a {@link Set} of {@link Sample3D}s around an overlay of
	 * gaussians.
	 * 
	 * @param numberOfSamples
	 * @return
	 */
	public Set<Sample3D> generateSamplesAroundDistributions(int numberOfSamples) {
		Set<Sample3D> result = new HashSet<Sample3D>(numberOfSamples);
		double weight = 1.0 / numberOfSamples;
		for (int i = 0; i < numberOfSamples; i++) {
			result.add(generateSampleAroundDistributions(weight));
		}
		return result;
	}

	/**
	 * Generates a new set weighted according to a random pose.
	 * 
	 * @param originalSamples
	 * @return
	 */
	public Set<Sample3D> generateWeightedSet(Set<Sample3D> originalSamples) {
		Set<Sample3D> result = new HashSet<Sample3D>(originalSamples.size());
		// compute real pose - weight is not important here
		Sample3D realPose = generateSampleAroundDistributions(0);

		for (Sample3D sample : originalSamples) {
			Sample3D weightedSample = generateWeightedSample(realPose, sample);
			result.add(weightedSample);
		}
		return result;
	}

	private Sample3D generateWeightedSample(Sample3D realPose, Sample3D sample) {
		double xDensity = SamplingUtils.computeDensity(sample.getX(), realPose.getX(), SamplingConstants.SIGMA_X);
		double yDensity = SamplingUtils.computeDensity(sample.getY(), realPose.getY(), SamplingConstants.SIGMA_Y);
		double zDensity = SamplingUtils.computeDensity(sample.getZ(), realPose.getZ(), SamplingConstants.SIGMA_Z);
		double alphaDensity = SamplingUtils.computeDensity(sample.getAlpha(), realPose.getAlpha(),
		    SamplingConstants.SIGMA_ANGLE);
		double betaDensity = SamplingUtils.computeDensity(sample.getBeta(), realPose.getBeta(),
		    SamplingConstants.SIGMA_ANGLE);
		double gammaDensity = SamplingUtils.computeDensity(sample.getGamma(), realPose.getGamma(),
		    SamplingConstants.SIGMA_ANGLE);
		double weight = xDensity * yDensity * zDensity * alphaDensity * betaDensity * gammaDensity;

		Sample3D weightedSample = new Sample3D();
		weightedSample.setX(sample.getX());
		weightedSample.setY(sample.getY());
		weightedSample.setZ(sample.getZ());
		weightedSample.setAlpha(sample.getAlpha());
		weightedSample.setBeta(sample.getBeta());
		weightedSample.setGamma(sample.getGamma());
		weightedSample.setWeight(weight);
		return weightedSample;
	}

}
