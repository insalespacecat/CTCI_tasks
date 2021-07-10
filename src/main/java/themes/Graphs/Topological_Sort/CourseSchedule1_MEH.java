package main.java.themes.Graphs.Topological_Sort;

import main.java.themes.Graphs.ALNode;

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
public class CourseSchedule1_MEH {
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

        for(ALNode n : graph) {
            if(n != null && checkGraphForCycles(n, new boolean[5001], new boolean[5001])) {
                return false;
            }
        }

        return true;
    }

    //pr = prerequisites
    public ALNode[] createGraphFromPrereq(int[][] pr) {
        ALNode[] graph = new ALNode[5000];

        for(int i = 0; i < pr.length; i++) {
            if(pr[i][0] >= graph.length ||
                    pr[i][1] >= graph.length ||
                    pr[i][0] == pr[i][1]) {
                return null;
            }

            if(graph[pr[i][0]] == null) {
                graph[pr[i][0]] = new ALNode(pr[i][0], new ArrayList<ALNode>());
            }

            if(graph[pr[i][1]] == null) {
                graph[pr[i][1]] = new ALNode(pr[i][1], new ArrayList<ALNode>());
            }

            graph[pr[i][1]].neighbors.add(graph[pr[i][0]]);
        }

        return graph;
    }


    //sN = starting Node;
    //cC = cycle checklist new boolean[graph.length];
    //true - cycle detected
    public boolean checkGraphForCycles(ALNode sN, boolean[] cC, boolean[] algLog) {
        algLog[sN.val] = true;
        cC[sN.val] = true;

        var res = false;

        for(ALNode n : sN.neighbors) {
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
}
