package main.java.themes.ArraysAndStrings.CrackingTheCodingInterview;

public class StringCompression {

    //String Compression: Implement a method to perform basic string compression using the counts of repeated characters.
    //For example, the string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than the original string,
    //your method should return the original string. You can assume the string has only uppercase and lowercase letters (a - z).

    //Straightforward option 1:
    //Create two variables: 1st to memorize a character, 2nd to count how many times it repeats
    //Create StringBuilder to form compressed string.
    //Iterate over the string.
    //If we meet a new character, then we push to stringBuilder a character and how many times it repeats
    //write to 1st variable a new character and 0 to the 2nd
    //Complexity O(n)

    public static String stringCompression(String stringToCompress){
        char first = ' ';
        int repeatCounter = 1;
        StringBuilder compressedStringBuilder = new StringBuilder();
        char[] stringToCompressArr = stringToCompress.toCharArray();
        for(int i = 0; i < stringToCompressArr.length; i++){
            if(first != stringToCompressArr[i]){
                if(i != 0){
                    compressedStringBuilder.append(first);
                    compressedStringBuilder.append(repeatCounter);
                }
                first = stringToCompressArr[i];
                repeatCounter = 1;
            } else {
                repeatCounter++;
                if(i == stringToCompressArr.length-1){
                    compressedStringBuilder.append(first);
                    compressedStringBuilder.append(repeatCounter);
                }
            }
        }
        return compressedStringBuilder.toString();
    }

    public static void main(String[] args){
        System.out.println(stringCompression("aaabbcceaaa"));
        System.out.println(stringCompression("bbbbbbbbbbb"));
        System.out.println(stringCompression("bbbbbbbbbbbaaacc"));
        System.out.println(stringCompression("abcdefgghhjklmnop"));
    }
}
