# Determinant Calculator
Java project to solve the determinant of a matrix using multiple methods  
Three classes to solve determinants, by the summation definition, cofactor expansion, and row reduction  
There is also a driver and runtime tester  

## Determinant Summation
### Runtime Complexity: O(n!)
The algorithm I used can likely be implemented a little bit better. Fundamentally the runtime complexity is O(n!), but my implementation
is likely a little bit worse due to a few unnecessary computations. This method also uses doubles, meaning it isn't largely that accurate for larger determinants,
but works well for lower values

## Cofactor Expansion
### Runtime Complexity: O(n!)
Cofactor expansion is slightly more efficient and was the method I was taught to use long ago. 
It just takes the first, row, and takes each value in the first row by itself multiplied by -1^index and multiplies it by 
the determinant of the resulting minor. It does this for every value. We do this method using recursion.

Strategically when one performs this method in real life, you would generally look for the 'easiest' row or column, to reduce
computations, but an implementation that searches for the 'easiest' row or column is likely slower at smaller matrices.
It might be worth doing though for larger matrices. This could be another fun class to try.

## Row Reduction
### Runtime Complexity: O(n^3)
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
The summation method has the worst runtime. Cofactor is slightly better. Row reduction method is obviously the best, as we can see in this image.  
Though hard to see, the row reduction method does seem consistent with O(n^3). As implemented in the runtime test class, you yourself
can see the difference in runtime.

[Runtime of Determinants Comparison.pdf](https://github.com/markstanl/DeterminantCalculator/files/13731475/Runtime.of.Determinants.Comparison.pdf)


## Other Classes
### Fraction
### Driver
### Utility
### Tester
### Runtime

