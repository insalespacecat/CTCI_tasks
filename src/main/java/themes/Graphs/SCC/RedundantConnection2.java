package main.java.themes.Graphs.SCC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RedundantConnection2 {
    class Solution {

        class Node {
            int val;
            List<Node> neighbors;

            Node(int val) {
                this.val = val;
                neighbors = new ArrayList<Node>();
            }
        }

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

                for(int i = 1; i <= n; i++) {
                    subsets[i] = new SubSet(i, 0);
                }
            }

            public int find(int node) {
                if(subsets[node].parent != node) {
                    subsets[node].parent = find(subsets[node].parent);
                }

                return subsets[node].parent;
            }

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

        private HashMap<Integer, ArrayList<Integer>> getNodesPointMap(HashMap<Integer, Node> graph, int n) {
            var npm = new HashMap<Integer, ArrayList<Integer>>();
            var algLog = new boolean[n+1];

            for(int i = 1; i < n+1; i++) {
                if(!algLog[i]) {
                    fillPointMap(graph.get(i), algLog, npm);
                }
            }

            return npm;
        }

        private void fillPointMap(Node s, boolean[] algLog, HashMap<Integer, ArrayList<Integer>> npm) {
            algLog[s.val] = true;

            for(Node n : s.neighbors) {
                var pointing = npm.get(n.val);

                if(pointing == null) {
                    pointing = new ArrayList<Integer>();
                    npm.put(n.val, pointing);
                }

                pointing.add(s.val);

                if(!algLog[n.val]) {
                    fillPointMap(n, algLog, npm);
                }
            }
        }

        //Brute force: find cyclic edge, delete it, remove it from graph, re-run union find on the graph to see if we have another
        //cyclic edge until we find no more cyclic edges, for each cyclic edge found check them for creation of additional root,
        //find the last in the arr and return.
        //Time complexity O((V+E)^2)+
        //Space complexity: O(V+E)
        public int[] findRedundantDirectedConnection(int[][] edges) {
            var cyclicEdges = new ArrayList<Integer[]>();
            //Edges to remove
            var ETR = new ArrayList<Integer[]>();
            var edgeFound = true;
            var graph = createGraph(edges);
            var edgesLog = new int[edges.length][2];

            for(int i = 0; i < edges.length; i++) {
                edgesLog[i][0] = edges[i][0];
                edgesLog[i][1] = edges[i][1];
            }

            while(edgeFound) {
                edgeFound = findCyclicEdge(edgesLog, cyclicEdges);
            }

            var npm = getNodesPointMap(graph, edges.length);
            boolean treeRootPresent = false;
            for(int i = 1; i < edges.length + 1; i++) {
                if(npm.get(i) == null) {
                    treeRootPresent = true;
                }
            }

            for(Integer[] cE : cyclicEdges) {
                if(treeRootPresent) {
                    if(npm.get(cE[1]).size() > 1) {
                        ETR.add(cE);
                    }
                } else {
                    ETR.add(cE);
                }

            }

            return findLastETRInArray(edges, ETR);
        }

        private HashMap<Integer, Node> createGraph(int[][] edges) {
            var g = new HashMap<Integer, Node>();

            for(int[] edge : edges) {
                Node n1 = g.get(edge[0]);
                Node n2 = g.get(edge[1]);

                if(n1 == null) {
                    n1 = new Node(edge[0]);
                    g.put(edge[0], n1);
                }

                if(n2 == null) {
                    n2 = new Node(edge[1]);
                    g.put(edge[1], n2);
                }

                n1.neighbors.add(n2);
            }

            return g;
        }

        private boolean findCyclicEdge(int[][] edges, ArrayList<Integer[]> cyclicEdges) {
            var dSet = new DisjointSet(edges.length);

            for(int[] edge : edges) {
                if(edge[0] < 0) {
                    if(!dSet.union((edge[0] * -1), edge[1])) {
                        return false;
                    }
                }
            }

            for(int[] edge : edges) {
                if(edge[0] > 0) {
                    if(!dSet.union(edge[0], edge[1])) {
                        var ce = new Integer[2];
                        ce[0] = edge[0];
                        ce[1] = edge[1];
                        cyclicEdges.add(ce);
                        edge[0] = edge[0] * (-1);
                        return true;
                    }
                }
            }

            return false;
        }

        public int[] findLastETRInArray(int[][] edges, List<Integer[]> etr) {
            var etrMap = new HashMap<String, Boolean>();
            var lastEdge = new int[2];

            for(Integer[] e : etr) {
                etrMap.put("" + e[0] + e[1], true);
            }

            for(int[] e : edges) {
                if(etrMap.get("" + e[0] + e[1]) != null) {
                    lastEdge = e;
                }
            }

            return lastEdge;
        }
    }
}
