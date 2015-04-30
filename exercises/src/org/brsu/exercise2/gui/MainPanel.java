package org.brsu.exercise2.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainPanel extends JPanel implements ActionListener, ChangeListener, DocumentListener, FocusListener {

	private static final int TEXTFIELD_WIDTH = 50;

	private static final long serialVersionUID = 1L;

	private static final int CONTROLS_WIDTH = 300;
	private static final int IMAGE_HEIGHT = 300;
	private static final int IMAGE_WIDTH = 300;

	private static final int SLIDER_WIDTH = 250;
	private static final int RADIO_BUTTON_WIDTH = 150;
	private static final int LABEL_WIDTH = 50;
	private static final int STANDARD_OBJECTS_HEIGHT = 20;

	private static final int OFFSET_BETWEEN_OBJECTS = 5;
	private static final int OFFSET_TOP = 15;
	private static final int OFFSET_LEFT = 15;

	private JSlider sliderRed;
	private JSlider sliderBlue;
	private JSlider sliderGreen;

	private JTextField textFieldRed;
	private JTextField textFieldGreen;
	private JTextField textFieldBlue;

	private JLabel labelRed;
	private JLabel labelGreen;
	private JLabel labelBlue;

	private JRadioButton radioButtonLeftImage;
	private JRadioButton radioButtonRightImage;

	private Color rightImageColor = new Color(127, 127, 127);
	private Color leftImageColor = new Color(127, 127, 127);

	private int currentVerticalPosition;

	public MainPanel() {
		setPreferredSize(new Dimension(950, 400));
		initializeComponents();
		doLayout();
		revalidate();
		repaint();
	}

	@Override
	public void doLayout() {
		currentVerticalPosition = OFFSET_TOP;

		positionGroup(labelRed, textFieldRed, sliderRed);
		positionGroup(labelGreen, textFieldGreen, sliderGreen);
		positionGroup(labelBlue, textFieldBlue, sliderBlue);

		radioButtonLeftImage.setBounds(OFFSET_LEFT, currentVerticalPosition, RADIO_BUTTON_WIDTH, STANDARD_OBJECTS_HEIGHT);
		radioButtonRightImage.setBounds(OFFSET_LEFT + RADIO_BUTTON_WIDTH, currentVerticalPosition, RADIO_BUTTON_WIDTH,
		    STANDARD_OBJECTS_HEIGHT);

	}

	private void positionGroup(JLabel label, JTextField textField, JSlider slider) {
		label.setBounds(OFFSET_LEFT, currentVerticalPosition, LABEL_WIDTH, STANDARD_OBJECTS_HEIGHT);
		textField.setBounds(OFFSET_LEFT + LABEL_WIDTH, currentVerticalPosition, TEXTFIELD_WIDTH, STANDARD_OBJECTS_HEIGHT);
		currentVerticalPosition += STANDARD_OBJECTS_HEIGHT + OFFSET_BETWEEN_OBJECTS;
		slider.setBounds(OFFSET_LEFT, currentVerticalPosition, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT);
		currentVerticalPosition += STANDARD_OBJECTS_HEIGHT + OFFSET_BETWEEN_OBJECTS;

	}

	private void initializeComponents() {
		setLayout(null);
		initializeLabels();
		initializeTextFields();
		initializeSliders();
		initializeRadioButtons();
	}

	private void initializeRadioButtons() {
		radioButtonLeftImage = createSingleRadioButton("Left Image");
		radioButtonLeftImage.setSelected(true);
		add(radioButtonLeftImage);
		radioButtonRightImage = createSingleRadioButton("Right Image");
		add(radioButtonRightImage);
	}

	private JRadioButton createSingleRadioButton(String buttonLabel) {
		JRadioButton radioButton = new JRadioButton();
		radioButton.setVisible(true);
		radioButton.setText(buttonLabel);
		radioButton.addActionListener(this);
		return radioButton;
	}

	private void initializeLabels() {
		labelRed = createSingleLabel("RED");
		add(labelRed);
		labelGreen = createSingleLabel("GREEN");
		add(labelGreen);
		labelBlue = createSingleLabel("BLUE");
		add(labelBlue);
	}

	private void initializeTextFields() {
		textFieldRed = createSingleTextField();
		add(textFieldRed);
		textFieldBlue = createSingleTextField();
		add(textFieldBlue);
		textFieldGreen = createSingleTextField();
		add(textFieldGreen);
	}

	private void initializeSliders() {
		sliderBlue = createSingleSlider();
		add(sliderBlue);
		sliderGreen = createSingleSlider();
		add(sliderGreen);
		sliderRed = createSingleSlider();
		add(sliderRed);
	}

	private JLabel createSingleLabel(String caption) {
		JLabel label = new JLabel(caption);
		label.setVisible(true);
		label.setSize(LABEL_WIDTH, STANDARD_OBJECTS_HEIGHT);
		return label;
	}

	private JTextField createSingleTextField() {
		JTextField textField = new JTextField("127");
		textField.setVisible(true);
		textField.addActionListener(this);
		textField.getDocument().addDocumentListener(this);
		textField.addFocusListener(this);
		textField.setSize(50, STANDARD_OBJECTS_HEIGHT);
		return textField;
	}

	private JSlider createSingleSlider() {
		JSlider slider = new JSlider();
		slider.setSize(SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT);
		slider.setMinimum(0);
		slider.setMaximum(255);
		slider.setValue(127);
		slider.addChangeListener(this);
		slider.setVisible(true);
		return slider;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(leftImageColor);
		g.fillRect(OFFSET_LEFT + CONTROLS_WIDTH, OFFSET_TOP, IMAGE_WIDTH, IMAGE_HEIGHT);
		g.setColor(rightImageColor);
		g.fillRect(OFFSET_LEFT + CONTROLS_WIDTH + IMAGE_WIDTH, OFFSET_TOP, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == radioButtonLeftImage) {
			radioButtonLeftImage.setSelected(true);
			radioButtonRightImage.setSelected(false);
		} else if (e.getSource() == radioButtonRightImage) {
			radioButtonRightImage.setSelected(true);
			radioButtonLeftImage.setSelected(false);
		}
		updateSlidersAndTextFields();
		doLayout();
	}

	private void updateSlidersAndTextFields() {
		int red = radioButtonLeftImage.isSelected() ? leftImageColor.getRed() : rightImageColor.getRed();
		int green = radioButtonLeftImage.isSelected() ? leftImageColor.getGreen() : rightImageColor.getGreen();
		int blue = radioButtonLeftImage.isSelected() ? leftImageColor.getBlue() : rightImageColor.getBlue();

		textFieldRed.setText(String.valueOf(red));
		textFieldGreen.setText(String.valueOf(green));
		textFieldBlue.setText(String.valueOf(blue));
		sliderRed.setValue(red);
		sliderGreen.setValue(green);
		sliderBlue.setValue(blue);
	}

	private void updateColor() {
		if (radioButtonLeftImage.isSelected()) {
			leftImageColor = readColorFromFields();
		} else {
			rightImageColor = readColorFromFields();
		}
	}

	private Color readColorFromFields() {
		return new Color(Integer.parseInt(textFieldRed.getText()), Integer.parseInt(textFieldGreen.getText()),
		    Integer.parseInt(textFieldBlue.getText()));
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		if (event.getSource() == sliderRed) {
			textFieldRed.setText(String.valueOf(sliderRed.getValue()));
		} else if (event.getSource() == sliderGreen) {
			textFieldGreen.setText(String.valueOf(sliderGreen.getValue()));
		} else if (event.getSource() == sliderBlue) {
			textFieldBlue.setText(String.valueOf(sliderBlue.getValue()));
		}
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		updateColor();
		// updateSliders();
		repaint();
	}

	private void updateSliders() {
		int red = radioButtonLeftImage.isSelected() ? leftImageColor.getRed() : rightImageColor.getRed();
		int green = radioButtonLeftImage.isSelected() ? leftImageColor.getGreen() : rightImageColor.getGreen();
		int blue = radioButtonLeftImage.isSelected() ? leftImageColor.getBlue() : rightImageColor.getBlue();

		sliderRed.setValue(red);
		sliderGreen.setValue(green);
		sliderBlue.setValue(blue);
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		updateSliders();
		repaint();
	}
}
