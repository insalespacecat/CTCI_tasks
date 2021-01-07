package main.java.themes.ArraysAndStrings.LeetCode;

public class LongestPalindrome {
    //Find the longest palindromic substring
    //In order to do that, we need to analyze the palindrome properties
    //It has same letters before center and after center
    //So the best solution here is to assume that every letter in the string
    //can be a center of the palindrome, and also every two letters in the
    //string can be a center of the palindrome, and from that center
    //try to expand the string in both directions (i-1, j+1) at the same time
    //this will give us O(N^2) performance.


    //the best solution here is to assume that every letter in the string
    //can be a center of the palindrome, and also every two letters in the
    //string can be a center of the palindrome, and from that center
    //try to expand the string in both directions (i-1, j+1) at the same time
    //this will give us O(N^2) performance.

    //Step 1: run cycle for to check that every letter as the center
    //Example 1: "babad"
    //String lps = ""; //LPS = longest palindromic substring
    //i = 0;
    //a = i;
    //b = i;
    //while(a > 0 && b < s.length()){
    //if(a-1 > 0 && b+1 < s.length() && isAPailndrome(s.substring(a-1, b+1)){
    //  a--;
    //  b++;
    //} else {
    //.   if(lps.length() < s.substring(a, b).length()){
    //      lps = s.substring(a, b);
    //.   }
    //    break;
    //}
    //}

    //Example 2: ""
    //Cycle for won't fire cuz i = 0 && !(i < s.length())

    //Example 3: "aaaaaaaa"
    //int i = 0; int j = 1;
    //while(j < s.length() && i < s.length()){
    //String lps = ""; //LPS = longest palindromic substring
    //i = 0;
    //a = i;
    //b = j;
    //while(a > 0 && b < s.length()){
    //if(a-1 > 0 && b+1 < s.length() && isAPailndrome(s.substring(a-1, b+1)){
    //  a--;
    //  b++;
    //} else {
    //.   if(lps.length() < s.substring(a, b).length()){
    //      lps = s.substring(a, b);
    //.   }
    //    break;
    //}
    //}
    //}

    private boolean isAPalindrome(String s, int a, int b){
        if(s.length() == 0){
            return false;
        }
        if(a >= b){
            return true;
        }
        if(s.charAt(a) != s.charAt(b)){
            return false;
        }

        return isAPalindrome(s, a+1, b-1);
    }

    public String longestPalindrome(String s) {

        if(s.length() == 1){
            return s;
        }

        if(s.length() == 2){
            if(s.charAt(0) == s.charAt(1)){
                return s;
            } else {
                return s.substring(0, 1);
            }
        }

        if(isAPalindrome(s, 0, s.length()-1)){
            return s;
        }

        String lps = ""; int a = 0; int b = 0;
        for(int i = 0; i < s.length(); i++){
            a = i; b = i;
            while(a >=0 && b < s.length()){
                if(a-1 >= 0 && b+1 < s.length() && s.charAt(a-1) == s.charAt(b+1)){
                    a--;
                    b++;
                } else {
                    if(lps.length() < s.substring(a, b+1).length()){
                        lps = s.substring(a, b+1);
                    }
                    break;
                }
            }
        }

        int i = 0; int j = 1;

        while(j < s.length() && i < s.length()){

            if(s.charAt(i) == s.charAt(j)){
                a = i;
                b = j;

                while(a >= 0 && b < s.length()){
                    if(a-1 >= 0 && b+1 < s.length() && s.charAt(a-1) == s.charAt(b+1)){
                        a--;
                        b++;
                    } else {
                        if(lps.length() < s.substring(a, b+1).length()){
                            lps = s.substring(a, b+1);
                        }
                        break;
                    }
                }
            }

            i++; j++;
        }

        return lps;
    }
}
