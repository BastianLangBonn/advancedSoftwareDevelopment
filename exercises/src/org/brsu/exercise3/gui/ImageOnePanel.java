package org.brsu.exercise3.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.brsu.exercise3.util.GridPainter;
import org.brsu.exercise3.util.ObjectCreator;

public class ImageOnePanel extends JPanel implements ActionListener, ChangeListener, DocumentListener {
	private static final long serialVersionUID = 1L;

	private static final String LABEL_LINE_HEIGHT_TEXT = "Vertical line height";
	private static final String LABEL_LINE_WIDTH_TEXT = "Horizontal line width";
	private static final String LABEL_RECTANGLE_HEIGHT_TEXT = "Rectangle height";
	private static final String LABEL_RECTANGLE_WIDTH_TEXT = "Rectangle width";

	private static final int LABEL_BIG_WIDHT = 150;
	private static final int TEXTFIELD_WIDTH = 50;
	private static final int CONTROLS_WIDTH = 300;
	private static final int IMAGE_HEIGHT = 300;
	private static final int IMAGE_WIDTH = 600;

	private static final int SLIDER_WIDTH = 250;
	private static final int RADIO_BUTTON_WIDTH = 150;
	private static final int LABEL_SMALL_WIDTH = 50;
	private static final int STANDARD_OBJECTS_HEIGHT = 20;

	private static final int OFFSET_BETWEEN_OBJECTS = 5;
	private static final int OFFSET_TOP = 15;
	private static final int OFFSET_LEFT = 15;

	private GridPainter gridPainter;
	private ObjectCreator objectCreator;

	private JSlider sliderRed;
	private JSlider sliderBlue;
	private JSlider sliderGreen;
	private JSlider sliderWidth;
	private JSlider sliderHeight;

	private JTextField textFieldRed;
	private JTextField textFieldGreen;
	private JTextField textFieldBlue;
	private JTextField textFieldWidth;
	private JTextField textFieldHeight;

	private JLabel labelRed;
	private JLabel labelGreen;
	private JLabel labelBlue;
	private JLabel labelWidth;
	private JLabel labelHeight;

	private JRadioButton radioButtonRectangles;
	private JRadioButton radioButtonLines;
	private JRadioButton radioButtonCircles;

	private Color colorRectangles = new Color(0, 0, 0);
	private Color colorLines = new Color(127, 127, 127);
	private Color colorCircles = new Color(255, 255, 255);
	private Color selectedColor = colorRectangles;

	private int currentVerticalPosition;

	private int rectangleWidth = 50;
	private int rectangleHeight = 50;

	private int verticalLineWidth = 10;
	private int horizontalLineHeight = 10;

	public ImageOnePanel() {
		gridPainter = new GridPainter();
		objectCreator = new ObjectCreator();
		initializeComponents();
		doLayout();
		updateSlidersAndTextFields();
		revalidate();
		repaint();
	}

	private void initializeComponents() {
		setLayout(null);
		initializeLabels();
		initializeTextFields();
		initializeSliders();
		initializeRadioButtons();
	}

	private void initializeTextFields() {
		textFieldRed = objectCreator.createSingleTextField(String.valueOf(selectedColor.getRed()), this, this,
		    STANDARD_OBJECTS_HEIGHT);
		add(textFieldRed);
		textFieldBlue = objectCreator.createSingleTextField(String.valueOf(selectedColor.getBlue()), this, this,
		    STANDARD_OBJECTS_HEIGHT);
		add(textFieldBlue);
		textFieldGreen = objectCreator.createSingleTextField(String.valueOf(selectedColor.getGreen()), this, this,
		    STANDARD_OBJECTS_HEIGHT);
		add(textFieldGreen);
		textFieldWidth = objectCreator.createSingleTextField(String.valueOf(rectangleWidth), this, this,
		    STANDARD_OBJECTS_HEIGHT);
		add(textFieldWidth);
		textFieldHeight = objectCreator.createSingleTextField(String.valueOf(rectangleHeight), this, this,
		    STANDARD_OBJECTS_HEIGHT);
		add(textFieldHeight);
	}

