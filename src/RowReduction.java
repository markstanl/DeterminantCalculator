
/**
 * Class that uses row reduction to solve determinants. Runtime: O(n^3)
 */
public class RowReduction {

  private Fraction[][] matrix; // the matrix representing this recursive
  private int size; // the size of this matrix: n
  private Fraction det; // the determinant

  /**
   * COnstructor for the row reduction class, takes in a fraction matrix, and performs row reduction
   * 
   * @param matrix the fraction matrix to be reduced
   * @throws IllegalArgumentException if he matrix is not square
   */
  public RowReduction(Fraction[][] matrix) {
    this.matrix = matrix;
    this.size = matrix.length;
    this.det = null;
    if (!Utility.isSquare(matrix)) {
      throw new IllegalArgumentException("Matrix must be square");
    }
    rowReduce();
  }

  /**
   * Same constructor as before, but takes in an int matrix as a parameter.
   * 
   * @param matrixInt the fraction matrix to be reduced
   * @throws IllegalArgumentException if he matrix is not square
   */
  public RowReduction(int[][] matrixInt) {
    Fraction[][] matrix = Utility.toFractionMatrix(matrixInt);
    this.matrix = matrix;
    this.size = matrix.length;
    this.det = null;
    if (!Utility.isSquare(matrix)) {
      throw new IllegalArgumentException("Matrix must be square");
    }
    rowReduce();
  }

  /**
   * Getter for the determinant of the this matrix
   * 
   * @return the fraction determinant value
   */
  public Fraction getDet() {
    return this.det;
  }

  /**
   * Row reduction method. Reduces the row to find the determinant by calling a helper method
   */
  private void rowReduce() {
    boolean done = false;
    // iterate through every column in the matrix
    Fraction runningDet = new Fraction(1);
    // Iterates through every row in the matrix
    for (int i = 0; i < this.size; i++) {
      // reduces at that row
      Fraction returned = rowReduceAt(i);
      // if the row reduction results in a 0, that row is empty and therefore the det is 0
      if (returned.equals(new Fraction(0))) {
        done = true;
        break;
      }
      // else keep the continuous running count
      runningDet.multiply(returned);
      
      //if(this.size == 5) System.out.println(this);
      //System.out.println(this);
    }
    
    // return the determinant
    if(done)this.det = new Fraction(0);
    else {
      
      this.det = runningDet;
    }
  }

  /**
   * Method to reduce at a specific row/column
   * 
   * @param columnIndex the row and column we want to start the row reduction at
   * @return the fraction we reduced at our point
   */
  private Fraction rowReduceAt(int columnIndex) {
    int nonzeroRow = -1;
    // finds the first non-zero row, starting at the aii of the matrix
    for (int i = columnIndex; i < this.size; i++) {
      if (!matrix[i][columnIndex].equals(new Fraction(0))) {
        nonzeroRow = i;
        break;
      }
    }
    if (nonzeroRow == -1)
      return new Fraction(0); // if non nonzero row is found, we have a full 0 row and det = 0

    // else swap the first nonzero row with the column index, so that
    swap(nonzeroRow, columnIndex);

    Fraction multiplier = new Fraction(1);
    // this new number, when multiplied by the pivot, will equal 0. we return the inverse of this
    multiplier.divide(matrix[columnIndex][columnIndex]);

    // this divides each of the row values by the number that brings the pivot to 1
    for (int i = columnIndex; i < this.size; i++) {
      matrix[columnIndex][i].multiply(multiplier);
    }
    
    //IN THIS POSITION. The target row is now all reduced such that the first row is a pivot

    //starts at the row after the index row
    for(int i = columnIndex+1 ; i < this.size ; i++) {
      Fraction multi = new Fraction(1);
      multi.multiply(matrix[i][columnIndex]);
      subtractRow(i, columnIndex, multi);
    }
    
    multiplier.invert(); // inverts the multiplier then returns it
    
    if (nonzeroRow != columnIndex)
      multiplier.multiply(new Fraction(-1)); // if we did a swap our matrix is multiplied by -1
    return multiplier;
  }
  
  private void subtractRow(int a, int b, Fraction multiplier) {
    for(int i = 0 ; i < this.size ; i++) {
      Fraction toSubtract = matrix[b][i].copy();
      toSubtract.multiply(multiplier);
      matrix[a][i].subtract(toSubtract);
    }
  }

  /**
   * Private method to swap two rows of the matrix
   * 
   * @param a the index of the first row
   * @param b the index of the second row
   */
  private void swap(int a, int b) {
    Fraction[] tempRow = matrix[a];
    matrix[a] = matrix[b];
    matrix[b] = tempRow;
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
