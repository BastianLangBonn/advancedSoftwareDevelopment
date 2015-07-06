package org.brsu.exercise3.gui;

import javax.swing.JFrame;

/**
 * Main {@link JFrame} for the GUI.
 * 
 * @author bastian
 * 
 */
public class MainFrame extends JFrame {

	private static final String TITLE = "Exercise 3 - Optical Illusions";

	private static final long serialVersionUID = 1L;

	private MainPanel mainPanel;

	public MainFrame() {
		// To close the program when closing the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 500);
		mainPanel = new MainPanel();
		mainPanel.setSize(this.getSize());
		mainPanel.setVisible(true);
		mainPanel.setOpaque(true);
		add(mainPanel);
		mainPanel.validate();
		setTitle(TITLE);
		setVisible(true);
	}
}
