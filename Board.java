/*
file name:      Board.java
Authors:        Robbie Bennett
last modified:  10/29/2024
Class: CS231
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.List;
import java.awt.Graphics;
import java.awt.Color;

public class Board {

    private Cell[][] board;
    public boolean finished;

    public Board() {
        // Constructor that gives each value of the board the initial value of 0.

        this.board = new Cell[9][9];
        this.finished = false;
        // SIZE = 9;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                this.board[row][col] = new Cell(row, col, 0);
            }
        }
    }

    public Board(int numLocked) {
        // Constructor that locks a certain number of cells.

        this.board = new Cell[9][9];
        Random random = new Random();

        // Initialize the board with Cell objects
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                this.board[row][col] = new Cell(row, col, 0);
            }
        }

        // Fill the board with valid values
        for (int i = 0; i < numLocked; i++) {
            int col;
            int row;
            int value;

            do {
                row = random.nextInt(9);
                col = random.nextInt(9);
                value = random.nextInt(9) + 1;
            } while (value(row, col) != 0 || !validValue(row, col, value));

            this.board[row][col].setValue(value);
            this.board[row][col].setLocked(true);
        }
    }

    public void set(int row, int col, boolean locked) {
        // Sets whether the Cell at the given row and col is locked.

        this.board[row][col].setLocked(locked);
    }

    public boolean read(String filename) {
        //Read method for the sodoku board.

        try {
            // assign to a variable of type FileReader a new FileReader object, passing
            // filename to the constructor
            FileReader fr = new FileReader(filename);
            // assign to a variable of type BufferedReader a new BufferedReader, passing the
            // FileReader variable to the constructor
            BufferedReader br = new BufferedReader(fr);

            // assign to a variable of type String line the result of calling the readLine
            // method of your BufferedReader object.
            String line = br.readLine();
            // start a while loop that loops while line isn't null
            int rowTracker = 0;

            while (line != null) {
                // print line
                // System.out.println(line);
                // assign to an array of Strings the result of splitting the line up by spaces
                // (line.split("[ ]+"))
                String[] arr = line.split("[ ]+");
                // let's see what this array holds:
                // System.out.println("the first item in arr: " + arr[0] + ", the second item in
                // arr: " + arr[1]);
                // print the size of the String array (you can use .length)
                // System.out.println(arr.length);
                // use the line to set various Cells of this Board accordingly

                // TODO THIS IS WHAT NEEDS TO BE FILLED IN!
                for (int col = 0; col < 9; col++) {
                    int val = Integer.parseInt(arr[col]);

                    set(rowTracker, col, val);
                    if (val > 0) {
                        set(rowTracker, col, true);
                    } else {
                        set(rowTracker, col, false);
                    }
                }

                rowTracker++;

                // assign to line the result of calling the readLine method of your
                // BufferedReader object.
                line = br.readLine();
            }
            // call the close method of the BufferedReader
            br.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }

        return false;
    }

    public String toString() {
        // Creates a string representation of the board.

        String result = "";

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int value = board[row][col].getValue();
                result += value + " ";
            }
            result += "\n"; // New line after each row
        }
        return result; // Return the concatenated string
    }

    public int getCols() {
        // Returns the number of columns.

        return this.board[0].length;
    }

    public int getRows() {
        // Returns the number of rows.

        return this.board.length;
    }

    public Cell get(int r, int c) {
        // Returns the Cell at location r, c.

        return this.board[r][c];
    }

    public boolean isLocked(int r, int c) {
        // Retunrs whether the Cell at R, c, is locked

        if (this.board[r][c].isLocked()) {
            return false;
        }

        return true;
    }

    public int numLocked() {
        // Returns the number of locked Cells on the board.

        int total = 0;

        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[0].length; col++) {
                if (this.board[row][col].isLocked()) {
                    total++;
                }
            }
        }
        return total;
    }

    public int value(int r, int c) {
        // Returns the value at Cell r,c.

        return this.board[r][c].getValue();
    }

    public void set(int r, int c, int value) {
        // Sets the value of the Cell at r, c.

        this.board[r][c].setValue(value);
    }

    public void set(int r, int c, int value, boolean locked) {
        // Sets the value and locked fields of the Cell at r, c.

        this.board[r][c].setValue(value);
        this.board[r][c].setLocked(locked);
    }

    public boolean validValue(int row, int col, int value) {
        // Checks if that the value is unique in its row, col, and 3x3 grid

        if (!(value >= 1 && value <= 9)) {
            return false;
        }

        // Checking the Rows.
        for (int i = 0; i < this.board.length; i++) {
            if (i != col && this.board[row][i].getValue() == value) {
                return false;
            }
        }

        // Checking the Cols.
        for (int i = 0; i < this.board.length; i++) {
            if (i != row && this.board[i][col].getValue() == value) {
                return false;
            }
        }

        // Checks that the value is not the same as its grid.

        int spaceRow = row / 3;
        int spaceCol = col / 3;

        for (int i = spaceRow * 3; i < spaceRow * 3 + 3; i++) {
            for (int j = spaceCol * 3; j < spaceCol * 3 + 3; j++) {
                if ((i != row || j != col) && this.board[i][j].getValue() == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean validSolution() {
        // Returns if the board is solved.

        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[0].length; col++) {
                if (this.board[row][col].getValue() == 0
                        || (!(validValue(row, col, this.board[row][col].getValue())))) {
                    return false;
                }
            }
        }

        return true;
    }

    public void draw(Graphics g, int scale){
        //Draw method.

        for(int i = 0; i<getRows(); i++){
            for(int j = 0; j<getCols(); j++){
                get(i, j).draw(g, j*scale+5, i*scale+10, scale);
            }
        } if(finished){
            if(validSolution()){
                g.setColor(new Color(0, 127, 0));
                g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale*3+5, scale*10+10);
            } else {
                g.setColor(new Color(127, 0, 0));
                g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale*3+5, scale*10+10);
            }
        }
    }

    public void changedFinished(boolean change){
        //Mutator method for finished.

        this.finished = change;
    }
}