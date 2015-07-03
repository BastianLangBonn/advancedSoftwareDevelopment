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

import org.brsu.exercise3.util.ObjectCreator;
import org.brsu.exercise3.util.ParallelLinesPainter;

public class ImageTwoPanel extends JPanel implements ActionListener, ChangeListener, DocumentListener {
	private static final long serialVersionUID = 1L;

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

	private ParallelLinesPainter parallelLinesPainter;
	private ObjectCreator objectCreator;

	private JSlider sliderRed;
	private JSlider sliderBlue;
	private JSlider sliderGreen;
	private JSlider sliderRectangleWidth;
	private JSlider sliderRectangleHeight;
	private JSlider sliderLineWidth;
	private JSlider sliderOffset;

	private JTextField textFieldRed;
	private JTextField textFieldGreen;
	private JTextField textFieldBlue;
	private JTextField textFieldRectangleWidth;
	private JTextField textFieldRectangleHeight;
	private JTextField textFieldLineWidth;
	private JTextField textFieldOffset;

	private JLabel labelRed;
	private JLabel labelGreen;
	private JLabel labelBlue;
	private JLabel labelRectangleWidth;
	private JLabel labelRectangleHeight;
	private JLabel labelLineWidth;
	private JLabel labelOffset;

	private JRadioButton radioButtonSecondRectangles;
	private JRadioButton radioButtonLines;
	private JRadioButton radioButtonFirstRectangles;

	private Color colorFirstRectangles = new Color(255, 255, 255);
	private Color colorLines = new Color(127, 127, 127);
	private Color colorSecondRectangles = new Color(0, 0, 0);
	private Color selectedColor = colorFirstRectangles;

	private int currentVerticalPosition = 0;
	private int rectangleWidth = 40;
	private int rectangleHeight = 40;
	private int lineWidth = 5;
	private int offset = 5;

	public ImageTwoPanel() {
		parallelLinesPainter = new ParallelLinesPainter();
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
		textFieldRectangleWidth = objectCreator.createSingleTextField(String.valueOf(rectangleWidth), this, this,
		    STANDARD_OBJECTS_HEIGHT);
		add(textFieldRectangleWidth);
		textFieldRectangleHeight = objectCreator.createSingleTextField(String.valueOf(rectangleHeight), this, this,
		    STANDARD_OBJECTS_HEIGHT);
		add(textFieldRectangleHeight);
		textFieldLineWidth = objectCreator.createSingleTextField(String.valueOf(lineWidth), this, this,
		    STANDARD_OBJECTS_HEIGHT);
		add(textFieldLineWidth);
		textFieldOffset = objectCreator.createSingleTextField(String.valueOf(offset), this, this, STANDARD_OBJECTS_HEIGHT);
		add(textFieldOffset);

	}

	private void initializeRadioButtons() {
		radioButtonSecondRectangles = objectCreator.createSingleRadioButton("Rectangles I", this);
		radioButtonSecondRectangles.setSelected(true);
		add(radioButtonSecondRectangles);
		radioButtonLines = objectCreator.createSingleRadioButton("Lines", this);
		add(radioButtonLines);
		radioButtonFirstRectangles = objectCreator.createSingleRadioButton("Rectangles II", this);
		add(radioButtonFirstRectangles);
	}

	private void initializeLabels() {
		labelRed = objectCreator.createSingleLabel("RED", LABEL_SMALL_WIDTH, STANDARD_OBJECTS_HEIGHT);
		add(labelRed);
		labelGreen = objectCreator.createSingleLabel("GREEN", LABEL_SMALL_WIDTH, STANDARD_OBJECTS_HEIGHT);
		add(labelGreen);
		labelBlue = objectCreator.createSingleLabel("BLUE", LABEL_SMALL_WIDTH, STANDARD_OBJECTS_HEIGHT);
		add(labelBlue);
		labelRectangleWidth = objectCreator.createSingleLabel("Rectangle Width", LABEL_BIG_WIDHT, STANDARD_OBJECTS_HEIGHT);
		add(labelRectangleWidth);
		labelRectangleHeight = objectCreator
		    .createSingleLabel("Rectangle Height", LABEL_BIG_WIDHT, STANDARD_OBJECTS_HEIGHT);
		add(labelRectangleHeight);
		labelLineWidth = objectCreator.createSingleLabel("Line Width", LABEL_BIG_WIDHT, STANDARD_OBJECTS_HEIGHT);
		add(labelLineWidth);
		labelOffset = objectCreator.createSingleLabel("Offset", LABEL_BIG_WIDHT, STANDARD_OBJECTS_HEIGHT);
		add(labelOffset);
	}

	private void initializeSliders() {
		sliderBlue = objectCreator.createSingleSlider(this, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT, 0, 255);
		add(sliderBlue);
		sliderGreen = objectCreator.createSingleSlider(this, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT, 0, 255);
		add(sliderGreen);
		sliderRed = objectCreator.createSingleSlider(this, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT, 0, 255);
		add(sliderRed);
		sliderRectangleWidth = objectCreator.createSingleSlider(this, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT, 0, 100);
		add(sliderRectangleWidth);
		sliderRectangleHeight = objectCreator.createSingleSlider(this, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT, 0, 100);
		add(sliderRectangleHeight);
		sliderLineWidth = objectCreator.createSingleSlider(this, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT, 0, 100);
		add(sliderLineWidth);
		sliderOffset = objectCreator.createSingleSlider(this, SLIDER_WIDTH, STANDARD_OBJECTS_HEIGHT, 0, 100);
		add(sliderOffset);
	}

