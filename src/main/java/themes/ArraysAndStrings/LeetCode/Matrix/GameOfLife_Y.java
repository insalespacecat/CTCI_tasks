package main.java.themes.ArraysAndStrings.LeetCode.Matrix;

//https://leetcode.com/problems/game-of-life/
//#matrix

//According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
//
//The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
//
//    Any live cell with fewer than two live neighbors dies as if caused by under-population.
//    Any live cell with two or three live neighbors lives on to the next generation.
//    Any live cell with more than three live neighbors dies, as if by over-population.
//    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
//
//The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.

//Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
//Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]

//Input: board = [[1,1],[1,0]]
//Output: [[1,1],[1,1]]

//Follow up:
//
//Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
//In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?


public class GameOfLife_Y {
    //Main tip: you can use predefined const + cycle to iterate over 8 neighbors
    //not writin every single condition by hand
    class Solution {
        //TIME: O(N*M)
        //SPACE: O(1)
        public void gameOfLife(int[][] board) {
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    board[i][j]++;
                }
            }

            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    board[i][j] = checkNbs(board, i, j);
                }
            }

            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    if(board[i][j] < 0) {
                        board[i][j] *= -1;
                    }

                    board[i][j]--;
                }
            }
        }

        private int checkNbs(int[][] b, int i, int j) {
            int a = 0;

            if(i > 0) {
                if(b[i-1][j] == 2 || b[i-1][j] == -1) {
                    a++;
                }
            }

            if(j > 0) {
                if(b[i][j-1] == 2 || b[i][j-1] == -1) {
                    a++;
                }
            }

            if(i < b.length - 1) {
                if(b[i+1][j] == 2 || b[i+1][j] == -1) {
                    a++;
                }
            }

            if(j < b[0].length - 1) {
                if(b[i][j+1] == 2 || b[i][j+1] == -1) {
                    a++;
                }
            }

            if(i > 0 && j > 0) {
                if(b[i-1][j-1] == 2 || b[i-1][j-1] == -1) {
                    a++;
                }
            }

            if(i < b.length - 1 && j > 0) {
                if(b[i+1][j-1] == 2 || b[i+1][j-1] == -1) {
                    a++;
                }
            }

            if(i < b.length - 1 && j < b[0].length - 1) {
                if(b[i+1][j+1] == 2 || b[i+1][j+1] == -1) {
                    a++;
                }
            }

            if(i > 0 && j < b[0].length - 1) {
                if(b[i-1][j+1] == 2 || b[i-1][j+1] == -1) {
                    a++;
                }
            }

            if(a < 2) {
                if(b[i][j] == 1) {
                    return 1;
                }

                if(b[i][j] == 2) {
                    return -1;
                }
            }

            if(a == 3) {
                if(b[i][j] == 1) {
                    return -2;
                }

                if(b[i][j] == 2) {
                    return 2;
                }
            }

            if(a > 3) {
                if(b[i][j] == 1) {
                    return 1;
                }

                if(b[i][j] == 2) {
                    return -1;
                }
            }

            return b[i][j];
        }
    }

    //LeetCode reference:
    class Solution {
        public void gameOfLife(int[][] board) {

            // Neighbors array to find 8 neighboring cells for a given cell
            int[] neighbors = {0, 1, -1};

            int rows = board.length;
            int cols = board[0].length;

            // Iterate through board cell by cell.
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {

                    // For each cell count the number of live neighbors.
                    int liveNeighbors = 0;

                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {

                            if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                                int r = (row + neighbors[i]);
                                int c = (col + neighbors[j]);

                                // Check the validity of the neighboring cell.
                                // and whether it was originally a live cell.
                                if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                                    liveNeighbors += 1;
                                }
                            }
                        }
                    }

                    // Rule 1 or Rule 3
                    if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                        // -1 signifies the cell is now dead but originally was live.
                        board[row][col] = -1;
                    }
                    // Rule 4
                    if (board[row][col] == 0 && liveNeighbors == 3) {
                        // 2 signifies the cell is now live but was originally dead.
                        board[row][col] = 2;
                    }
                }
            }

            // Get the final representation for the newly updated board.
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (board[row][col] > 0) {
                        board[row][col] = 1;
                    } else {
                        board[row][col] = 0;
                    }
                }
            }
        }
    }
}
