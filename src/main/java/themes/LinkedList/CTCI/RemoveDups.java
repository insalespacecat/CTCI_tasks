package main.java.themes.LinkedList.CTCI;

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

    //s = starting node
    public static void removeDups(Node s){
        Node i = s;
        boolean[] ASCII128 = new boolean[128];
        while(i.next != null) {
            if (ASCII128[i.value]) {
                i.value = i.next.value;
                i.next = i.next.next;
            } else {
                ASCII128[i.value] = true;
            }
            i = i.next;
        }
    }

}
