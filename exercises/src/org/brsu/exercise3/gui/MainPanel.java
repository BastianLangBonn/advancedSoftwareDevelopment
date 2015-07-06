package org.brsu.exercise3.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private ImageOnePanel imageOnePanel;
	private ImageTwoPanel imageTwoPanel;
	private JButton buttonSwitchImage;

	public MainPanel() {
		setPreferredSize(new Dimension(950, 500));
		initializeComponents();
		validate();
		doLayout();
		repaint();
	}

	private void initializeComponents() {
		setLayout(null);
		imageOnePanel = new ImageOnePanel();
		imageOnePanel.setPreferredSize(new Dimension(950, 450));
		imageOnePanel.setVisible(true);
		imageOnePanel.setBounds(0, 0, 950, 450);
		imageOnePanel.setOpaque(true);
		add(imageOnePanel);

		imageTwoPanel = new ImageTwoPanel();
		imageTwoPanel.setPreferredSize(new Dimension(950, 450));
		imageTwoPanel.setVisible(false);
		imageTwoPanel.setBounds(0, 0, 950, 450);
		imageTwoPanel.setOpaque(true);
		add(imageTwoPanel);

		buttonSwitchImage = new JButton("Switch Image");
		buttonSwitchImage.addActionListener(this);
		buttonSwitchImage.setBounds(50, 450, 200, 20);
		// buttonSwitchImage.setBounds(0, 0, 100, 20);
		buttonSwitchImage.setVisible(true);
		add(buttonSwitchImage);
	}

	@Override
	public void doLayout() {
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (imageOnePanel.isVisible()) {
			imageOnePanel.setVisible(false);
			imageTwoPanel.setVisible(true);
		} else {
			imageOnePanel.setVisible(true);
			imageTwoPanel.setVisible(false);
		}
	}
}