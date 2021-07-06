package main.java.themes.Graphs.Topological_Sort;

import main.java.themes.Graphs.AdjListNode;

import java.util.ArrayList;


//https://leetcode.com/problems/course-schedule/
//There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
//You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course
//bi first if you want to take course ai.
//
//For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//
//Return true if you can finish all courses. Otherwise, return false.

//So here actually sufficient to check only if there are any loops in the graph and that's it.
public class CourseSchedule1 {
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

    //Run this method to get answer
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0) {
            return true;
        }

        var graph = createGraphFromPrereq(prerequisites);

        if(graph == null) {
            return false;
        }

        //sP = starting point;
        //var sP = findStartingPoint(graph);

        for(AdjListNode n : graph) {
            if(n != null && checkGraphForCycles(n, new boolean[5001], new boolean[5001])) {
                return false;
            }
        }


        /*
        var tO = getTopologicalOrder(
                sP, new boolean[5000], new StringBuilder()
            ).toString();
        System.out.println("Topological order: " + tO);
        var tON = tO.split("").length;
        System.out.println(tON);
        System.out.println(tON+1);
        */

        //return tON+2 >= numCourses;
        return true;
    }

    /*
    //returns node starting from which you can cover most of the nodes
    public Node[] findStartingPoint(Node[] graph) {
        //DETECT disconnected subgraphs
        //Running DFS forEach node
        //collecting DFS logs
        //if starting node's counter != graph.length -> we have discon subgphs
        //try to match counter's algLog to others log to get counter == graph.lengh
        //nodes of counters matched are disconnected subgraphs.
        int[] counters = new int[graph.length+2];
        Node[] nodes = new Node[graph.length+2];
        boolean[][] algLogs = new boolean[graph.length+2][graph.length+2];

        int counter = 0;

        int bI = -1;
        var i = 0;

        while(graph[i] == null) {
            i++;
        }

        while(i < graph.length) {
            var res = 0;

            if(graph[i] != null) {
                algLogs[i] = new boolean[graph.length+2];
                res = DFS(graph[i], algLogs[i], 0);
                counters[i] = res;
                nodes[i] = node;
            }

            if(res > counter) {
                counter = res;
                bI = i;
            }

            i++;
        }

        if(counters[bI] == graph.length) {
            return (new Nodes[1][0] = nodes[bI]);
        } else {
            for(int i = 0; i < algLogs.length; i++) {
                for(int j = 0; j < algLogs[i].length; j++) {

                }
            }
        }

        for()

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
    */

    //pr = prerequisites

    public AdjListNode[] createGraphFromPrereq(int[][] pr) {
        AdjListNode[] graph = new AdjListNode[5000];

        for(int i = 0; i < pr.length; i++) {
            if(pr[i][0] >= graph.length ||
                    pr[i][1] >= graph.length ||
                    pr[i][0] == pr[i][1]) {
                return null;
            }

            if(graph[pr[i][0]] == null) {
                graph[pr[i][0]] = new AdjListNode(pr[i][0], new ArrayList<AdjListNode>());
            }

            if(graph[pr[i][1]] == null) {
                graph[pr[i][1]] = new AdjListNode(pr[i][1], new ArrayList<AdjListNode>());
            }

            graph[pr[i][1]].neighbors.add(graph[pr[i][0]]);
        }

        return graph;
    }


    //sN = starting Node;
    //cC = cycle checklist new boolean[graph.length];
    //true - cycle detected
    public boolean checkGraphForCycles(AdjListNode sN, boolean[] cC, boolean[] algLog) {
        algLog[sN.val] = true;
        cC[sN.val] = true;

        var res = false;

        for(AdjListNode n : sN.neighbors) {
            if(cC[n.val]) {
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


    /*
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
    */
}
