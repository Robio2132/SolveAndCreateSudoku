/*
file name:      Cell.java
Authors:        Robbie Bennett
last modified:  10/29/2024
Class: CS231
*/

import java.awt.Graphics;
import java.awt.Color;

public class Cell {
    //Establishing variables.
    
    protected int row;
    protected int col;
    protected int value;
    protected boolean isLocked;

    public Cell(int row, int col, int value){
        //First Constructor

        this.row = row;
        this.col = col;
        this.value = value;
        this.isLocked = false;
    }

    public Cell(int row, int col, int value, boolean locked){
        //Second Constructor.

        this.row = row;
        this.col = col;
        this.value = value;
        this.isLocked = false;
    }

    public int getRow(){
        //Returns the Cell's row index

        return this.row;
    }

    public int getCol(){
        //Return the Cell's coloumn index.

        return this.col;
    }

    public int getValue(){
        //Return the Cell's value

        return this.value;
    }

    public void setValue(int newval){
        //Sets the cell's value

        this.value = newval;
    }

    public boolean isLocked(){
        //Return the value of the lovked field.

        return this.isLocked;
    }

    public void setLocked(boolean lock){
        //Set the cell's locked field to the new value.

        this.isLocked = lock;
    }

    public String toString(){
        //Returns a to string representation of the row, column, and value. 
        
        return "[Rows:   "  + this.row + "   Cols:   " +   this.col + "   Value:   " + this.value + "]";

    }

    public void draw(Graphics g, int x, int y, int scale){
        //Draw method.
        
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked()? Color.BLUE : Color.RED);
        g.drawChars(new char[] {toDraw}, 0, 1, x, y);
    }
}
