package main.java.themes.LinkedList.CrackingTheCodingInterview;


public class DeleteMiddleNode {
    // Delete middle node:
    // You are given the node instance from the singly-linked list
    // Create void function which will remove the node from the list
    // To eliminate the node we usually have to overwrite the pointer
    // of the previous node, but here we don't have access to the
    // previous node.
    // So in order to eliminate the node as given we simply have to
    // assign to the given node pointer the value of the next node
    // like deleteNode(Node N)
    // node.value = node.next.value;
    // node.next = node.next.next;

    //You should also discuss extreme situation that the node given
    //is the last node, in this case we will just check that the next
    //node exists before overwriting

    void deleteNode(Node node){
        if(node.next != null) {
            node.value = node.next.value;
            node.next = node.next.next;
        }
    }

}
