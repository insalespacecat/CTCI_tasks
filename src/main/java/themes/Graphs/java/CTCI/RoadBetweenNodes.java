package main.java.themes.Graphs.java.CTCI;

import main.java.themes.Graphs.java.ALDNode;

import java.util.HashMap;
import java.util.Map;

//Given a directed graph.
//Design an algorithm to find out whether the is a route between two nodes.
public class RoadBetweenNodes {

    //Time complexity: O(V+E) - standard DFS time.
    //Space complexity: O(V): standard DFS space complexity is O(h) - where h is max height of tree
    // + O(V) algLog map which might store up to V pairs. h < V -> O(V + h) = O(V).

    //To determine if there is a route between two nodes we can use simple DFS algorithm
    //We will go on with the algorithm and stop and return true if we found the node
    public boolean areConnected(ALDNode startPoint, String endPoint) {
        return DFS(startPoint, endPoint, new HashMap<String, Boolean>());
    }

    //DFS:
    //Goes in max depth for each neighbor node
    //Has log to determine visited nodes (even for directed graphs -> remember cycles)
    //Goes up if no nodes were found
    public boolean DFS(ALDNode startPoint, String endPoint, Map<String, Boolean> algLog) {
        algLog.put(startPoint.val, true);
        var res = false;

        for(ALDNode n : startPoint.neighbors) {
            if(n.val.equals(endPoint)) {
                return true;
            }

            if(algLog.get(n.val) == null) {
               res = DFS(n, endPoint, algLog);
               if(res) {
                   break;
               }
            }
        }

        return res;
    }
}
