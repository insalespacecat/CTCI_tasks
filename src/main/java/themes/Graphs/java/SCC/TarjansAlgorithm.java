package main.java.themes.Graphs.java.SCC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Tarjan's algorithm allows us to detect SCC in the graph.
//It's basically a modified algorithm to detect cycles in the graph.
//In directed graph, we pick a random node and start DFS from it.
//algLog of visited nodes is shared between all runs.
//on the run we keep also pathLog, which records all the nodes that we visited in the current run.
//for each node we keep map of values called lowLink. It determines the lowest value node it can reach in SCC.
//We go deep as normal DFS, for each node marking algLog and pathLog, set lowLink of the node to node.val
//[because by default single node is SCC and lowest it can reach to is to itself?]
//Do not go into the nodes that are already marked in algLog.
//When we hit the end of graph branch, as we start to go back up the recursion:
//- don't delete nodes from pathLog!
//- check if the node that we just left is in pathLog.
//- if it is, find which lowLink is lower, of current node or of node we just left.
//- assign lowest lowLink to current node.
//- if all the neighbors of the node have been visited and lowLink of the node === node.val,
//- get all the nodes with that lowLink out of the pathLog, and store them in SCCFound.

public class TarjansAlgorithm {
    public class ALDNode {
        public int val;
        public List<ALDNode> neighbors;

        ALDNode(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    public ArrayList<ArrayList<Integer>> getSCC(Map<Integer, ALDNode> graph, int n) {
        var algLog = new boolean[n];
        var lowLink = new int[n];
        var SCC = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i < algLog.length; i++) {
           if(!algLog[i]) {
               var node = graph.get(i);
               if(node != null) {
                   tarjans(node, algLog, new HashMap<Integer, Boolean>(), lowLink, SCC);
               }
           }
        }

        return SCC;
    }

    public void tarjans(ALDNode s, boolean[] algLog, Map<Integer, Boolean> pathLog,
                        int[] lowLink, ArrayList<ArrayList<Integer>> SCC) {
        algLog[s.val] = true;
        pathLog.put(s.val, true);
        lowLink[s.val] = s.val;

        for(ALDNode n : s.neighbors) {
            if(!algLog[s.val]) {
                tarjans(n, algLog, pathLog, lowLink, SCC);
            }

            if(pathLog.get(n.val) != null) {
                if(lowLink[s.val] > lowLink[n.val]) {
                    lowLink[s.val] = lowLink[n.val];
                }
            }
        }

        if(lowLink[s.val] == s.val) {
            getSCCNodes(lowLink, s, pathLog, SCC);
        }
    }

    public void getSCCNodes(int[] lowLink, ALDNode s, Map<Integer, Boolean> pathLog,
                            ArrayList<ArrayList<Integer>> SCC) {
        var SCCNodes = new ArrayList<Integer>();

        for(Integer key : pathLog.keySet()) {
            if(lowLink[key] == s.val) {
                SCCNodes.add(key);
            }
        }

        for(Integer node : SCCNodes) {
            if(pathLog.get(node) != null) {
                pathLog.remove(node);
            }
        }

        SCC.add(SCCNodes);
    }
}
