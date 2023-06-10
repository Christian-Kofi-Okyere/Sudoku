# Sudoku
Stack-based solver for the classical Sudoku Game that implements a depth-first search algorithm

## Background <br>
Sudoku is a popular puzzle game that involves filling a 9x9 grid with numbers from 1 to 9. The objective is to ensure that each row, each column, and each of the nine 3x3 subgrids (also known as boxes or regions) contains all the numbers from 1 to 9 without any repetition. Here are some key points about Sudoku:

1. Grid Structure: The Sudoku grid consists of 81 cells organized in a 9x9 matrix. Some cells are pre-filled with numbers, which serve as clues to help solve the puzzle.

2. Rules: The goal is to fill the empty cells with numbers from 1 to 9, ensuring that no repetition occurs within rows, columns, or 3x3 subgrids. Each row, column, and subgrid must contain all the numbers from 1 to 9 exactly once.

## Simulation of Sudoku <br>
This project is an implementation of a simple Sudoku Game using a node based stack. Stack is a data structure that works on the Last-In-First-Out principle(LIFO). This is useful to our Sudoku solver when backtracking and change some previous values.
The main classes used in this projects are:<br>
- Cell Class which contains methods that make a cell and can set values to the cell<br>
- LinkedList class which the underlying data structure to implement our stack<br>
- Stack class which contains methods for a stack <br>
- Board and Sudoku classes which creates the underlying board and solves our Sudoku game respectively
- The LandscapeDisplay class creates and displays our visualization of the board.


## Installation <br>
To test your own version of the Sudoku Game, simply clone this repository to your local machine:
git clone https://github.com/Christian-Kofi-Okyere/Sudoku

## Usage<br>
To run the game simulator, simply execute the Exploration class.<br>
-> javac Exploration.java -> java Exploration <br>
This runs a simulation of the game and generates the results in an interface showing a normal sudoku game and the process it takes to solve the game.

## Contributing<br>
Contributions to this project are welcome. To contribute, simply fork this repository, make your changes, and submit a pull request.
