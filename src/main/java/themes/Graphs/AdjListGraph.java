package main.java.themes.Graphs;

import java.util.*;

public class AdjListGraph {
    public List<Node> adjList;

    public AdjListGraph() {
        this.adjList = List.of(new Node(1));
    }

    public AdjListGraph(Node node) {
        this.adjList = List.of(node);
    }

    public AdjListGraph(Node[] nodes) {
        this.adjList = Arrays.asList(nodes);
    }

    public static boolean compareGraphs(Node s, Node sC, boolean[] algLog) {
        StringBuilder ns = new StringBuilder(s.val + ": [");
        StringBuilder ns2 = new StringBuilder(sC.val + ": [");

        for(Node n : s.neighbors) {
            ns.append(n.val).append(" ");
        }

        for(Node n : sC.neighbors) {
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

        for(Node n: s.neighbors) {
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

    public static Node findMatchingNode(Node node, List<Node> nodes) {
        for(Node n: nodes) {
            if(n.val == node.val) {
                return n;
            }
        }

        return null;
    }

    public static Node cloneGraph(Node node) {
        //Tasks sets a bound for number of vertices no more than 100
        var s2 = new Node(1);
        deepCloneGraph(node, s2, new boolean[100], new HashMap<Integer, List<Node>>());
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

    public static void deepCloneGraph(Node s, Node s2, boolean[] algLog, Map<Integer, List<Node>> sCM) {
        algLog[s.val] = true;

        if(s.neighbors.size() == 0) {
            return;
        }

        for(Node n : s.neighbors) {
            if(!algLog[n.val]) {
                var n2 = new Node(n.val, new ArrayList<>(List.of(s2)));
                s2.neighbors.add(n2);
                deepCloneGraph(n, n2, algLog, sCM);
            } else if(findMatchingNode(n, s2.neighbors) == null) {
                var sCL = sCM.get(n.val);
                if(sCL == null) {
                    sCL = new ArrayList<Node>();
                }
                sCL.add(s2);
                sCM.put(n.val, sCL);
            }
        }

        var sCL = sCM.get(s2.val);
        if(sCL != null) {
            for (Node sC : sCL) {
                s2.neighbors.add(sC);
                sC.neighbors.add(s2);
            }
        }
    }




}
