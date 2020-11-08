package main.java.ArraysAndStringsLeetCode;

public class ZigZagConversion {
    //The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
    //(you may want to display this pattern in a fixed font for better legibility)
    //
    //P   A   H   N
    //A P L S I I G
    //Y   I   R
    //And then read line by line: "PAHNAPLSIIGYIR"
    //
    //Write the code that will take a string and make this conversion given a number of rows

    //Brute force solution:
    //OK the most straightforward and dumbest solution will be to create a matrix and actually
    //write our word in ZigZag pattern to the matrix and then to read it with well-known
    //for(int i...) { for(int j...) ... } construction
    //Complexity of that is awful

    public String convert(String s, int numRows) {
        if(numRows == 1){
            return s;
        }
        char[][] matrix = new char[numRows][s.length()];
        int j = 0, i = 0, counter = 0;
        while(counter < s.length()){
            while(j < numRows) {
                if (counter == s.length()) {
                    break;
                }
                matrix[j][i] = s.charAt(counter);
                counter++;
                j++;
            }
            if(counter == s.length()){
                break;
            }
            while(j > 0){
                if(counter == s.length()){
                        break;
                }
                if(j == numRows){
                    j--;
                }
                j--;
                i++;
                matrix[j][i] = s.charAt(counter);
                counter++;
            }
            j++;
        }
        StringBuilder SB = new StringBuilder();
        for(int k = 0; k < numRows; k++){
            for(int l = 0; l < s.length(); l++){
                if(matrix[k][l] != '\0'){
                    SB.append(matrix[k][l]);
                }
            }
        }
        return SB.toString();
    }


    public static void main(String[] args){
    }
}
