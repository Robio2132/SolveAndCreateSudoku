/*
file name:      BoardTester.java
Authors:        Robbie Bennett
last modified:  10/29/2024
Class: CS231
How To Run:    1. javac BoardTester.java      2. java BoardTester
*/


public class BoardTester {

        public static void main(String[] args) {
        //Calls the test methods.

        testBoardInitial();
        testBoardWithLockedCells();
        testSetAndGet();
        testValidValue();
        testNumLocked();
        testValidSolution();
    }

    public static void testBoardInitial() {
        //Tests the board initial constructor.

        Board board = new Board();
        System.out.println("Test Board Initialization:");
        System.out.println(board);
    }

    public static void testBoardWithLockedCells() {
        //Tests the other constructor.

        Board board = new Board(5); 
        System.out.println("Test Board with Locked Cells:");
        System.out.println(board);
        System.out.println("Number of locked cells: " + board.numLocked());
    }

    public static void testSetAndGet() {
        //Tests the set and get methods.

        Board board = new Board();
        board.set(0, 0, 5);
        System.out.println(board);
        System.out.println("Test Set and Get:");
        System.out.println("Value at (0,0): " + board.get(0, 0).getValue());
    }

    public static void testValidValue() {
        //Tests the valid value method. 

        Board board = new Board();
        board.set(0, 0, 5);
        boolean valid = board.validValue(0, 1, 5);
        System.out.println("Test invalid Value: " + (valid ));
    }

    public static void testNumLocked() {
        //Tests the numLocked method.

        Board board = new Board(5);
        System.out.println("Test Num Locked:");
        System.out.println("Number of locked cells: " + board.numLocked());
    }

    public static void testValidSolution() {
        //Tests the valid solution method.

        Board board = new Board();
        board.set(0, 0, 5);
        board.set(0, 1, 3);
       
        System.out.println("Test Invalid Solution: " + (board.validSolution()));
        System.out.println("Test Valid Solution: " + (board.validSolution()));
    }
}