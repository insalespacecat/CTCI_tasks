package main.java.themes.ArraysAndStrings.CTCI;

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
    //complexity O(n)

    //Option 3
    //Create matrix n*2: int[][] strCompArr = new int[str.toCharArray.length][1];
    //Iterate both strings O(n) complexity
    //strCompArr[str1Arr[i]][0] += 1;
    //strCompArr[str2Arr[i]][1] += 1;
    //Iterate over strCompArr and check strCompArr[i][0] == strCompArr[i][1] - n
    //complexity O(n)

    public static boolean checkPermutation(String str, String str2) {
        char[] str1Arr = str.toCharArray();
        char[] str2Arr = str2.toCharArray();
        if(str1Arr.length != str2Arr.length){
            return false;
        }
        int[][] strCompArr = new int[128][2];
        for(int i = 0; i < strCompArr.length; i++){
            strCompArr[i][0] = 0; strCompArr[i][1] = 0;
        }
        for(int i = 0; i < str1Arr.length; i++){
            strCompArr[str1Arr[i]][0] += 1;
            strCompArr[str2Arr[i]][1] += 1;
        }
        for(int i = 0; i < str1Arr.length; i++){
            System.out.println("Str1 index " + (int)str1Arr[i] + " encountered " + strCompArr[str1Arr[i]][0] + " times");
            System.out.println("Str2 index " + (int)str1Arr[i] + " encountered " + strCompArr[str1Arr[i]][1] + " times");
            System.out.println(strCompArr[str1Arr[i]][0] + "==" + strCompArr[str2Arr[i]][1]);
            if (strCompArr[str1Arr[i]][0] != strCompArr[str1Arr[i]][1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(checkPermutation("hello world", "olleh world"));
        System.out.println(checkPermutation("hei amigos", "let's go over here"));
        System.out.println(checkPermutation("aaaaa", "a"));
        System.out.println(checkPermutation("bbbbb", "bbbbb"));
    }
}

//JS version:
/*
not so memory efficient, but working:
function checkPermutation(str1, str2) {
  const str1Arr = str1.split('');
  const str2Arr = str2.split('');

  if(str1Arr.length !== str2Arr.length) {
    return false;
  }

  const strCompArr1 = {};
  const strCompArr2 = {};

   for(let i = 0; i < str1Arr.length; i++) {
      strCompArr1[str1Arr[i]]? strCompArr1[str1Arr[i]]++ : strCompArr1[str1Arr[i]] = 1;
      strCompArr2[str2Arr[i]]? strCompArr2[str2Arr[i]]++ : strCompArr2[str2Arr[i]] = 1;
   }

   for(let key of Object.keys(strCompArr1)) {
     if(strCompArr2[key] !== strCompArr1[key]) {
        return false;
     }
    }

   return true;

   time complexity still O(N)
}

NOT WORKING?? PROBLEM WITH str1Arr[i].charCodeAt() - changes the whole array?
function checkPermutation(str1, str2) {
  const str1Arr = str1.split('');
  const str2Arr = str2.split('');

  if(str1Arr.length !== str2Arr.length) {
    return false;
  }

   const strCompArr = Array(128).fill(Array(2));

   strCompArr.forEach(value => {
     value[0] = 0;
     value[1] = 0;
   });

   for(let i = 0; i < str1Arr.length; i++) {
      strCompArr[str1Arr[i].charCodeAt(0)][0] += 1;
      strCompArr[str2Arr[i].charCodeAt(0)][1] += 1;
   }

   for(let i = 0; i < str1Arr.length; i++){
     if (strCompArr[str1Arr[i].charCodeAt(0)][0] !== strCompArr[str1Arr[i].charCodeAt(0)][1]) {
       return false;
     }
   }

   return true;
}
 */
