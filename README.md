# Determinant Calculator
Java project to solve the determinant of a matrix using multiple different methods
Currently uses the definition of a determinant summation, and cofactor expansion to find the determinant of a matrix
Working on using row reduction to use 

## Determinant Summation
Runtime Complexity: O(n!)
This is a bit slower than the cofactor expansion, likely by my own doing, as it needs to calculate all permutations, which
takes O(n!) n being the length of the matrix, then solve the signs for the permutations, which is O(n!), n being the length of a matrix 
again, and then sums the matrix up from there

## Cofactor Expansion
Runtime Complexity: O(n!)
This one is slightly more efficent, as it just takes the first, row, and multiplies each constant by itself multiplied by -1^index by the 
determininant of the resulting minor. It does this for every value. 

## Row Reduction
Runtime Complexity: O(n^3)

| N | O(n!)  | O(n^3) |
| --| -------| ------ |
| 1 | 1      | 1      |
| 2 | 2      | 8      |
| 3 | 6      | 27     |
| 4 | 24     | 64     |
| 5 | 120    | 125    |
| 6 | 720    | 216    |
| 7 | 5040   | 343    |

O(n^3) is much quicker with a matrix with greater than 6 rows and columns
