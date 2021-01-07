package main.java.themes.ArraysAndStrings.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class ThreeSum {

    //This is basically a subset of twoSum problem.
    //TwoSum's best conceviable time is O(N)
    //So runtime here sould be O(N^2) because
    //We basically run sliding window N times.

    //Another complication here is that we can have more
    //then one solution, and we cannot have duplicate solutions here.
    //To solve that problem we have to check if a is already presented
    //in the solutions before the sliding window is fired

    private void quickSort(int[] nums, int left, int right){

        int l = left;
        int r = right;
        int m = nums[(l+r)/2];

        do
        {
            while(m > nums[l]) {l++;}
            while(m < nums[r]) {r--;}

            if(l <= r){
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l++; r--;
            }

        } while(l < r);

        if(l < right) {quickSort(nums, l, right);}
        if(r > left) {quickSort(nums, left, r);}
    }

    private List<List<Integer>> calcThreeSum(int[] nums){
        //ts = three sum
        List<List<Integer>> ts = new ArrayList<>();
        for(int i = 0; i < nums.length-2; i++){
            if(ts.size() != 0 && ts.get(ts.size()-1).get(0) == nums[i]){
                continue;
            }
            int b = i+1;
            int c = nums.length-1;
            while(c > i && b < nums.length && c != b){
                if(nums[i]+nums[b]+nums[c] == 0){
                    ArrayList<Integer> answ = new ArrayList<Integer>();
                    answ.add(nums[i]);
                    answ.add(nums[b]);
                    answ.add(nums[c]);
                    if(ts.size() == 0){
                        ts.add(answ);
                    }
                    if(!ts.get(ts.size()-1).equals(answ)){
                        ts.add(answ);
                    }
                }
                if(nums[i]+nums[b]+nums[c] > 0){
                    c--;
                } else {
                    b++;
                }
            }
        }
        return ts;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length == 0){
            return new ArrayList<>();
        }
        quickSort(nums, 0, nums.length-1);
        return calcThreeSum(nums);
    }
}
