package org.brsu.exercise3.util;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;

public class ObjectCreator {
	public JLabel createSingleLabel(String caption, int width, int height) {
		JLabel label = new JLabel(caption);
		label.setVisible(true);
		label.setSize(width, width);
		return label;
	}

	public JRadioButton createSingleRadioButton(String buttonLabel, ActionListener listener) {
		JRadioButton radioButton = new JRadioButton();
		radioButton.setVisible(true);
		radioButton.setText(buttonLabel);
		radioButton.addActionListener(listener);
		return radioButton;
	}

	public JTextField createSingleTextField(String value, DocumentListener documentListener,
	    ActionListener actionListener, int height) {
		JTextField textField = new JTextField(value);
		textField.setVisible(true);
		textField.addActionListener(actionListener);
		textField.getDocument().addDocumentListener(documentListener);
		textField.setSize(50, height);
		return textField;
	}

	public JSlider createSingleSlider(ChangeListener listener, int width, int height, int min, int max) {
		JSlider slider = new JSlider();
		slider.setSize(width, height);
		slider.setMinimum(min);
		slider.setMaximum(max);
		slider.setValue(127);
		slider.addChangeListener(listener);
		slider.setVisible(true);
		return slider;
	}
}
