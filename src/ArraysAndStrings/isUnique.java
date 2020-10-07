package ArraysAndStrings;

import java.util.Arrays;
import java.util.HashSet;

public class isUnique {

    //Implement an algorithm to determine if a string has all unique characters
    //What if you cannot use additional data structures?

    //Straight forward option 1:
    // 1) Character[] phrase = str.toCharArray(); - n
    // 2) int[] ASCIIPhrase;
    // for (int i = 0; i < phrase.length; i++) { ASCIIPhrase = (int) phrase[i]; } - n
    // 3) quickSort ASCII - n*logn
    // 4) for (int i = 0; i < ASCIIPhrase; i++) {
    //      binarySearch(ASCIIPhrase, i, ASCIIPhrase.length, ASCIIPhrase[i];
    //    }  - n*logn
    // TimeSum = n + n + nlogn + nlogn = 2n + 2nlogn.
    // which is O(nlogn): nlogn > n and dominates it, 2 is constant.

    //Option 2:
    //Use hashset (which is a hashmap with pairs "Key - new Object()") + cycle - O(n) cuz set.add is O(1)


     static boolean isUnique (String phrase) {
        HashSet<Character> checkSet = new HashSet<Character>();
        char[] charactersArray = phrase.toCharArray();
        System.out.println(Arrays.toString(charactersArray));
        for(int i = 0; i < charactersArray.length; i++){
            System.out.println(charactersArray[i]);
            if(charactersArray[i] == ' '){
                System.out.println("Space met");
                continue;
            }
            if(!checkSet.add(charactersArray[i])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(isUnique("abcd efj hijk"));
        System.out.println(isUnique("Hello, my friend! How is it going?"));
        System.out.println(isUnique("aaaaaaaaaa"));
        System.out.println(isUnique("Yes"));
        System.out.println(isUnique("Its okay"));
    }
}
