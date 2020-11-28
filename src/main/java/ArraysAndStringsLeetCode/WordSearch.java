package main.java.ArraysAndStringsLeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class C {
    int x;
    int y;
    C (int x, int y){
        this.x = x;
        this.y = y;
    }
}

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

    private HashMap<Character, ArrayList<C>> prepareAMap(char[][] board, String word){
        boolean[] wordChecker = new boolean[128];
        for(int i = 0; i < word.length(); i++){
            wordChecker[(int) word.charAt(i)] = true;
        }
        HashMap<Character, ArrayList<C>> map = new HashMap<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(wordChecker[(int) board[i][j]]){
                    if(map.get(board[i][j]) != null){
                        ArrayList<C> list = map.get(board[i][j]);
                        list.add(new C(i, j));
                        map.replace(board[i][j], list);
                    } else {
                        ArrayList<C> list = new ArrayList<C>();
                        list.add(new C(i, j));
                        map.put(board[i][j], list);
                    }
                }
            }
        }
        return map;
    }

    private boolean checkIfExists(HashMap<Character, ArrayList<C>> map, String word){
        boolean isFound = true;
        for(int i = 0; i < word.length()-1; i++){
            ArrayList<C> C1 = map.get(word.charAt(i));
            ArrayList<C> C2 = map.get(word.charAt(i+1));
            for(C c1: C1){
                for(C c2: C2){
                    if(c1.x+1 == c2.x && c1.y == c2.y){
                        isFound = true;
                        continue;
                    }
                    if(c1.x-1 == c2.x && c1.y == c2.y){
                        isFound = true;
                        continue;
                    }
                    if(c1.y-1 == c2.y && c1.x == c2.x){
                        isFound = true;
                        continue;
                    }
                    if(c1.y+1 == c2.y && c1.x == c2.x) {
                        isFound = true;
                    } else {
                        isFound = false;
                        break;
                    }
                }
            }
        }
        return isFound;
    }

    public boolean optimized(char[][] board, String word){
        return checkIfExists(
                prepareAMap(board, word),
                word
        );
    }

    public static void main(String[] args){}
}
