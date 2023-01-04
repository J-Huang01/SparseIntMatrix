## CSCI 1933 
## Project2: SparseIntMatrix
## 3/30/2022

#### PART V. 
(a) For a square matrix of N x N elements with m non-zero elements, how much memory is required for the SparseIntMatrix implementation? How much for a standard 2D array implementation?
(b) For a square matrix, assume N=100,000 and m=1,000,000. Is the SparseIntMatrix implementation more space-efficient, and if so, by how much? (i.e. was it worth all of the effort you just went through implementing it?) For what value of m does the 2D array implementation become more space-efficient than the SparseIntMatrix implementation?

Answer:
(a). Since row and column labels, data, row and column links all require one memory unit, storing a matrix element requires a total of 5 memory units. 
	For the last element in a row or column, there will be one less memory unit that is they don't store the next link element node. 
	For an N*N matrix with m non-zero elements, the memory required is 5m-2N. 
	For a 2D array, each element requires one memory unit and the memory required is N^2.

(b). When N = 100,000 and m = 1,000,000, the SparseIntMatrix implementation requires 5,000,000 - 200,000 = 4,800,000 units of memory according to the above equation. 2D array implementation requires (100,000)^2 = 10,000,000,000 units of memory. So SparseIntMatrix is more space-efficient, saving about 2083 times more space. 
	When 5m - 2N > N^2, the 2D array implementation is more space-efficient and can be derived as m > (N^2 + 2N)/5. If this relationship exists between m and N, the 2D array is more space-efficient. When N=100,000 and only m>2,000,040,000, 2D array is more space-efficient, but obviously m could not bigger than N.

#### Classes we created and edited:

• MatrixEntry.java
• MatrixEntryTest.java
• SparseIntMatrix.java
• SparseIntMatrixTest.java

#### How to compile and run your program

Using the run function that comes with intelliJ, the main method is in SparseIntMatrixTest class. 

For the example files: 
1. matrix1_data.txt is going to show Goldy;
2. and then matrix2_data.txt is a noise background, but when minus(matrix2_noise.txt) is applied, UMN logos shows up.

For the MatrixEntryTest.java:
	we already use print statement to show our final outcome.

#### Additional features that you implemented (if applicable)

1. Our method of setElement() in SparseIntMatrix.java. That's aim to adding elements is split into adding column elements and adding row elements, so that the two helper function methods are still boolean, but it helps us to simplify the method of setting	 elements better.
#### Any outside sources (aside from course resources) consulted for ideas used in the project, in the format:
We do not use any outside sources.

Statement: I certify that the information contained in this README file is complete and accurate. I have both read and followed the rules described in the “Course Policies” section of the course syllabus.
