package main.java.themes.Graphs.UnionFind;

//Src: https://leetcode.com/problems/redundant-connection/
//In this problem, a tree is an undirected graph that is connected and has no cycles.
//You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added.
//The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
//The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
//Return an edge that can be removed so that the resulting graph is a tree of n nodes.
//If there are multiple answers, return the answer that occurs last in the input.

//Example 1:
//Input: edges = [[1,2],[1,3],[2,3]]
//Output: [2,3]

//Example 2:
//Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
//Output: [1,4]

//Constraints:
//n == edges.length
//3 <= n <= 1000
//edges[i].length == 2
//1 <= ai < bi <= edges.length
//ai != bi
//There are no repeated edges.
//The given graph is connected.

public class RedundantConnection {
    class SubSet {
        int parent;
        int rank;

        SubSet(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    //Disjoint set with path compression and union by rank
    //Relies on class subset for storing ranks for each node.
    //At the beginning we have N ununited nodes, each node has itself as its parent.
    //When we unite nodes, root remain to have itself as a parent, when nodes that
    //depend on root have root as a parent.
    class DisjointSet {
        SubSet[] subsets;

        //Init set with N subsets. Set parent of each node to itself,
        //set rank to 0.
        DisjointSet(int n) {
            subsets = new SubSet[n+1];

            for(int i = 1; i <= n; i++) {
                subsets[i] = new SubSet(i, 0);
            }
        }

        //Find with path compression.
        //It will compress path if we happen not to have already straight path to the root.
        public int find(int node) {
            if(subsets[node].parent != node) {
                subsets[node].parent = find(subsets[node].parent);
            }

            return subsets[node].parent;
        }

        //Union by rank. Accepts two elements to union. Finds their parents.
        //Compares the ranks of their parents. parent with higher rank eats parent with lower rank;
        public boolean union(int n1, int n2) {
            int p1 = find(n1);
            int p2 = find(n2);

            if(p1 == p2) {
                return false;
            }

            if(subsets[p1].rank > subsets[p2].rank) {
                subsets[p2].parent = p1;
                incrementRank(p1, p2);
            }

            if(subsets[p2].rank > subsets[p1].rank) {
                subsets[p1].parent = p2;
                incrementRank(p2, p1);
            }

            if(subsets[p1].rank == subsets[p2].rank) {
                subsets[p2].parent = p1;
                incrementRank(p1, p2);
            }

            return true;
        }

        private void incrementRank(int p1, int p2) {
            if(subsets[p2].rank == 0) {
                subsets[p1].rank++;
            } else {
                subsets[p1].rank += subsets[p2].rank;
            }
        }

    }


    //Time complexity O(N)+
    //Space complexity O(N)
    public int[] findRedundantConnection(int[][] edges) {
        var dSet = new DisjointSet(edges.length);

        for(int[] edge : edges) {
            if(!dSet.union(edge[0], edge[1])) {
                return edge;
            }
        }

        return null;
    }
}