	@Override
	public void doLayout() {
		currentVerticalPosition = OFFSET_TOP;

		positionGroup(labelRed, textFieldRed, sliderRed);
		positionGroup(labelGreen, textFieldGreen, sliderGreen);
		positionGroup(labelBlue, textFieldBlue, sliderBlue);

		radioButtonSecondRectangles.setBounds(OFFSET_LEFT, currentVerticalPosition, RADIO_BUTTON_WIDTH,
		    STANDARD_OBJECTS_HEIGHT);
		radioButtonLines.setBounds(OFFSET_LEFT + RADIO_BUTTON_WIDTH, currentVerticalPosition, RADIO_BUTTON_WIDTH,
		    STANDARD_OBJECTS_HEIGHT);
		currentVerticalPosition += STANDARD_OBJECTS_HEIGHT;
		radioButtonFirstRectangles.setBounds(OFFSET_LEFT, currentVerticalPosition, RADIO_BUTTON_WIDTH,
		    STANDARD_OBJECTS_HEIGHT);
		currentVerticalPosition += STANDARD_OBJECTS_HEIGHT;
		currentVerticalPosition += STANDARD_OBJECTS_HEIGHT;

		positionGroup(labelRectangleWidth, textFieldRectangleWidth, sliderRectangleWidth);
		positionGroup(labelRectangleHeight, textFieldRectangleHeight, sliderRectangleHeight);
		positionGroup(labelLineWidth, textFieldLineWidth, sliderLineWidth);
		positionGroup(labelOffset, textFieldOffset, sliderOffset);
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
		parallelLinesPainter.setFirstRectangleColor(colorFirstRectangles);
		parallelLinesPainter.setSecondRectangleColor(colorSecondRectangles);
		parallelLinesPainter.setLineColor(colorLines);
		parallelLinesPainter.setRectangleWidth(rectangleWidth);
		parallelLinesPainter.setRectangleHeight(rectangleHeight);
		parallelLinesPainter.setLineWidth(lineWidth);
		parallelLinesPainter.setOffset(offset);
		parallelLinesPainter.paintImage(g, OFFSET_LEFT + CONTROLS_WIDTH, OFFSET_TOP, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setRadioButtonsToFalse();
		if (e.getSource() == radioButtonSecondRectangles) {
			radioButtonSecondRectangles.setSelected(true);
		} else if (e.getSource() == radioButtonLines) {
			radioButtonLines.setSelected(true);
		} else if (e.getSource() == radioButtonFirstRectangles) {
			radioButtonFirstRectangles.setSelected(true);
		}
		updateSlidersAndTextFields();
		doLayout();
	}

	private void setRadioButtonsToFalse() {
		radioButtonSecondRectangles.setSelected(false);
		radioButtonLines.setSelected(false);
		radioButtonFirstRectangles.setSelected(false);
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
		} else if (event.getSource() == sliderRectangleWidth) {
			textFieldRectangleWidth.setText(String.valueOf(sliderRectangleWidth.getValue()));
		} else if (event.getSource() == sliderRectangleHeight) {
			textFieldRectangleHeight.setText(String.valueOf(sliderRectangleHeight.getValue()));
		} else if (event.getSource() == sliderLineWidth) {
			textFieldLineWidth.setText(String.valueOf(sliderLineWidth.getValue()));
		} else if (event.getSource() == sliderOffset) {
			textFieldOffset.setText(String.valueOf(sliderOffset.getValue()));
		}
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		System.out.println("---insert update---");
		updateParameters();
		repaint();
	}

	private void updateParameters() {
		if (radioButtonSecondRectangles.isSelected()) {
			colorFirstRectangles = readColorFromFields();
		} else if (radioButtonLines.isSelected()) {
			colorLines = readColorFromFields();
		} else if (radioButtonFirstRectangles.isSelected()) {
			colorSecondRectangles = readColorFromFields();
		}
		rectangleHeight = Integer.parseInt(textFieldRectangleHeight.getText());
		rectangleWidth = Integer.parseInt(textFieldRectangleWidth.getText());
		offset = Integer.parseInt(textFieldOffset.getText());
		lineWidth = Integer.parseInt(textFieldLineWidth.getText());
	}

	private Color readColorFromFields() {
		return new Color(Integer.parseInt(textFieldRed.getText()), Integer.parseInt(textFieldGreen.getText()),
		    Integer.parseInt(textFieldBlue.getText()));
	}

	private void updateSlidersAndTextFields() {
		Color color;
		if (radioButtonFirstRectangles.isSelected()) {
			color = colorSecondRectangles;
		} else if (radioButtonLines.isSelected()) {
			color = colorLines;
		} else if (radioButtonSecondRectangles.isSelected()) {
			color = colorFirstRectangles;
		} else {
			throw new IllegalStateException("No radio button selected.");
		}
		sliderRed.setValue(color.getRed());
		sliderGreen.setValue(color.getGreen());
		sliderBlue.setValue(color.getBlue());
		sliderRectangleWidth.setValue(rectangleWidth);
		sliderRectangleHeight.setValue(rectangleHeight);
		sliderLineWidth.setValue(lineWidth);
		sliderOffset.setValue(offset);
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
	}
}
