package org.brsu.exercise5.control;

import org.brsu.exercise5.model.Board;

/**
 * Class to solve the tiler problem
 * 
 * @author Bastian Lang
 * 
 */
public class Solver {

	private int iterationCount = 0;
	private int[] rowConstraints;
	private int[] columnConstraints;

	public Board solve(int[] rowConstraints, int[] columnConstraints) {

		this.rowConstraints = rowConstraints;
		this.columnConstraints = columnConstraints;
		return computeSolution(new Board(columnConstraints.length, rowConstraints.length), 0, rowConstraints.length
		    * columnConstraints.length);
	}

	private Board computeSolution(Board board, int depth, int numberOfTiles) {
		// All tiles set, check if configuration is valid
		if (depth >= numberOfTiles) {
			iterationCount++;
			if (new BoardValidator(board, rowConstraints, columnConstraints).validateTiles()) {
				System.out.println(String.format("Solution found after %d iterations.", iterationCount));
				return board;
			}
			return null;
		}

		// Compute current row and column
		int currentRow = depth / columnConstraints.length;
		int currentColumn = depth % columnConstraints.length;

		// check constraints
		Board result = null;
		boolean blackPossible = checkIfBlackPossible(board, currentColumn, currentRow);
		boolean hasToBeBlack = hasToBeBlack(board, currentColumn, currentRow);
		if (hasToBeBlack && !blackPossible) {
			return null;
		}
		// set black
		if (blackPossible) {
			Board blackBoard = new Board(board);
			blackBoard.setTile(currentColumn, currentRow, true);
			result = computeSolution(blackBoard, depth + 1, numberOfTiles);
			if (result != null) {
				return result;
			}
		}
		// set white
		if (!hasToBeBlack) {
			// set white tile only if possible wrt constraints
			Board whiteBoard = new Board(board);
			whiteBoard.setTile(currentColumn, currentRow, false);
			result = computeSolution(whiteBoard, depth + 1, numberOfTiles);
		}
		return result;
	}

	private boolean hasToBeBlack(Board board, int currentColumn, int currentRow) {
		// Only one black to the left?
		int counter = 0;
		for (int i = currentColumn - 1; i >= 0; i--) {
			if (!board.getTile(i, currentRow) || counter >= 2) {
				break;
			} else {
				counter++;
			}
		}
		if (counter == 1) {
			return true;
		}

		// Only one black to the top?
		counter = 0;
		for (int i = currentRow - 1; i >= 0; i--) {
			if (!board.getTile(currentColumn, i) || counter >= 2) {
				break;
			} else {
				counter++;
			}
		}
		if (counter == 1) {
			return true;
		}
		return false;
	}

	private boolean checkIfBlackPossible(Board board, int currentColumn, int currentRow) {
		// Not possible if row constrained fullfilled
		int numberOfBlackTilesInRow = board.getNumberOfBlackTilesInRow(currentRow);
		if (rowConstraints[currentRow] <= numberOfBlackTilesInRow) {
			return false;
		}

		// Not possible if above white, but diagonal black
		if (currentRow == 0) {
			// First row -> okay
			return true;
		}
		if (board.getTile(currentColumn, currentRow - 1)) {
			// black above -> okay
			return true;
		}
		if (currentColumn > 0 && board.getTile(currentColumn - 1, currentRow - 1)) {
			// top left black -> not okay
			return false;
		}
		if (currentColumn + 1 < columnConstraints.length && board.getTile(currentColumn + 1, currentRow - 1)) {
			// top right black -> not okay
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// 3x3
		// int[] rowConstraints = { 2, 2, 0 };
		// int[] columnConstraints = { 0, 2, 2 };
		// 4x5
		// int[] rowConstraints = { 2, 2, 0, 3, 3 };
		// int[] columnConstraints = { 2, 4, 2, 2 };
		// 6x6
		int[] rowConstraints = { 4, 4, 0, 5, 5, 5 };
		int[] columnConstraints = { 5, 5, 3, 2, 5, 3 };

		// 10x10
		// int[] rowConstraints = { 7, 7, 7, 4, 4, 6, 2, 8, 6, 2 };
		// int[] columnConstraints = { 3, 7, 4, 6, 6, 2, 7, 5, 8, 5 };
		System.out.println(new Solver().solve(rowConstraints, columnConstraints));
	}

}
