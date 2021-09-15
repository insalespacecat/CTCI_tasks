package main.java.themes.ArraysAndStrings.LeetCode;

//#3pointers
//src: https://leetcode.com/problems/merge-sorted-array/

//You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
//
//Merge nums1 and nums2 into a single array sorted in non-decreasing order.
//
//The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

public class MergeSortedArray_G {
    class Solution {
        //start iterating from the end of the arrays
        //check which value is bigger nums1[i] or nums2[j]
        //the biggest value is guaranteed to be the last in the arr nums1
        //if nums2 is bigger then just put it in the end and do j--;
        //if nums1 is bigger then swap it in the end and do i--;
        //putPoint-- whenever we put something in the end;
        //Complexity: O(N+M);
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int i = m-1;
            int j = n-1;
            //putPoint
            int pp = nums1.length-1;

            if(m == 0 && nums1.length > 0) {
                for(int k = 0; k < nums1.length; k++) {
                    nums1[k] = nums2[k];
                }
            } else {
                while(j >= 0) {
                    if(i == -1) {
                        nums1[pp] = nums2[j];
                        j--;
                        pp--;
                        continue;
                    }

                    if(nums1[i] > nums2[j]) {
                        nums1[pp] = nums1[i];
                        nums1[i] = 0;
                        i--;
                        pp--;
                    } else {
                        nums1[pp] = nums2[j];
                        j--;
                        pp--;
                    }
                }
            }
        }
    }
}
