package main.java.themes.Graphs.UnionFind;

//Source: https://leetcode.com/problems/number-of-provinces/
//There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
//and city b is connected directly with city c, then city a is connected indirectly with city c.
//A province is a group of directly or indirectly connected cities and no other cities outside of the group.
//You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
//Return the total number of provinces.

//Example 1:
//Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
//Output: 2

//Example 2:
//Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
//Output: 3

//Constraints:
//    1 <= n <= 200
//    n == isConnected.length
//    n == isConnected[i].length
//    isConnected[i][j] is 1 or 0.
//    isConnected[i][i] == 1
//    isConnected[i][j] == isConnected[j][i]

public class NumberOfProvinces {

    class SubSet {
        int parent;
        int rank;

        SubSet(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    class DisjointSet {
        SubSet[] subsets;

        DisjointSet(int n) {
            subsets = new SubSet[n+1];

            for(int i = 0; i < n+1; i++) {
                subsets[i] = new SubSet(i, 0);
            }
        }

        int find(int node) {
            if(subsets[node].parent != node) {
                subsets[node].parent = find(subsets[node].parent);
            }

            return subsets[node].parent;
        }

        void union(int n1, int n2) {
            int p1 = find(n1);
            int p2 = find(n2);

            if(subsets[p1].rank > subsets[p2].rank ||
                    subsets[p2].rank == subsets[p1].rank) {
                subsets[p2].parent = p1;
                incrementRank(p1, p2);
            }

            if(subsets[p2].rank > subsets[p1].rank) {
                subsets[p1].parent = p2;
                incrementRank(p2, p1);
            }
        }

        void incrementRank(int p1, int p2) {
            if(subsets[p2].rank == 0) {
                subsets[p1].rank++;
            } else {
                subsets[p1].rank += subsets[p2].rank;
            }
        }

        int findNumberOfSubsets() {
            int acc = 0;
            var arr = new boolean[subsets.length];

            for(int i = 1; i < subsets.length; i++) {
                int parent = find(i);

                if(!arr[parent]) {
                    acc++;
                    arr[parent] = true;
                }
            }

            return acc;
        }
    }

    //Complexity:
    //If we use path compression + union by rank, then find and union both have complexity O(1)+
    //Which makes runtime O(N)+
    //Space complexity: O(N)
    public int findCircleNum(int[][] isConnected) {
        var ds = new DisjointSet(isConnected.length);

        for(int i = 0; i < isConnected.length; i++) {
            for(int j = 0+i; j < isConnected.length; j++) {
                if(i == j) {
                    continue;
                }

                if(isConnected[i][j] == 1) {
                    ds.union(i+1, j+1);
                }
            }
        }

        return ds.findNumberOfSubsets();
    }
}
