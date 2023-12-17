import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * The definition of a determinant solved using the sum.
 * 
 * Runtime: O(n^2)
 */
public class Summation {

  private double[][] matrix; // the initial matrix
  private int size; // the size of the matrix, n
  private ArrayList<int[]> permutations; // the array list of permutations
  private ArrayList<Integer> permutationSigns; // the array list of permutation signs. INDEXES OF
                                               // PERMUTATIONS MATCH TO THEIR SIGN

  /**
   * Constructor for this class. Initializes all fields, and calls the initializing permutation
   * methods.
   * 
   * @param matrix the square 2d array of the matrox
   * @throws IllegalArgumentException if the matrix is not square
   */
  public Summation(double[][] matrix) {
    // if the matrix is not square, throws an exception
    if (!isSquare(matrix)) {
      throw new IllegalArgumentException("Matrix must be square");
    }
    this.matrix = matrix;
    this.size = matrix[0].length;
    this.permutations = new ArrayList<int[]>();
    this.permutationSigns = new ArrayList<Integer>();
    permutor();
    permutationSignsFinder();
  }

  public int solver() {
    int det = 0;
    for (int i = 0; i < permutations.size(); i++) {
      int runningPermVal = permutationSigns.get(i);
      for (int j = 0; j < this.size; j++) {
        runningPermVal *= matrix[j][permutations.get(i)[j]];
      }
      det += runningPermVal;
    }
    return det;
  }

  /**
   * Returns the permutation at the given index
   * 
   * @param index the index of the permutation you want returned
   * @return the int array of the permutation at the index
   */
  public int[] getPermutation(int index) {
    if (index < 0 || index >= permutations.size())
      throw new NoSuchElementException("value outside of range");
    return permutations.get(index);
  }

  /**
   * Returns the permutation sign at the given index
   * 
   * @param index the index of the permutation sign you want returned
   * @return the int array of the permutation sign at the index
   */
  public int getPermutationSign(int index) {
    if (index < 0 || index >= permutationSigns.size())
      throw new NoSuchElementException("value outside of range");
    return permutationSigns.get(index);
  }

  /**
   * initializes the permutor array list. does this by initializing an array of the size of the
   * matrix (n) by the following principle, {0, 1, 2, ... n}, and recursively swapping all possible
   * values in every possible location, and adds them to an arrayList
   */
  private void permutor() {
    int[] initialVal = new int[this.size];
    for (int i = 0; i < this.size; i++) {
      initialVal[i] = i;
    }
    permutorRecursion(size, initialVal);
  }

  /**
   * Recursively initialzes all possible permutations from a given initial permutation. does so
   * recursively by swapping all possible values of the first index, then recursively calls the next
   * value swapping the indexes.
   * 
   * @param n     the current index we are swapping
   * @param array the current array we are working with
   */
  private void permutorRecursion(int n, int[] array) {
    // base case, this is when all values have been swapped
    if (n == 1) {
      permutations.add(array);
    } else {
      // iterates through all possible starting indexes
      for (int i = array.length - n; i < array.length; i++) {
        // and calls this method recursively with the index to swap starting at the next value
        permutorRecursion(n - 1, swap(array, (array.length - n), i));
      }
    }
  }

  /**
   * iterates through all permutations in the permutation arrayList, and iterates through each
   * individual value, and checks how many following values are greater than it; this is the
   * definition of the sign of a permutation.
   */
  private void permutationSignsFinder() {
    // iterates through all permutations
    for (int i = 0; i < permutations.size(); i++) {
      // sets a count
      int count = 1;
      // iterates through all numbers in a permutation
      for (int j = 0; j < permutations.get(i).length; j++) {
        // it then compares them to every following integer in the array
        for (int k = j + 1; k < permutations.get(i).length; k++) {
          // if a value after the one tested is greater than the one tested, adds to count
          if (permutations.get(i)[j] < permutations.get(i)[k]) {
            count++;
          }
        }
      }
      // returns the -1 to the power of the count
      permutationSigns.add((int) Math.pow(-1, count));
    }
  }

  /**
   * takes in an array as a parameter, as well as the indexes we wish to swap, and returns a copy of
   * the array with the indexes switched
   * 
   * @param array the array we want to do the swapping with
   * @param i     the index of the first int we wish to swap
   * @param j     the index of the second int we wish to swap
   * @return a copy of the array with the values switched
   */
  private int[] swap(int[] array, int i, int j) {
    if (j >= array.length || i >= array.length) {
      System.out.println("               " + i + "" + j + "" + array.length);
      throw new IllegalArgumentException("index cannot be rgeater than length");
    }

    int[] newArray = Arrays.copyOf(array, array.length);
    int temp = newArray[i];
    newArray[i] = newArray[j];
    newArray[j] = temp;
    return newArray;
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
      if (matrix[i].length != m)
        return false;
    }
    return true;
  }

  /**
   * public method to print all of the permutations. Calls the array print class to help print the
   * array visually. used to double check
   */
  public void printPermutations() {
    for (int i = 0; i < permutations.size(); i++) {
      Utility.printArray(permutations.get(i));
    }
  }

  /**
   * public method to print all of the permutations next to their respective sign. calls the utility
   * class to help print out the array
   */
  public void printPermutationsAndSigns() {
    for (int i = 0; i < permutations.size(); i++) {
      Utility.printArray(permutations.get(i));
      System.out.println("   " + permutationSigns.get(i));
    }
  }

  /**
   * To String method. prints out an array that visually looks as follows [a11, a12, ... a1n] [a21,
   * a22, ... a2n] [ : : ] [am1, am2, ... amn]
   * 
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
