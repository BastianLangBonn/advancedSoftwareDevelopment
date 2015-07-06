package org.brsu.exercise3.util;

import java.awt.Color;
import java.awt.Graphics;

public class ParallelLinesPainter {

	private int rectangleHeight = 40;
	private int rectangleWidth = 40;
	private int lineWidth = 5;
	private Color firstRectangleColor = new Color(255, 255, 255);
	private Color lineColor = new Color(127, 127, 127);
	private Color secondRectangleColor = new Color(0, 0, 0);
	private int offset = 15;

	public void paintImage(Graphics g, int startX, int startY, int imageWidth, int imageHeight) {
		g.setColor(firstRectangleColor);
		g.fillRect(startX, startY, imageWidth, imageHeight);
		drawLines(g, startX, startY, imageWidth, imageHeight);
	}

	private void drawLines(Graphics g, int startX, int startY, int imageWidth, int imageHeight) {
		g.setColor(lineColor);
		int x = startX;
		int y = startY + rectangleHeight;
		// Draw lines
		while (y + lineWidth < startY + imageHeight) {
			g.fillRect(x, y, imageWidth, lineWidth);
			y += rectangleHeight + lineWidth;
		}
		drawRectangles(g, startX, startY, imageWidth, imageHeight);
	}

	private void drawRectangles(Graphics g, int startX, int startY, int imageWidth, int imageHeight) {
		int x;
		int y;
		int row = 0;
		int step = 1;
		g.setColor(secondRectangleColor);
		y = startY;
		x = startX;
		while (y + rectangleHeight < startY + imageHeight) {
			x = fillRowWithRectangles(g, startX, imageWidth, x, y, rectangleHeight, row * offset);
			x = startX;
			y += rectangleHeight + lineWidth;
			row += step;
			if (row == 2 || row == 0) {
				step *= -1;
			}
		}
		x = fillRowWithRectangles(g, startX, imageWidth, x, y, startY + imageHeight - y, row * offset);
	}

	private int fillRowWithRectangles(Graphics g, int startX, int imageWidth, int x, int y, int currentRectangleHeight,
	    int lineOffset) {
		g.setColor(firstRectangleColor);
		g.fillRect(x, y, lineOffset, currentRectangleHeight);
		x += lineOffset;
		changeColor(g);
		while (x + rectangleWidth < startX + imageWidth) {
			g.fillRect(x, y, rectangleWidth, currentRectangleHeight);
			x += rectangleWidth;
			changeColor(g);
		}
		g.fillRect(x, y, imageWidth + startX - x, currentRectangleHeight);
		return x;
	}

	private void changeColor(Graphics g) {
		if (g.getColor() == secondRectangleColor) {
			g.setColor(firstRectangleColor);
		} else {
			g.setColor(secondRectangleColor);
		}
	}

	public void setFirstRectangleColor(Color firstRectangleColor) {
		this.firstRectangleColor = firstRectangleColor;
	}

	public void setSecondRectangleColor(Color colorSecondRectangles) {
		this.secondRectangleColor = colorSecondRectangles;
	}

	public void setLineColor(Color colorLines) {
		this.lineColor = colorLines;
	}

	public void setRectangleWidth(int rectangleWidth) {
		this.rectangleWidth = rectangleWidth;
	}

	public void setRectangleHeight(int rectangleHeight) {
		this.rectangleHeight = rectangleHeight;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
