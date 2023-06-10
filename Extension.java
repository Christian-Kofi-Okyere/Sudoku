/*
 * Author: Christian Okyere
 * Title: Solving Sudoku
 * File: Extension.java
 */

// takes inputs from user
import java.util.Scanner;

public class Extension {
    
    public static void main (String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            int[] numOfStarterValues = {10};

            System.out.println("Enter the number of simulations: ");
            int numTrials = sc.nextInt();
            
            
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

                
                double avgTime = numSolutions > 0 ? totalTime / numSolutions : 0;
                System.out.printf("Number of initial values: %d \nNumber of solutions found: %d \nAverage time used: %dms\n", numTrials, numSolutions, (int) avgTime);
                System.out.println("-----------------------------------");
            }
        }
    }
}
