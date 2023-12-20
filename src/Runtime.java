import java.util.ArrayList;

/**
 * This class tests the runtime efficiency of all the algorithms generated
 */
public class Runtime {

  private long[] factorialRate;// the default factorial base rate
  private long[] cubicRate;// the default cubic base rate
  private long[] sumAvgRate;// the actual summation rate
  private long[] cofactorAvgRate;// the actual cofactor rate
  private long[] rowAvgRate;// the actual row reduction rate
  private long[] eachTimeTaken;// the time taken for any iteration to generate
  private long timeTaken;// the time it takes for this entire method to generate
  private int length;// the length we wish to test to
  private double performanceMultiplier; // a double multiplier the allows for more determinants to
                                        // be searched for if the performance of the computer
                                        // allows

  /**
   * Constructor for this instance. initializes all of the time taken arrays
   * 
   * @param performanceMultiplier a double multiplying the number of matrices tested to check for
   *                              efficiency;
   * @param length                the maximum length you want to iterate up until
   */
  public Runtime(int length, double performanceMultiplier) {
    if (performanceMultiplier < 0)
      throw new IllegalArgumentException("the performance multiplier must be positive");
    if (length < 0)
      throw new IllegalArgumentException("the length must be positive");
    long constructionStartTime = System.currentTimeMillis();
    this.length = length;
    this.performanceMultiplier = performanceMultiplier;
    initializeTrueFactorial();
    initializeTrueCubic();
    this.sumAvgRate = new long[length];
    this.cofactorAvgRate = new long[length];
    this.rowAvgRate = new long[length];
    this.eachTimeTaken = new long[length];
    intializeAverageArrays();
    this.timeTaken = (System.currentTimeMillis() - constructionStartTime) / 1000;

  }

  /**
   * This method initializes all of the average time arrays. It tests the determinant method against
   * at most 1500 different matricies, and averages that out for each size of matrix, by each
   * methodm and adds the average to an array
   */
  private void intializeAverageArrays() {
    // iterates through every starting size
    for (int i = 0; i < factorialRate.length; i++) {
      long startTimeSeconds = System.currentTimeMillis();

      long[] sumRates = new long[(int) (determinNum(i + 1))];
      long[] cofactorRates = new long[(int) (determinNum(i + 1))];
      long[] rowRates = new long[(int) (determinNum(i + 1))];

      // Generates a number of matrices (relative to the size of the array)
      // with the seed 73, with a max value respective to the size, and the dedicated size
      ArrayList<int[][]> matricies =
          Utility.generateMatricies((int) (determinNum(i + 1)), 77, (15 - i), i + 1);;

      // iterates through each matrix in the generated matrix
      for (int j = 0; j < matricies.size(); j++) {
        // and tracks the time it takes to solve the determinant, and adds it to an array
        long start = System.nanoTime();
        Summation summation = new Summation(matricies.get(j));
        long end = System.nanoTime();

        sumRates[j] = end - start;

        start = System.nanoTime();
        Cofactor cofactor = new Cofactor(matricies.get(j));
        end = System.nanoTime();

        cofactorRates[j] = end - start;

        start = System.nanoTime();
        RowReduction rowReduction = new RowReduction(matricies.get(j));
        end = System.nanoTime();

        rowRates[j] = end - start;
      }

      // and finally calculates the average time it took to do all of the matrices, and adds such
      // to the avg time array
      long timeAvgSum = 0;
      for (long time : sumRates) {
        timeAvgSum += time;
      }
      timeAvgSum /= sumRates.length;
      sumAvgRate[i] = timeAvgSum;

      long timeAvgCo = 0;
      for (long time : cofactorRates) {
        timeAvgCo += time;
      }
      timeAvgCo /= cofactorRates.length;
      cofactorAvgRate[i] = timeAvgCo;

      long timeAvgRow = 0;
      for (long time : rowRates) {
        timeAvgRow += time;
      }
      timeAvgRow /= rowRates.length;
      rowAvgRate[i] = timeAvgRow;

      this.eachTimeTaken[i] = (System.currentTimeMillis() - startTimeSeconds) / 1000;
    }
  }

