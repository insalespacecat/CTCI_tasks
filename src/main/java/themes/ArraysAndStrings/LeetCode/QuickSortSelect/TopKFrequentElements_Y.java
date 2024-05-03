package main.java.themes.ArraysAndStrings.LeetCode.QuickSortSelect;

//#quickselect
//src: https://leetcode.com/problems/top-k-frequent-elements/

//Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
//Example 1:
//Input: nums = [1,1,1,2,2,3], k = 2
//Output: [1,2]
//Example 2:
//Input: nums = [1], k = 1
//Output: [1]
//Constraints:
//1 <= nums.length <= 105
//k is in the range [1, the number of unique elements in the array].
//It is guaranteed that the answer is unique.
//Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

//Comments on approach:
//1) Don't do this generic conversion:
//var al = new ArrayList<Map.Entry<Integer,Integer>>(hm.entrySet());
//var me = new HashMap.Entry[al.size()];
//al.toArray(me);
//It makes no sense.
//It is much better to just construct an array of unique elements from nums and then query hashmap
//by those elements [see leetcode solution]

//2) The task is a continuation of KthLargestElement.
//The difference here, is that we have to construct frequency map, and run
//quick select on this frequency map.
//How big is the element is determined by how many times it was met in the original array.
//As we find our pivot, we have the whole partition sorted, so we can go ahead and extract our
//answer from arr.length - k to arr.length. Those will be top K largest elements
//Don't forged about average space O(logN) and worst space O(N)
//Average time: O(N), worst time: O(N^2)

public class TopKFrequentElements_Y {
    /*
    class Solution {
    //Space: O(N)
    //Time: O(N) worst: O(N^2)
    public int[] topKFrequent(int[] nums, int k) {
        if(nums.length == 1) {
            var answ = new int[1];
            answ[0] = nums[0];
            return answ;
        }

        var hm = getHMFreq(nums);
        var al = new ArrayList<Map.Entry<Integer,Integer>>(hm.entrySet());
        var me = new HashMap.Entry[al.size()];
        al.toArray(me);
        qs(me, 0, me.length - 1, me.length - k);

        return getResult(me, k);
    }

    private int[] getResult(Map.Entry<Integer, Integer>[] me, int k) {
        var answ = new int[k];

        for(int i = 0; i < k; i++) {
            answ[i] = me[me.length - 1 - i].getKey();
        }

        return answ;
    }

    private void qs(Map.Entry<Integer, Integer>[] me, int l, int r, int k) {
        var p = part(l, r, me);

        if(p > k) {
            qs(me, l, p - 1, k);
        }

        if(p < k) {
            qs(me, p + 1, r, k);
        }
    }

    private int part(int l, int r, Map.Entry<Integer, Integer>[] me) {
        var p = l + (int)(Math.random() * (r - l));
        swap(me, p, r);
        p = r;
        int i = l - 1;

        for(int j = l; j < r; j++) {
            if(me[j].getValue() < me[p].getValue()) {
                i++;
                swap(me, i, j);
            }
        }

        swap(me, i + 1, p);
        p = i + 1;
        return p;
    }

    private void swap(Map.Entry<Integer, Integer>[] me, int i, int j) {
        var temp = me[i];
        me[i] = me[j];
        me[j] = temp;
    }

    private HashMap<Integer, Integer> getHMFreq(int[] nums) {
        var hm = new HashMap<Integer, Integer>();

        for(int i = 0; i < nums.length; i++) {
            var f = hm.get(nums[i]);

            if(f == null) {
                hm.put(nums[i], 1);
            } else {
                hm.put(nums[i], f+1);
            }
        }

        return hm;
    }
     */

    /*
    LeetCode solution:
     */

    class Solution {
        int[] unique;
        Map<Integer, Integer> count;

        public void swap(int a, int b) {
            int tmp = unique[a];
            unique[a] = unique[b];
            unique[b] = tmp;
        }

        public int partition(int left, int right, int pivot_index) {
            int pivot_frequency = count.get(unique[pivot_index]);
            // 1. move pivot to end
            swap(pivot_index, right);
            int store_index = left;

            // 2. move all less frequent elements to the left
            for (int i = left; i <= right; i++) {
                if (count.get(unique[i]) < pivot_frequency) {
                    swap(store_index, i);
                    store_index++;
                }
            }

            // 3. move pivot to its final place
            swap(store_index, right);

            return store_index;
        }

        public void quickselect(int left, int right, int k_smallest) {
        /*
        Sort a list within left..right till kth less frequent element
        takes its place.
        */

            // base case: the list contains only one element
            if (left == right) return;

            // select a random pivot_index
            Random random_num = new Random();
            int pivot_index = left + random_num.nextInt(right - left);

            // find the pivot position in a sorted list
            pivot_index = partition(left, right, pivot_index);

            // if the pivot is in its final sorted position
            if (k_smallest == pivot_index) {
                return;
            } else if (k_smallest < pivot_index) {
                // go left
                quickselect(left, pivot_index - 1, k_smallest);
            } else {
                // go right
                quickselect(pivot_index + 1, right, k_smallest);
            }
        }

        public int[] topKFrequent(int[] nums, int k) {
            // build hash map : character and how often it appears
            count = new HashMap();
            for (int num: nums) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }

            // array of unique elements
            int n = count.size();
            unique = new int[n];
            int i = 0;
            for (int num: count.keySet()) {
                unique[i] = num;
                i++;
            }

            // kth top frequent element is (n - k)th less frequent.
            // Do a partial sort: from less frequent to the most frequent, till
            // (n - k)th less frequent element takes its place (n - k) in a sorted array.
            // All element on the left are less frequent.
            // All the elements on the right are more frequent.
            quickselect(0, n - 1, n - k);
            // Return top k frequent elements
            return Arrays.copyOfRange(unique, n - k, n);
        }
    }
}
