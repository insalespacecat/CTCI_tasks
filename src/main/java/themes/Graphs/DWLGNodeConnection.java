package main.java.themes.Graphs;

public class DWLGNodeConnection {
    public DWLGNode toNode;
    public double weight;

    public DWLGNodeConnection(DWLGNode toNode, double weight) {
        this.toNode = toNode;
        this.weight = weight;
    }
}
