package main.java.themes.LinkedList.CrackingTheCodingInterview;

public class SumLists {
    // You have two numbers represented by two linked lists
    // each node contains 1 digit
    // Numbers are stored in reverse order, such as the
    // head of the list is the first digit
    // Write a function that will sum up these two numbers
    // and return a new number in linked list format

    //Example:
    //Input 7 -> 1 -> 6 + 5 -> 9 -> 2 = 617+295 = 912 = 2 -> 1 -> 8

    //OK numbers are stored in reversed order so we might use 1st grade
    //math class addition tactics: sum 2 digits, write the result, remember
    //the exceed.

    //We will implement iterative algorithm with int buffer, which will remember the
    //number from the last addition.
    //int buffer = 0 by default.

    //What we have to do is to
    // 1) spawn 3 iterators,
    // 2) sum two numbers + buffer
    // 3) if sum > 10, sumListNode.data = sum%10, buffer = sum-sum%10
    // 4) else sumListNode.data = sum, buffer = 0;
    //And we will also require some checks that the list contains the next node,
    //and if it does not, then we will just sum up 1 number + buffer or only add buffer.
    //Complexity: O(N)




}
