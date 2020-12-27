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
}
