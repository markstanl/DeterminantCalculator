import java.lang.Math;

/**
 * Recursively solves a matrix by the cofactor expansion
 * 
 * @runtime O(n^2)
 */
public class Cofactor {

  private double[][] matrix; // the matrix representing this recursive
  private int size; // the size of this matrix: n


  /**
   * Constructor for this class. Initializes all local variables
   * 
   * @param matrix the matrix represented by the instance
   * @throws IllegalArgumentException if the matrix is not square
   */
  public Cofactor(double[][] matrix) {
    // if the matrix is not square, throw an exception
    if (!isSquare(matrix)) {
      throw new IllegalArgumentException("Matrix must be square");
    }
    this.matrix = matrix;
    this.size = matrix[0].length;
  }


  /**
   * Solves the determinant of the matrix. Calls a private helper method to do so recursively
   * 
   * @return the determinant of the matrix
   */
  public double solver() {
    // if the size is 1, just return that value
    if (size == 1) {
      return matrix[0][0];
    }

    double det = solverRecursion(this.matrix, 0);

    return det;
  }

  /**
   * Recursively solves the matrix by iterating through all possible initial values, then adds the
   * resulting determinant of that minor to the running determinant
   * 
   * @param matrix     the matrix which we are solving for
   * @param runningDet the current running determinant
   * @return the determinant
   */
  private double solverRecursion(double[][] matrix, int runningDet) {

    // base case, uses the formula for a 2x2 matrix
    if (matrix.length == 2) {
      return ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
    }

    // iterates through the first row, and finds the determinant of the resulting minor
    for (int i = 0; i < matrix.length; i++) {
      runningDet += Math.pow(-1, i) * matrix[0][i]
          * solverRecursion(matrixCompressor(new int[] {0, i}, matrix), runningDet);
    }
    return runningDet;
  }

  /**
   * Removes the row and column at the given index from the given matrix. Assumes valid
   * implementation of the matrix
   * 
   * @param coordinates   the coordinate array of the coordinate 
   * @param matrix       the matrix ot be reduced
   * @throws IllegalArgumentException if the coordinate array does not contain 2 values
   * @return
   */
  public double[][] matrixCompressor(int[] coordinates, double[][] matrix) {

    if (coordinates.length != 2) {
      throw new IllegalArgumentException("Coordinates must be two integers");
    }

    double[][] newMatrix = new double[matrix.length - 1][matrix.length - 1];

    int rowCount = 0;
    for (int i = 0; i < matrix.length; i++) {

      // reset for every row
      int columnCount = 0;
      // only runs if the row does not contain the row with the coordinate
      if (i != coordinates[0]) {

        for (int j = 0; j < matrix.length; j++) {

          if (j != coordinates[1]) {
            newMatrix[rowCount][columnCount] = matrix[i][j];
            columnCount++;
          }
        }
        // if this function ran, we know it must be able to move to the next iteration
        rowCount++;
      }
    }
    return newMatrix;
  }


  /**
   * private helper method to determine whether or not the given matrix is square
   * 
   * @param matrix the matrix being tested
   * @return true if the matrix is square, false otherwise
   */
  private boolean isSquare(double[][] matrix) {
    int m = matrix.length;
    for (int i = 0; i < matrix.length; i++) {
      // iterates through all arrays in the 2d array, if any of them are not equal to the length of
      // the initial array, it immediately returns false
      if (matrix[i].length != m)
        return false;
    }
    return true;
  }

  /**
   * To String method. prints out an array that visually looks as follows
   * [a11, a12, ... a1n]
   * [a21, a22, ... a2n]
   * [ :             : ]
   * [am1, am2, ... amn]
   * @return the string representation of this object
   */
  @Override
  public String toString() {
    String str = "";
    for (int i = 0; i < this.size; i++) {
      str += "[";
      for (int j = 0; j < this.size; j++) {
        str += " " + matrix[i][j];
        if (j < this.size - 1)
          str += ",";
      }
      str += "]" + "\r\n";
    }

    return str;
  }



}
