package main.java.themes.ArraysAndStrings.LeetCode.Matrix;

//Solved it ok in OK time but it was hard for me to keep track of so many variables.
//might want to solve another time
//#array #matrix #simulation

//src: https://leetcode.com/problems/spiral-matrix/

//Given an m x n matrix, return all elements of the matrix in spiral order.
//Example 1:
//Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [1,2,3,6,9,8,7,4,5]
//Example 2:
//Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//Output: [1,2,3,4,8,12,11,10,9,5,6,7]

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix_Y {
    class Solution {
        private int steps = 0;
        private int limit = 0;

        public List<Integer> spiralOrder(int[][] matrix) {
            limit = matrix.length * matrix[0].length;
            var o = new ArrayList<Integer>(limit);
            int layer = 0;

            while(steps < limit) {
                getUpSide(layer, layer, matrix[0].length - layer, o, matrix);
                getRightSide(matrix[0].length - 1 - layer, layer + 1, matrix.length - layer, o, matrix);
                getDownSide((matrix.length - 1 - layer), matrix[0].length - 2 - layer, layer, o, matrix);
                getLeftSide(layer, matrix.length - 2 - layer, layer + 1, o, matrix);
                layer++;
            }

            return o;
        }

        private void getUpSide(int row, int s, int end, ArrayList<Integer> o, int[][] m) {
            for(int i = s; i < end; i++) {
                if(steps < limit) {
                    o.add(m[row][i]);
                }

                steps++;
            }
        }

        private void getRightSide(int col, int s, int end, ArrayList<Integer> o, int[][] m) {
            for(int i = s; i < end; i++) {
                if(steps < limit) {
                    o.add(m[i][col]);
                }

                steps++;
            }
        }

        private void getDownSide(int row, int s, int end, ArrayList<Integer> o, int[][] m) {
            for(int i = s; i >= end; i--) {
                if(steps < limit) {
                    o.add(m[row][i]);
                }

                steps++;
            }
        }

        private void getLeftSide(int col, int s, int end, ArrayList<Integer> o, int[][] m) {
            for(int i = s; i >= end; i--) {
                if(steps < limit) {
                    o.add(m[i][col]);
                }

                steps++;
            }
        }
    }
}
