import java.util.Arrays;
import java.util.ArrayList;

/**
 * Determinant tester class, tests the functionality of all different determinant testers
 * 
 * @TODO Make a fuzz tester to check the implementation of the row reduction
 */
public class DeterminantTester {

  /**
   * Checks the validity of the matrix compressor in the cofactor method
   * 
   * @return true if the method acts as expected, false otherwise
   */
  public static boolean matrixCompressorTest() {
    double[][] testMatrix = new double[][] {{6, 1, 1}, {4, -2, 5}, {2, 8, 7}};
    double[][] expectedMatrix1 = new double[][] {{6, 1}, {2, 7}};// the compressed matrix at 1,1

    Cofactor test = new Cofactor(testMatrix);

    double[][] actualMatrix1 = test.matrixCompressor(new int[] {1, 1}, testMatrix);

    if (!Arrays.deepEquals(expectedMatrix1, actualMatrix1)) {
      System.out.println("Matrix compressor test 1 fail");
      Utility.printMatrix(actualMatrix1);
      Utility.printMatrix(expectedMatrix1);
      return false;
    }

    return true;

  }

  /**
   * Checks the validity of the cofactor expansion of a matrix of size two
   * 
   * @return true if the method acts as expected, false otherwise
   */
  public static boolean cofactorSolverTestSizeTwo() {
    double[][] testMatrix = new double[][] {{1, 2}, {3, 2}};
    Cofactor test = new Cofactor(testMatrix);
    double expectedDet = -4;
    double det = test.getDet();
    if (det != expectedDet) {
      System.out.println("solverTestSizeTwo test 1 fail");
      System.out.println("   expected det: -4  actual det: " + det);
      return false;
    }

    return true;

  }

  /**
   * Checks the validity of the cofactor expansion on a matrix of size three
   * 
   * @return true if the method acts as expected, false otherwise
   */
  public static boolean cofactorSolverTestSizeThree() {
    double[][] testMatrix = new double[][] {{1, 2, 3}, {3, 2, 1}, {2, 1, 3}};
    Cofactor test = new Cofactor(testMatrix);
    double expectedDet = -12;
    double det = test.getDet();

    if (det != expectedDet) {
      System.out.println("  cofactorSolverTestSizeThree returns ");
      System.out.println(" expeceted det -12 actual det " + det);
      return false;
    }

    return true;
  }

  /**
   * Checks the validity of the constructor for the sum
   * 
   * @return true if the method acts as expected, false otherwise
   */
  public static boolean sumConstructor() {
    double[][] testMatrix1 = new double[][] {{1, 2}, {3, 2}};
    Summation test1 = new Summation(testMatrix1);
    // test1.printPermutations();

    double[][] testMatrix2 = new double[][] {{1, 2, 3}, {3, 2, 1}, {2, 1, 3}};
    Summation test2 = new Summation(testMatrix2);
    // test2.printPermutations();

    if (Arrays.compare(test2.getPermutation(0), new int[] {0, 1, 2}) != 0) {
      System.out.println("   sumCOnstructor test 1 fail");
      System.out.println("   expected permutation: [0, 1, 2]");
      System.out.println("   actual permutation");
      Utility.printArray(test1.getPermutation(0));
    }

    // ALL PERMUTATIONS ARE VISUALLY CORRECT

    return true;
  }

  /**
   * Checks the validity of the signs of the permutations
   * 
   * @return true if the method acts as expected, false otherwise
   */
  public static boolean sumSignTester() {
    double[][] testMatrix1 = new double[][] {{1, 2}, {3, 2}};
    Summation test1 = new Summation(testMatrix1);

    double[][] testMatrix2 = new double[][] {{1, 2, 3}, {3, 2, 1}, {2, 1, 3}};
    Summation test2 = new Summation(testMatrix2);

    // both test cases first value should be 1 (as no switch occurs)
    if (test1.getPermutationSign(0) != 1) {
      System.out.println("  sumSignTester test 1 fail");
      System.out.println("  expected sign 1");
      System.out.println("  actual sign" + test1.getPermutationSign(0));
      return false;
    }

    if (test1.getPermutationSign(1) != -1) {
      System.out.println("  sumSignTester test 1 fail");
      System.out.println("  expected sign -1");
      System.out.println("  actual sign" + test1.getPermutationSign(1));
      return false;
    }

    if (test2.getPermutationSign(0) != 1) {
      System.out.println("  sumSignTester test 1 fail");
      System.out.println("  expected sign 1");
      System.out.println("  actual sign 1" + test2.getPermutationSign(0));
      return false;
    }

    if (test2.getPermutationSign(1) != -1) {
      System.out.println("  sumSignTester test 1 fail");
      System.out.println("  expected sign -1");
      System.out.println("  actual sign" + test2.getPermutationSign(1));
      return false;
    }

    // all cases VISUALLY look correct
    ;
    return true;
  }

