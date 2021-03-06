package org.brsu.exercise2.gui;

import javax.swing.JFrame;

/**
 * Main {@link JFrame} for the GUI.
 * 
 * @author bastian
 * 
 */
public class MainFrame extends JFrame {

	private static final String TITLE = "Exercise 2 - The Color Tester";

	private static final long serialVersionUID = 1L;

	private MainPanel mainPanel;

	public MainFrame() {
		// To close the program when closing the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 500);
		mainPanel = new MainPanel();
		mainPanel.setSize(this.getSize());
		add(mainPanel);
		mainPanel.setVisible(true);
		mainPanel.setOpaque(true);
		mainPanel.validate();
		setTitle(TITLE);
		setVisible(true);
	}
}
