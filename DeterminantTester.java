import java.util.Arrays;

public class DeterminantTester {


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

  public static boolean cofactorSolverTestSizeTwo() {
    double[][] testMatrix = new double[][] {{1, 2}, {3, 2}};
    Cofactor test = new Cofactor(testMatrix);
    double expectedDet = -4;
    double det = test.solver();
    if (det != expectedDet) {
      System.out.println("solverTestSizeTwo test 1 fail");
      System.out.println("   expected det: -4  actual det: "+det);
      return false;
    }

    return true;

  }

  public static boolean cofactorSolverTestSizeThree() {
    double[][] testMatrix = new double[][] {{1, 2, 3}, {3, 2, 1}, {2, 1, 3}};
    Cofactor test = new Cofactor(testMatrix);
    double expectedDet = -12;
    double det = test.solver();

    if (det != expectedDet) {
      System.out.println("  cofactorSolverTestSizeThree returns ");
      System.out.println(" expeceted det -12 actual det " + det);
      return false;
    }

    return true;
  }

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

  public static boolean sumDeterminantSolver() {
    double[][] testMatrix1 = new double[][] {{1, 2}, {3, 2}};
    Summation test1 = new Summation(testMatrix1);
    double expectedDet1 = -4;
    double det1 = test1.solver();
    
    if (det1 != expectedDet1) {
      System.out.println("sumDeterminantSolver test 1 fail");
      System.out.println("   expected det: -4  actual det: "+det1);
      return false;
    }
    
    double[][] testMatrix2 = new double[][] {{1, 2, 3}, {3, 2, 1}, {2, 1, 3}};
    Cofactor test2 = new Cofactor(testMatrix2);
    double expectedDet2 = -12;
    double det2 = test2.solver();

    if (det2 != expectedDet2) {
      System.out.println("  sumDeterminantSolver test 2 fail ");
      System.out.println(" expeceted det -12 actual det " + det2);
      return false;
    }
    return true;
  }


  private static boolean runTests() {

    boolean matrixCompressor = matrixCompressorTest();
    boolean cofactorSolverTestSizeTwo = cofactorSolverTestSizeTwo();
    boolean cofactorSolverTestSizeThree = cofactorSolverTestSizeThree();
    boolean sumSignTester = sumSignTester();
    boolean sumConstructor = sumConstructor();

    String resultMatrixCompressor = matrixCompressor ? "pass" : "fail";
    System.out.println("Matrix compressor: " + resultMatrixCompressor);

    String resultcofactorSolverTestSizeTwo = cofactorSolverTestSizeTwo ? "pass" : "fail";
    System.out.println("cofactorSolverTestSizeTwo: " + resultcofactorSolverTestSizeTwo);

    String resultcofactorSolverTestSizeThree = cofactorSolverTestSizeThree ? "pass" : "fail";
    System.out.println("cofactorSolverTestSizeThree: " + resultcofactorSolverTestSizeThree);

    String resultsumSignTester = sumSignTester ? "pass" : "fail";
    System.out.println("sumSignTester: " + resultsumSignTester);
    
    String resultsumConstructor = sumConstructor? "pass" : "fail";
    System.out.println("sumConstructor: "+resultsumConstructor);

    return matrixCompressor && cofactorSolverTestSizeTwo && cofactorSolverTestSizeThree
        && sumSignTester && sumConstructor;

  }

  public static void main(String[] args) {
    // runTests();
    sumDeterminantSolver();
  }
}
