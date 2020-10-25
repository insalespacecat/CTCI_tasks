package main.java.ArraysAndStrings;


public class OneAway {

    //One Away: There are three types of edits that can be performed on strings: insert a character,
    //remove a character, or replace a character. Given two strings, write a function to check if
    //they are one edit (or zero edits) away.

    //Straightforward option one: iterate over both strings,
    //We will have 3 options: 1) Character replaced 2) Character removed 3) Character inserted
    //And will check it with switch i guess like that:
    //if (referenceStr.length() == editedStr.length()) -> boolean replacedCharacterMet = false; -> if(referenceStrArr[i] != editedStrArr[i]) { replacedCharacterMet = true; }
    //if (referenceStr.length() == editedStr.length()+1) -> boolean removedCharacterMet = false; -> if(referenceStrArr[i] != editedStrArr[i]) { removedCharacterMet = true; }
    //-> if(removedCharacterMet) compare referenceStrArr[i+1] with editedStrArr[i] until referenceStrArr.length-1
    //if (referenceStr.length()+1 == editedStr.length()) -> boolean insertedCharacterMet = false; -> if(referenceStrArr[i] != editedStrArr[i]) { insertedCharacterMet = true; }
    //-> if(insertedCharacterMet) compare referenceStrArr[i] with editedStrArr[i+1] until referenceStrArr.length+1
    //Complexity will be O(n)

    public boolean oneAway(String referenceStr, String editedStr) {
        char[] referenceStrArr = referenceStr.toCharArray();
        char[] editedStrArr = editedStr.toCharArray();
        if(referenceStr.length() == editedStr.length()) {
            boolean replacedCharacterMet = false;
            for(int i = 0; i < referenceStrArr.length; i++){
               if(referenceStrArr[i] != editedStrArr[i]){
                   if(replacedCharacterMet){
                       return false;
                   } else {
                       replacedCharacterMet = true;
                   }
               }
            }
            return true;
        }
        if(referenceStr.length()-1 == editedStr.length()){
            boolean removedCharacterMet = false;
            for(int i = 0; i < referenceStrArr.length-1; i++){
                if(removedCharacterMet){
                    if(referenceStrArr[i+1] != editedStrArr[i]){
                        return false;
                    }
                } else {
                    if (referenceStrArr[i] != editedStrArr[i]) {
                        removedCharacterMet = true;
                        i--;
                    }
                }
            }
            return true;
        }
        if(referenceStr.length()+1 == editedStr.length()){
            boolean insertedCharacterMet = false;
            for(int i = 0; i < referenceStr.length(); i++){
                if(insertedCharacterMet){
                    if(referenceStrArr[i] != editedStrArr[i+1]){
                        return false;
                    }
                } else {
                    if(referenceStrArr[i] != editedStrArr[i]){
                        insertedCharacterMet = true;
                        i--;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args){
    }
}
