/*
 * Author : Nitish Reddy Koripalli
 * Date : 19th May 2015
 */
package org.brsu.exercise5.control;

import java.util.ArrayList;

public class Display extends SearchSpace {
	public Display() {
		;
	}

	public static void displayAxisSearchSpaceSizes(SearchSpace searchSpaceObj) {
		System.out.format("Axis Search Space Sizes : ");
		for (int i = 0; i < searchSpaceObj.searchSpace.size(); i++) {
			System.out.format("%3d ", searchSpaceObj.searchSpace.get(i).size());
		}
		System.out.println();
	}

	public static void displaySearchSpace(SearchSpace searchSpaceObj) {
		System.out.println("Search Space:");
		for (int i = 0; i < searchSpaceObj.searchSpace.size(); i++) {
			System.out.format("%3d ", i + 1);
			displayAxisSearchSpace(searchSpaceObj.searchSpace.get(i));
		}
	}

	public static void displayAxisSearchSpace(ArrayList<Integer[]> axisSearchSpace) {
		System.out.println("Axis Search Space:");
		for (int i = 0; i < axisSearchSpace.size(); i++) {
			System.out.format("%3d ", i + 1);
			displayAxisSearchNode(axisSearchSpace.get(i));
		}
	}

	public static void displayAxisSearchNode(Integer[] axisSearchNode) {
		System.out.format("Axis Search Node : ");
		for (int i = 0; i < axisSearchNode.length; i++) {
			System.out.format("%3d ", axisSearchNode[i]);
		}
		System.out.println();
	}
}
