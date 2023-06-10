/*
 * Author: Christian Okyere
 * Title: Solving Sudoku
 * File: Cell.java
 */

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    

    public int row; // row number
    public int column; // column number 
    public int value;  // value to insert 
    public boolean locked;  // whether position is filled or not

    // cell constructor one
    public Cell(){
        this.row = 0;
        this.column = 0;
        this.value = 0;
        this.locked = false;
    }

    // cell constructor two
    public Cell(int row, int col, int value){
        this.row = row;
        this.column = col;
        this.value = value;
        this.locked = false;
    }

    // cell constructor three
    public Cell(int row, int col, int value, boolean locked){
        this.row = row;
        this.column = col;
        this.value = value;
        this.locked = locked;
    }

    // returns the row index
    public int getRow(){
        return this.row;
    }

    // return the column index
    public int getCol(){
        return this.column;
    }

    // gets the value fo the specified row and column
    public int getValue(){
        return this.value;
    }

    // sets the value in the cell to newval
    public void setValue(int newval){
        this.value = newval;
    }

    // return the value of the locked field 
    public boolean isLocked(){
        return this.locked;
    }

    // sets the Cell's locked field to the new value
    public void setLocked(boolean lock){
        this.locked = lock;
    }

    public String toString(){
        String output = "" + value;
        return output;
    }

    public void draw(Graphics g, int x, int y, int scale){
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked()? Color.BLUE : Color.RED);
        g.drawChars(new char[] {toDraw}, 0, 1, x, y);
    }
}
