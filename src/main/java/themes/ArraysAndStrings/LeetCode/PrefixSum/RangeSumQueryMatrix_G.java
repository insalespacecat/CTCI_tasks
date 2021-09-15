package main.java.themes.ArraysAndStrings.LeetCode.PrefixSum;

//#martix #prefixSum
//src: https://leetcode.com/problems/range-sum-query-2d-immutable/

//Given a 2D matrix matrix, handle multiple queries of the following type:
//
//    Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
//
//Implement the NumMatrix class:
//
//    NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
//    int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
//
//Example 1:
//Input
//["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
//[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
//Output
//[null, 8, 11, 12]
//
//Explanation
//NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
//numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
//numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
//numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)
//
//
//
//Constraints:
//
//    m == matrix.length
//    n == matrix[i].length
//    1 <= m, n <= 200
//    -105 <= matrix[i][j] <= 105
//    0 <= row1 <= row2 < m
//    0 <= col1 <= col2 < n
//    At most 104 calls will be made to sumRegion.

public class RangeSumQueryMatrix_G {
    class NumMatrix {
        int[][] m;
        int[][] pm;

        public NumMatrix(int[][] matrix) {
            this.m = matrix;
            this.pm = buildPrefixMatrix(matrix);
        }

        //Time per 1 sumRegion: O(1)
        //Time per construction: O(n*m). Space per construction: O(n*m)
        public int sumRegion(int row1, int col1, int row2, int col2) {
            if(col1 == 0 && row1 == 0) {
                return pm[row2][col2];
            }

            if(col1 == 0) {
                return pm[row2][col2] - pm[row1-1][col2];
            }

            if(row1 == 0) {
                return pm[row2][col2] - pm[row2][col1-1];
            }

            return pm[row2][col2] - pm[row2][col1-1] - pm[row1-1][col2] + pm[row1-1][col1-1];
        }

        private int[][] buildPrefixMatrix(int[][] m) {
            var prefixMatrix = new int[m.length][m[0].length];

            for(int i = 0; i < m.length; i++) {
                for(int j = 0; j < m[i].length; j++) {
                    prefixMatrix[i][j] = m[i][j];

                    if(j > 0) {
                        prefixMatrix[i][j] += prefixMatrix[i][j-1];
                    }
                }
            }

            for(int i = 0; i < m.length; i++) {
                for(int j = 0; j < m[i].length; j++) {
                    if(i > 0) {
                        prefixMatrix[i][j] += prefixMatrix[i-1][j];
                    }
                }
            }

            return prefixMatrix;
        }
    }

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
}