  /**
   * private helper method to make sure we don't overload on time for any particular method. 1500
   * matrices is quick for a 1x1 matrix, but not a 10x10
   * 
   * @param the integer of the current size iteration
   * @return the integer to be subtracted from 1500 for # of generations
   */
  private double determinNum(int i) {
    switch (i) {
      case 1:
        return 1000000 * this.performanceMultiplier;
      case (2):
        return 700000 * this.performanceMultiplier;
      case (3):
        return 300000 * this.performanceMultiplier;
      case (4):
        return 80000 * this.performanceMultiplier;
      case (5):
        return 30000 * this.performanceMultiplier;
      case (6):
        return 5000 * this.performanceMultiplier;
      case (7):
        return 500 * this.performanceMultiplier;
      case (8):
        return 60 * this.performanceMultiplier;
      case (9):
        return 5 * this.performanceMultiplier;
      case (10):
        return 1 * this.performanceMultiplier;
      case (11):
        return 1 * this.performanceMultiplier;
      default:
        return 1 * this.performanceMultiplier;
    }

  }

  /**
   * This method initializes the true factorial time array, as to allow us to compare time
   * expectancies
   */
  private void initializeTrueFactorial() {
    this.factorialRate = new long[this.length];
    for (int i = 0; i < this.factorialRate.length; i++) {
      int runs = factorial(i + 1);
      long start = System.nanoTime();
      int arbitraryFunction = 16;
      for (int j = 0; j < runs; j++) {
        arbitraryFunction *= 1;
      }

      long finish = System.nanoTime();
      this.factorialRate[i] = finish - start;
    }
  }

  /**
   * This method initializes the true cubic time array
   */
  private void initializeTrueCubic() {
    this.cubicRate = new long[factorialRate.length];
    for (int i = 0; i < this.factorialRate.length; i++) {
      int runs = (int) Math.pow((i + 1), 3);
      long start = System.nanoTime();

      int arbitraryFunction = 16;
      for (int j = 0; j < runs; j++) {
        arbitraryFunction *= 1;
      }

      long finish = System.nanoTime();
      this.cubicRate[i] = finish - start;
    }
  }

  /**
   * Private method to recursively find the factorial, as for some reason it is not a part of Math
   * 
   * @param n the number we wish to find the factorial of
   * @return the factorial of n
   */
  private int factorial(int n) {
    if (n == 1)
      return 1;
    return n * factorial(n - 1);
  }

  /**
   * toString method Used to visualize all of the data gathered in this method. follows the
   * following idea
   * 
   * Size 1 took .01 seconds to find the determinant
   * 
   */
  public String toString() {
    String returnStr = "";
    for (int i = 0; i < this.length; i++) {
      returnStr += "Size " + Integer.toString(i + 1) + " took " + Long.toString(eachTimeTaken[i])
          + " seconds to find the determinant, and ran with " + determinNum(i + 1) + " matricies"
          + "\r\n";
      returnStr += "   Factorial Rate: " + factorialRate[i] + "\r\n";
      returnStr += "   Cubic Rate:     " + cubicRate[i] + "\r\n";
      returnStr += "   Summation Rate: " + sumAvgRate[i] + "\r\n";
      returnStr += "   Cofactor Rate:  " + cofactorAvgRate[i] + "\r\n";
      returnStr += "   Gaussian Rate:  " + rowAvgRate[i] + "\r\n";
    }
    returnStr += "This class took " + Long.toString(timeTaken) + " seconds to create";

    return returnStr;
  }

  /**
   * private helper method to print all of the arrays in an array visualization
   */
  public void printArrayForm() {
    System.out.println(
        "In order: factorial default rate, cubic default rate, summation avg rate, cofactor avg rate, row reducation avg rate");
    Utility.printArray(factorialRate);
    Utility.printArray(cubicRate);
    Utility.printArray(sumAvgRate);
    Utility.printArray(cofactorAvgRate);
    Utility.printArray(rowAvgRate);
  }

  /**
   * This class is used to test runtime efficiency
   * 
   * @param args
   */
  public static void main(String[] args) {
    // The first parameter is the size of the maximum matrices, the second is a "performance"
    // multiplier. This changes the total number of matrices tested for each different size.
    // 1 means normal, 0 means none. This class at 1 should take around first parameter seconds. if
    // it takes more than this, it is recommended to change the second parameter
    Runtime john = new Runtime(10, 1.0);
    System.out.println(john);
  }
}
