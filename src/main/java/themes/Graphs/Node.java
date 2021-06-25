package main.java.themes.Graphs;

import java.util.ArrayList;
import java.util.List;

//Adjacency list node of graph
public class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }

    public Node(int val, ArrayList<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }

    //The list won't work here - you can't take from list with size 0 element 2 - they are not auto-instantiated
    //But with Node s only we have no idea of which size algLog to create ?
    public void DFS(Node s, List<Boolean> algLog) {
        if(s.neighbors.size() == 0) {
            return;
        }

        for(Node n : s.neighbors) {
            if(!algLog.get(n.val)) {
                algLog.set(n.val, true);
                DFS(n, algLog);
            }
        }
    }

}
