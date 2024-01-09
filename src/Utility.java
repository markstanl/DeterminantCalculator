import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
        if(j != 0) str += ",";
        str += " "+ matrix[i][j] ;
      }
      System.out.println(str + "]");
      }
  }

  /**
   * method that prints out a string representation of a matrix [a11, a12, ... a1n] [a21, a22, ...
   * a2n] [ : : ] [am1, am2, ... amn]
   * 
   * same method as before but with integers
   * 
   * @param matrix the 2d int array representing a matrix
   */
  public static void printMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      String str = "[";
      for (int j = 0; j < matrix[i].length; j++) {
        if(j != 0) str += ",";
        str += " "+ matrix[i][j] ;
      }
      System.out.println(str + "]");
    }
  }
  
  /**
   * method that prints out a string representation of a matrix [a11, a12, ... a1n] [a21, a22, ...
   * a2n] [ : : ] [am1, am2, ... amn]
   * 
   * same method as before but with Fractions
   * 
   * @param matrix the 2d int array representing a matrix
   */
  public static void printMatrix(Fraction[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      String str = "[";
      for (int j = 0; j < matrix[i].length; j++) {
        if(j != 0) str += ",";
        str += " "+ matrix[i][j] ;
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
  public static void printArray(long[] array) {
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
  public static void printArray(String[] array) {
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
   * @param array the int array we want to print
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


  public static double[][] toDoubleMatrix(int[][] matrix) {
    double[][] returnMatrix = new double[matrix.length][matrix.length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        returnMatrix[i][j] = matrix[i][j];
      }
    }
    return returnMatrix;
  }

  /**
   * isSquare method determines if a matrix is square
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
  public static Fraction[][] toFractionMatrix(int[][] matrix) {
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

  public static boolean closeEnough(double val1, double val2) {
    return Math.abs(val1 - val2) < 0.001;
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

  /**
   * Generates different sized matrices and returns an arrayList full of them, the max size is 6
   * 
   * @param numOfMatricies the number of matrices we want generated
   * @param seed           the seed of the random number generator
   * @return the arrayList of matrices
   */
  public static ArrayList<int[][]> generateMatricies(int numOfMatricies) {
    Random randGen = new Random();
    ArrayList<int[][]> returnArrayList = new ArrayList<int[][]>();
    // runs a num of matrices amount of times
    for (int i = 0; i < numOfMatricies; i++) {
      int size = randGen.nextInt(6) + 1;
      int[][] addMatrix = new int[size][size];
      for (int j = 0; j < size; j++) {
        // iterates size number of times, size times
        for (int k = 0; k < size; k++) {
          addMatrix[j][k] = randGen.nextInt(40)-20;
        }
      }
      returnArrayList.add(addMatrix);
    }

    return returnArrayList;
  }

  /**
   * Same method as before, generates a bunch of different matrices, but this method has a
   * parameter to cap the value of the numbers the matrices can have, and the size of the matrix , to make visual verification a
   * little bit easier
   * 
   * @param numOfMatricies the number of matrices we wish to generate
   * @param seed           the seed of this random number generation
   * @param valCap         the maximum value of the absolute value, exclusive
   * @param sizeCap       the maximum size of a matrix
   * @return
   */
  public static ArrayList<int[][]> generateMatricies(int numOfMatricies, int seed, int valCap,
      int sizeCap) {
    Random randGen = new Random(seed);
    ArrayList<int[][]> returnArrayList = new ArrayList<int[][]>();
    // runs a num of matricies amount of times
    for (int i = 0; i < numOfMatricies; i++) {
      int[][] addMatrix = new int[sizeCap][sizeCap];
      for (int j = 0; j < sizeCap; j++) {
        // iterates size number of times, size times
        for (int k = 0; k < sizeCap; k++) {
          addMatrix[j][k] = randGen.nextInt(2 * valCap) - valCap; // gens a number at 2* the val
                                                                  // cap, then subtracts that value
                                                                  // by val cap(so the maximum
                                                                  // number is valcap, and the
                                                                  // minimum is -valcap
        }
      }
      returnArrayList.add(addMatrix);
    }

    return returnArrayList;
  }
  
  /**
   * Simple utility method to see if a string is parseable into an integer
   * @param str   String value we wish to check
   * @return      true if the string is an integer, false otherwise
   */
  public static boolean isInt(String str) {
    try {
      Integer.parseInt(str);
      return true;
    }
    catch (NumberFormatException e) {
      return false;
    }
  }
  
  /**
   * Simple method to sleep for sleepSeconds number of seconds. Cleans up code quite well.
   * @param sleepSeconds
   */
  public static void sleep(int sleepSeconds) {
    try {
      TimeUnit.SECONDS.sleep(sleepSeconds);
    } catch (InterruptedException e) {
    }
  }
  
}
