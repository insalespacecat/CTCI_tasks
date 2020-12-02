package main.java.themes.ArraysAndStrings.CrackingTheCodingInterview;

public class ZeroMatrix {

    //Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to O.

    //Straightforward option 1:
    //To do that we simply have to memorize our M and N and run two cycles with M and N complexity to
    //set row and column to zero. The hugest problem of that algorithm is that it have to be able
    //to work with any matrix, it means that we have to iterate over every element of the matrix to find zero
    //we cannot run a binary search or something. So the complexity will be O(MxN)

    public static int[][] zeroMatrix(int[][] matrix, int M, int N){
        int zeroM = 0;
        int zeroN = 0;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(matrix[i][j] == 0){
                    zeroM = i;
                    zeroN = j;
                    break;
                }
            }
        }
        for(int j = 0; j < N; j++){
            matrix[zeroM][j] = 0;
        }
        for(int i = 0; i < M; i++){
            matrix[i][zeroN] = 0;
        }
        return matrix;
    }

    public static void main(String[] args){
        int[][] matrix = new int[4][6];
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 6; j++){
                matrix[i][j] = i+j;
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        zeroMatrix(matrix,4,6);
        System.out.println();
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 6; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 6; j++){
                if(i == 2 && j == 3){
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = i + j + 1;
                }
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        zeroMatrix(matrix,4,6);
        System.out.println();
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 6; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
