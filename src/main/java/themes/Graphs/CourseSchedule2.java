package main.java.themes.Graphs;

import java.util.ArrayList;

public class CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0 && numCourses > 0) {
            var res = new int[numCourses];
            for(int i = 0; i < numCourses; i++) {
                res[i] = i;
            }
            return res;
        }

        if(prerequisites.length == 0) {
            return new int[0];
        }

        var graph = createGraphFromPrereq(prerequisites);

        if(graph == null) {
            return new int[0];
        }
        //sP = starting point;
        var sP = findStartingPoint(graph);

        System.out.println("Checking for cycles...");

        if(checkGraphForCycles(sP, new boolean[5000], new boolean[5000])) {
            return new int[0];
        }

        var tO = getTopologicalOrder(
                sP, new boolean[5000], new StringBuilder(String.valueOf(sP.val))
        ).toString();

        var sA = tO.split("");
        var iA = new int[sA.length];
        for(int i = sA.length-1; i > 0; i--) {
            iA[sA.length-i] = Integer.parseInt(sA[i]);
        }

        return iA;
    }

    //returns node starting from which you can cover most of the nodes
    public AdjListNode findStartingPoint(AdjListNode[] graph) {
        int counter = 0;
        AdjListNode adjListNode = graph[0];

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
                adjListNode = graph[i];
            }

            i++;
        }

        return adjListNode;
    }

    public int DFS(AdjListNode sN, boolean[] algLog, int c) {
        c++;
        algLog[sN.val] = true;

        for(AdjListNode n : sN.neighbors) {
            if(!algLog[n.val]) {
                c = DFS(n, algLog, c);
            }
        }

        return c;
    }

    //pr = prerequisites
    public AdjListNode[] createGraphFromPrereq(int[][] pr) {
        AdjListNode[] graph = new AdjListNode[pr.length + 2];

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
        System.out.println("Checking node " + sN.val);
        System.out.println("with neighbors " + sN.neighbors.toString());
        algLog[sN.val] = true;
        cC[sN.val] = true;

        var res = false;

        for(AdjListNode n : sN.neighbors) {
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

    public StringBuilder getTopologicalOrder(AdjListNode sN, boolean[] algLog, StringBuilder sB) {
        algLog[sN.val] = true;

        for(AdjListNode n : sN.neighbors) {
            if(!algLog[n.val]) {
                sB = getTopologicalOrder(n, algLog, sB);
                sB.append(n.val); //475263
            }
        }

        return sB;
    }
}
