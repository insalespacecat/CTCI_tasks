package main.java.themes.ArraysAndStrings.LeetCode;

//Given array of int[] nums and target
//find two nums, a and b which satisfy condition
//a+b = target.
//Array nums is sorted.


//Solution 1:
//If array nums is sorted we could fire up binary search
//in order to find b = target - a[i]. This will give us
//solution O(NlogN)

//Solution 2:
//We could use sliding window approach in order to
//find the pair required.
//put 1st pointer int A = 0 to the start
//2nd pointer int B = arr.length to the end
//if(arr[a]+arr[b] > target) => b--;
//if(arr[a]+arr[b] < target) => a++;
//This will give us complexity O(N)

public class TwoSumII_ArrayIsSorted {
    public int[] twoSum(int[] numbers, int target) {
        int a = 0;
        int b = numbers.length-1;
        while(numbers[a]+numbers[b] != target){
            if(numbers[a]+numbers[b] > target){
                b--;
            } else {
                a++;
            }
        }
        int[] sum = {a+1, b+1};
        return sum;
    }
}
