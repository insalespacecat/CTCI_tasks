package main.java.themes.LinkedList.CrackingTheCodingInterview;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class RemoveDups {
    //Write the code to remove duplicates from an unsorted linked list

    //Considerations:
    //1) There is no difference is the list singly linked or doubly linked
    //2) What kind of data stores the linked list?
    //3) If data is complex (like objects) what is considered as a duplicate?

    // We will assume that liked list stores characters

    // In order to solve the problem effectively, we need to have a buffer
    // to help us determine if we already met the data fragment.
    // If we are not allowed to use buffer, then we will have to iterate a
    // list and compare each node to every other which will take O(N^2) time.

    // If we are allowed to have buffer and we have data as chars and their
    // encoding is for example ASCII. Then we will use 128 boolean array
    // to determine whether we already met this character or not.
    // We will iterate over the list only once, wiping out the node if it is
    // duplicated. This will take O(N) time.

    //I think that this is very clumsy to write down each list problem because
    //it consumes incredible amount of time. We will conspect briefly
    //each problem and move forward and write the actual code and tests only for the
    //hard ones.
    public static LinkedList<Character> removeDups(LinkedList<Character> l){
        boolean[] t = new boolean[128];
        for(ListIterator<Character> i = l.listIterator(); i.hasNext();){

        }
        return null;
    }

}
