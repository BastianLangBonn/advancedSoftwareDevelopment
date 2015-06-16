package org.brsu.exercise4.view;

import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.brsu.exercise4.model.Sample2D;

public class Sample2DVisualizer extends JFrame {

	private static final long serialVersionUID = 1L;

	private MainPanel2D mainPanel;

	public Sample2DVisualizer(String title, Set<Sample2D> samples) {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		mainPanel = new MainPanel2D(samples);
		mainPanel.setVisible(true);
		JScrollPane scrollPane = new JScrollPane(mainPanel);
		scrollPane.getVerticalScrollBar().setMinimum(-200);
		scrollPane.getHorizontalScrollBar().setMinimum(-200);
		scrollPane.setVisible(true);
		add(scrollPane);
		setTitle(title);
		setVisible(true);
	}

}
