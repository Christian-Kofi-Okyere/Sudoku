/*
* Author: Christian Okyere
* Title: Solving Sudoku
* File: Board.java
*/


import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;



// keeps track of the sudoku board itself    
public class Board {
    private Cell[][] cells;
    public final int SIZE = 9;
    public boolean finished;
    
    // Constructor that creates a 9x9 2D array of cells with initial values zero
    public Board() {
        cells = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new Cell (i, j, 0);
            }
        }
    }

    public Board(int numOfLockedCells) {
        this();
        
        Random random = new Random();

        int count = 0;
        
        //Generates numOfLockedCells of randomly placed locked cell
        while (count < numOfLockedCells) {

            //generate random row and column numbers
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            
            //sets value and locks it at a valid spot
            if (!cells[row][col].isLocked()) {
                //generate a random value from 1 to size
                int value = random.nextInt(SIZE) + 1;
                if (validValue(row, col, value)) {
                    cells[row][col].setValue(value);
                    cells[row][col].setLocked(true);
                    count++;
                }
            }
        }       
   
    }

    public boolean getFinished(){
        return this.finished;
    }

    public void setFinished(boolean fin){
        this.finished = fin;
    }

    

    // returns the number of columns
    public int getCols(){
        return SIZE;
    }

    // returns the number of rows
    public int getRows(){
        return SIZE;
    }

    // returns the cell at location r,c
    public Cell get(int r, int c){
        return cells[r][c];
    }
    
    // returns whether the cell is locked or not
    public boolean isLocked(int r, int c){
        if (cells[r][c].isLocked() == true){
            return true;
        }
        return false;
    }

    // returns the number of locked cells
    public int numLocked(){
        int count = 0;
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (cells[i][j].isLocked() == true){
                    count++;
                }
            }
        }
        return count;
    }

    // returns the value at cell r,c
    public int value(int r, int c){
        return cells[r][c].getValue();
    }

    // sets the value at cell r, c
    public void set(int r, int c, int value){
        cells[r][c].setValue(value);
    }

    // sets the value and locked fields of the cell at r, c
    public void set(int r, int c, int value, boolean locked){
        cells[r][c].setValue(value);
        cells[r][c].setLocked(locked);
    }

    public boolean read(String filename) {
        try {
        // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
        FileReader fr = new FileReader(filename);
        // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
        BufferedReader br = new BufferedReader(fr);
        
        // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
        String line = br.readLine();
        // start a while loop that loops while line isn't null
        int row = 0;
        while(line != null){
            // print line
            // assign to an array of Strings the result of splitting the line up by spaces (line.split("[ ]+"))
            String[] results = line.split("[ ]+");
            // print the size of the String array (you can use .length)
            for (int i = 0; i < cells.length; i++){
            if (Integer.parseInt(results[i]) != 0){
                cells[row][i] = new Cell(row, i, Integer.parseInt(results[i]), true);
            }
            else{
                cells[row][i] = new Cell(row, i, Integer.parseInt(results[i]));
            }
            }
            // use the line to set various Cells of this Board accordingly
            // assign to line the result of calling the readLine method of your BufferedReader object.
            line = br.readLine();
            row++;
        }
        // call the close method of the BufferedReader
        br.close();
        return true;
        }
        catch(FileNotFoundException ex) {
        System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
        System.out.println("Board.read():: error reading file " + filename);
        }
        return false;
    }

    public boolean validValue(int row, int col, int value){

        if (value < 1 || value > 9) {
            return false;
        }

        for (int i = 0; i < 9; i++) {

            if (value(row, i) == value && i != col ) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (i != row && value(i, col) == value) {
                return false;
            }
        }

        int squareRow = (row / 3) * 3;
        int squareCol = (col / 3) * 3;
        for (int i = squareRow; i < squareRow + 3; i++) {
            for (int j = squareCol; j < squareCol + 3; j++) {
                if (i != row && j != col && cells[i][j].getValue() == value) {
                    return false;
                }
            }
        }
    
        return true;
    }



    public boolean validSolution(){

        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if (cells[i][j].getValue() < 1 || cells[i][j].getValue() > 9 && validValue(i, j,cells[i][j].getValue()) == true){
                    return false;
                } 
                else if (validValue(i, j, cells[i][j].getValue()) == false){
                    return false;
                }
            }
        }
        return true;
    }


    public void draw(Graphics g, int scale){
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


    

    public String toString(){
        String output = "";
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                output += cells[i][j].getValue() + " ";
                if ((j + 1) % 3 == 0){
                    output += " ";
                }
            }
            output += "\n";
            if ((i + 1) % 3 == 0){
                output += "\n";
            }
        }
        return output;
    }

    public static void main(String[] args){
        Board board = new Board();

        if (args.length > 0){
            board.read(args[0]);
            System.out.println(board.toString());
        }
        else {
            System.out.println("Pass a file name");
        }

        System.out.println(board.isLocked(0, 2));
        System.out.println(board.isLocked(0, 1));
    }

}
