package org.brsu.test;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JApplet;
import javax.swing.JLabel;

public class ExampleApplet extends JApplet {

	@Override
	public void init() {
		super.init();
		getContentPane().setBackground(Color.ORANGE);
		setLayout(new BorderLayout());
		JLabel label = new JLabel("An applet a day keeps the doctor away.");
		add(label, BorderLayout.CENTER);
	}

}
