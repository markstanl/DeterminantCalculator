import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

public class DeterminantDriver {

  private Scanner scanner; // scanner to read user input command lines
  private int[][] matrix;

  public DeterminantDriver() {
    this.scanner = new Scanner(System.in);
  }

  private void displayMenu() {
    System.out.println("--------------------------------------------------------------------");
    System.out.println("1) Make a new matrix");
    System.out.println("2) Solve it by summation");
    System.out.println("3) Solve it by cofactor expansion");
    System.out.println("4) Solve it by row reduction");
    System.out.println("5) Exit");
    System.out.println("Your command should only be the digit of the command you wish to run");
  }

  // I didn't like how this looked. Can fix later
  /*
   * private void generateMatrix() { System.out.println("----------------------------");
   * System.out.println("1) Generate a random matrix"); System.out.println("2) Input a matrix");
   * System.out.println("Only enter the digit");
   * 
   * String promptCommandLine = "ENTER COMMAND: "; System.out.print(promptCommandLine); String
   * command = scanner.nextLine();
   * 
   * if (command.strip().equals("1")){ this.matrix = Utility.generateMatricies(1).get(0);
   * Utility.printMatrix(matrix); } else if(command.strip().equals("2")){
   * System.out.println("enter the values of the matrix as follows");
   * System.out.println("a11, a12, ..., a1n     then hit enter");
   * System.out.println("do so n times"); System.out.println("ex. 1, 2 (enter)");
   * System.out.println("    3, 4");
   * 
   * command = scanner.nextLine(); String[] strArray = command.split(",");
   * 
   * boolean everythingWorked = true;
   * 
   * double[][] matrix = new double[strArray.length][strArray.length];
   * 
   * for(int i = 0 ; i < strArray.length ; i++) { if(i == 0) { for(int k = 0 ; k < strArray.length ;
   * k++) { strArray[k] = strArray[k].strip();
   * 
   * try{ Double.parseDouble(strArray[k]); } catch (Exception e){
   * System.out.println("you did something worng. Try again!"); everythingWorked = false; break; } }
   * for(int j = 0 ; j < strArray.length ; j++) { matrix[i][j] = Integer.parseInt(strArray[j]); } }
   * else {
   * 
   * } }
   * 
   * if(everythingWorked) { System.out.println("Everything worked"); Utility.printMatrix(matrix); }
   * } }
   */

  private void processUserCommands() {

    String command = "";

    while (!command.strip().equals("5")) {
      displayMenu();

      String promptCommandLine = "ENTER COMMAND: ";
      System.out.print(promptCommandLine);
      command = scanner.nextLine();


      if (command.strip().equals("1")) {
        this.matrix = Utility.generateMatricies(1).get(0);
        System.out.println("Here is your generated matrix");
        Utility.printMatrix(matrix);
      } else if (command.strip().equals("2")) {

        Instant start = Instant.now();
        Summation summation = new Summation(this.matrix);
        System.out.println("The determinant by summation is " + summation.getDet());
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println(" This method took " + timeElapsed);

      } else if (command.strip().equals("3")) {

        Instant start = Instant.now();
        Cofactor cofactor = new Cofactor(this.matrix);
        System.out.println("The determinant by cofactor is " + cofactor.getDet());
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println(" This method took " + timeElapsed);

      } else if (command.strip().equals("4")) {
        
        Instant start = Instant.now();
        RowReduction rowReduction = new RowReduction(this.matrix);
        System.out.println("The determinant by RowReduction is " + rowReduction.getDet());
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println(" This method took " + timeElapsed);
        
      } else {
        System.out.println("Input a valid argument please");
      }
    }

    System.out.println("Thank you for joining us");
  }

  public void run() {
    System.out.println(
        "Welcome to the determinant driver. Solve determinants using various different methods");
    processUserCommands();
    scanner.close();
  }

  public static void main(String[] args) {
    new DeterminantDriver().run();
  }

}
