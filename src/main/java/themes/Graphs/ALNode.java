package main.java.themes.Graphs;

import java.util.ArrayList;
import java.util.List;

//Adjacency list node of graph
public class ALNode {
    public int val;
    public List<ALNode> neighbors;

    public ALNode() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public ALNode(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }

    public ALNode(int val, ArrayList<ALNode> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }

    //The list won't work here - you can't take from list with size 0 element 2 - they are not auto-instantiated
    //But with Node s only we have no idea of which size algLog to create ?
    public void DFS(ALNode s, List<Boolean> algLog) {
        if(s.neighbors.size() == 0) {
            return;
        }

        for(ALNode n : s.neighbors) {
            if(!algLog.get(n.val)) {
                algLog.set(n.val, true);
                DFS(n, algLog);
            }
        }
    }

}
