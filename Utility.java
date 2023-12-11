/**
 * Public class to help with various utility issues.
 */
public class Utility {

  /**
   * method that prints out a string representation of a matrix [a11, a12, ... a1n] [a21, a22, ...
   * a2n] [ : : ] [am1, am2, ... amn]
   * 
   * @param matrix the 2d double array representing a matrix
   */
  public static void printMatrix(double[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      String str = "[";
      for (int j = 0; j < matrix[i].length; j++) {
        str += " " + matrix[i][j] + ",";
      }
      System.out.println(str + "]");
    }
  }

  /**
   * Prints a string representation of an array
   * 
   * @param array the double array we want to print
   */
  public static void printArray(double[] array) {
    String str = "[";
    for (int i = 0; i < array.length; i++) {
      str += array[i];
      if (i != array.length - 1)
        str += ", ";
    }
    System.out.println(str + "] ");
  }

  /**
   * Prints a string representation of an array
   * 
   * @param array the double array we want to print
   */
  public static void printArray(int[] array) {
    String str = "[";
    for (int i = 0; i < array.length; i++) {
      str += array[i];
      if (i != array.length - 1)
        str += ", ";
    }
    System.out.println(str + "] ");
  }

  /**
   * isSquare method determins if a matrix is square
   * 
   * @param matrix the matrix we wish to check
   * @return true if the matrix is Square, false otherwise
   */
  public static boolean isSquare(double[][] matrix) {
    int m = matrix.length;
    for (int i = 0; i < matrix.length; i++) {
      if (matrix[i].length != m)
        return false;
    }
    return true;
  }

}
