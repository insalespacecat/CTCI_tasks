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

    //OK, best option i have actually learnt after seeing the explanation is to use "sliding window"
    //The case here is that we use the same thing as option one but we will use pointers in a different
    //way: pointers i and j, i is the beginning of the substring, j is the end of the substring. We push j
    //while substring is valid, if substring becomes not valid, then we start to push our i pointer.
    //This will have complexity N+N=O(N)
    //Just need to implement it:)
    //We will assume ASCII extended encoding and any characters possible

    //After implementation goes optimization:
    //1) If we use Set then we could get rid of currentLength
    //2) We could replace set with ASCII boolean array
    //but memory efficiency of that is discussable
    //our substring won't be longer than 256 symbols
    //and in most cases it will be smaller then 256 symbols
    //But HashSet consumes much more space then an array
    //Cuz HashSet is a HashMap which stores pairs Key - New Object()
    //and HashMap basically stores type Entry, which contains
    //object, key, keyHash, pointer
    //that will consume far more memory then just an array of same size

    //abbrev: LU = longest unique, CL = currentLength

    public int longestUnique(String str){
        char[] strArr = str.toCharArray();
        HashSet<Character> cache = new HashSet<>();
        int LU = 0;
        int j = 0; int i = 0;
        boolean valid = true;
        while(j < strArr.length-1) {
            while (valid && j < strArr.length) {
                valid = cache.add(strArr[j]);
                if(valid) {
                    j++;
                }
            }
            if (cache.size() > LU) {
                LU = cache.size();
            }
            while (!valid && i < strArr.length) {
                cache.remove(strArr[i]);
                i++;
                valid = cache.add(strArr[j]);
                if (valid) {
                    j++;
                }
            }
        }
        return LU;
    }

    public static void main(String[] args){}
}
