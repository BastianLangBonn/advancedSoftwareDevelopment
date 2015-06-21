package org.brsu.exercise4.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Set;

import javax.swing.JPanel;

import org.brsu.exercise4.model.Sample2D;

public class MainPanel2D extends JPanel {

	private static final long serialVersionUID = 1L;

	private int magnifyingFactor = 5;
	private int circleSize = 2;
	private static final int X_OFFSET = 250;
	private static final int Y_OFFSET = 250;

	private Set<Sample2D> samples;

	public MainPanel2D(Set<Sample2D> samples) {
		this.samples = samples;
		setPreferredSize(new Dimension(500, 500));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Sample2D sample : samples) {
			int x = (int) sample.getX() + X_OFFSET;
			int y = (int) sample.getY() + Y_OFFSET;
			double theta = sample.getTheta();
			g.drawLine(x, y, x + (int) (magnifyingFactor * Math.cos(theta)), y + (int) (magnifyingFactor * Math.sin(theta)));
			g.drawArc(x - circleSize / 2, y - circleSize / 2, circleSize, circleSize, 0, 360);
		}
	}

}
