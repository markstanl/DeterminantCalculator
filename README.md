# Determinant Calculator
Java project to solve the determinant of a matrix using multiple methods  
Three classes to solve determinants, by the Leibniz formula, cofactor expansion, and row reduction  
There is also a driver and runtime tester  

## [Leibniz Formula](https://en.wikipedia.org/wiki/Leibniz_formula_for_determinants)
### Runtime Complexity: O(n!)
The algorithm I used can likely be implemented a little bit better. Fundamentally the runtime complexity is O(n!), but my implementation
is likely a little bit worse due to a few unnecessary computations. This method also uses doubles, meaning it isn't largely that accurate for larger determinants,
but works well for lower values

## [Cofactor Expansion](https://en.wikipedia.org/wiki/Laplace_expansion)
### Runtime Complexity: O(n!)
Cofactor expansion is slightly more efficient and was the method I was taught to use long ago. 
It just takes the first, row, and takes each value in the first row by itself multiplied by -1^index and multiplies it by 
the determinant of the resulting minor. It does this for every value. We do this method using recursion.

Strategically when one performs this method in real life, you would generally look for the 'easiest' row or column, to reduce
computations, but an implementation that searches for the 'easiest' row or column is likely slower at smaller matrices.
It might be worth doing though for larger matrices. This could be another fun class to try.

## [Row Reduction](https://en.wikipedia.org/wiki/Gaussian_elimination)
### Runtime Complexity: O(n^3)
@IMPORTANT Because of the transition to BigInteger, Row Reduction takes a little bit longer than it did originally. This is most apparent on small-size matrices. 
The Row Reduction is the best implementation out of the 3. It has a runtime of O(n^3), significantly better than the aforementioned, as we
can see here: 

  
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

O(n^3) grows much slower for larger matrices. This class uses the Fraction class as a replacement for numbers. More details on that later.
We first reduce the first row and column so that the pivot (index of the matrix where the column index = row index) is 1. We then temporarily
multiply the row by the value in the next row, same column, and then subtract each value in the row below it by the first row. We repeat this
process until each row has been reduced, and then move on to the next pivot on the diagonal. The product of the number the diagonals initially get 
divided by is the determinant.

There are also some extra checks to get done early involving a determinant of 0, but other than that it is a great runtime and uses BigInteger to store lots of data.



## Tested Runtime  
The summation method has the worst runtime. The cofactor is slightly better. The row reduction method is the best, as we can see in this image.  
Though hard to see, the row reduction method does seem consistent with O(n^3). As implemented in the runtime test class, you
can see the difference in runtime based on your hardware.

[Runtime of Determinants Comparison.pdf](https://github.com/markstanl/DeterminantCalculator/files/13731475/Runtime.of.Determinants.Comparison.pdf)


## Other Classes
### Fraction
This class is how we deal with numbers in row reduction. Because there will necessarily be some improper fraction, we don't want to use doubles
as there will be some rounding error that messes up the final integer result. This class implements BigIntegers to be used as the numerator and 
denominator. We then implement a few methods, including arithmetic functions, comparability methods, and some integer and double estimation methods. 
This makes the result much easier than having to deal with weird double rounding errors.  

### Driver
This class allows a user to interact with all of the determinant-solving classes. We allow the user to either randomly generate a matrix, or allow the user to
input their own matrix. They can then compute the determinant with any one of the three methods, and the runtime taken to compute the determinant is printed.  
### Utility
The utility simplifies a few common functions we need to perform. Including printing the matrix and arrays, converting a matrix to a different value,
 checking if a matrix is square, checking if two double values are close enough to reasonably conclude they computed the same number, a gcd and lcm calculator,
 a method to sleep for a certain amount of time, and finally some methods to randomly compute matrices.  
 
### Tester
The tester class was used in concurrence with the development of every class and method to make sure that everything worked as expected.
### Runtime
The runtime experiment class can be used on any device to check the average runtime of every method, used on every size of matrix. There are clear
details in the main method to assist a user in using it. The method tests each different size and averages out the time to calculate a specific amount of 
determinants.