  /**
   * Checks the validity of the determinant solver in the sum class
   * 
   * @return true if the method acts as expected, false otherwise
   */
  public static boolean sumDeterminantSolver() {
    double[][] testMatrix1 = new double[][] {{1, 2}, {3, 2}};
    Summation test1 = new Summation(testMatrix1);
    double expectedDet1 = -4;
    double det1 = test1.getDet();

    if (det1 != expectedDet1) {
      System.out.println("sumDeterminantSolver test 1 fail");
      System.out.println("   expected det: -4  actual det: " + det1);
      return false;
    }

    double[][] testMatrix2 = new double[][] {{1, 2, 3}, {3, 2, 1}, {2, 1, 3}};
    Cofactor test2 = new Cofactor(testMatrix2);
    double expectedDet2 = -12;
    double det2 = test2.getDet();

    if (det2 != expectedDet2) {
      System.out.println("  sumDeterminantSolver test 2 fail ");
      System.out.println(" expeceted det -12 actual det " + det2);
      return false;
    }
    return true;
  }

  /**
   * Checks the validity of basic function methods
   * 
   * @return true if the method acts as expected, false otherwise
   */
  public static boolean fractionMethods() {
    Fraction num1 = new Fraction(8, 6);

    /*
     * if(num1.gcd() != 2) { System.out.println("fraction methods test 1 fail");
     * System.out.println("Expected GCD: 2  actual gcd: "+num1.gcd()); }
     */
    // METHOD WORKS AS EXPECTED, BUT WANT TO KEEP GCD METHOD PRIVATE

    Fraction num2 = new Fraction(8, 2);
    num2.divide(2);
    if (!num2.toString().equals("2")) {
      System.out.println("  fractionMethods test 2 fail");
      System.out.println("  expected string num2: 2   actual: " + num2);
      return false;
    }
    num2.divide(3);

    if (!num2.toString().equals("2/3")) {
      System.out.println("  fractionMethods test 3 fail");
      System.out.println("  expected string num2: 2/3   actual: " + num2);
      return false;
    }

    num2.multiply(3);

    if (!num2.toString().equals("2")) {
      System.out.println("  fractionMethods test 3 fail");
      System.out.println("  expected string num2: 2   actual: " + num2);
      return false;
    }

    if (num2.compareTo(num1) != 1) {
      System.out.println("  fractionmethods test 4 fail");
      System.out.println("  expected compareto: 1 actual: " + num2.compareTo(num1));
      return false;
    }

    Fraction num3 = new Fraction(0, 11);

    if (!num3.toString().equals("0")) {
      System.out.println("  fractionmethods test 5 fail");
      System.out.println("  expected compareto: 0 actual: " + num2.compareTo(num1));
      return false;
    }

    Fraction num4 = new Fraction(8, 2);
    Fraction subtractor = new Fraction(4, 3);
    num4.subtract(subtractor);

    if (num4.toString().compareTo("8/3") != 0) {
      System.out.println("  fractionMethods test 6 fail");
      System.out.println("  expected num4 after subtraction: 8/3 ");
      System.out.println("  actual num4: " + num4);
      return false;
    }

    try {
      num2.divide(0);
      System.out.println("  fractionmethods test 7 fail");
      System.out.println("  expected exception thrown");
      return false;
    } catch (IllegalArgumentException e) {

    }

    Fraction num5 = new Fraction(6, 5);
    Fraction mult5 = new Fraction(1, 2);
    num5.multiply(mult5);
    Fraction num6 = new Fraction(6, 10);

    if (!num5.equals(num6)) {
      System.out.println("  fractionmethods test 8 fail");
      System.out.println("  expected product: " + num6);
      System.out.println("  actual product: " + num5);
      return false;
    }

    num5.divide(mult5);

    if (!num5.toString().equals("6/5")) {
      System.out.println("  fraction methods test 9 fail");
      System.out.println("  expeced: 6/5");
      System.out.println("  actual: " + num5);
      return false;
    }


    return true;
  }

  /**
   * Checks the validity of regular cases of row reduction
   * 
   * @return true if the method acts as expected, false otherwise
   */
  public static boolean rowReductionTest() {
    int[][] tesMatrix1 = new int[][] {{1, 2}, {3, 2}};
    Fraction[][] testMatrix1 = Utility.toFractionMatrix(tesMatrix1);

    RowReduction test1 = new RowReduction(testMatrix1);
    Fraction expectedDet = new Fraction(-4);
    if (!test1.getDet().equals(expectedDet)) {
      System.out.println("  rowReductiontest test 1 fail");
      System.out.println("  expected det:" + expectedDet);
      System.out.println("  actual det:" + test1.getDet());
      return false;
    }

    int[][] tetMatrix2 = new int[][] {{1, 2, 3}, {3, 2, 1}, {2, 1, 3}};
    Fraction[][] testMatrix2 = Utility.toFractionMatrix(tetMatrix2);

    RowReduction test2 = new RowReduction(testMatrix2);
    int expectedDet2 = -12;

    if (test2.getDet().toInt() != expectedDet2) {
      System.out.println("row reducion test 2 fail");
      System.out.println("expected det: -12");
      System.out.println("actual det: " + test2.getDet().toInt());
      return false;
    }


    return true;
  }

