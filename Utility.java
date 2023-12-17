/**
 * Public static class to help with various utility issues.
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

  /**
   * same as before, but for an integer matrix
   * 
   * @param matrix the matrix we wish to check
   * @return true if the matrix is Square, false otherwise
   */
  public static boolean isSquare(int[][] matrix) {
    int m = matrix.length;
    for (int i = 0; i < matrix.length; i++) {
      if (matrix[i].length != m)
        return false;
    }
    return true;
  }

  /**
   * same as before, but for a fraction matrix
   * 
   * @param matrix the matrix we wish to check
   * @return true if the matrix is Square, false otherwise
   */
  public static boolean isSquare(Fraction[][] matrix) {
    int m = matrix.length;
    for (int i = 0; i < matrix.length; i++) {
      if (matrix[i].length != m)
        return false;
    }
    return true;
  }

  /**
   * Converts an integer matrix into a fraction matrix
   * 
   * @param matrix the old integer matrix
   * @return the new fraction matrix
   */
  public static Fraction[][] toFraction(int[][] matrix) {
    if (!isSquare(matrix))
      throw new IllegalArgumentException("matrix must be square");
    Fraction[][] returnMatrix = new Fraction[matrix.length][matrix.length]; // initializes the new
                                                                            // 2d array
    for (int i = 0; i < matrix.length; i++) {
      for (int k = 0; k < matrix.length; k++) {
        returnMatrix[i][k] = new Fraction(matrix[i][k]);
      }
    }

    return returnMatrix;
  }

  /**
   * Finds the greatest common divisor of 2 integers
   * 
   * @param n1 the first integer
   * @param n2 the second
   * @return the greatest common divisor
   */
  public static int gcd(int n1, int n2) {
    if (n2 == 0) {
      return n1;
    }
    return gcd(n2, n1 % n2);
  }

  /**
   * Finds the least common multiple of two Fractions. Does so by calling another method
   * 
   * @param n1 The first fraction
   * @param n2 The second fraction
   * @return the integer of the least common multiple
   */
  public static int lcm(Fraction n1, Fraction n2) {
    if (n1.getDenom() != 1 || n2.getDenom() != 1) {
      throw new IllegalArgumentException("One fraction has a denominator. We only deal with ints");
    } else
      return lcm(n1.toInt(), n2.toInt());
  }

  /**
   * Finds the least common multiple of two integers
   * 
   * @param n1 the first integer
   * @param n2 the second integer
   * @return the least common multiple
   */
  public static int lcm(int n1, int n2) {
    return n1 * n2 / gcd(n1, n2); // this is a common way to calculate the least common multiple
  }


}
