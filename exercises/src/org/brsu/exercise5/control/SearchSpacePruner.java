/*
 * Author : Nitish Reddy Koripalli
 * Date : 20th May 2015
 */

package org.brsu.exercise5.control;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchSpacePruner {

	/**
	 * Constructor
	 */
	public SearchSpacePruner() {

	}

	/**
	 * Apply Minimum Side Constraint on SearchSpace
	 * 
	 * @param searchSpace
	 * @param minimumSide
	 */
	public void applyMinimumSideConstraint(SearchSpace searchSpace,
	    int minimumSide) {

		for (ArrayList<Integer[]> axisSearchSpace : searchSpace.searchSpace) {
			applyMinimumSideConstraint(axisSearchSpace, minimumSide);
		}
	}

	/**
	 * Apply Minimum Side Constraint on AxisSearchSpace
	 * 
	 * PRUNING happens only in this function.
	 * 
	 * @param axisSearchSpace
	 * @param minimumSide
	 */
	private void applyMinimumSideConstraint(ArrayList<Integer[]> axisSearchSpace,
	    int minimumSide) {

		// iterate though all axisSearchNodes in axisSearchSpace and apply
		// minimumSide constraint. If constraint fails, then remove the
		// axisSearchNode.
		Iterator<Integer[]> axisSearchSpaceIterator = axisSearchSpace.iterator();
		while (axisSearchSpaceIterator.hasNext()) {
			Integer[] axisSearchNode = axisSearchSpaceIterator.next();
			if (applyMinimumSideConstraint(axisSearchNode, minimumSide) == false) {
				// prune
				// System.out.format("MinSide Prune : ");
				// Display.displayAxisSearchNode(axisSearchNode);
				axisSearchSpaceIterator.remove();
			}
		}
	}

	/**
	 * Apply Minimum Side Constraint on AxisSearchNode
	 * 
	 * If there are any Black Blocks that are less than minimum side then prune
	 * 
	 * @param axisSearchNode
	 * @param minimumSide
	 */
	private boolean applyMinimumSideConstraint(Integer[] axisSearchNode,
	    int minimumSide) {

		int sideCount = 0;

		for (int i = 0; i < axisSearchNode.length; i++) {
			// if the tile is Black, then increase count
			if (axisSearchNode[i] == SearchSpace.BLACK) {
				sideCount += 1;
			}
			// if the tile is WHITE, then check for constraint
			else {
				// if the sideCount was between zero and minimumSide, then prune the
				// axisSearchNode
				if ((sideCount > 0) && (sideCount < minimumSide)) {
					return false;
				}
				sideCount = 0;
			}
		}

		// this is an extreme condition re-check
		if ((sideCount > 0) && (sideCount < minimumSide)) {
			return false;
		}

		return true;
	}

	/**
	 * Apply Adjacent Sum Constraint on SearchSpace
	 * 
	 * @param searchSpace
	 * @param axisLength
	 * @param axisConstraints
	 */
	public void applyAdjacentSumConstraint(SearchSpace searchSpace,
	    int[] axisConstraints) {

		for (int i = 0; i < searchSpace.searchSpace.size(); i++) {
			ArrayList<Integer[]> axisSearchSpace = searchSpace.searchSpace.get(i);
			applyAdjacentSumConstraint(axisSearchSpace, axisConstraints, i);
		}
	}

	/**
	 * Apply Adjacent Sum Constraint on AxisSearchSpace
	 * 
	 * Pruning happens only in this function.
	 * 
	 * @param axisSearchSpace
	 * @param axisLength
	 * @param axisConstraints
	 * @param axisIndex
	 */
	private void applyAdjacentSumConstraint(ArrayList<Integer[]> axisSearchSpace,
	    int[] axisConstraints, int axisIndex) {

		Iterator<Integer[]> axisSearchSpaceIterator = axisSearchSpace.iterator();
		while (axisSearchSpaceIterator.hasNext()) {
			Integer[] axisSearchNode = axisSearchSpaceIterator.next();
			if (applyAdjacentSumConstraint(axisSearchNode, axisConstraints, axisIndex) == false) {

				// prune
				// System.out.format("AdjSum Prune : ");
				// Display.displayAxisSearchNode(axisSearchNode);
				axisSearchSpaceIterator.remove();
			}
		}
	}

	/**
	 * Apply Adjacent Sum Constraint on AxisSearchNode
	 * 
	 * @param axisSearchNode
	 * @param axisLength
	 * @param axisConstraints
	 * @param axisIndex
	 * @return
	 */
	private boolean applyAdjacentSumConstraint(Integer[] axisSearchNode,
	    int[] axisConstraints, int axisIndex) {

		int numberOfAxes = axisConstraints.length;
		int maximumSide = getMaximumSideOfAxisSearchNode(axisSearchNode);
		int compareIndex = 0;
		int axisLength = axisSearchNode.length;

		if (axisIndex > 0) {
			compareIndex = axisIndex - 1;
			if (maximumSide > axisConstraints[compareIndex]) {
				if ((maximumSide + axisConstraints[compareIndex] + 1) > axisLength) {
					// System.out.format("Index:%d Max:%d Cmp:%d - ", axisIndex,
					// maximumSide, axisConstraints[compareIndex]);
					return false;
				}
			}
		}

		if (axisIndex < (numberOfAxes - 1)) {
			compareIndex = axisIndex + 1;
			if (maximumSide > axisConstraints[compareIndex]) {
				if ((maximumSide + axisConstraints[compareIndex] + 1) > axisLength) {
					// System.out.format("Index:%d Max:%d Cmp:%d - ", axisIndex,
					// maximumSide, axisConstraints[compareIndex]);
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Private helper function to get length of maximum continuous block of Black
	 * Tiles.
	 * 
	 * @param axisSearchNode
	 * @return
	 */
	private int getMaximumSideOfAxisSearchNode(Integer[] axisSearchNode) {
		int maxCount = 0;
		int count = 0;

		for (int i = 0; i < axisSearchNode.length; i++) {
			if (axisSearchNode[i] == SearchSpace.BLACK) {
				count += 1;
				if (maxCount < count) {
					maxCount = count;
				}
			} else {
				count = 0;
			}
		}
		// extreme case
		if (maxCount < count) {
			maxCount = count;
		}
		return maxCount;
	}

	/**
	 * Apply Adjacent Exhaustive Constraint on SearchSpace
	 * 
	 * Check every axisSearchNode in the SearchSpace if it satisfies certain
	 * constraints with all its adjacentAxisSearchSpace.
	 * 
	 * @param searchSpace
	 */
	public void applyAdjacentExhaustiveConstraint(SearchSpace searchSpace) {
		int numberOfAxes = searchSpace.searchSpace.size();
		for (int axisIndex = 0; axisIndex < numberOfAxes; axisIndex++) {
			ArrayList<Integer[]> axisSearchSpace = searchSpace.searchSpace
			    .get(axisIndex);
			applyAdjacentExhaustiveConstraint(searchSpace, axisSearchSpace,
			    axisIndex, numberOfAxes);
		}
	}

	/**
	 * Apply Adjacent Exhaustive Constraint on AxisSearchSpace
	 * 
	 * PRUNING takes place in this function
	 * 
	 * @param searchSpace
	 * @param axisSearchSpace
	 * @param axisIndex
	 * @param numberOfAxes
	 */
	private void applyAdjacentExhaustiveConstraint(SearchSpace searchSpace,
	    ArrayList<Integer[]> axisSearchSpace, int axisIndex, int numberOfAxes) {

		Iterator<Integer[]> axisSearchSpaceIterator = axisSearchSpace.iterator();

		while (axisSearchSpaceIterator.hasNext()) {
			Integer[] axisSearchNode = axisSearchSpaceIterator.next();
			if (applyAdjacentExhaustiveConstraint(searchSpace, axisSearchNode,
			    axisIndex, numberOfAxes) == false) {
				// System.out.format("AdjExh Prune : ");
				// Display.displayAxisSearchNode(axisSearchNode);

				axisSearchSpaceIterator.remove();
			}
		}
	}

	/**
	 * Apply Adjacent Exhaustive Constraint on AxisSearchNode
	 * 
	 * @param searchSpace
	 * @param axisSearchNode
	 * @param axisIndex
	 * @param numberOfAxes
	 * @return
	 */
	private boolean applyAdjacentExhaustiveConstraint(SearchSpace searchSpace,
	    Integer[] axisSearchNode, int axisIndex, int numberOfAxes) {

		int compareIndex = 0;
		ArrayList<Integer[]> adjacentSearchSpace;

		if (axisIndex > 0) {
			compareIndex = axisIndex - 1;
			adjacentSearchSpace = searchSpace.searchSpace.get(compareIndex);
			if (compareAxisSearchNodeToAdjacentSearchSpace(axisSearchNode,
			    adjacentSearchSpace) == false) {
				return false;
			}
		}

		if (axisIndex < (numberOfAxes - 1)) {
			compareIndex = axisIndex + 1;
			adjacentSearchSpace = searchSpace.searchSpace.get(compareIndex);
			if (compareAxisSearchNodeToAdjacentSearchSpace(axisSearchNode,
			    adjacentSearchSpace) == false) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Compare axisSearchNode with each adjacentSearchNode in the
	 * adjacentSearchSpace
	 * 
	 * @param axisSearchNode
	 * @param adjacentSearchSpace
	 * @return
	 */
	private boolean compareAxisSearchNodeToAdjacentSearchSpace(
	    Integer[] axisSearchNode, ArrayList<Integer[]> adjacentSearchSpace) {

		// if there is even one success, then let the axisSearchNode succeed
		// System.out.format("Adjacent Search Space Size : %d\n",
		// adjacentSearchSpace.size());
		for (Integer[] adjacentSearchNode : adjacentSearchSpace) {
			if (compareAxisSearchNodeToAdjacentSearchNode(axisSearchNode,
			    adjacentSearchNode) == true) {
				// System.out.println("AdjSearchNode Success");
				return true;
			}
			// System.out.println("AdjSearchNode Fail");
		}
		// System.out.println("AdjSearchSpace Fail");
		return false;
	}

	/**
	 * Compare axisSearchNode with an adjacentSearchNode
	 * 
	 * @param axisSearchNode
	 * @param adjacentSearchNode
	 * @return
	 */
	private boolean compareAxisSearchNodeToAdjacentSearchNode(
	    Integer[] axisSearchNode, Integer[] adjacentSearchNode) {

		// System.out.format("AdjExh Axis : ");
		// Display.displayAxisSearchNode(axisSearchNode);
		// System.out.format("AdjExh Adjc : ");
		// Display.displayAxisSearchNode(adjacentSearchNode);

		boolean lastTimeBothBlack = false;
		boolean lastTimeBothWhite = true;
		boolean lastTimeAlternateWhiteBlack = false;
		boolean lastTimeAlternateBlackWhite = false;

		for (int i = 0; i < axisSearchNode.length; i++) {
			// if both are black;
			if ((adjacentSearchNode[i] == SearchSpace.BLACK)
			    && (axisSearchNode[i] == SearchSpace.BLACK)) {
				if (lastTimeBothBlack == true) {
					// no problem
				}
				if (lastTimeBothWhite == true) {
					// no problem
				}
				if (lastTimeAlternateBlackWhite == true) {
					// bad
					// System.out.println("BB-BW-F");
					return false;
				}
				if (lastTimeAlternateWhiteBlack == true) {
					// bad
					// System.out.println("BB-WB-F");
					return false;
				}

				lastTimeBothBlack = true;
				lastTimeBothWhite = false;
				lastTimeAlternateWhiteBlack = false;
				lastTimeAlternateBlackWhite = false;
			} // if both are white;
			else if ((adjacentSearchNode[i] == SearchSpace.WHITE)
			    && (axisSearchNode[i] == SearchSpace.WHITE)) {
				if (lastTimeBothBlack == true) {
					// no problem
				}
				if (lastTimeBothWhite == true) {
					// no problem
				}
				if (lastTimeAlternateWhiteBlack == true) {
					// no problem
				}
				if (lastTimeAlternateBlackWhite == true) {
					// no problem
				}
				lastTimeBothBlack = false;
				lastTimeBothWhite = true;
				lastTimeAlternateWhiteBlack = false;
				lastTimeAlternateBlackWhite = false;
			}
			// if alternating - BlackWhite
			else if ((adjacentSearchNode[i] == SearchSpace.BLACK)
			    && (axisSearchNode[i] == SearchSpace.WHITE)) {
				if (lastTimeBothBlack == true) {
					// bad
					// System.out.println("BW-BB-F");
					return false;
				}
				if (lastTimeBothWhite == true) {
					// no problem
				}
				if (lastTimeAlternateBlackWhite == true) {
					// no problem
				}
				if (lastTimeAlternateWhiteBlack == true) {
					// bad
					// System.out.println("BW-WB-F");
					return false;
				}
				lastTimeBothBlack = false;
				lastTimeBothWhite = false;
				lastTimeAlternateBlackWhite = true;
				lastTimeAlternateWhiteBlack = false;
			}
			// if alternating - WhiteBlack
			else if ((adjacentSearchNode[i] == SearchSpace.WHITE)
			    && (axisSearchNode[i] == SearchSpace.BLACK)) {
				if (lastTimeBothBlack == true) {
					// bad
					// System.out.println("WB-BB-F");
					return false;
				}
				if (lastTimeBothWhite == true) {
					// no problem
				}
				if (lastTimeAlternateBlackWhite == true) {
					// bad
					// System.out.println("WB-BW-F");
					return false;
				}
				if (lastTimeAlternateWhiteBlack == true) {
					// no problem
				}
				lastTimeBothBlack = false;
				lastTimeBothWhite = false;
				lastTimeAlternateBlackWhite = false;
				lastTimeAlternateWhiteBlack = true;
			}
		}
		return true;
	}

	/*
	 * Main : Testing
	 */
	public static void main(String[] args) {
		SearchSpacePruner ssp = new SearchSpacePruner();
		Integer[] one = { 1, 1, 1, 0, 0, 0, 0 };
		Integer[] two = { 1, 1, 1, 0, 1, 1, 1 };
		System.out.println(ssp.compareAxisSearchNodeToAdjacentSearchNode(one, two));
	}
}
