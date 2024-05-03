package main.java.themes.ArraysAndStrings.LeetCode.QuickSortSelect;

public class KthLargestElement_Y {
    //#quickselect
    //src: https://leetcode.com/problems/kth-largest-element-in-an-array/
    //Given an integer array nums and an integer k, return the kth largest element in the array.
    //
    //Note that it is the kth largest element in the sorted order, not the kth distinct element.
    //
    //Example 1:
    //
    //Input: nums = [3,2,1,5,6,4], k = 2
    //Output: 5
    //
    //Example 2:
    //
    //Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
    //Output: 4

    //Time: O(N), Space: O(1)
    //class Solution {
    //    public int findKthLargest(int[] nums, int k) {
    //        int[] numsCopy = new int[nums.length];
    //
    //        for(int i = 0; i < numsCopy.length; i++) {
    //            numsCopy[i] = nums[i];
    //        }
    //
    //        return qs(0, numsCopy.length-1, numsCopy, numsCopy.length - k);
    //    }
    //
    //    private int qs(int l, int r, int[] arr, int k) {
    //        var p = part(l, r, arr);
    //
    //        if(p == k) {
    //            return arr[p];
    //        }
    //
    //        if(p > k) {
    //           return qs(l, p-1, arr, k);
    //        } else {
    //           return qs(p+1, r, arr, k);
    //        }
    //    }
    //
    //    private int part(int l, int r, int[] arr){
    //         int p = r;
    //         int i = l-1;
    //
    //         for(int j = l; j < r; j++) {
    //             if(arr[j] <= arr[p]) {
    //                 i++;
    //
    //                 swap(arr, i, j);
    //             }
    //         }
    //
    //         swap(arr, p, i+1);
    //
    //         return i+1;
    //    }
    //
    //    private void swap(int[] arr, int i, int j) {
    //        int temp = arr[i];
    //        arr[i] = arr[j];
    //        arr[j] = temp;
    //    }
    //}

}
