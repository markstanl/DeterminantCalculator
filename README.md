# DeterminantCalculator
Java project to solve the determinant of a matrix using multiple different methods
Currently uses the definition of a determinant summation, and cofactor expansion to find the determinant of a matrix
Working on using row reduction to use 

##Determinant Summation
Runtime Complexity: O(n^2)
This is a bit slower than the cofactor expansion, likely by my own doing, as it needs to calculate all permutations, which
takes O(n^2) n being the length of the matrix, then solve the signs for the permutations, which is O(n^2) again, and then sums the matrix
up from there, taking O(n^2)
##Cofactor Expansion
Runtime Complexity: O(n^2)
This one is slightly more efficent, as it just takes the first, row, and multiplies each constant by itself multiplied by -1^index by the 
determininant of the resulting minor. It does this for every value. 
