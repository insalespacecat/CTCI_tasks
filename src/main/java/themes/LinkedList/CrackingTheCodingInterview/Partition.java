package main.java.themes.LinkedList.CrackingTheCodingInterview;

public class Partition {
    //Write code to partition linked list around a certain value
    //It means that we have to 'split' list into two parts
    //first will contain values that are lesser than x,
    //second will contain values that are bigger than x.
    //You don't need to make x a center of the list.

    //Example:
    // input: 3 - > 5 -> 8 -> 5 -> 10 -> 2 -> 1 [x = 5]
    // output: 3 -> 1 -> 2 -> 10 -> 5 -> -> 5 -> 8

    //What has been done in the example is x swapped with values
    //lesser than x.

    //In this case we have two options:
    //1) X is contained within the list
    //2) X is not contained within the list
    //And one consideration: We want complexity O(N)

    //Here we use two pointers approach:
    //1st pointer is at the beginning of the list
    //if it finds the number that is greater than
    //x, we stop
    //2nd pointer is at the end of the list
    //if it finds number that is smaller than
    //x we stop and swap the values.
    //By when two pointers meet, the point of the
    //meet will be the separator of two partitions
    //Complexity: O(N)
}
