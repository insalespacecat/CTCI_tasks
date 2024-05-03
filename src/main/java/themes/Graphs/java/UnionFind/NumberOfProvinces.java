package main.java.themes.Graphs.java.UnionFind;

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
            for(int j = i+1; j < isConnected.length; j++) {
                if(isConnected[i][j] == 1) {
                    ds.union(i+1, j+1);
                }
            }
        }

        return ds.findNumberOfSubsets();
    }
}

/*
class Solution {

    //Step 1: create disjoint set
    //Step 2: Go over graph to fill DS
    //Step 3: calc number of of subgroups.

    //We are going to use union by size + path compression
    //to achieve find operation complexity of O(1)+ (Reverse-ackermann function, max =5 for all conveviable numbers in universe)
    class SS {
        int s;
        int p;

        SS(int n) {
            this.s = 1;
            this.p = n;
        }
    }

    //DS has two main functions: union and find.
    //We implement disjoint set array of subSets.
    //Each index is a node in a graph which stores class SS
    //which tells us the parent of a node and it's size.
    //At the beginning each subset has size 0 and parent of itself
    //when we do union we find parents of each node passed to us
    //and we determine which parent has bigger size and
    //reassign parent with smaller size under parent with bigger size.
    //adding to bigger's parent size.
    //find with path compression recursively searches for the node
    //that has as parent itself and as it goes up from a recursion it
    //reassigns all the nodes' subparent to the root parent it found.
    class DS {
        SS[] ss;

        DS(int n) {
            this.ss = new SS[n+1];

            for(int i = 1; i < n+1; i++) {
                ss[i] = new SS(i);
            }
        }

        public int find(int s) {
            if(ss[s].p != s) {
                ss[s].p = find(ss[s].p);
            }

            return ss[s].p;
        }

        public boolean union(int n1, int n2) {
            var p1 = find(n1);
            var p2 = find(n2);

            if(p1 == p2) {
                return false;
            }

            if(ss[p1].s >= ss[p2].s) {
                ss[p1].s += ss[p2].s;
                ss[p2].p = p1;
            } else {
                ss[p2].s += ss[p1].s;
                ss[p1].p = p2;
            }

            return true;
        }

        public int numberOfSS() {
            var counter = 0;

            for(int i = 1; i < ss.length; i++) {
                if(ss[i].p == i) {
                    counter++;
                }
            }

            return counter;
        }
    }

    //Time O(N^2)+
    //Space: O(N)
    public int findCircleNum(int[][] isConnected) {
        var icon = isConnected;
        var ds = new DS(icon.length);

        for(int i = 0; i < icon.length; i++) {
            for(int j = i; j < icon.length; j++) {
                if(i != j && icon[i][j] == 1) {
                    ds.union(i+1, j+1);
                }
            }
        }

        return ds.numberOfSS();
    }
}
 */
