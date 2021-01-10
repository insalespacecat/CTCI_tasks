package main.java.themes.LinkedList.LeetCode;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class RemoveNth {

    //Given the head of a linked list, remove the nth node from the end of the list and return its head.
    //
    //Follow up: Could you do this in one pass?

    //Input: head = [1,2,3,4,5], n = 2
    //Output: [1,2,3,5]

    //Input: head = [1], n = 1
    //Output: []

    //Input: head = [1,2], n = 1
    //Output: [1]

    //Constraints:
    //
    //The number of nodes in the list is sz.
    //1 <= sz <= 30
    //0 <= Node.val <= 100
    //1 <= n <= sz

    //In order to do that we have to create an array
    //of 30 elements which will store pointers to the
    //each list node.
    //When we hit the end on first iteration, we will just
    //pickup Nth value from the array and delete it.

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next == null){
            return null;
        }

        ListNode i = head;
        ListNode[] a = new ListNode[30];
        int it = 0;
        while(i != null){
            a[it] = i;
            it++;
            i = i.next;
        }

        if(n == 1){
            a[it-2].next = null;
        } else {
            a[it-n].val = a[it-n].next.val;
            a[it-n].next = a[it-n].next.next;
        }

        return head;
    }

}
