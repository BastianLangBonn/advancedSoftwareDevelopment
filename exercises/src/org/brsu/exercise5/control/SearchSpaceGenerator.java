/*
 * Author : Nitish Reddy Koripalli
 * Date   : 18th May 2015
 */

package org.brsu.exercise5.control;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchSpaceGenerator {

	private static final int START_DEPTH_OF_RECURSION = 1;
	private static final int START_INDEX = 0;

	private int[] axisConstraints = { 0 };
	private int axisLength = 0;

	/**
	 * Constructor
	 * 
	 * @param constraints
	 * @param searchSpace
	 */
	public SearchSpaceGenerator(int axisLength, int[] axisConstraints) {
		this.axisConstraints = Arrays.copyOf(axisConstraints,
		    axisConstraints.length);
		this.axisLength = axisLength;
	}

	/**
	 * Generates new AxisSearchSpace (ArrayList<Integer[]>) Objects and adds them
	 * into a SearchSpace Object using the constraints given in the constructor.
	 * It finally returns the SearchSpace object.
	 * 
	 * @return
	 */
	public SearchSpace generateSearchSpace() {
		int numberOfAxes = this.axisConstraints.length;
		SearchSpace searchSpace = new SearchSpace(this.axisLength, numberOfAxes);
		for (int axisConstraint : this.axisConstraints) {
			ArrayList<Integer[]> axisSearchSpace = new ArrayList<Integer[]>();
			generateAxisSearchSpace(axisSearchSpace, this.axisLength, axisConstraint);
			searchSpace.addAxisSearchSpace(axisSearchSpace);
		}
		return searchSpace;
	}

	/**
	 * Generates new AxisSearchNode (Integer[]) Objects, by making all possible
	 * combinations using axisLength and axisConstraints and adds them into the
	 * AxisSearchSpace (ArrayList<Integer[]>) Object.
	 * 
	 * The AxisSearchSpace Object is given as a parameter and is populated using
	 * its reference.
	 * 
	 * @param axisSearchSpace
	 * @param axisLength
	 * @param axisConstraint
	 */
	private void generateAxisSearchSpace(ArrayList<Integer[]> axisSearchSpace,
	    int axisLength, int axisConstraint) {

		Integer[] axisSearchNodeTemplate = new Integer[axisLength];
		// initialize the template to all whites
		for (int i = 0; i < axisLength; i++) {
			axisSearchNodeTemplate[i] = SearchSpace.WHITE;
		}

		generateAxisSearchNodes(axisSearchSpace, axisSearchNodeTemplate,
		    axisLength, axisConstraint, START_DEPTH_OF_RECURSION, START_INDEX);
	}

	/**
	 * Generate axisSearchNodes (Integer[])
	 * 
	 * Every possibility of an axisSearchNodes is created through recursion. At
	 * the final depth which is when recursionDepth equals axisConstraint for that
	 * axis, full axisSearchNodes are formed and stored into axisSearchSpace.
	 * 
	 * If final depth is not reached, then the function recursively dives deeper.
	 * 
	 * @param axisSearchSpace
	 * @param axisSearchNodeTemplate
	 * @param axisLength
	 * @param axisConstraint
	 * @param recursionDepth
	 * @param startIndex
	 */
	private void generateAxisSearchNodes(ArrayList<Integer[]> axisSearchSpace,
	    Integer[] axisSearchNodeTemplate, int axisLength, int axisConstraint,
	    int recursionDepth, int startIndex) {

		// (recursionDepth - 1) is the startIndex for that depth
		int pastIndex = startIndex;
		for (int currentIndex = startIndex; currentIndex < ((axisLength - axisConstraint) + recursionDepth); currentIndex++) {
			axisSearchNodeTemplate[pastIndex] = SearchSpace.WHITE;
			axisSearchNodeTemplate[currentIndex] = SearchSpace.BLACK;
			pastIndex = currentIndex;

			// stop recursion, and start populating the axisSearchSpace with
			// axisSearchNodes.
			if (recursionDepth == axisConstraint) {
				// create a copy of the axisSearchNode since the axisSearchNodeTemplate
				// will be reused throughout the recursion and since it is a reference
				// it will keep changing its value
				Integer[] axisSearchNode = Arrays.copyOf(axisSearchNodeTemplate,
				    axisSearchNodeTemplate.length);
				// Display.displayAxisSearchNode(axisSearchNode);
				axisSearchSpace.add(axisSearchNode);
			}
			// if we have not reached the recursionDepth limit which is
			// axisConstraint then keep diving deeper with incrementing
			// recursionDepth.
			else {
				generateAxisSearchNodes(axisSearchSpace, axisSearchNodeTemplate,
				    axisLength, axisConstraint, recursionDepth + 1, currentIndex + 1);
			}
		}
		// when done with loop, remember to reset the pastIndex to WHITE
		axisSearchNodeTemplate[pastIndex] = SearchSpace.WHITE;
	}

	/*
	 * Main : behavior testing
	 */
	public static void main(String[] args) {
		int[] testAxisConstraints = { 3, 4, 5 };
		int testAxisLength = 5;
		SearchSpaceGenerator searchSpaceGenerator = new SearchSpaceGenerator(
		    testAxisLength, testAxisConstraints);
		SearchSpace searchSpace = searchSpaceGenerator.generateSearchSpace();

		Display.displaySearchSpace(searchSpace);
	}
}