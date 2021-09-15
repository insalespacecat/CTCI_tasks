package main.java.themes.ArraysAndStrings.LeetCode.PrefixSum;

//src: https://leetcode.com/problems/maximum-average-subarray-i/

//You are given an integer array nums consisting of n elements, and an integer k.
//
//Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.
//
//
//
//Example 1:
//
//Input: nums = [1,12,-5,-6,50,3], k = 4
//Output: 12.75000
//Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
//
//Example 2:
//
//Input: nums = [5], k = 1
//Output: 5.00000
//
//
//
//Constraints:
//
//    n == nums.length
//    1 <= k <= n <= 105
//    -104 <= nums[i] <= 104


//PREFIX SUM LOOKS KINDA NOT VERY EFFECTIVE HERE))

public class MaximumAverageSubarray_G {
    class Solution {
        //We have to start by calculating initial k numbers from index 0 to k
        //after that we have to move the window to the left and for each move check
        //if we found better average.
        //Prefix sum might help us to optimize the algorithm.
        //if we go over the array and calc the prefix sum we'll
        //just need to check j - i
        public double findMaxAverage(int[] nums, int k) {
            var ps = getPrefixSum(nums);
            int i = 0; int j = k;
            double max = ps[j-1] / k;

            while(j < nums.length) {
                if(max < ((ps[j] - ps[i]) / k)) {
                    max = ((ps[j] - ps[i]) / k);
                }

                j++; i++;
            }

            return max;
        }

        private double[] getPrefixSum(int[] nums) {
            var ps = new double[nums.length];

            for(int i = 0; i < nums.length; i++) {
                ps[i] = nums[i];
                if(i > 0) {
                    ps[i] += ps[i-1];
                }
            }

            return ps;
        }
    }
}
