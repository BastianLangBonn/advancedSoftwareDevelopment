package org.brsu.exercise4.control;

import java.util.Set;

import org.brsu.exercise4.model.Sample2D;
import org.brsu.exercise4.view.Sample2DVisualizer;

public class Application {

	public static void main(String[] args) {
		RandomSample2DGenerator generator = new RandomSample2DGenerator();
		// new Sample2DVisualizer("10 uniformly distributed 2D samples",
		// generator.generateUniformlyDistributedSamples(10));
		// new Sample2DVisualizer("100 uniformly distributed 2D samples",
		// generator.generateUniformlyDistributedSamples(100));
		// new Sample2DVisualizer("1000 uniformly distributed 2D samples",
		// generator.generateUniformlyDistributedSamples(1000));
		// new Sample2DVisualizer("10k uniformly distributed 2D samples",
		// generator.generateUniformlyDistributedSamples(10000));

		Set<Sample2D> gaussians10 = generator.generateSamplesAroundDistributions(10);
		// Set<Sample2D> gaussians100 =
		// generator.generateSamplesAroundDistributions(100);
		// Set<Sample2D> gaussians1000 =
		// generator.generateSamplesAroundDistributions(1000);
		// Set<Sample2D> gaussians10k =
		// generator.generateSamplesAroundDistributions(10000);
		new Sample2DVisualizer("10 gaussian distributed 2D samples", gaussians10);
		// new Sample2DVisualizer("100 gaussian distributed 2D samples",
		// gaussians100);
		// new Sample2DVisualizer("1000 gaussian distributed 2D samples",
		// gaussians1000);
		// new Sample2DVisualizer("10k gaussian distributed 2D samples",
		// gaussians10k);

		Set<Sample2D> resampled10 = generator.resampleSet(gaussians10);
		// Set<Sample2D> resampled100 = generator.resampleSet(gaussians100);
		// Set<Sample2D> resampled1000 = generator.resampleSet(gaussians1000);
		// Set<Sample2D> resampled10k = generator.resampleSet(gaussians10k);
		new Sample2DVisualizer("10 resampled gaussian distributed 2D samples", resampled10);
		// new Sample2DVisualizer("100 resampled distributed 2D samples",
		// resampled100);
		// new Sample2DVisualizer("1000 resampled distributed 2D samples",
		// resampled1000);
		// new Sample2DVisualizer("10k resampled distributed 2D samples",
		// resampled10k);

		System.out.println(gaussians10.size());
		System.out.println(resampled10.size());

	}
}
