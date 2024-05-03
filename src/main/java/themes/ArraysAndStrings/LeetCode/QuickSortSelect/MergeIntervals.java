package main.java.themes.ArraysAndStrings.LeetCode.QuickSortSelect;

//#quicksort
//src: https://leetcode.com/problems/merge-intervals/

//Given an array of intervals where intervals[i] = [starti, endi],
//merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

//Example 1:
//
//Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
//
//Example 2:
//
//Input: intervals = [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping.
//
//
//
//Constraints:
//
//1 <= intervals.length <= 104
//intervals[i].length == 2
//0 <= starti <= endi <= 104

public class MergeIntervals {
    class Solution {
        //Sort intervals by theia first value
        //Go over sorted intervals and merge 'em if next interval's start is less than prev interval end.
        //Time: O(NlogN), Space: O(N) (if no array copy, O(logN) - average, O(N) - worst case (quick sort stack))
        public int[][] merge(int[][] intervals) {
            if(intervals.length == 1) {
                return intervals;
            }

            var ic = getIntervalsCopy(intervals);
            qs(ic, 0, ic.length - 1);
            return mergeIntervals(ic);
        }

        private int[][] mergeIntervals(int[][] ic) {
            var ri = 0;

            for(int i = 0; i < ic.length - 1; i++) {
                if(ic[i][1] >= ic[i + 1][0]) {
                    ic[i + 1][0] = ic[i][0];
                    ic[i][0] = -1;

                    if(ic[i][1] > ic[i + 1][1]) {
                        ic[i + 1][1] = ic[i][1];
                    }

                    ri++;
                }
            }

            var answ = new int[ic.length - ri][2];
            int j = 0;

            for(int i = 0; i < ic.length; i++) {
                if(ic[i][0] != -1) {
                    answ[j][0] = ic[i][0];
                    answ[j][1] = ic[i][1];
                    j++;
                }
            }

            return answ;
        }

        private int[][] getIntervalsCopy(int[][] intervals) {
            var ic = new int[intervals.length][2];

            for(int i = 0; i < intervals.length; i++) {
                ic[i][0] = intervals[i][0];
                ic[i][1] = intervals[i][1];
            }

            return ic;
        }

        public void qs(int[][] ic, int l, int r) {
            if(r > l) {
                var p = part(ic, l, r);
                qs(ic, l, p - 1);
                qs(ic, p + 1, r);
            }
        }

        private int part(int[][] ic, int l, int r) {
            var p = l + (int)(Math.random() * (r - l));
            swap(ic, p, r);
            p = r;
            int i = l - 1;

            for(int j = l; j < r; j++) {
                if(ic[j][0] < ic[p][0]) {
                    i++;
                    swap(ic, i, j);
                }
            }

            swap(ic, i + 1, p);
            return i + 1;
        }

        public void swap(int[][] ic, int i, int j) {
            var temp = ic[i];
            ic[i] = ic[j];
            ic[j] = temp;
        }
    }
}
