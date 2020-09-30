package ArraysAndStrings;

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

    isUnique () {

    }

    public static void main(String[] args){

    }
}
