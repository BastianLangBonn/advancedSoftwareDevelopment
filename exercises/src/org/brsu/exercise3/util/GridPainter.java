package org.brsu.exercise3.util;

import java.awt.Color;
import java.awt.Graphics;

public class GridPainter {

	private Color rectangleColor;
	private Color lineColor;
	private int horizontalLineHeight;
	private int rectangleHeight;
	private int verticalLineWidth;
	private int rectangleWidth;
	private Color circleColor;

	public GridPainter() {
		rectangleColor = new Color(0, 0, 0);
		lineColor = new Color(127, 127, 127);
		circleColor = new Color(255, 255, 255);
		rectangleHeight = 50;
		rectangleWidth = 50;
		horizontalLineHeight = 10;
		verticalLineWidth = 10;
	}

	public void paintGrid(Graphics g, int imageX, int imageY, int width, int height) {
		g.setColor(rectangleColor);
		g.fillRect(imageX, imageY, width, height);
		g.setColor(lineColor);
		drawHorizontalLines(g, imageX, imageY, width, height);
		drawVerticalLines(g, imageX, imageY, width, height);
		g.setColor(circleColor);
		drawCircles(g, imageX, imageY, width, height);

	}

	private void drawCircles(Graphics g, int imageX, int imageY, int width, int height) {
		int x = imageX + rectangleWidth;
		while (x + verticalLineWidth < imageX + width) {
			int y = imageY + rectangleHeight;
			while (y + horizontalLineHeight < imageY + height) {
				g.fillArc(x, y, verticalLineWidth, horizontalLineHeight, 0, 360);
				y += horizontalLineHeight;
				y += rectangleHeight;
			}
			x += verticalLineWidth;
			x += rectangleWidth;
		}
	}

	private void drawVerticalLines(Graphics g, int imageX, int imageY, int width, int height) {
		int x = imageX + rectangleWidth;
		while (x + verticalLineWidth < width + imageX) {
			g.fillRect(x, imageY, verticalLineWidth, height);
			x += verticalLineWidth;
			x += rectangleWidth;
		}
	}

	private void drawHorizontalLines(Graphics g, int imageX, int imageY, int width, int height) {
		int y = imageY + rectangleHeight;
		while (y + horizontalLineHeight < height + imageY) {
			g.fillRect(imageX, y, width, horizontalLineHeight);
			y += horizontalLineHeight;
			y += rectangleHeight;
		}
	}

	public void setRectangleColor(Color colorRectangles) {
		rectangleColor = colorRectangles;
	}

	public void setCircleColor(Color colorCircles) {
		circleColor = colorCircles;
	}

	public void setLineColor(Color colorLines) {
		lineColor = colorLines;
	}

	public void setRectangleWidth(int rectangleWidth) {
		this.rectangleWidth = rectangleWidth;
	}

	public void setRectangleHeight(int rectangleHeight) {
		this.rectangleHeight = rectangleHeight;
	}

	public void setVerticalLineWidth(int verticalLineWidth) {
		this.verticalLineWidth = verticalLineWidth;
	}

	public void setHorizontalLineHeight(int horizontalLineHeight) {
		this.horizontalLineHeight = horizontalLineHeight;
	}
}
