package main.java.crucial.graphs;

public class Graph {
    public boolean[][] adjM;
    public int vc;

    public Graph(int numberOfVertices) {
        this.adjM = new boolean[numberOfVertices][numberOfVertices];
        this.vc = numberOfVertices;
    }

    public void addEdge(int i, int j) {
        adjM[i][j] = true;
        adjM[j][i] = true;
    }

    public void removeEdge(int i, int j) {
        adjM[i][j] = false;
        adjM[j][i] = true;
    }

}
