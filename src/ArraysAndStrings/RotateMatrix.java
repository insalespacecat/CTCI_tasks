package ArraysAndStrings;

import java.util.Arrays;

public class RotateMatrix {

    //Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
    //write a method to rotate the image by 90 degrees. Сan you do this in place?

    //Ok the problem is written a little bit tricky, don't like that style
    //Let's assume that a "pixel" is an array index, and size 4 bytes is a size of int value (int = 4 bytes)
    //Though that an "image" is a matrix of integers of size NxN
    //To "rotate" a matrix means that we swap columns and rows

    //Straightforward option 1:
    //To rotate matrix by 90 degrees we will create an additional matrix B
    //and write B[i][j] = A[j][i]

    //Complexity is O(n^2)

    //To rotate in place we just swap B[i][j] with a [j][i] - same O(n^2)

    public static int[][] rotateMatrix(int[][] matrix){
        int[][] rotatedMatrix = new int[matrix.length][matrix.length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                rotatedMatrix[i][j] = matrix[j][i];
            }
        }
        return rotatedMatrix;
    }

    public static int[][] rotateMatrixInPlace(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }

    public static void main(String[] args){
        int[][] matrix = new int[4][4];
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                matrix[i][j] = i+j-1;
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(Arrays.deepToString(rotateMatrix(matrix)));
        System.out.println(Arrays.deepToString(rotateMatrixInPlace(matrix)));
    }
}
