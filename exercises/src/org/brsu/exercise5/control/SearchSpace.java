/*
 * Author : Nitish Reddy Koripalli
 * Date : 20th May 2015
 */

package org.brsu.exercise5.control;

import java.util.ArrayList;

public class SearchSpace {

	public static final int WHITE = 0;
	public static final int BLACK = 1;

	public static final int NODE_TYPE_ROW = 0;
	public static final int NODE_TYPE_COLUMN = 1;

	public ArrayList<ArrayList<Integer[]>> searchSpace = new ArrayList<ArrayList<Integer[]>>();

	private int iteratorAxisSearchSpaceIndex = 0;
	private int iteratorAxisSearchNodeIndex = 0;
	private boolean iteratorHasNextFlag = true;

	private int numberOfAxes = 0;
	private int axisLength = 0;

	private boolean[][] booleanSearchNode;

	/**
	 * Default Constructor
	 */
	public SearchSpace() {

	}

	/**
	 * Constructor
	 */
	public SearchSpace(int axisLength, int numberOfAxes) {
		this.axisLength = axisLength;
		this.numberOfAxes = numberOfAxes;
	}

	/**
	 * Add an axisSearchSpace into searchSpace
	 * 
	 * @param axisSearchSpace
	 */
	public void addAxisSearchSpace(ArrayList<Integer[]> axisSearchSpace) {
		searchSpace.add(axisSearchSpace);
	}

	/**
	 * Reset the iterator indexes, flags and the booleanSpace
	 * 
	 * @param nodeType
	 */
	public void resetIterator() {
		this.iteratorAxisSearchSpaceIndex = 0;
		this.iteratorAxisSearchNodeIndex = 0;
		this.iteratorHasNextFlag = true;

		this.booleanSearchNode = new boolean[this.axisLength][this.numberOfAxes];
		updateBooleanSearchNode();
	}

	/**
	 * Returns the next available BooleanSearchNode in the iterator
	 * 
	 * @return
	 */
	public boolean[][] getNextBooleanSearchNode() {
		updateNextIteratorIndexes();
		updateBooleanSearchNode();

		boolean[][] nextBooleanSearchNode = new boolean[this.booleanSearchNode.length][this.booleanSearchNode[0].length];
		for (int i = 0; i < this.booleanSearchNode.length; i++) {
			for (int j = 0; j < this.booleanSearchNode[0].length; j++) {
				nextBooleanSearchNode[i][j] = this.booleanSearchNode[i][j];
			}
		}

		return nextBooleanSearchNode;
	}

	/**
	 * Updates the iterator indexes within valid searchSpace limits
	 */
	private void updateNextIteratorIndexes() {
		// if iterator has a next valid space available
		if (this.iteratorHasNext() == true) {
			// valid axisSearchSpace index
			if (this.iteratorAxisSearchSpaceIndex < this.numberOfAxes) {
				ArrayList<Integer[]> axisSearchSpace = searchSpace
				    .get(this.iteratorAxisSearchSpaceIndex);
				// axisSearchNode index within limits
				if (this.iteratorAxisSearchNodeIndex < (axisSearchSpace.size() - 1)) {
					this.iteratorAxisSearchNodeIndex += 1;
				}
				// axisSearchNode beyond limit, move to next axisSearchSpaceIndex
				else {
					if (this.iteratorAxisSearchSpaceIndex < (this.numberOfAxes - 1)) {
						this.iteratorAxisSearchSpaceIndex += 1;
						this.iteratorAxisSearchNodeIndex = 0;
					} else {
						this.iteratorHasNextFlag = false;
					}
				}
			}
		}
	}

	/**
	 * Updates the booleanSearchNode to current iterator indexes
	 */
	private void updateBooleanSearchNode() {
		Integer[] axisSearchNode = this.searchSpace.get(
		    this.iteratorAxisSearchSpaceIndex)
		    .get(this.iteratorAxisSearchNodeIndex);

		for (int i = 0; i < this.axisLength; i++) {
			if (axisSearchNode[i] == BLACK) {
				this.booleanSearchNode[this.iteratorAxisSearchSpaceIndex][i] = true;
			} else {
				this.booleanSearchNode[this.iteratorAxisSearchSpaceIndex][i] = false;
			}
		}
	}

	/**
	 * Returns a boolean flag to indicate if there any more booleanSearchNodes to
	 * iterate through.
	 * 
	 * @return
	 */
	public boolean iteratorHasNext() {
		return this.iteratorHasNextFlag;
	}

	/**
	 * Displays the booleanSearchNode
	 */
	public void displayBooleanSearchNode() {
		System.out.format("Search Space : Axis-Index : %3d  Node-Index : %3d\n",
		    this.iteratorAxisSearchSpaceIndex, this.iteratorAxisSearchNodeIndex);
		for (int i = 0; i < this.booleanSearchNode.length; i++) {
			for (int j = 0; j < this.booleanSearchNode[0].length; j++) {
				if (this.booleanSearchNode[j][i] == true) {
					System.out.format("%2d ", BLACK);
				} else {
					System.out.format("%2d ", WHITE);
				}
			}
			System.out.println();
		}
	}
}
