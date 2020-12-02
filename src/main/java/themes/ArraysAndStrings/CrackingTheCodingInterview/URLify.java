package main.java.themes.ArraysAndStrings.CrackingTheCodingInterview;

public class URLify {

    //URLify: Write a method to replace all spaces in a string with '%20:
    //You may assume that the string has sufficient space at the end to hold
    //the additional characters, and that you are given the "true" length of the string.
    //(Note: If implementing in Java, please use a character array so that you can perform this operation in place.)

    //Straightforward option 1:
    //Split the string by spaces to substrings - n
    //Glue the strings using StringBuilder - x where x is length of substring + 3y where y is number of spaces and
    //3 number of characters in %20
    //Complexity - O(n) -> n + x + 3y; n >= x and dominates x, n > 3y and dominates it;

    public static String URLifyTheString(String string){
        String[] words = string.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < words.length; i++){
            stringBuilder.append(words[i]);
            stringBuilder.append("%20");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args){
        System.out.println(URLifyTheString("Hei, amigos. let's go here!"));
    }
}
