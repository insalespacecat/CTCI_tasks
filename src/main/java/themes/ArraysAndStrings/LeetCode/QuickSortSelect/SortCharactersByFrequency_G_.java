package main.java.themes.ArraysAndStrings.LeetCode.QuickSortSelect;

public class SortCharactersByFrequency_G_ {
    class Solution {
        private HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        //Step 1: Construct char frequency arr ascii 128 (N)
        //Step 2: Sort char arr qs (NlogN) - optimize?
        //Step 3: Construct a new string with SB going from most freq to least freq
        //Time O(NlogN), Space: O(N)
        //Or if we consider k as number of unique characters in hashmap cuz
        //unique characters are not infinite, we are going to have O(N + klogk)
        //Space O(N + K) == O(N)
        public String frequencySort(String s) {
            for(int i = 0; i < s.length(); i++) {
                var f = hm.get(s.charAt(i));

                if(f == null) {
                    hm.put(s.charAt(i), 1);
                } else {
                    hm.put(s.charAt(i), f + 1);
                }
            }

            var unique = new char[hm.size()];
            var sb = new StringBuilder();
            int k = 0;

            for(Character key : hm.keySet()) {
                unique[k] = key;
                k++;
            }

            qs(unique, 0, unique.length - 1);

            for(int i = unique.length - 1; i >= 0; i--) {
                var f = hm.get(unique[i]);

                for(int j = 0; j < f; j++) {
                    sb.append(unique[i]);
                }
            }

            return sb.toString();
        }

        private void qs(char[] arr, int l, int r) {
            if(r > l) {
                var p = part(arr, l, r);

                qs(arr, l, p - 1);
                qs(arr, p + 1, r);
            }
        }

        private int part(char[] arr, int l, int r) {
            int p = l + (int)(Math.random() * (r - l));
            swap(arr, p, r);
            p = r;
            int i = l - 1;
            var pF = hm.get(arr[p]);

            for(int j = l; j < arr.length; j++) {
                if(hm.get(arr[j]) < pF) {
                    i++;

                    swap(arr, i, j);
                }
            }

            swap(arr, i + 1, p);
            return i + 1;
        }

        private void swap(char[] arr, int i, int j) {
            var temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
