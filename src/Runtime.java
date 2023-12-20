import java.util.ArrayList;

public class Runtime {

  private long[] factorialRate;
  private long[] cubicRate;
  private long[] sumAvgRate;
  private long[] cofactorAvgRate;
  private long[] rowAvgRate;

  public Runtime(int length) {
    String[] compare = initializeStringArray(length);
    Utility.printArray(compare);
    initializeTrueFactorial(length);
    initialzeTrueCubic();
    this.sumAvgRate = new long[length];
    this.cofactorAvgRate = new long[length];
    this.rowAvgRate = new long[length];
    intializeAverageArrays();
    
    Utility.printArray(this.sumAvgRate);
    Utility.printArray(this.cofactorAvgRate);
    Utility.printArray(this.rowAvgRate);
    
  }

  private void intializeAverageArrays() {
    for (int i = 0; i < factorialRate.length; i++) {
      
      long[] sumRates = new long[(int) (1500 - Math.pow(i, 3))];
      long[] cofactorRates = new long[(int) (1500 - Math.pow(i, 3))];
      long[] rowRates = new long[(int) (1500 - Math.pow(i, 3))];
      
      // Generates a number of matricies (relative to the size of the array)
      //with the seed 73, with a max value respective to the size, and the dedicated size
      ArrayList<int[][]> matricies =
          Utility.generateMatricies((int) (1500 - Math.pow(i, 3)), 77, (15 - i), i+1);;
      
      //iterates through each matrix in the generated matrix
      for(int j = 0 ; j < matricies.size(); j++) {
        long start = System.nanoTime();
        Summation summation = new Summation(matricies.get(j));
        long end = System.nanoTime();
        
        sumRates[j] = end-start;
        
        start = System.nanoTime();
        Cofactor cofactor = new Cofactor(matricies.get(j));
        end = System.nanoTime();
        
        cofactorRates[j] = end-start;
        
        try {
        start = System.nanoTime();
        RowReduction rowReduction = new RowReduction(matricies.get(j));
        end = System.nanoTime();
        }
        catch (Exception e) {
          Utility.printMatrix(matricies.get(j));
        }
        
        rowRates[j] = end-start;
      }
      
      long timeAvgSum = 0;
      for(long time : sumRates ) {
        timeAvgSum += time;
      }
      timeAvgSum /= sumRates.length;
      sumAvgRate[i] = timeAvgSum;
      
      long timeAvgCo = 0;
      for(long time : cofactorRates ) {
        timeAvgCo += time;
      }
      timeAvgCo /= cofactorRates.length;
      cofactorAvgRate[i] = timeAvgCo;
      
      long timeAvgRow = 0;
      for(long time : rowRates ) {
        timeAvgRow += time;
      }
      timeAvgRow /= rowRates.length;
      rowAvgRate[i] = timeAvgRow;
    }
  }

  private void initializeTrueFactorial(int length) {
    this.factorialRate = new long[length];
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
    Utility.printArray(this.factorialRate);
  }

  private void initialzeTrueCubic() {
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
    Utility.printArray(this.cubicRate);
  }
  
  private String[] initializeStringArray(int length) {
    String[] returnArray = new String[length];
    for(int i = 0 ; i < length; i++) {
      String str = Integer.toString(i+1);
      str += "  ";
      if(i > 4) {
        for (int j = 4 ; j < i ; j++) str += " ";
      }
      returnArray[i] = str;
    }
    
    return returnArray;
  }

  private int factorial(int n) {
    if (n == 1)
      return 1;
    return n * factorial(n - 1);
  }

  public static void main(String[] args) {
    new Runtime(8);
  }
}
