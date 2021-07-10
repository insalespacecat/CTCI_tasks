package main.java.themes.Graphs;

import java.util.*;

public class ALGraph {
    public List<ALNode> adjList;

    public ALGraph() {
        this.adjList = List.of(new ALNode(1));
    }

    public ALGraph(ALNode adjListNode) {
        this.adjList = List.of(adjListNode);
    }

    public ALGraph(ALNode[] adjListNodes) {
        this.adjList = Arrays.asList(adjListNodes);
    }

    public static boolean compareGraphs(ALNode s, ALNode sC, boolean[] algLog) {
        StringBuilder ns = new StringBuilder(s.val + ": [");
        StringBuilder ns2 = new StringBuilder(sC.val + ": [");

        for(ALNode n : s.neighbors) {
            ns.append(n.val).append(" ");
        }

        for(ALNode n : sC.neighbors) {
            ns2.append(n.val).append(" ");
        }

        ns.append("]");
        ns2.append("]");

        System.out.println(ns.toString());
        System.out.println(ns2.toString());
        System.out.println("");

        if(s.neighbors.size() == 0 && sC.neighbors.size() == 0) {
            return true;
        }

        if(sC.neighbors.size() != s.neighbors.size()) {
            return false;
        }

        var res = true;
        algLog[s.val] = true;

        for(ALNode n: s.neighbors) {
            var nC = findMatchingNode(n, sC.neighbors);

            if (nC == null) {
                return false;
            }

            if (!algLog[n.val]) {
                res = compareGraphs(n, nC, algLog);
            }
        }

        return res;
    }

    public static ALNode findMatchingNode(ALNode adjListNode, List<ALNode> adjListNodes) {
        for(ALNode n: adjListNodes) {
            if(n.val == adjListNode.val) {
                return n;
            }
        }

        return null;
    }

    public static ALNode cloneGraph(ALNode adjListNode) {
        //Tasks sets a bound for number of vertices no more than 100
        var s2 = new ALNode(1);
        deepCloneGraph(adjListNode, s2, new boolean[100], new HashMap<Integer, List<ALNode>>());
        return s2;
    }

    //s = start of the actual graph
    //s2 = start of the copy graph (pass new Node(1));

    //scheduledConnectionsMap == sCM
    //var sCM = new HashMap<Integer, List<Node>>();
    //Begin with n1
    //n1C.neighbors = new ArrayList<>();
    //For each neighbor of n1
    //if neighbor is not visited
    //n1C.neighbors.add(new Node(neighbor.val, List.of(n1C)));
    //if neighbor is visited
    //var sCL = sCM.get(neighbor.val);
    //sCL.add(n1C);
    //sCM.put(neighbor.val, sCL);

    public static void deepCloneGraph(ALNode s, ALNode s2, boolean[] algLog, Map<Integer, List<ALNode>> sCM) {
        algLog[s.val] = true;

        if(s.neighbors.size() == 0) {
            return;
        }

        for(ALNode n : s.neighbors) {
            if(!algLog[n.val]) {
                var n2 = new ALNode(n.val, new ArrayList<>(List.of(s2)));
                s2.neighbors.add(n2);
                deepCloneGraph(n, n2, algLog, sCM);
            } else if(findMatchingNode(n, s2.neighbors) == null) {
                var sCL = sCM.get(n.val);
                if(sCL == null) {
                    sCL = new ArrayList<ALNode>();
                }
                sCL.add(s2);
                sCM.put(n.val, sCL);
            }
        }

        var sCL = sCM.get(s2.val);
        if(sCL != null) {
            for (ALNode sC : sCL) {
                s2.neighbors.add(sC);
                sC.neighbors.add(s2);
            }
        }
    }




}
