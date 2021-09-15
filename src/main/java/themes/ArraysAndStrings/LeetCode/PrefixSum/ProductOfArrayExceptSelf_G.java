package main.java.themes.ArraysAndStrings.LeetCode.PrefixSum;

public class ProductOfArrayExceptSelf_G {
    //calculate STE and ETS prefixProduct arrays
    //answer is going to be:
    //[ETS.length-1, ETS.length-2*STE[0], ETS.length-3*STE[1] ... ]
    //Time: O(N); O(N);
    public int[] productExceptSelf(int[] nums) {
        var STE = new int[nums.length];
        var ETS = new int[nums.length];
        var answ = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            STE[i] = nums[i];

            if(i > 0) {
                STE[i] = STE[i] * STE[i - 1];
            }
        }

        for(int i = nums.length - 1; i >= 0; i--) {
            ETS[nums.length - (i + 1)] = nums[i];

            if(i < nums.length - 1) {
                ETS[nums.length - (i + 1)] = ETS[nums.length - (i + 1)] * ETS[nums.length - (i + 2)];
            }
        }

        answ[0] = ETS[nums.length - 2];
        answ[nums.length - 1] = STE[STE.length - 2];

        int i = nums.length - 3; int j = 0; int k = 1;
        while(i >= 0 && j < nums.length - 2 && k < nums.length - 1) {
            answ[k] = ETS[i] * STE[j];
            i--; j++; k++;
        }

        return answ;
    }
}
