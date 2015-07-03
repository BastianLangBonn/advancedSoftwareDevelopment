/*
 * Author : Bastian Lang, Nitish Reddy Koripalli
 * Date : 18th May 2015
 */
package org.brsu.exercise5.control;

public class ConfusedTiler {
	private static final int NO_OF_ROWS = 10;
	private static final int[] ROW_CONSTRAINTS = { 7, 7, 7, 4, 4, 6, 2, 8, 6, 2 };

	private static final int NO_OF_COLUMNS = 10;
	private static final int[] COLUMN_CONSTRAINTS = { 3, 7, 4, 6, 6, 2, 7, 5, 8,
	    5 };

	private static final int MINIMUM_SIDE_LENGTH = 2;

	private SearchSpace searchSpace;

	/**
	 * Default Constructor
	 */
	public ConfusedTiler() {
		;
	}

	/**
	 * Run the confused tiler problem
	 */
	public void run() {
		generateSearchSpace();
		pruneSearchSpace();
		validateSearchSpace();
	}

	/**
	 * Generates the searchSpace using the searchSpaceGenerator Class
	 */
	private void generateSearchSpace() {
		SearchSpaceGenerator searchSpaceGenerator = new SearchSpaceGenerator(
		    NO_OF_ROWS, COLUMN_CONSTRAINTS);
		// generate searchSpace
		this.searchSpace = searchSpaceGenerator.generateSearchSpace();
		Display.displayAxisSearchSpaceSizes(this.searchSpace);
	}

	/**
	 * Prunes the searchSpace using the searchSpacePruner class
	 */
	private void pruneSearchSpace() {
		SearchSpacePruner searchSpacePruner = new SearchSpacePruner();
		// prune searchSpace - minimumSide constraint
		searchSpacePruner.applyMinimumSideConstraint(this.searchSpace,
		    MINIMUM_SIDE_LENGTH);
		Display.displayAxisSearchSpaceSizes(this.searchSpace);

		// // prune searchSpace - adjacentSum constraint
		searchSpacePruner.applyAdjacentSumConstraint(this.searchSpace,
		    COLUMN_CONSTRAINTS);
		Display.displayAxisSearchSpaceSizes(this.searchSpace);

		// prune searchSpace - adjacentExhaustive constraint
		searchSpacePruner.applyAdjacentExhaustiveConstraint(this.searchSpace);
		Display.displayAxisSearchSpaceSizes(this.searchSpace);
	}

	private void validateSearchSpace() {
		this.searchSpace.resetIterator();
		while (this.searchSpace.iteratorHasNext()) {
			this.searchSpace.getNextBooleanSearchNode();
			this.searchSpace.displayBooleanSearchNode();
		}
	}

	public static void main(String[] args) {
		ConfusedTiler confusedTilerObj = new ConfusedTiler();
		confusedTilerObj.run();
	}

}