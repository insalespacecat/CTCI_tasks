package main.java.themes.LinkedList.CrackingTheCodingInterview;

public class ReturnKth {
    //Implement an algorithm to find Kth to last
    //element of a singly-linked-list

    //Ok the trick here is that the list is singly-linked
    //and we simply cannot iterate backwards.
    //1) Here we want to have the length of the linked list.
    //In java we can call .toArray() method and check the length
    //of the array in order to find how long the list is.
    //2) After we know the length then we can iterate to the proper
    //place.
    //This will take O(N-K) time.
    //If we don't have convenient java method .toArray or we
    //implement our own linked-list then we will simply
    //Iterate through all the list twice: 1st time to find the
    //Length, 2nd time to to get the node.
    //This will take O(N + N-K) which is too O(N-K) time
}
