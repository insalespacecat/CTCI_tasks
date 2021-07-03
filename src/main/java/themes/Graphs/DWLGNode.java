package main.java.themes.Graphs;

import java.util.*;

//Directed weighted letter graph node.
//a ->(2) b ->(1) c
public class DWLGNode {
    public String val;
    public Map<String, DWLGNodeConnection> neighbors;

    public DWLGNode(String val) {
        this.val = val;
        this.neighbors = new HashMap<String, DWLGNodeConnection>();
    }

    public static double getMinCost(DWLGNode start, DWLGNode finish, HashMap<String, Double> costs) {
        var shortestPaths = dijkstra(start, costs, new HashMap<String, Boolean>());
        var res = shortestPaths.get(finish.val);
        return Objects.requireNonNullElse(res, -1.0d);
    }

    //Dikstra algo steps:
    //1) We will have a starting node and map of costs for each node
    //Init map with cost to starting node = 0.
    //Init queue for modified BFS (without checking if neighbor was already visited by another iteration)
    //2) Init step with starting node
    //3) Iteration:
    // - save step cost from map: var sC = map.get(step.val)
    // - for each connection c of the step
    // - calculate cost of coming to connection: c.weight + sC
    // - if connection has no step defined in map -> append s.weight + sC
    // - if we found cost that is lower than already present in map -> overwrite cost in the map
    // - append c.toNode to queue
    // - take another step from queue

    public static Map<String, Double> dijkstra(DWLGNode node, Map<String, Double> costs, Map<String, Boolean> algLog) {
        Queue<DWLGNode> queue = new LinkedList<>();
        //s = step
        var s = node;

        while(s != null) {
            //sC = step cost;
            var sC = costs.get(s.val);
            algLog.put(s.val, true);

            for(DWLGNodeConnection c : s.neighbors.values()) {
                //cC = connection cost
                var cC = c.weight + sC;
                if(cC < costs.get(c.toNode.val)) {
                    costs.put(c.toNode.val, cC);
                    if(algLog.get(c.toNode.val) != null) {
                        queue.add(c.toNode);
                    }
                }
                if(algLog.get(c.toNode.val) == null) {
                    queue.add(c.toNode);
                }
            }

            s = queue.poll();
        }

        return costs;
    }


    public static DWLGNode getClosestNode(DWLGNode s) {
        double closest = Double.MAX_VALUE;
        DWLGNode closestNode = null;

        for(DWLGNodeConnection c : s.neighbors.values()) {
            if(c.weight < closest) {
                closest = c.weight;
                closestNode = c.toNode;
            }
        }

        return closestNode;
    }
}
