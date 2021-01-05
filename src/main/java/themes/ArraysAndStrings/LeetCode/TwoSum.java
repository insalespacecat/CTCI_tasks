package main.java.themes.ArraysAndStrings.LeetCode;


//Given an int[] nums array which holds numbers
//and int target. Find 2 numbers a and b from nums
//that will produce result a+b = target.
//There is always a solution and it is guaranteed to be unique

import java.util.HashMap;

public class TwoSum {
    //Brute force approach:
    //So it is very easy to state a simple brute force solution
    //which is basically "Try to sum all the numbers with each other"
    //Will take O(N^2) time.

    //Optimized approach:
    //We could use a hashmap in order to have optimized runtime.
    //The strategy is simple:
    //1) Form a HashMap - key: num[i], value: i
    //2) Iterate through num:
    //b = target - num[i]. if(hm.get(b) !== null && hm.get !== i)
    //then we found our solution
    //Runtime is O(N)

    private HashMap<Integer,Integer> createHashMap(int[] nums){
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            hm.put(nums[i], i);
        }
        return hm;
    }

    private int[] findTwoSum(HashMap<Integer, Integer> hm, int[] nums, int target){
        int[] twoSum = new int[2];
        for(int i = 0; i < nums.length; i++){
            int b = target - nums[i];
            if(hm.get(b) != null && hm.get(b) != i){
                twoSum[0] = i;
                twoSum[1] = hm.get(b);
                return twoSum;
            }
        }
        return twoSum;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hm = createHashMap(nums);
        return findTwoSum(hm, nums, target);
    }
}
