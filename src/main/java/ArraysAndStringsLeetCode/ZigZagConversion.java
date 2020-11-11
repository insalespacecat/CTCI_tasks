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

    //Optimized solution:
    //It is clear that in order to optimize the algorithm we should throw the matrix away
    //The thing why we implemented brute force is to see the pattern how we form each row in a more clear way
    //So the pattern is the following:
    //For example
    //"PAYPALISHIRING" 3 rows
    //P   A   H   N
    //A P L S I I G
    //Y   I   R
    // first row:
    // 3x-3x-3x-3x
    // second row:
    // 1x-1x-1x-1x
    // third row:
    // 3x-3x-3x
    //"PAYPALISHIRING" 4 rows
    //P     I    N
    //A   L S  I G
    //Y A   H R
    //P     I
    //To form the first row we skip 5 characters between each character
    // 5x - 5x - 5x - 5x
    //To form the 2nd row we skip
    // 3x - 1x - 3x - 1x
    //To form the 3rd we skip
    // 1x - 3x - 1x - 3x
    //To form the 4th
    //5x - 5x - 5x - 5x
    //"PAYPALISHIRING" 5 rows
    //P       H
    //A     S I
    //Y   I   R
    //P L     I G
    //A       N
    // 7x-7x-7x-7x
    // 5x-1x-5x-1x
    // 3x-3x-3x-3x
    // 1x-5x-1x-5x
    // 7x-7x-7x-7x
    //The thing here is that number of characters we skip in the 1st and last rows is an arithmetic
    //progression and can be calculated from number of rows using arithmetic progression formula:
    //an = a1 + (n-2)d - original formula
    //an = 2 + (numRows - 2) * 2
    //After calculating the 1st and last CTS = characters to skip, we can get from those skips for each other row
    //it is enough to see the pattern how rows from 2nd to N-1 are changing
    //we can define CTS1 and CTS2, CTS1 = CTS, CTS2 = 1, and after that they change like CTS1 -= 2, CTS2 += 2;
    //until the last row, pretty straightforward.

    public String convertBruteForce(String s, int numRows) {
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

    public String convertOptimized(String s, int numRows){
        if(numRows == 1){
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        //CTS = characters to skip
        int CTSReference;
        int CTS1;
        int CTS2;
        if (numRows == 2) {
            CTSReference = 2;
            CTS1 = 2;
            CTS2 = 2;
        } else {
            CTSReference = 2 + ((numRows - 2) * 2);
            CTS1 = ((numRows - 2) * 2);
            CTS2 = 2;
        }
        boolean CTSSwitcher = false;
        //p = pointer
        int p = 0;
        for(int i = 0; i < numRows; i++){
            p = i;
            switch (i){
                case 0:
                    while(p < s.length()){
                        sb.append(s.charAt(p));
                        p += CTSReference;
                    }
                    break;
                case 1:
                    while(p < s.length()){
                        sb.append(s.charAt(p));
                        if(!CTSSwitcher){
                            p += CTS1;
                            CTSSwitcher = true;
                        } else {
                            p += CTS2;
                            CTSSwitcher = false;
                        }
                    }
                    break;
                default:
                    CTSSwitcher = false;
                    if(i == numRows-1){
                        while(p < s.length()){
                            sb.append(s.charAt(p));
                            p += CTSReference;
                        }
                        break;
                    }
                    CTS1 -= 2;
                    CTS2 += 2;
                    while(p < s.length()) {
                        sb.append(s.charAt(p));
                        if (!CTSSwitcher) {
                            p += CTS1;
                            CTSSwitcher = true;
                        } else {
                            p += CTS2;
                            CTSSwitcher = false;
                        }
                    }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
    }
}
