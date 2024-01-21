# Determinant Calculator
["In mathematics, the determinant is a scalar value that is a function of the entries of a square matrix."](https://en.wikipedia.org/wiki/Determinant)
The determinant of a matrix is a value that contains important information about the square matrix it is derived from. This
project contains three different methods to find that value. Use the driver for easy access, or the runtime experiment to
see the runtime efficiency of each method.

## [Leibniz Formula](https://en.wikipedia.org/wiki/Leibniz_formula_for_determinants)
### Runtime Complexity: O(n!)
The Leibniz Formula finds the determinant as a sum of n! values, which are found using permutations.
This method was introduced to me as the definition of a determinant, and as a simple way to first prove the determinant lemmas.
This method uses doubles, making it less accurate for larger determinants but effective for smaller values.

## [Cofactor Expansion](https://en.wikipedia.org/wiki/Laplace_expansion)
### Runtime Complexity: O(n!)
Cofactor expansion is slightly more efficient.
It is found using an interesting strategy. First, choose any row or column, mentally applying a + and - sign to each value in 
your chosen tuple. Then, sum together each of the values in the row, multiplied by their sign, and the determinant of the resulting "minor" at their index.
A "minor" at a given index is found by removing the row and column at a given index, and compressing the matrix, resulting in a new minor matrix size n-1.
(For example, if we have a 3x3 matrix, and want to find the minor at the center value a22, the resulting minor is simply a 2x2 matrix, consisting of all the corner values of the original matrix).
This method is employed recursively.

Strategically, when performing this method in real life, one might choose the 'easiest' row or column to reduce computations. 
The idea here is if the value of a row is 0, you can ignore computing the resulting minor in computations.
However, an implementation searching for the 'easiest' row or column might be slower for smaller matrices.

## [Row Reduction](https://en.wikipedia.org/wiki/Gaussian_elimination)
### Runtime Complexity: O(n^3)
⚠️ IMPORTANT: Due to the transition to BigInteger, Row Reduction takes a bit longer than the original version, especially noticeable on small-size matrices. 
Row reduction is slightly more complicated than the other methods, I implore referring to the link to see how it is typically done.

My implementation starts by looking for a non-zero pivot and switching that to the current diagonal pivot.
It then divides the row such that the pivot value is 1. We then subtract each row underneath that pivot by the index row multiplied by
whatever constant is required to make the value under the pivot become 0. 
This constant is then multiplied into the running determinant value.
This is done until we reach the bottom right index.
Thus, the determinant is the final running determinant value. 


Row Reduction is the most efficient among the three methods, with a runtime of O(n^3). This runtime improvement is evident in the following table:

  
| N | O(n!)  | O(n^3) |
| --| -------| ------ |
| 1 | 1      | 1      |
| 2 | 2      | 8      |
| 3 | 6      | 27     |
| 4 | 24     | 64     |
| 5 | 120    | 125    |
| 6 | 720    | 216    |
| 7 | 5040   | 343    |
| 8 | 40320  | 512    |

![O(n!)](https://github.com/markstanl/DeterminantCalculator/assets/146277800/cf2d904b-c21d-41be-83b6-8f148f4b1943)

O(n^3) growth is significantly slower for larger matrices. This class uses the Fraction class as a replacement for numbers.


## Tested Runtime  
The summation (Leibniz) method has the worst runtime, while the cofactor is slightly better. The row reduction method is the best, consistently following O(n^3). 
The runtime experiment class demonstrates the difference based on your hardware.

[Runtime of Determinants Comparison.pdf](https://github.com/markstanl/DeterminantCalculator/files/13731475/Runtime.of.Determinants.Comparison.pdf)


## Other Classes
### Fraction
This class manages numbers in row reduction. 
It uses BigInteger to handle improper fractions, avoiding rounding errors common in doubles. 
It includes arithmetic functions, comparability methods, and integer and double estimation methods.

### Driver
This class enables user interaction with determinant-solving classes. 
Users can either generate a matrix randomly or input their own. 
They can then compute the determinant with any of the three methods, and the runtime is printed.

### Runtime
The runtime experiment class can be used on any device to check the average runtime of every method, used on every size of matrix. There are clear
details in the main method to assist a user in using it. The method tests each different size and averages out the time to calculate a specific amount of 
determinants.

### Utility
The utility simplifies common functions, including printing matrices and arrays, converting a matrix to a different value, checking if a matrix is square, checking if two double values are close enough, a gcd and lcm calculator, a method to sleep for a specified time, and methods to randomly compute matrices.
 
### Tester
The tester class was used in concurrence with the development of every class and method to make sure that everything worked as expected.


