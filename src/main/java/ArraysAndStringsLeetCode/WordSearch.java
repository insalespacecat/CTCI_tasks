package main.java.ArraysAndStringsLeetCode;

import java.util.ArrayList;
import java.util.HashMap;

class C {
    int x;
    int y;

    C (int x, int y){
        this.x = x;
        this.y = y;
    }
}

//TODO: Miscellaneous practice: Arrays&Strings, BFS, Recursion.
public class WordSearch {
    // Given an m x n board and a word, find if the word exists in the grid.
    //
    // The word can be constructed from letters of sequentially adjacent cells,
    // where "adjacent" cells are horizontally or vertically neighboring.
    // The same letter cell may not be used more than once.

    // Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
    // Output: true

    // Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
    // Output: true

    // Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
    // Output: false

    // m == board.length
    // n = board[i].length
    // 1 <= m, n <= 200
    // 1 <= word.length <= 103
    // board and word consists only of lowercase and uppercase English letters.


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

    // Abstract
    // Basically the task is simple: to check if the word exists we have to repeat one algorithm:
    // check if all the neighboring cells have the next letter in the word.
    // i.e. 4 comparisons (x-1, x+1, y-1, y+1).
    // if we find the next letter in the neighbor cell, then we are good and we can continue.
    // if we do not find the next letter, then we break

    // SOO, the BRUTE FORCE approach will be just repeat this process for every cell in the board,
    // And if the word exists, we will eventually find the proper starting point from which we can start and
    // using 4 comparisons algorithm appear at the end.
    // COMPLEXITY of that is: O(N*M), where N is size of the board, and M is size of the word.
    // And this is so bad that leetcode considers that out of runtime limit.

    // SOO, the OPTIMIZED approach is the following:
    // We know that 9/10 best possible runtime in strings problems is O(N), which means that
    // we have to read input only once, and during that reading do the magic stuff that will
    // allow us separately run O(N) or less algorithm to actually come up with a solution

    // Here is what we will do:

    // 1) We will read the board once, and will create a hashmap.
    // HashMap keypair will be... KEY: Letter of the word + VALUE: it coordinates in the board. (x,y)
    // So key is Character, and for coordinates we will create class C { int x; int y; }
    // And we will come up with HashMap<Character, ArrayList<C>>

    // 2) After that, the "magic" that we are supposed to do is the following:
    // We have cached all occurrences of words characters
    // What we have to do is iterate over hashmap and check if the letters are connectable or not.
    // By comparing only the letters that we are interested in
    // We will do that firing up the same 4 comparisons algorithm (x+1, x-1, y+1, y-1).