	private void initializeRadioButtons() {
		radioButtonRectangles = objectCreator.createSingleRadioButton("Rectangles", this);
		radioButtonRectangles.setSelected(true);
		add(radioButtonRectangles);
		radioButtonLines = objectCreator.createSingleRadioButton("Lines", this);
		add(radioButtonLines);
		radioButtonCircles = objectCreator.createSingleRadioButton("Circles", this);
		add(radioButtonCircles);
	}

	private void initializeLabels() {
		labelRed = objectCreator.createSingleLabel("RED", LABEL_SMALL_WIDTH, STANDARD_OBJECTS_HEIGHT);
		add(labelRed);
		labelGreen = objectCreator.createSingleLabel("GREEN", LABEL_SMALL_WIDTH, STANDARD_OBJECTS_HEIGHT);
		add(labelGreen);
		labelBlue = objectCreator.createSingleLabel("BLUE", LABEL_SMALL_WIDTH, STANDARD_OBJECTS_HEIGHT);
		add(labelBlue);
		labelWidth = objectCreator.createSingleLabel(LABEL_RECTANGLE_WIDTH_TEXT, LABEL_BIG_WIDHT, STANDARD_OBJECTS_HEIGHT);
		add(labelWidth);
		labelHeight = objectCreator
		    .createSingleLabel(LABEL_RECTANGLE_HEIGHT_TEXT, LABEL_BIG_WIDHT, STANDARD_OBJECTS_HEIGHT);
		add(labelHeight);
	}

	private void initializeSliders() {
		sliderBlue = objectCreator.createSingleSlider(this, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT, 0, 255);
		add(sliderBlue);
		sliderGreen = objectCreator.createSingleSlider(this, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT, 0, 255);
		add(sliderGreen);
		sliderRed = objectCreator.createSingleSlider(this, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT, 0, 255);
		add(sliderRed);
		sliderWidth = objectCreator.createSingleSlider(this, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT, 0, 100);
		add(sliderWidth);
		sliderHeight = objectCreator.createSingleSlider(this, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT, 0, 100);
		add(sliderHeight);
	}

	@Override
	public void doLayout() {
		currentVerticalPosition = OFFSET_TOP;

		positionGroup(labelRed, textFieldRed, sliderRed);
		positionGroup(labelGreen, textFieldGreen, sliderGreen);
		positionGroup(labelBlue, textFieldBlue, sliderBlue);
		if (!radioButtonCircles.isSelected()) {
			positionGroup(labelWidth, textFieldWidth, sliderWidth);
			positionGroup(labelHeight, textFieldHeight, sliderHeight);
		}

		radioButtonRectangles.setBounds(OFFSET_LEFT, currentVerticalPosition, RADIO_BUTTON_WIDTH, STANDARD_OBJECTS_HEIGHT);
		radioButtonLines.setBounds(OFFSET_LEFT + RADIO_BUTTON_WIDTH, currentVerticalPosition, RADIO_BUTTON_WIDTH,
		    STANDARD_OBJECTS_HEIGHT);
		currentVerticalPosition += STANDARD_OBJECTS_HEIGHT;
		radioButtonCircles.setBounds(OFFSET_LEFT, currentVerticalPosition, RADIO_BUTTON_WIDTH, STANDARD_OBJECTS_HEIGHT);
	}

