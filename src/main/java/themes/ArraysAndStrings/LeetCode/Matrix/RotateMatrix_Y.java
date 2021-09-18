package main.java.themes.ArraysAndStrings.LeetCode.Matrix;

//src: https://leetcode.com/problems/rotate-image/
//#matrix

//You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
//
//You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

//example 1:
//Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [[7,4,1],[8,5,2],[9,6,3]]

//example 2:
//Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]

//Example 3:
//Input: matrix = [[1]]
//Output: [[1]]

//Example 4:
//Input: matrix = [[1,2],[3,4]]
//Output: [[3,1],[4,2]]

public class RotateMatrix_Y {
    class Solution {
        //Go layer-by-layer
        //For each layer cache the up side.
        //Then as we meet the right side start adding characters in the right side
        //to the cache (queue) and then replace them with top ones in the cache.
        //Do that until we reach up side again. Then free the queue.
        //Time: O(N^2);
        //Space: O(N);
        public void rotate(int[][] matrix) {
            int left = 0;
            int up = 0;
            int right = matrix[0].length - 1;
            int down = matrix.length - 1;
            int layers = matrix.length % 2 == 0 ? matrix.length / 2 : (matrix.length - 1) / 2;
            Queue<Integer> q = new LinkedList();

            while(up < layers) {
                for(int i = up; i < right; i++) {
                    q.add(matrix[up][i]);
                }

                for(int i = up; i < down; i++) {
                    q.add(matrix[i][right]);
                    matrix[i][right] = q.poll();
                }

                for(int i = right; i > left; i--) {
                    q.add(matrix[down][i]);
                    matrix[down][i] = q.poll();
                }

                for(int i = down; i > up; i--) {
                    q.add(matrix[i][left]);
                    matrix[i][left] = q.poll();
                }

                for(int i = up; i < right; i++) {
                    matrix[up][i] = q.poll();
                }

                up++;
                left++;
                right--;
                down--;
            }

        }
    }
}