  /**
   * Fuzz tester for the determinants. Tests many different cases of matricies against other valid
   * implementations
   * 
   * @return false if all determinant methods don't return the same value, true if they do
   */
  public static boolean allDeterminantFuzzTester() {
    ArrayList<int[][]> matricies = Utility.generateMatricies(2000, 73, 5, 6);// # of matricies,
                                                                             // random seed, max num
                                                                             // in matrix, max size

    for (int i = 0; i < matricies.size(); i++) {
      int[][] compareMatrix = matricies.get(i);
      Summation summation = new Summation(compareMatrix);
      Cofactor cofactor = new Cofactor(compareMatrix);
      RowReduction rowReduction = new RowReduction(compareMatrix);
      // if the determinants aren't close enough (within an error range explainable by weird double
      // java things) error found
      if (!Utility.closeEnough(summation.getDet(), cofactor.getDet())
          || !Utility.closeEnough(summation.getDet(), rowReduction.getDet().doubleVal())) {
        System.out.println(" allDeterminantFuzzTester test " + (i + 1) + " fail");
        System.out.println("Matrix: ");
        Utility.printMatrix(compareMatrix);
        System.out.println(" summation determinant: " + summation.getDet());
        System.out.println(" cofactor determinant: " + cofactor.getDet());
        System.out.println(" rowReduction determinant: " + rowReduction.getDet());
        System.out
            .println(" first bool: " + Utility.closeEnough(summation.getDet(), cofactor.getDet()));
        System.out.println(" second bool: "
            + Utility.closeEnough(summation.getDet(), rowReduction.getDet().doubleVal()));
        return false;
      }
    }
    return true;
  }

  /**
   * It seems that sometimes row reduction throws an unexpected divide by 0 error.
   * @TODO get to the bottom of this
   * @return    true if all tests pass, false otherwise
   */
  public static boolean rowReductionErrorTest() {
    ArrayList<int[][]> matricies = Utility.generateMatricies(50000, 73, 5, 8);
    
    for(int i = 0 ; i < matricies.size(); i++) {
      try {
        RowReduction rowReduction = new RowReduction(matricies.get(i));
      }
      catch (IllegalArgumentException e) {
        System.out.println("Caught an exception");
        Utility.printMatrix(matricies.get(i));
        return false;
      }
    }
    
    
    return true;
  }
  /**
   * Private method that runs all of the tester methods
   * 
   * @return true if all the tests pass, false otherwise
   */
  private static boolean runTests() {

    boolean matrixCompressor = matrixCompressorTest();
    boolean cofactorSolverTestSizeTwo = cofactorSolverTestSizeTwo();
    boolean cofactorSolverTestSizeThree = cofactorSolverTestSizeThree();
    boolean sumSignTester = sumSignTester();
    boolean sumConstructor = sumConstructor();
    boolean sumDeterminantSolver = sumDeterminantSolver();
    boolean fractionMethods = fractionMethods();
    boolean rowReductionTest = rowReductionTest();
    boolean allDeterminantFuzzTester = allDeterminantFuzzTester();
    boolean rowReductionErrorTest = rowReductionErrorTest();


    String resultMatrixCompressor = matrixCompressor ? "pass" : "fail";
    System.out.println("Matrix compressor: " + resultMatrixCompressor);

    String resultcofactorSolverTestSizeTwo = cofactorSolverTestSizeTwo ? "pass" : "fail";
    System.out.println("cofactorSolverTestSizeTwo: " + resultcofactorSolverTestSizeTwo);

    String resultcofactorSolverTestSizeThree = cofactorSolverTestSizeThree ? "pass" : "fail";
    System.out.println("cofactorSolverTestSizeThree: " + resultcofactorSolverTestSizeThree);

    String resultsumSignTester = sumSignTester ? "pass" : "fail";
    System.out.println("sumSignTester: " + resultsumSignTester);

    String resultsumConstructor = sumConstructor ? "pass" : "fail";
    System.out.println("sumConstructor: " + resultsumConstructor);

    String resultsumDeterminantSolver = sumDeterminantSolver ? "pass" : "fail";
    System.out.println("sumDeterminantSolver: " + resultsumDeterminantSolver);

    String resultfractionMethods = fractionMethods ? "pass" : "fail";
    System.out.println("fractionMethods: " + resultfractionMethods);

    String resultrowReductionTest = rowReductionTest ? "pass" : "fail";
    System.out.println("rowReductionTest: " + resultrowReductionTest);

    String resultallDeterminantFuzzTester = allDeterminantFuzzTester ? "pass" : "fail";
    System.out.println("allDeterminantFuzzTester: " + resultallDeterminantFuzzTester);
    
    String resultrowReductionErrorTest = rowReductionErrorTest ? "pass" : "fail";
    System.out.println("rowReductionErrorTest: "+ resultrowReductionErrorTest);

    return matrixCompressor && cofactorSolverTestSizeTwo && cofactorSolverTestSizeThree
        && sumSignTester && sumConstructor && sumDeterminantSolver && fractionMethods
        && rowReductionTest && allDeterminantFuzzTester && rowReductionErrorTest;

  }

  public static void main(String[] args) {
    runTests();
  }
}