	private void positionGroup(JLabel label, JTextField textField, JSlider slider) {
		label.setBounds(OFFSET_LEFT, currentVerticalPosition, label.getWidth(), STANDARD_OBJECTS_HEIGHT);
		textField.setBounds(OFFSET_LEFT + label.getWidth(), currentVerticalPosition, TEXTFIELD_WIDTH,
		    STANDARD_OBJECTS_HEIGHT);
		currentVerticalPosition += STANDARD_OBJECTS_HEIGHT + OFFSET_BETWEEN_OBJECTS;
		slider.setBounds(OFFSET_LEFT, currentVerticalPosition, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT);
		currentVerticalPosition += STANDARD_OBJECTS_HEIGHT + OFFSET_BETWEEN_OBJECTS;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		gridPainter.setRectangleColor(colorRectangles);
		gridPainter.setCircleColor(colorCircles);
		gridPainter.setLineColor(colorLines);
		gridPainter.setRectangleWidth(rectangleWidth);
		gridPainter.setRectangleHeight(rectangleHeight);
		gridPainter.setVerticalLineWidth(verticalLineWidth);
		gridPainter.setHorizontalLineHeight(horizontalLineHeight);
		gridPainter.paintGrid(g, OFFSET_LEFT + CONTROLS_WIDTH, OFFSET_TOP, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setRadioButtonsToFalse();
		setVisibilityOfWidthAndHeightComponents(true);
		if (e.getSource() == radioButtonRectangles) {
			radioButtonRectangles.setSelected(true);
			setParameterLabelTexts(LABEL_RECTANGLE_WIDTH_TEXT, LABEL_RECTANGLE_HEIGHT_TEXT);
		} else if (e.getSource() == radioButtonLines) {
			radioButtonLines.setSelected(true);
			setParameterLabelTexts(LABEL_LINE_WIDTH_TEXT, LABEL_LINE_HEIGHT_TEXT);
		} else if (e.getSource() == radioButtonCircles) {
			radioButtonCircles.setSelected(true);
			setVisibilityOfWidthAndHeightComponents(false);
		}
		updateSlidersAndTextFields();
		doLayout();
	}

	private void setRadioButtonsToFalse() {
		radioButtonRectangles.setSelected(false);
		radioButtonLines.setSelected(false);
		radioButtonCircles.setSelected(false);
	}

	private void setParameterLabelTexts(String lineWidthText, String lineHeightText) {
		labelWidth.setText(lineWidthText);
		labelHeight.setText(lineHeightText);
	}

	private void setVisibilityOfWidthAndHeightComponents(boolean visible) {
		labelWidth.setVisible(visible);
		labelHeight.setVisible(visible);
		textFieldWidth.setVisible(visible);
		textFieldHeight.setVisible(visible);
		sliderWidth.setVisible(visible);
		sliderHeight.setVisible(visible);
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		System.out.println("state changed");
		if (event.getSource() == sliderRed) {
			textFieldRed.setText(String.valueOf(sliderRed.getValue()));
		} else if (event.getSource() == sliderGreen) {
			textFieldGreen.setText(String.valueOf(sliderGreen.getValue()));
		} else if (event.getSource() == sliderBlue) {
			textFieldBlue.setText(String.valueOf(sliderBlue.getValue()));
		} else if (event.getSource() == sliderWidth) {
			textFieldWidth.setText(String.valueOf(sliderWidth.getValue()));
		} else if (event.getSource() == sliderHeight) {
			textFieldHeight.setText(String.valueOf(sliderHeight.getValue()));
		}
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		System.out.println("---insert update---");
		updateParameters();
		repaint();
	}

	private void updateParameters() {
		if (radioButtonRectangles.isSelected()) {
			colorRectangles = readColorFromFields();
			rectangleHeight = Integer.parseInt(textFieldHeight.getText());
			rectangleWidth = Integer.parseInt(textFieldWidth.getText());
		} else if (radioButtonLines.isSelected()) {
			colorLines = readColorFromFields();
			horizontalLineHeight = Integer.parseInt(textFieldHeight.getText());
			verticalLineWidth = Integer.parseInt(textFieldWidth.getText());
		} else if (radioButtonCircles.isSelected()) {
			colorCircles = readColorFromFields();
		}
	}

	private Color readColorFromFields() {
		return new Color(Integer.parseInt(textFieldRed.getText()), Integer.parseInt(textFieldGreen.getText()),
		    Integer.parseInt(textFieldBlue.getText()));
	}

	private void updateSlidersAndTextFields() {
		Color color;
		int width;
		int height;
		if (radioButtonCircles.isSelected()) {
			color = colorCircles;
			width = 0;
			height = 0;
		} else if (radioButtonLines.isSelected()) {
			color = colorLines;
			width = verticalLineWidth;
			height = horizontalLineHeight;
		} else if (radioButtonRectangles.isSelected()) {
			color = colorRectangles;
			width = rectangleWidth;
			height = rectangleHeight;
		} else {
			throw new IllegalStateException("No radio button selected.");
		}
		sliderRed.setValue(color.getRed());
		sliderGreen.setValue(color.getGreen());
		sliderBlue.setValue(color.getBlue());
		sliderWidth.setValue(width);
		sliderHeight.setValue(height);
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
	}
}
