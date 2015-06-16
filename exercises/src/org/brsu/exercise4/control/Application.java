package org.brsu.exercise4.control;

import org.brsu.exercise4.view.Sample2DVisualizer;

public class Application {

	public static void main(String[] args) {
		RandomSample2DGenerator generator = new RandomSample2DGenerator();
		new Sample2DVisualizer("10k uniformly distributed 2D samples", generator.generateUniformlyDistributedSamples(10000));
		new Sample2DVisualizer("10 uniformly distributed 2D samples", generator.generateUniformlyDistributedSamples(10));
		new Sample2DVisualizer("100 uniformly distributed 2D samples", generator.generateUniformlyDistributedSamples(100));
	}

}
