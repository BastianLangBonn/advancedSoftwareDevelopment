package org.brsu.exercises;

/**
 * Class to store the results computed in {@link Part3} and provide a decent
 * toString method.
 * 
 * @author bastian
 * 
 */
public class ResultTable {

	private int[] multipliedBy2;
	private int[] cubes;
	private double[] squareRoots;
	private double[] nthRootsOf2;
	private double[] powers10;
	private double[] eToPowersN;

	public ResultTable() {
		multipliedBy2 = new int[101];
		cubes = new int[101];
		squareRoots = new double[101];
		nthRootsOf2 = new double[101];
		powers10 = new double[101];
		eToPowersN = new double[101];
	}

	public void addMultiplicationBy2Result(int product, int index) {
		multipliedBy2[index] = product;
	}

	public void addCube(int cube, int index) {
		cubes[index] = cube;
	}

	public void addSquareRoot(double squareRoot, int index) {
		squareRoots[index] = squareRoot;
	}

	public void addNthRootOf2(double nthRootOf2, int index) {
		nthRootsOf2[index] = nthRootOf2;
	}

	public void add10ToPower(double element, int index) {
		powers10[index] = element;
	}

	public void addEToPower(double product, int index) {
		eToPowersN[index] = product;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
		    .append("n\t|\tf(n) = 2n\t|\tf(n) = n³\t|\tf(n) = n^(1/2)\t\t|\tf(n) = 2^(1/n)\t|\tf(n) = 10^n\t|\tf(n) = e^n\t|\n");
		for (int i = 0; i <= 100; i++) {
			stringBuilder.append(String.format(
			    "%d\t|\t%d\t\t|\t%d\t\t|\t%f\t\t|\t%f\t|\t%.0f\t\t|\t%.4f\t\t|\n", i,
			    multipliedBy2[i], cubes[i], squareRoots[i], nthRootsOf2[i],
			    powers10[i], eToPowersN[i]));
		}

		return stringBuilder.toString();
	}

}
