package main.java.themes.Recursion.ClassicRecursiveAlgorithms;

//So simplified quicksort implementation looks like this:
//
//1) choose rightmost value as a pivot
//2) set i to left-1 set j to left
//3) j is our scanner var for the array.
//4) for(int j = left; j < right; j++)
//We need to accumulate values that are less then pivot in the beginning of the array
//5) check if arr[j] <= arr[pivot]
//if the condition is false, then it means that this value is bigger and it is to be swapped from the beginning of the array.
//if condition is true then we have to swap j into the beginning.
//
//As soon as we find the first j to swap, i helps: i always points at the border between values that are less than pivot and values that are bigger than pivot.
//At the beginning we don't have any border, therefore i is a negative -1, when j does the first steps it could go with two scenarios:
//
//- 1st value in the array is bigger then pivot and it's to be swapped, we continue to iterate j until we find 1st element less or equal the pivot.
// then -> i++; swap the values; i now points at the border between values less than pivot and bigger than pivot.
//- 1st value in the array is less or equals the pivot
// -> i++; swap value in place; we established our border.
//
//Then we just continue to swap values that are less then pivot to the beginning of the array until we don’t pass the whole array.
//When we finish, i always points at the end of the window, so we must swap pivot with i+1 to finish sorting, and i+1 becomes a new pivot
//It is always better though to choose random number as a pivot then always choose last value or middle value or first value

public class QuickSort {
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            int[] numsCopy = new int[nums.length];

            for(int i = 0; i < numsCopy.length; i++) {
                numsCopy[i] = nums[i];
            }

            qs(0, numsCopy.length-1, numsCopy);

            return numsCopy[numsCopy.length - k];
        }

        private void qs(int l, int r, int[] arr) {
            if(l < r) {
                var p = part(l, r, arr);

                qs(l, p-1, arr);
                qs(p+1, r, arr);
            }
        }

        private int part(int l, int r, int[] arr){
            int p = r;
            int i = l-1;

            for(int j = l; j < r; j++) {
                if(arr[j] <= arr[p]) {
                    i++;

                    swap(arr, i, j);
                }
            }

            swap(arr, p, i+1);

            return i+1;
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

}
