/*
* Author: Christian Okyere
* Title: Solving Sudoku
* File: Sudoku.java
*/

// solves the sudoku game

public class Sudoku {
    Board board;
    LandscapeDisplay ld;

    public Sudoku(int lockedValues){
        board = new Board(lockedValues);
        ld = new LandscapeDisplay(board);
    }

    public int findNextValue(Cell currentCell){
        for(int i = currentCell.getValue() + 1; i < 10; i++){
            if (board.validValue(currentCell.getRow(), currentCell.getCol(), i)){
                return i;
            }
        }
        return 0;
    }

    public Cell findNextCell() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.get(row, col).getValue() == 0) {
                    if (findNextValue(board.get(row, col)) != 0) {
                        
                        board.get(row, col).setValue(findNextValue(board.get(row, col)));
                        return board.get(row, col);
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    public Board getBoard(){
        return this.board;
    }

    public boolean solve(int delay) {
        Stack<Cell> stack = new LinkedList<>();
        System.out.println(board);
        
        if (delay > 0)
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    if (ld != null)
        ld.repaint();

        while (stack.size() < ((board.SIZE * board.SIZE) - board.numLocked())) {
            Cell next = findNextCell();
            //System.out.println(board);

            if (delay > 0)
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            if (ld != null)
                ld.repaint();

            while (next == null && stack.size() > 0 && board.validSolution()== false) {
                Cell temp = stack.pop();
                int tempVal = findNextValue(temp);
                temp.setValue(tempVal);

                if (tempVal != 0) {
                    next = temp;
                }
            }
            if (next == null) {
                System.out.println(board);
                return false;
            } else {
                stack.push(next);
            }
        }
        board.setFinished(true);
        System.out.println(board);
        return true;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: Include integer value for the number of originally filled cells");
        } else {
            Sudoku gameSudoku = new Sudoku(Integer.parseInt(args[0]));

            long timeBefore = System.currentTimeMillis();
            // gameSudoku.solveRec(gameSudoku.findNextCell());
            gameSudoku.solve(5);
            // Long timeAfter = System.currentTimeMillis();
            // long timeUsed = timeAfter - timeBefore;
            // System.out.println(gameSudoku.board);
            // System.out.println(timeUsed);
        }
    }
}
