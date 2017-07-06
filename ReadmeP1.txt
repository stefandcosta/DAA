------------------------------------------------------------------------------------------
STEFAN SIMON ANTHONY D'COSTA
SANTA CLARA UNIVERSITY
COEN 279
W1284923
------------------------------------------------------------------------------------------
Program Constraints:-
*Maximum input value for N is 100.

------------------------------------------------------------------------------------------

Output format:-
x
Nmax
x^N
x^N
.
.
.

------------------------------------------------------------------------------------------

Algorithm Used:-
Divide and Conquer using Recursion

------------------------------------------------------------------------------------------

Time Complexity:-
O(log n)
Recurence Relation: M(n)=M(n/2)+2 for worst case
By Master's Theorem: a=1, b=2, d=0
Thus, a=b^d
Hence, complexity is O(n^d logn)=O(n^0 logn)=O(log n)

------------------------------------------------------------------------------------------

Space Complexity:-
O(1)
No arrays were used compute and only 1 temporary variable was used.

------------------------------------------------------------------------------------------
