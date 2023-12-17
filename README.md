# Determinant Calculator
Java project to solve the determinant of a matrix using multiple different methods
Currently uses the definition of a determinant summation, and cofactor expansion to find the determinant of a matrix
Working on using row reduction to use 

## Determinant Summation
### Runtime Complexity: O(n!)
The algorithm I used can likely be implemented a little bit better. Fundamentally the runtime complexity is O(n!), but my implementation
takes O(n!) to calculate the permutations, O(n!) to calculate the signs of each permutation, then O(n!) again to calculate the sums.
This can be made better by finding the sign during the permutation generator

## Cofactor Expansion
### Runtime Complexity: O(n!)
This one is slightly more efficent, as it just takes the first, row, and multiplies each constant by itself multiplied by -1^index by the 
determininant of the resulting minor. It does this for every value. 

## Row Reduction
### Runtime Complexity: O(n^3)
This runtime is much better than its predecessors for larger matricies. 

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

O(n^3) obviously grows much slower for larger matricies

##Other Classes
I implemented a utility class with various helpful methods, so I don't need to create the same method in 
every class
I also implemented a fraction class which is used in the row reduction. This was to make everything look a little nicer
This method performs division, and though it would work with doubles, It may end with some weird number, so I used 
fractions
I plan to implement a runtime tester method to visually see the different runtimes
