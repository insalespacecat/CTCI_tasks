package main.java.themes.Graphs;

import java.util.ArrayList;
import java.util.List;

//Adjacency list node of graph
public class AdjListNode {
    public int val;
    public List<AdjListNode> neighbors;

    public AdjListNode() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public AdjListNode(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }

    public AdjListNode(int val, ArrayList<AdjListNode> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }

    //The list won't work here - you can't take from list with size 0 element 2 - they are not auto-instantiated
    //But with Node s only we have no idea of which size algLog to create ?
    public void DFS(AdjListNode s, List<Boolean> algLog) {
        if(s.neighbors.size() == 0) {
            return;
        }

        for(AdjListNode n : s.neighbors) {
            if(!algLog.get(n.val)) {
                algLog.set(n.val, true);
                DFS(n, algLog);
            }
        }
    }

}