    // Buuuuuuuuuuut... it is not that easy.
    // - First of all our word could have the same letter repeated multiple times,
    // so for example in "ABCB" what can come up with WRONG TRUE, because
    // when we go from B to C we find that they are connectable, and when we go
    // from C to B we will find out that they are connectable too, but THEY ARE NOT,
    // because we can literally went a step back with this 4 comparisons. So we need
    // to cross away the letters that we have already passed in order to avoid
    // looping and checking the same letter two or more times
    // - The second thing is that we could have AMBIGUOUS choices.
    // and they might lead to different results.
    // For example, board:
    // {
    // {'A', 'B', 'C', 'E'},
    // {'S', 'F', 'E', 'S'},
    // {'A', 'D', 'E', 'E'}
    // }
    // and the word "ABCESEEEFS"
    // Here on step 3 we face an ambiguous choice:
    // {
    // {'x', 'x', 'x', 'E'},
    // {'S', 'F', 'E', 'S'},
    // {'A', 'D', 'E', 'E'}
    // }
    // We may go down and don't find the answer
    // {
    // {'x', 'x', 'x', 'E'},
    // {'S', 'F', 'x', 'x'},
    // {'A', 'D', 'x', 'x'}
    // }
    // Or we may also go right and face another ambiguous choice
    // {
    // {'x', 'x', 'x', 'x'},
    // {'S', 'F', 'E', 'x'},
    // {'A', 'D', 'E', 'E'}
    // }
    // Which will lead us either to correct answer or to wrong FALSE
    // So the complexity here is that we might face ambiguous choices and deal with them
    // How we can deal with these>>>
    // We should save the state of map and the choice we made in order to replay the algorithm
    // from that place if we won't find the correct answer by picking the first of all the choices.
    // And since the situation with ambiguous choices is pretty common we have to maintain the
    // ArrayList for all the map states and ArrayList for all indexes in the word.
    // And we also have to store the 3rd ArrayList of class ANs (Ambiguous Neighbors) which will
    // indicate which of 4 neighbors are valid to go, and which aren't
    // So after each try if we will not find the word, we should return to this place, cross of of the
    // neighbors and go to another. ANd what if we meet another ambiguous choice out theree?? AA??


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
        if(map.size() == 0){
            return false;
        }
        if(map.size() == 1){
            return map.get(word.charAt(0)).size() >= word.length();
        }
        boolean isFound = false;
        ArrayList<Integer> iAmbiguous = new ArrayList<Integer>();
        ArrayList<HashMap<Character, ArrayList<C>>> mapStatesCache = new ArrayList<>();
        for(int i = 0; i < word.length()-1; i++){
            ArrayList<C> C1 = map.get(word.charAt(i));
            ArrayList<C> C2 = map.get(word.charAt(i+1));
            for(C c1: C1){
                for(C c2: C2){
                    isFound = false;
                    if(c1.x+1 == c2.x && c1.y == c2.y){
                        isFound = true;
                        c1.x = -10;
                    }
                    if(c1.x-1 == c2.x && c1.y == c2.y){
                        if(isFound){
                            iAmbiguous.add(i);
                            c1.x = -10;
                            mapStatesCache.add(map);
                        } else {
                            isFound = true;
                            c1.x = -10;
                        }

                    }
                    if(c1.y-1 == c2.y && c1.x == c2.x){
                        if(isFound){
                            iAmbiguous.add(i);
                            c1.x = -10;
                            mapStatesCache.add(map);
                        } else {
                            isFound = true;
                            c1.x = -10;
                        }
                    }
                    if(c1.y+1 == c2.y && c1.x == c2.x) {
                        if(isFound){
                            iAmbiguous.add(i);
                            c1.x = -10;
                            mapStatesCache.add(map);
                        } else {
                            isFound = true;
                            c1.x = -10;
                        }
                    }
                }
                if(isFound){
                    break;
                }
            }
            if(!isFound){
                break;
            }
        }
        if(!isFound){
            int iANavigator = 0;
                while(iAmbiguous.size() != 0) {
                    for (int i = iAmbiguous.get(iANavigator); i < word.length() - 1; i++) {
                        HashMap<Character, ArrayList<C>> mapCache = mapStatesCache.get(iANavigator);
                        ArrayList<C> C1 = mapCache.get(word.charAt(i));
                        ArrayList<C> C2 = mapCache.get(word.charAt(i + 1));
                        for (C c1 : C1) {
                            for (C c2 : C2) {
                                if (c1.x + 1 == c2.x && c1.y == c2.y) {
                                    isFound = true;
                                    c1.x = -10;
                                }
                                if (c1.x - 1 == c2.x && c1.y == c2.y) {
                                    if (isFound) {
                                        iAmbiguous.add(i);
                                        c1.x = -10;
                                        mapStatesCache.add(map);
                                    } else {
                                        isFound = true;
                                        c1.x = -10;
                                    }

                                }
                                if (c1.y - 1 == c2.y && c1.x == c2.x) {
                                    if (isFound) {
                                        iAmbiguous.add(i);
                                        c1.x = -10;
                                        mapStatesCache.add(map);
                                    } else {
                                        isFound = true;
                                        c1.x = -10;
                                    }
                                }
                                if (c1.y + 1 == c2.y && c1.x == c2.x) {
                                    if (isFound) {
                                        iAmbiguous.add(i);
                                        c1.x = -10;
                                        mapStatesCache.add(map);
                                    } else {
                                        isFound = true;
                                        c1.x = -10;
                                    }
                                } else {
                                    isFound = false;
                                }
                            }
                            if (isFound) {
                                break;
                            }
                        }
                        if (!isFound) {
                            iAmbiguous.remove(iANavigator);
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
