package main.java.ArraysAndStringsLeetCode;

public class WordSearch {

    public boolean bruteForce(char[][] board, String word){
        if(word.length() > board.length*board[0].length){
            return false;
        }
        if(board.length == 1 && board[0].length == 1){
            if(word.length() == 1){
                if(word.charAt(0) == board[0][0]){
                    return true;
                } else {
                    return false;
                }
            }
        }
        int ic = 0, jc = 0, counter = 0;
        boolean[][] wayMark = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == word.charAt(counter)){
                    jc = j; ic = i;
                    while(counter != word.length()-1){
                        if(ic < board.length-1){
                            if(board[ic+1][jc] == word.charAt(counter+1)){
                                if(!wayMark[ic+1][jc]) {
                                    ic++;
                                    counter++;
                                    wayMark[ic][jc] = true;
                                    continue;
                                }
                            }
                        }
                        if(jc < board[i].length-1) {
                            if (board[ic][jc + 1] == word.charAt(counter + 1)) {
                                if(!wayMark[ic][jc+1]) {
                                    jc++;
                                    counter++;
                                    wayMark[ic][jc] = true;
                                    continue;
                                }
                            }
                        }
                        if(ic > 0){
                            if(board[ic-1][jc] == word.charAt(counter+1)){
                                if(!wayMark[ic-1][jc]) {
                                    ic--;
                                    counter++;
                                    wayMark[ic][jc] = true;
                                    continue;
                                }
                            }
                        }
                        if(jc > 0){
                            if(board[ic][jc-1] == word.charAt(counter+1)) {
                                if(!wayMark[ic][jc-1]) {
                                    jc--;
                                    counter++;
                                    wayMark[ic][jc] = true;
                                } else {
                                    break;
                                }
                            }
                        } else { counter = 0; break; }
                    }
                }
                if(counter == word.length()-1){
                    return true;
                } else {
                    counter = 0;
                    wayMark = new boolean[board.length][board[0].length];
                }
            }
        }
        return false;
    }

    public static void main(String[] args){}
}
