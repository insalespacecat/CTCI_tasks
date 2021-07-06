package main.java.themes.Graphs.Topological_Sort;

import main.java.themes.Graphs.AdjListNode;

import java.util.ArrayList;

//Course schedule that actually requires topological sort))
//https://leetcode.com/problems/course-schedule-ii/

//There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
//You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates
//that you must take course bi first if you want to take course ai.
//
//For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//
//Return the ordering of courses you should take to finish all courses. If there are many valid answers,
//return any of them. If it is impossible to finish all courses, return an empty array.

//Example 1:
//Input: numCourses = 2, prerequisites = [[1,0]]
//Output: [0,1]
//Explanation: There are a total of 2 courses to take.
//To take course 1 you should have finished course 0. So the correct course order is [0,1].

//Example 2:
//Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//Output: [0,2,1,3]
//Explanation: There are a total of 4 courses to take.
//To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
//So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

//Example 3:
//Input: numCourses = 1, prerequisites = []
//Output: [0]

//Constraints:
//1 <= numCourses <= 2000
//0 <= prerequisites.length <= numCourses * (numCourses - 1)
//prerequisites[i].length == 2
//0 <= ai, bi < numCourses
//ai != bi
//All the pairs [ai, bi] are distinct.

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


//Optimized working solution O(V+E)
//class Solution {
//  static int WHITE = 1;
//  static int GRAY = 2;
//  static int BLACK = 3;
//
//  boolean isPossible;
//  Map<Integer, Integer> color;
//  Map<Integer, List<Integer>> adjList;
//  List<Integer> topologicalOrder;
//
//  private void init(int numCourses) {
//    this.isPossible = true;
//    this.color = new HashMap<Integer, Integer>();
//    this.adjList = new HashMap<Integer, List<Integer>>();
//    this.topologicalOrder = new ArrayList<Integer>();
//
//    // By default all vertces are WHITE
//    for (int i = 0; i < numCourses; i++) {
//      this.color.put(i, WHITE);
//    }
//  }
//
//  private void dfs(int node) {
//
//    // Don't recurse further if we found a cycle already
//    if (!this.isPossible) {
//      return;
//    }
//
//    // Start the recursion
//    this.color.put(node, GRAY);
//
//    // Traverse on neighboring vertices
//    for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<Integer>())) {
//      if (this.color.get(neighbor) == WHITE) {
//        this.dfs(neighbor);
//      } else if (this.color.get(neighbor) == GRAY) {
//        // An edge to a GRAY vertex represents a cycle
//        this.isPossible = false;
//      }
//    }
//
//    // Recursion ends. We mark it as black
//    this.color.put(node, BLACK);
//    this.topologicalOrder.add(node);
//  }
//
//  public int[] findOrder(int numCourses, int[][] prerequisites) {
//
//    this.init(numCourses);
//
//    // Create the adjacency list representation of the graph
//    for (int i = 0; i < prerequisites.length; i++) {
//      int dest = prerequisites[i][0];
//      int src = prerequisites[i][1];
//      List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
//      lst.add(dest);
//      adjList.put(src, lst);
//    }
//
//    // If the node is unprocessed, then call dfs on it.
//    for (int i = 0; i < numCourses; i++) {
//      if (this.color.get(i) == WHITE) {
//        this.dfs(i);
//      }
//    }
//
//    int[] order;
//    if (this.isPossible) {
//      order = new int[numCourses];
//      for (int i = 0; i < numCourses; i++) {
//        order[i] = this.topologicalOrder.get(numCourses - i - 1);
//      }
//    } else {
//      order = new int[0];
//    }
//
//    return order;
//  }
//}
