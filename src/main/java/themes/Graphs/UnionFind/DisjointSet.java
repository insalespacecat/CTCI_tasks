package main.java.themes.Graphs.UnionFind;

//Disjoint set is a data structure that contains sets which do not have any overlapping nodes.
//It stores an array (or a map in case of nodes labeled with letters)
//Array works like that: for example you have graph 0 -> 1 -> 2   3 -> 4
//This graph has two disconnected subgraphs in it, and both of subgraphs don't have overlapping values.
//Union find stores that subgraphs in the arrays below:
// [ 1, 2, -3, 4, -2 ]
//   0  1   2  3   4
//What this array does is that: 1) node value is array index 2) arr[node.val] is parent node of the set.
//If node is a parent node, then we store a negative value which indicates how many nodes are there in this set.
//This data structure should be optimized using path compression technique, which makes all nodes in the set point
//directly to the root node, which grants us O(1) performance of determining to which set does it belong.
//Union find [path compression] arrays:
// [ 2, 2, -3, 4, -2 ]
//   0  1   2  3   4
//So we can use this data structure to detect cycles in a graph with find operation.
//we use find operation on both nodes, for example 0, 1. If they both belong to the same root,
//then the edge we found will form a cycle.
//Union operation actually constructs sets we want to use so badly.
//Imagine starting with 0, 1, 2, 3, 4 nodes and [0,1],[1,2],[3,4] edges. (By the way you absolutely do start
//with that in every single graph problem on leetcode). You will be going through edges constructing your union find arrays.
//Starting with [0,1] -> find(1); find(0); neither of the edges have their own set -> form a new one with random parent.
//[1,2]. find(1), find(2) -> 1 belongs to set with root 0 -> append 2 to this set.
//[3,4]. find(3), find(4) -> no set -> create one.
//In more complex example where we will meet two sets that need to be joined, we are going to join set with less
//nodes to set with more nodes. (Or if number of nodes is equal, then we are going to pick parent set randomly)
//This is heavily used in kruskhal's algorithm, which sorts edges of the graph by weight and starts basically to construct disjoint
//set checking if appending a new edge will create a cycle or not. If it creates a cycle -> skip edge. And doing so until we run out
//of edges.

//So basically to create this data structure, we will need to create array or a map of size V
//We will need to write two methods: union and find.

//Disjoint set with path compression and union by rank
//Relies on class subset for storing ranks for each node.
//At the beginning we have N ununited nodes, each node has itself as its parent.
//When we unite nodes, root remain to have itself as a parent, when nodes that
//depend on root have root as a parent.

class DisjointSet {

    class SubSet {
        int size;
        int parent;

        SubSet(int size, int parent) {
            this.size = size;
            this.parent = parent;
        }
    }

    SubSet[] ss;

    DisjointSet(int n) {
        ss = new SubSet[n+1];

        for(int i = 1; i < n+1; i++) {
            ss[i] = new SubSet(1, i);
        }
    }

    int find(int n) {
        if(ss[n].parent != n) {
            ss[n].parent = find(ss[n].parent);
        }

        return ss[n].parent;
    }

    boolean union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if(p1 == p2) {
            return false;
        }

        if(ss[p1].size > ss[p2].size || ss[p1].size == ss[p2].size) {
            ss[p2].parent = p1;
            ss[p1].size += ss[p2].size;
        }

        if(ss[p1].size < ss[p2].size) {
            ss[p1].parent = p2;
            ss[p2].size += ss[p1].size;
        }

        return true;
    }

}

