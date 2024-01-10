import java.math.BigInteger;

/**
 * Fraction class. Sets up a new number system. Has a numerator and denominator, as well as various
 * methods and operators one would expected with a fraction
 */
public class Fraction implements Comparable<Fraction> {

  private BigInteger numerator; // the numerator
  private BigInteger denominator; // the denominator

  /**
   * First constructor for a fraction. Takes a single input, the numerator
   * 
   * @param numerator the new numerator
   */
  public Fraction(int numerator) {
    this.numerator = new BigInteger(Integer.toString(numerator));
    this.denominator = BigInteger.ONE;
    reduce();
  }

  /**
   * Second constructor for a fraction, takes a numerator and a denominator
   * 
   * @param numerator   the numerator of the fraction
   * @param denominator the denominator of the fraction
   */
  public Fraction(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("cannot divide by zero");
    }
    this.numerator = new BigInteger(Integer.toString(numerator));
    this.denominator = new BigInteger(Integer.toString(denominator));
    reduce();
  }

  public Fraction(BigInteger numerator, BigInteger denominator) {
    if (denominator.equals(BigInteger.ZERO)) {
      throw new IllegalArgumentException("cannot divide by zero");
    }
    this.numerator = numerator;
    this.denominator = denominator;
    reduce();
  }

  /**
   * Multiplies the fraction by an integer
   * 
   * @param toMult the integer to multiply this fraction by
   */
  public void multiply(int toMult) {
    this.numerator = numerator.multiply(new BigInteger(Integer.toString(toMult)));
    reduce();
  }

  /**
   * Multiplies this fraction by another fraction
   * 
   * @param toMult the fraction to multiply by
   */
  public void multiply(Fraction toMult) {
    this.numerator = this.numerator.multiply(toMult.numerator);
    this.denominator = this.denominator.multiply(toMult.denominator);
    reduce();
  }

  /**
   * Divides a fraction by another fraction. Multiplies by the reciprocal
   * 
   * @param toDiv the fraction to divide by
   */
  public void divide(Fraction toDiv) {
    if (toDiv.equals(new Fraction(0)))
      throw new IllegalArgumentException("cannot divide by zero");
    multiply(invert(toDiv));
  }

  /**
   * Divides the fraction by an integer
   * 
   * @param divisor the integer to divide the fraction by
   */
  public void divide(int divisor) {
    if (divisor == 0)
      throw new IllegalArgumentException("cannot divide by zero");
    this.denominator = this.denominator.multiply(new BigInteger(Integer.toString(divisor)));
    reduce();
  }

  /**
   * Makes an inverse of a fraction
   * 
   * @param toInvert the fraction to be inverted
   * @return a new fraction that is the inverse of a copy of the fraction
   */
  private Fraction invert(Fraction toInvert) {
    Fraction returnInvert = toInvert.copy();
    BigInteger save = returnInvert.denominator;
    returnInvert.denominator = returnInvert.numerator;
    returnInvert.numerator = save;
    returnInvert.reduce();
    return returnInvert;
  }

  /**
   * Inverts the function. The other function returns a copy of an inverse
   */
  public void invert() {
    BigInteger save = this.denominator;
    this.denominator = this.numerator;
    this.numerator = save;
    reduce();
  }

  /**
   * Subtracts this fraction by another fraction
   * 
   * @param subtractor the fraction we want to subtract from the
   */
  public void subtract(Fraction subtractor) {
    Fraction subtractorCopy = subtractor.copy();
    BigInteger lcm = Utility.lcm(this.denominator, subtractorCopy.denominator);
    this.numerator = this.numerator.multiply(lcm.divide(this.denominator));
    subtractorCopy.numerator =
        subtractorCopy.numerator.multiply(lcm.divide(subtractorCopy.denominator));
    this.denominator = this.denominator.multiply(lcm.divide(this.denominator));
    subtractorCopy.denominator =
        subtractorCopy.denominator.multiply(lcm.divide(subtractorCopy.denominator));
    this.numerator = this.numerator.subtract(subtractorCopy.numerator);
    reduce();
  }

  /**
   * returns the double estimation of this fraction
   * 
   * @return
   */
  public double doubleVal() {
    return (this.numerator.doubleValue()) / (this.denominator.doubleValue());
  }

  /**
   * returns a copy of this fraction
   * 
   * @return
   */
  public Fraction copy() {
    return new Fraction(this.numerator, this.denominator);
  }

  /**
   * reduces this fraction
   */
  private void reduce() {
    if (this.numerator.equals(BigInteger.ZERO) || this.denominator.equals(BigInteger.ZERO)) {
      this.denominator = BigInteger.ONE;
      this.numerator = BigInteger.ZERO;
    }
    
    BigInteger gcd = BigInteger.ONE;

    //gcd has issues with negatives in the denominator, here is where we deal with them
    if (this.denominator.compareTo(BigInteger.ZERO) < 0) {
      BigInteger tempDenominator = this.denominator;
      
      if(this.denominator.compareTo(BigInteger.ZERO) < 0) {
        BigInteger tempMult = new BigInteger("-1");
        tempDenominator = tempDenominator.multiply(tempMult);
      }
      
      gcd = Utility.gcd(numerator, tempDenominator);

      if(this.denominator.compareTo(BigInteger.ZERO) < 0) {
        gcd.multiply(new BigInteger("-1"));
      }
    }
    else {
    gcd = this.gcd();}
    
    

    if (!gcd.equals(BigInteger.ONE)) {
      this.numerator = this.numerator.divide(gcd);
      this.denominator = this.denominator.divide(gcd);
    }
    if (denominator.compareTo(BigInteger.ZERO) < 0) {
      this.numerator = this.numerator.multiply(new BigInteger("-1"));
      this.denominator = this.denominator.multiply(new BigInteger("-1"));
    }
  }

  /**
   * Finds he greatest common denominator
   * 
   * @return
   */
  private BigInteger gcd() {
    return Utility.gcd(this.numerator, this.denominator);
  }

  /**
   * Getter for the denominator
   * 
   * @return the integer value for the denominator
   */
  public BigInteger getDenom() {
    return this.denominator;
  }

  /**
   * returns a the integer value of the fraction
   * 
   * @return the integer value of the fraction
   * @throws IllegalStateException if it cannot be parsed into an integer
   */
  public BigInteger toInt() {
    if (!this.denominator.equals(BigInteger.ONE))
      throw new IllegalStateException("mus'nt have a denominator");
    else
      return this.numerator;
  }

  /**
   * Returns a string representation of this fraction, in the following form Numerator/Denominator
   * unless the denominator is 1 Ex. 8/3 or 8
   * 
   * @return a string representation of this fraction
   */
  @Override
  public String toString() {
    // if the denominator is 1, no need to do any fancy things, return 1
    if (this.denominator.equals(BigInteger.ONE))
      return (this.numerator.toString());
    else
      return (this.numerator.toString()) + "/" + (this.denominator.toString());
  }

  /**
   * Checks to see if two fractions are equal
   * 
   * @param other the fraction we are comparing this instance to
   * @return true if the fractions are equal, false otherwise
   */
  public boolean equals(Fraction other) {
    return this.numerator.equals(other.numerator) && this.denominator.equals(other.denominator);
  }

  /**
   * Compares this fraction to another fraction
   * 
   * @returns 0 if the fractions are the same, 1 if this value is greater than the other, and -1 if
   *          the other value is greater
   */
  @Override
  public int compareTo(Fraction other) {
    if (this.numerator == other.numerator && this.denominator == other.denominator)
      return 0;
    if (this.doubleVal() < other.doubleVal())
      return -1;
    return 1;
  }

}
