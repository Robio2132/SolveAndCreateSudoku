/*
file name:      Sudoku.java
Authors:        Robbie Bennett
last modified:  10/29/2024
Class: CS231
*/

import java.util.ArrayList;
import java.util.Random;

public class Sudoku {

    public Board board;
    private LandscapeDisplay ld;

    public Sudoku(int preFilledCells) {
        // Constructor that creates a board with
        // some number of pre-determined randomly placed values.

        this.board = new Board(preFilledCells);
        this.ld = new LandscapeDisplay(board);
    }

    public int findNextValue(int row, int col) {
        // Finds the next value.

        // Get the current value at the specified cell
        int currentValue = this.board.get(row, col).getValue();

        // Try values starting from currentValue + 1 to 9
        for (int value = currentValue + 1; value <= 9; value++) {
            // System.out.println("Trying " + value + " at " + row + ", " + col);
            if (this.board.validValue(row, col, value)) {
                return value; // Return the first valid value found
            }
        }
        return 0; // Return 0 if no valid values are found
    }

    public Cell findNextCell() {
        // Iterate through the board to find the next empty cell

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                Cell cell = board.get(row, col);
                if (cell.getValue() == 0) { // Check if the cell is empty
                    int nextValue = findNextValue(row, col);
                    if (nextValue != 0) {
                        cell.setValue(nextValue); // Update cell with the valid value
                        return cell; // Return the updated cell
                    } else {
                        return null; // No valid value found, return null
                    }
                }
            }
        }
        return null; // Return null if no empty cells are found
    }

    public Cell findNextCell2() {
        // Initialize variables to track the next cell with the least options
        Cell nextCell = null;
        int minOptions = Integer.MAX_VALUE;

        // Iterate through the board to find the next empty cell
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                Cell cell = board.get(row, col);
                if (cell.getValue() == 0) { // Check if the cell is empty
                    int optionsCount = 0;

                    // Count valid options for this cell
                    for (int value = 1; value <= 9; value++) {
                        if (board.validValue(row, col, value)) {
                            optionsCount++;
                        }
                    }

                    // Update if this cell has fewer options than the current minimum
                    if (optionsCount < minOptions) {
                        // System.out.println("Trying cell at  " + row + " " + col);
                        minOptions = optionsCount;
                        nextCell = cell;
                    }

                }
            }
        }

        int nextValue = findNextValue(nextCell.getRow(), nextCell.getCol());
        if (nextValue != 0) {
            nextCell.setValue(nextValue); // Update cell with the valid value
            return nextCell; // Return the updated cell
        } else {
            return null; // No valid value found, return null
        }

    }

    public boolean solve() {
        // Solves the puzzle by backtracking each cell to find a viable option.

        LinkedList<Cell> stack = new LinkedList<>();
        int unspecifiedCellsCount = 0;
        int delay = 10;

        // Count the number of unspecified (empty) cells
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                if (board.value(row, col) == 0) {
                    unspecifiedCellsCount++;
                }
            }
        }

        int iterationCap = 10000;
        // Start solving the Sudoku puzzle
        while (stack.size() < unspecifiedCellsCount  && iterationCap > 0) {
            iterationCap--;

            Cell next = findNextCell();
            System.out.println("next: " + next);


            // Backtracking loop
            while (next == null && !stack.isEmpty()) {
                Cell poppedCell = stack.pop();
                int nextValue = findNextValue(poppedCell.getRow(), poppedCell.getCol());

                // If a valid value is found, set it and update next
                if (nextValue != 0) {
                    poppedCell.setValue(nextValue);
                    next = poppedCell;
                } else {
                    poppedCell.setValue(0); // Reset if no valid value is found
                }
            }

            // If no next cell can be found, it means all possibilities are exhausted
            if (next == null) {
                return false; // Puzzle cannot be solved
            } else {
                stack.push(next); // Push the current cell onto the stack
            }

            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    System.out.println("Got Interupted.");

                }

                if (ld != null) {
                    ld.repaint();
                }
            }
        }

        board.changedFinished(true);
        return true; // The board has been successfully solved
    }

    public boolean solve2() {
        // Solves the puzzle by finding cells with the least amount of viable options.

        LinkedList<Cell> stack = new LinkedList<>();
        int unspecifiedCellsCount = 0;
        int delay = 0;

        // Count the number of unspecified (empty) cells
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                if (board.value(row, col) == 0) {
                    unspecifiedCellsCount++;
                }
            }
        }

        int iterationCap = 1000000;
        // Start solving the Sudoku puzzle
        while (stack.size() < unspecifiedCellsCount && iterationCap > 0) {
            iterationCap--;

            Cell next = findNextCell2();
            // System.out.println("next: " + next);
            // Backtracking loop
            while (next == null && !stack.isEmpty()) {
                Cell poppedCell = stack.pop();
                int nextValue = findNextValue(poppedCell.getRow(), poppedCell.getCol());

                // If a valid value is found, set it and update next
                if (nextValue != 0) {
                    poppedCell.setValue(nextValue);
                    next = poppedCell;
                } else {
                    poppedCell.setValue(0); // Reset if no valid value is found
                }
            }

            // If no next cell can be found, it means all possibilities are exhausted
            if (next == null) {
                return false; // Puzzle cannot be solved
            } else {
                stack.push(next); // Push the current cell onto the stack
            }

            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    System.out.println("Got Interupted.");

                }

                if (ld != null) {
                    ld.repaint();
                }
            }
        }

        board.changedFinished(true);
        if (iterationCap == 0) System.out.println("timed out");
        return board.validSolution(); // The board has been successfully solved
    }

    //Extensions!!!

    public Cell singleCandidate(int checkForNSolutions) {
        // Operates the same as solve 2, but prioritzes the search for cells with int checkForNSolutions amount of solutions. 

        Cell nextCell = null;
        int minOptions = Integer.MAX_VALUE;
    
        // Iterate through the board to find the next empty cell
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                Cell cell = board.get(row, col);
                if (cell.getValue() == 0) { // Check if the cell is empty
                    int optionsCount = 0;
                    ArrayList<Integer> candidates = new ArrayList<>();
    
                    // Count valid options for this cell
                    for (int value = 1; value <= 9; value++) {
                        if (board.validValue(row, col, value)) {
                            optionsCount++;
                            candidates.add(value);
                        }
                    }
    
                    // Set cell value if there's exactly one candidate
                    if (candidates.size() == checkForNSolutions) {
                        cell.setValue(candidates.get(0));
                        return cell; // Return the filled cell immediately
                    }
    
                    // Update if this cell has fewer options than the current minimum
                    if (optionsCount < minOptions) {
                        minOptions = optionsCount;
                        nextCell = cell; // Track the next cell with the least options
                    }
                }
            }
        }
    
        // If a candidate cell was found, find and set its next valid value
        if (nextCell != null) {
            int nextValue = findNextValue(nextCell.getRow(), nextCell.getCol());
            if (nextValue != 0) {
                nextCell.setValue(nextValue); // Update cell with the valid value
                return nextCell; // Return the updated cell
            }
        }
        return null; // No valid value found, return null
    }


    public boolean solve3(int checkForNSolutions) {
        // Solves the puzzle by finding cells with the specified amount of solutions. 

        LinkedList<Cell> stack = new LinkedList<>();
        int unspecifiedCellsCount = 0;
        int delay = 0;

        // Count the number of unspecified (empty) cells
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                if (board.value(row, col) == 0) {
                    unspecifiedCellsCount++;
                }
            }
        }

        int iterationCap = 1000000;
        // Start solving the Sudoku puzzle
        while (stack.size() < unspecifiedCellsCount && iterationCap > 0) {
            iterationCap--;

            Cell next = singleCandidate(checkForNSolutions);
            // System.out.println("next: " + next);
            // Backtracking loop
            while (next == null && !stack.isEmpty()) {
                Cell poppedCell = stack.pop();
                int nextValue = findNextValue(poppedCell.getRow(), poppedCell.getCol());

                // If a valid value is found, set it and update next
                if (nextValue != 0) {
                    poppedCell.setValue(nextValue);
                    next = poppedCell;
                } else {
                    poppedCell.setValue(0); // Reset if no valid value is found
                }
            }

            // If no next cell can be found, it means all possibilities are exhausted
            if (next == null) {
                return false; // Puzzle cannot be solved
            } else {
                stack.push(next); // Push the current cell onto the stack
            }

            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    System.out.println("Got Interupted.");

                }

                if (ld != null) {
                    ld.repaint();
                }
            }
        }

        board.changedFinished(true);
        if (iterationCap == 0) System.out.println("timed out");
        return board.validSolution(); // The board has been successfully solved
    }
}
