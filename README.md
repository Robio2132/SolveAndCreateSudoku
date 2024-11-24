/*
file name:      README!.txt
Authors:        Robbie Bennett
last modified:  10/29/2024
Class: CS231
Creates a Sodoku board and solves it using back tracking (contains a method to visualize the board.) 
*/


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

How to run Test.java:


Confirm that the delay variable is set to an integer between 0 and 10 in the solve and solve2 method in Sudoku.java
depending on which method you wish to test (0 will be the fastest but will not produce the visual representation of the board.)

Determine the amount of initial cells and trials to be run in the variables at the top of the Test.java program (Under the comment declared variables.)

Uncomment the type of test you wish to verify and make sure that the other tests are commented out.
    - Test1 runs the solve 1 method(backtracking each cell.)
    - Test2 runs the solve2 method(backtracking the cells with the least amount of viable options.)
    - Test3 runs the solve2 method for the amount of trials specified in the parameters above.
    - Extension Test runs the solve 3 method (single agent solve method.) Make sure to specify which amount of initial cells the program should prioritze (see comments in the method.)

Compile:  javac Test.java
Run:  java Test  

The result should be an amount of boards(n) with the success rate and loss rate printed in the terminal below.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

To run BoardTester.java (This is an extension.)


Type the following into the terminal:

Compile: javac BoardTester.java      

Run: java BoardTester
