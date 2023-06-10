/*
 * Author: Christian Okyere
 * Title: Solving Sudoku
 * File: Exploration.java
 */


 // used to explore how changes in parameters affect the time it takes to solve the sudoku

 public class Exploration {

    public static void main(String[] args) {
        int[] numOfStarterValues = {40};
        int numTrials = 5;

        for (int num : numOfStarterValues) {
            int numSolutions = 0;
            long totalTime = 0;

            for (int i = 0; i < numTrials; i++) {
                Sudoku sudoku = new Sudoku(num);
                long startTime = System.currentTimeMillis();
                sudoku.solve(0);
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;

                if (sudoku.getBoard().getFinished() == true) {
                    numSolutions++;
                    totalTime += duration;
                }

                sudoku.getBoard().setFinished(false);
            }
            ;

            double avgTime = numSolutions > 0 ? totalTime / numSolutions : 0;
            System.out.printf("Number of initial values: %d\n Number of solutions found: %d\n Average time: %dms\n", num, numSolutions, (int) avgTime);
            System.out.println("------------------------------------");
        }
    }
}
