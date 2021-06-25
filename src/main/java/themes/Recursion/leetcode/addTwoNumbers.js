/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */


/*
Solution. (Non-recursive)
The simplest straightforward solution would be to:
1) go over each linked list to find the length of each one
2) after finding the length, we could run 2nd iteration.
knowing the length of the list allows us to construct the number
for example: 5 -> 6 -> 4. We will count length starting with 0. Thus,
To construct our number we will use a simple formula: n * 10^(length-i)
so 5 * 10^2 = 500,
6 * 10^1 = 60,
4 * 10^0 = 4.
500 + 60 + 4 = 564.
3) after constructing each number we will have to break the answer back
into number. - this obviously would not work with recursion.
Complexity gotta be O(N).
*/


/*
Solution. (Recursive)
We might try to apply simple addition algorithm which we learnt in school.
  `
  3 4 2
+ 4 6 5
--------
  8 0 7

We might want to break this algorithm down into piecies:

2 -> 4 -> 3
5 -> 6 -> 4

at each recursive iteration we will have 2 digits from 2 lists to add,
we will have a spot to write the result down and we will have to deal with accumulator
that is going to be engaged if sum of 2 digits is more than 10.

so the recursive function might look like this:

function(l1, l2, lr) {

    let res = l1.val + l2.val;
    if(res >= 10) {
        lr.val = res % 10;
        lr.next.val = (res - lr.val) / 10;
    } else {
        lr.val = res;
    }

};

extreme conds:
1) !l1.next;
2) !l2.next;

pay attention:
1) create a new node of lr on each ri

exit cond:
1) !l1.next && !l2.next
*/

var addTwoNumbers = function(l1, l2) {
    let lr = new ListNode(0, null);
    addTwoNumbersRec(l1, l2, lr);
    return lr;
};

function addTwoNumbersRec(l1, l2, lr) {
    if(!l1 && !l2) {
        return;
    }

    if(l1?.next || l2?.next) {
        lr.next = new ListNode(0, null);
    }

    if(l1 && l2) {
        let res = lr.val + l1.val + l2.val
        lr.val = 0;
        lrNewVal(lr, res);
        addTwoNumbersRec(l1.next, l2.next, lr.next);
    }

    if(!l1) {
        let res = l2.val + lr.val;
        lr.val = 0;
        lrNewVal(lr, res);
        addTwoNumbersRec(l1, l2.next, lr.next);
    }

    if(!l2) {
        let res = l1.val + lr.val;
        lr.val = 0;
        lrNewVal(lr, res);
        addTwoNumbersRec(l1.next, l2, lr.next);
    }
}

function lrNewVal(lr, res) {
    if(res >= 10) {
        lr.val += res % 10;
        if(!lr.next) {
            lr.next = new ListNode(0, null);
        }
        lr.next.val += (res - res % 10) / 10;
    } else {
        lr.val += res;
    }
}