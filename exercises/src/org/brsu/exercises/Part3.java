package org.brsu.exercises;

/**
 * Class containing the code to solve part 3 of exercise 1.
 * 
 * @author bastian
 * 
 */
public class Part3 {

	public static void main(String[] args) {
		Part3 part3 = new Part3();
		part3.run();
	}

	public void run() {
		ResultTable result = new ResultTable();
		for (int i = 0; i <= 100; i++) {
			result.addMultiplicationBy2Result(computeMultiplicationBy2(i), i);
			result.addCube(computeCube(i), i);
			result.addSquareRoot(computeSquareRoot(i), i);
			result.addNthRootOf2(computeNthRootOf2(i), i);
			result.add10ToPower(compute10ToPowerOf(i), i);
			result.addEToPower(computeEToPower(i), i);
		}
		System.out.println(result.toString());
	}

	private double computeEToPower(int exponent) {
		return Math.pow(Math.E, exponent);
	}

	private double compute10ToPowerOf(int exponent) {
		return Math.pow(10, exponent);
	}

	private double computeNthRootOf2(int exponent) {
		// Is not defined for exponent == 0. Java will return Infinity. An exception
		// should be thrown if input is 0, but for this exercise there would be a
		// lot of overhead just to get this case right. So I will stick with
		// accepting 0 and getting Infinity here.
		return Math.pow(2, 1.0 / exponent);
	}

	private double computeSquareRoot(int base) {
		return Math.sqrt(base);
	}

	private int computeCube(int base) {
		return (int) Math.pow(base, 3);
	}

	private int computeMultiplicationBy2(int factor) {
		return factor * 2;
	}

}
