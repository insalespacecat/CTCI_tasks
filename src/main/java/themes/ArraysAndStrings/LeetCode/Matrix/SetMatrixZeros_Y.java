package main.java.themes.ArraysAndStrings.LeetCode.Matrix;

//src: https://leetcode.com/problems/set-matrix-zeroes/
//#matrix

//Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.
//
//You must do it in place.

//Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
//Output: [[1,0,1],[0,0,0],[1,0,1]]

//Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

//Constraints:
//
//    m == matrix.length
//    n == matrix[0].length
//    1 <= m, n <= 200
//    -231 <= matrix[i][j] <= 231 - 1
//
//
//
//Follow up:
//
//    A straightforward solution using O(mn) space is probably a bad idea.
//    A simple improvement uses O(m + n) space, but still not the best solution.
//    Could you devise a constant space solution?

public class SetMatrixZeros_Y {
    class Solution {
        //To maximize memory efficiency, we could use the first cell of a column and a row
        //to indicate that this row is to be nullified after the first run.
        //even if that cell was already 0 it's going to have the same effect.
        //Time: O(N*M)
        //Space: O(1)
        public void setZeroes(int[][] matrix) {
            //nullify first col and first row
            boolean nfc = false;
            boolean nfr = false;

            for(int i = 0; i < matrix[0].length; i++) {
                if(matrix[0][i] == 0) {
                    nfr = true;
                }
            }

            for(int i = 0; i < matrix.length; i++) {
                if(matrix[i][0] == 0) {
                    nfc = true;
                }
            }

            for(int i = 1; i < matrix.length; i++) {
                for(int j = 1; j < matrix[0].length; j++) {
                    if(matrix[i][j] == 0) {
                        matrix[0][j] = 0;
                        matrix[i][0] = 0;
                    }
                }
            }

            for(int i = 1; i < matrix[0].length; i++) {
                if(matrix[0][i] == 0) {
                    zeroTheCol(i, matrix);
                }
            }

            for(int i = 1; i < matrix.length; i++) {
                if(matrix[i][0] == 0) {
                    zeroTheRow(i, matrix);
                }
            }

            if(nfr) {
                zeroTheRow(0, matrix);
            }

            if(nfc) {
                zeroTheCol(0, matrix);
            }
        }

        private void zeroTheRow(int row, int[][] matrix) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrix[row][j] = 0;
            }
        }

        private void zeroTheCol(int col, int[][] matrix) {
            for(int j = 0; j < matrix.length; j++) {
                matrix[j][col] = 0;
            }
        }
    }
}
