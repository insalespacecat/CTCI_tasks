package ArraysAndStrings;

public class CheckPermutation {
    //Implement an algorithm to decide if one string is a permutation of the other

    //Examples
    //1) Hello world, how is it - ollehw roldhow , si it
    //1) hi my name is andrei - hey, andrei, i'm dasha


    //Straightforward option 1
    //1) Quick Sort by ASCII - nlogn + nlogn
    //2) string1 == string2
    //3) O(nlogn)

    //Option 2
    //1) Go over both of the strings with complexity n+n
    //2) Use a hashmap to store keypairs 'a' - int occurrences[1]
    //i.e. symbol and array of 2 elements: element 0 contains
    //int of occurrences in the first string, second - occurrences
    //in the second string. And then we will just need to iterate over hashmap
    //iteration complexity will be >= 128 or 256
    // approx. O(n)

    //Option 3
    // Create matrix n*2: int[][] strCompArr = new int[str.toCharArray.length][1];
    // Iterate both strings O(n) complexity
    // strCompArr[str1Arr[i]][0] += 1;
    // strCompArr[str2Arr[i]][1] += 1;
    // Iterate over strCompArr and check strCompArr[i][0] == strCompArr[i][1] - n
    // complexity O(n)


    public static void main(String[] args){
        String str = "hello world";
        String str2 = "olleh world";
        char[][] strCompArr = new char[str.toCharArray().length][1];
        for(int i = 0; i < strCompArr.length; i++){
            strCompArr[i][0] = 0; strCompArr[i][1] = 0;
        }
        char[] str1Arr = str.toCharArray();
        char[] str2Arr = str2.toCharArray();
        for(int i = 0; i < str1Arr.length; i++){
            strCompArr[str1Arr[i]][0] += 1;
            strCompArr[str2Arr[i]][1] += 1;
        }

    }
}
