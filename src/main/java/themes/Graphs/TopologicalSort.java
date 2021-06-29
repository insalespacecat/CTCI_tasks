package main.java.themes.Graphs;

import java.util.ArrayList;
import java.util.stream.Collectors;


//https://leetcode.com/problems/course-schedule/
//There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
//You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course
//bi first if you want to take course ai.
//
//For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//
//Return true if you can finish all courses. Otherwise, return false.

public class TopologicalSort {
    //1) prereq is not adjacency list. So we have to take it and construct graph as adjacency list based on it.
    //2) use topological sort on graph in order to find the order of course taking:
    // - if order is found, return true.
    // - if algorithm fails return false.

    //Example 3:
    //numCourses = 7
    //prereq = [[2, 1],[3, 1],[4, 2],[5, 2],[6, 3],[7, 5]]

    //***Convert prereq to adjL
    //1) create empty AdjListGraph with size prereq+1;
    //var aL = new Node[prereq.length + 1];
    //2) Start with node 1
    // if list does not contain it -> create node 1
    // if list does not contain node it depends on -> create nodeDep
    // nodeDep.neighbors.add(node1)
    //for each p : prereq
    // if al[p[0]] == null {
    //    aL[p[0]] = new ArrayList<Node>();
    // }
    // if(aL[p[1]] == null) {
    //    aL[p[1]] = List.of(new Node(p[0]));
    // } else {
    //    aL[p[1]].add(new Node(p[0]);
    //}
    //edgeCase: what if we have a cyclic graph on this step?
    //prereq = [[2, 1],[3, 1],[4, 2],[5, 2],[6, 3],[7, 5]]
    //***Check graph for cycles **otherwise top sort with cause stack overflow is it is DCG
    //***Fire up topological sort

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0) {
            return true;
        }

        var graph = createGraphFromPrereq(prerequisites);

        if(graph == null) {
            return false;
        }

        System.out.println("Graph created from prereq...");
        for(int i = 0; i < graph.length; i++) {
            if(graph[i] != null) {
                System.out.println(i + ": " + graph[i].neighbors.stream().map(s -> s.val).collect(Collectors.toList()));
            }
        }

        //sP = starting point;
        var sP = findStartingPoint(graph);

        System.out.println("Checking for cycles...");

        if(checkGraphForCycles(sP, new boolean[5000], new boolean[5000])) {
            System.out.println("Graph contains cycles!");
            return false;
        }

        var tO = getTopologicalOrder(
                sP, new boolean[5000], new StringBuilder()
        ).toString();
        System.out.println("Topological order: " + tO);
        var tON = tO.split("").length;
        System.out.println(tON);
        System.out.println(tON+1);

        return tON+2 >= numCourses;
    }

    //returns node starting from which you can cover most of the nodes
    public Node findStartingPoint(Node[] graph) {
        int counter = 0;
        Node node = graph[0];

        var i = 0;

        while(graph[i] == null) {
            i++;
        }

        while(i < graph.length) {
            var res = 0;

            if(graph[i] != null) {
                res = DFS(graph[i], new boolean[graph.length+1], 0);
            }

            if(res > counter) {
                counter = res;
                node = graph[i];
            }

            i++;
        }

        System.out.println("Starting point is " + node.val);
        System.out.println("With " + i + " available nodes");

        return node;
    }

    public int DFS(Node sN, boolean[] algLog, int c) {
        c++;
        algLog[sN.val] = true;

        for(Node n : sN.neighbors) {
            if(!algLog[n.val]) {
                c = DFS(n, algLog, c);
            }
        }

        return c;
    }

    //pr = prerequisites
    public Node[] createGraphFromPrereq(int[][] pr) {
        Node[] graph = new Node[pr.length + 2];

        for(int i = 0; i < pr.length; i++) {
            if(pr[i][0] >= graph.length ||
                    pr[i][1] >= graph.length ||
                    pr[i][0] == pr[i][1]) {
                return null;
            }

            if(graph[pr[i][0]] == null) {
                graph[pr[i][0]] = new Node(pr[i][0], new ArrayList<Node>());
            }

            if(graph[pr[i][1]] == null) {
                graph[pr[i][1]] = new Node(pr[i][1], new ArrayList<Node>());
            }

            graph[pr[i][1]].neighbors.add(graph[pr[i][0]]);
        }

        return graph;
    }

    //sN = starting Node;
    //cC = cycle checklist new boolean[graph.length];
    //true - cycle detected
    public boolean checkGraphForCycles(Node sN, boolean[] cC, boolean[] algLog) {
        System.out.println("Checking node " + sN.val);
        System.out.println("with neighbors " + sN.neighbors.toString());
        algLog[sN.val] = true;
        cC[sN.val] = true;

        var res = false;

        for(Node n : sN.neighbors) {
            if(cC[n.val]) {
                System.out.println("Cycle detected in " + n.val);
                return true;
            }

            if(!algLog[n.val]) {
                res = checkGraphForCycles(n, cC, algLog);
                if(res) {
                    return res;
                }
                cC[n.val] = false;
            }
        }

        return res;
    }

    public StringBuilder getTopologicalOrder(Node sN, boolean[] algLog, StringBuilder sB) {
        algLog[sN.val] = true;

        for(Node n : sN.neighbors) {
            if(!algLog[n.val]) {
                sB = getTopologicalOrder(n, algLog, sB);
                sB.append(n.val); //475263
            }
        }

        return sB;
    }
}
