package main.java.themes.ArraysAndStrings.CrackingTheCodingInterview;

public class PalindromePermutation {

    //Given a string, write a function to check if it is a permutation of a palindrome.
    //A palindrome is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters.
    //the palindrome does not need to be limited to just dictionary words.


    //Straightforward option 1:
    //We need to check if we can construct a palindrome from the input phrase
    //The thing with the palindrome is that it consists from two parts and
    //these two parts have the same letters. It means that if we go over the phrase
    //calculate all the letters and check if every letter except middle (if number of letters is odd) can be divided by 2
    //Let's consider the example "dammit im mad"
    //Eliminating spaces it is dammitimmad
    //We have the following letters: 2*d 2*a 4*m 2*i 1*t
    //it is a palindrome cuz we have 1 anchor - t, all others can be divided by 2

    public static boolean palindromePermutation(String phrase){
        char[] phraseCharacters = phrase.toCharArray();
        int[] ASCIISymbolsPresented = new int[128];
        boolean middleAnchor = false;
        for(int i = 0; i < ASCIISymbolsPresented.length; i++){
            ASCIISymbolsPresented[i] = 0;
        }
        for(int i = 0; i < phraseCharacters.length; i++){
            ASCIISymbolsPresented[phraseCharacters[i]] += 1;
        }
        for(int i = 0; i < ASCIISymbolsPresented.length; i++){
            if(ASCIISymbolsPresented[i] == 0 || ASCIISymbolsPresented[i] == ' '){
                continue;
            }
            if(phraseCharacters.length % 2 != 0 && ASCIISymbolsPresented[i] % 2 != 0){
                if(!middleAnchor) {
                    middleAnchor = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(palindromePermutation("ddaammmmiit"));
        System.out.println(palindromePermutation("dda ammmmi it"));
        System.out.println(palindromePermutation("taco cat"));
        System.out.println(palindromePermutation("taco cat taco cat"));
        System.out.println(palindromePermutation("No my friend!"));
        System.out.println(palindromePermutation("No my friend!!dneirf ym oN"));
    }
}
