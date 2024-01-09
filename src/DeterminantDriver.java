import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

/**
 * Driver class that allows a user to interact with the determinant classes
 */
public class DeterminantDriver {

  private Scanner scanner; // scanner to read user input command lines
  private int[][] matrix; // matrix to be filled

  /**
   * Constructor that just intializes the scanner
   */
  public DeterminantDriver() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Private helper method that displays the menu of options
   */
  private void displayMenu() {
    System.out.println("--------------------------------------------------------------------");
    System.out.println("1) Randomly generate a new matrix");
    System.out.println("2) Input your own matrix");
    System.out.println("3) Solve the determinant by summation");
    System.out.println("4) Solve the determinant by cofactor expansion");
    System.out.println("5) Solve the determinant by row reduction");
    System.out.println("6) Exit");
    System.out.println("Your command should only be the digit of the command you wish to run");
  }

  /**
   * Private helper method that allows a user to systematically input values that turn into the
   * future matrix.
   */
  private void userInputMatrix() {

    String command = "";
    int userSizeInput = -1;

    // simple while loop to get a valid size from the user. Size must be in [1,10]
    System.out.println("Input a number, this number will be the size of your matrix");
    while (userSizeInput > 10 || userSizeInput <= 0) {
      command = scanner.nextLine();
      try {
        int tempUserSizeInput = Integer.parseInt(command.strip());
        if (tempUserSizeInput <= 0) {
          System.out.println("The size of the matrix must be greater than or equal to 0");
        } else if (tempUserSizeInput > 10) {
          System.out
              .println("That's too big of a matrix for you to type out. I can't let you do that");
        } else
          userSizeInput = tempUserSizeInput;
      } catch (NumberFormatException e) {
        System.out.println("Please enter a valid integer");
      }
    }


    // initializes the return matrix to the proper size, and a string to help with the readability
    // of the loop to come
    int[][] returnMatrix = new int[userSizeInput][userSizeInput];
    String[] rowNth = new String[] {"first", "second", "third", "fourth", "fifth", "sixth",
        "seventh", "eighth", "ninth", "tenth"};


    // initial instructions
    System.out
        .println("To fill your matrix, type out the rows following this pattern and press enter");
    System.out.println("1, 2, 13, 79, -36, 10000, 900000");



    int count = 0; // this count only gets updated if a matrix row is updated
    while (count < userSizeInput && !command.equals("Exit")) {
      System.out.println(
          "Type in the integer array for your " + rowNth[count] + " row, or type 'Exit' to exit");

      command = scanner.nextLine();
      String[] inputArray = command.split(","); // makes a new array with the string values after
                                                // the split

      // check for the validity of the array. Checks to make sure it is the right length, and all
      // values can be parsed into an integer
      if (inputArray.length == userSizeInput) { // first checks if its the right size else error


        boolean validValues = true;
        // iterate through the array to check that all values are integers
        for (int i = 0; i < inputArray.length; i++) {
          inputArray[i] = inputArray[i].strip();
          if (!Utility.isInt(inputArray[i]))
            validValues = false;
        }
        if (validValues) {// if that last test passes

          // we set all of the values in the temporary matrix row to that of the input array
          for (int i = 0; i < inputArray.length; i++) {
            returnMatrix[count][i] = Integer.parseInt(inputArray[i]);
          }
          count++;
        } else // else statement for the integer parse array checker
          System.out.println("Please make sure all of your values are integers");
      } else { // else statement for the size checker
        System.out.println("Please make sure your matrix has " + userSizeInput + " numbers");
      }
    }

    if (command.equals("Exit")) { // now we just need to check if the user didn't give up mid-way
                                  // through
      System.out.println("You are now exiting the matrix generation, and have not made a matrix");
    } else
      this.matrix = returnMatrix;


  }

  /**
   * Main private helper method that processes user commands to use the driver. Either calls other
   * methods to help process, or does so in the method.
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
        Utility.sleep(3);
        
      } else if (command.strip().equals("2")) {
        userInputMatrix();
        if(matrix != null) {System.out.println("Here is your inputted matrix");
        Utility.printMatrix(this.matrix);}
        Utility.sleep(3);
        
      } else if (command.strip().equals("3")) {
        if (matrix == null) {
          System.out
              .println("You must initialize a matrix before attempting to find the determinant");
        Utility.sleep(2);}
        else {
          Instant start = Instant.now();
          Summation summation = new Summation(this.matrix);
          System.out.println("The determinant by summation is " + summation.getDet());
          Instant end = Instant.now();
          Duration timeElapsed = Duration.between(start, end);
          System.out.println(" This method took " + timeElapsed);
          Utility.sleep(2);
        }
      } else if (command.strip().equals("4")) {
        if (matrix == null) {
          System.out
              .println("You must initialize a matrix before attempting to find the determinant");
        Utility.sleep(2);}
        else {
          Instant start = Instant.now();
          Cofactor cofactor = new Cofactor(this.matrix);
          System.out.println("The determinant by cofactor is " + cofactor.getDet());
          Instant end = Instant.now();
          Duration timeElapsed = Duration.between(start, end);
          System.out.println(" This method took " + timeElapsed);
          Utility.sleep(2);
        }
      } else if (command.strip().equals("5")) {
        if (matrix == null)
          System.out
              .println("You must initialize a matrix before attempting to find the determinant");
        else {
          Instant start = Instant.now();
          RowReduction rowReduction = new RowReduction(this.matrix);
          System.out.println("The determinant by row reduction is " + rowReduction.getDet());
          Instant end = Instant.now();
          Duration timeElapsed = Duration.between(start, end);
          System.out.println(" This method took " + timeElapsed);
        }
      } else {
        System.out.println("Input a valid argument please");
        Utility.sleep(2);
      }
    }

    System.out.println("Thank you for joining us");
  }

  /**
   * Public method that simply starts running the class
   */
  public void run() {
    System.out.println(
        "Welcome to the determinant driver. Solve determinants using various different methods");
    Utility.sleep(1);
    processUserCommands();
    scanner.close();
  }

  /**
   * Main method. Runs the driver
   */
  public static void main(String[] args) {
    new DeterminantDriver().run();
  }

}
