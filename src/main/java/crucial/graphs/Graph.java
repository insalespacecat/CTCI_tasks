package main.java.crucial.graphs;

public class Graph {
    public boolean[][] adjM;
    public int vc;

    public Graph(int numberOfVertices) {
        this.adjM = new boolean[numberOfVertices][numberOfVertices];
        this.vc = numberOfVertices;
    }

}
