package main.java.ArraysAndStringsLeetCode;

import java.util.HashSet;

public class LongestUnique {
    //Given a string s, find the length of the longest substring without repeating characters and return its length
    //s consists of English letters, digits, symbols and spaces.
    //Input: s = "abcabcbb"
    //Output: 3
    //Explanation: The answer is "abc", with the length of 3.
    //Input: s = "pwwkew"
    //Output: 3
    //Explanation: The answer is "wke", with the length of 3.
    //Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

    //Straightforward option 1:
    //To find the substring we should search for continuous string without breaks.
    //That means that we need to take the letter, go on while we don't meet the duplicate,
    //remember the length and take the next letter.
    //Step 1: init the algorithm, Step 2: length of substring with unique char's from that letter,
    //Step 3: remember the length if it is bigger than biggest.
    //Complexity:  O(N*k), where k is size of the biggest substring

    //Option 2: in order to decrease Tsum we could remember the duplicated character = DC that we met
    //and do not perform substring counting until we pass this character.
    //For example shuczunshcuna
    //shucz is our substring. U is duplicated character, then, we won't start building the
    //string with letter 'h' and letter 'u'
    //It has problems if we implement it with first letters



    public int longestUnique(String str){
        char[] strArr = str.toCharArray();
        HashSet<Character> buffer = new HashSet<>();
        boolean NDM = true;
        char DC = '['; boolean DCExists = false;
        int longest = 0;
        int SSIndex;
        for(int i = 0; i < strArr.length; i++){
            if(DCExists){
                if(strArr[i] == DC){
                    DCExists = false;
                }
                continue;
            }
            SSIndex = i;
            while (NDM){
                NDM = buffer.add(strArr[SSIndex]);
                if(SSIndex < strArr.length-1) {
                    SSIndex++;
                }
            }
            DC = strArr[SSIndex]; DCExists = true;
            if(longest < buffer.size())
                longest = buffer.size();
            NDM = true;
            buffer.clear();
        }
        return longest;
    }

    public static void main(String[] args){}
}
