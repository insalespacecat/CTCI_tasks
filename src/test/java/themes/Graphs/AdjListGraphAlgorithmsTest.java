package test.java.themes.Graphs;

import main.java.themes.Graphs.AdjListGraph;
import main.java.themes.Graphs.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdjListGraphAlgorithmsTest {

    public static AdjListGraph[] graphs;

    @BeforeAll
    public static void GraphsInit() {
        graphs = new AdjListGraph[3];

        Node[] g1Nodes = new Node[7];
        Node[] g2Nodes = new Node[8];
        Node[] g3Nodes = new Node[6];

        simpleNodesInit(g1Nodes);
        simpleNodesInit(g2Nodes);
        simpleNodesInit(g3Nodes);

        g1Nodes[0].neighbors = List.of(g1Nodes[1]);
        g1Nodes[1].neighbors = List.of(g1Nodes[0], g1Nodes[2], g1Nodes[3]);
        g1Nodes[2].neighbors = List.of(g1Nodes[1], g1Nodes[3], g1Nodes[4]);
        g1Nodes[3].neighbors = List.of(g1Nodes[1], g1Nodes[2]);
        g1Nodes[4].neighbors = List.of(g1Nodes[2], g1Nodes[6]);
        g1Nodes[5].neighbors = List.of(g1Nodes[6]);
        g1Nodes[6].neighbors = List.of(g1Nodes[4], g1Nodes[5]);

        g2Nodes[0].neighbors = List.of(g2Nodes[1], g2Nodes[2]);
        g2Nodes[1].neighbors = List.of(g2Nodes[0], g2Nodes[3]);
        g2Nodes[2].neighbors = List.of(g2Nodes[0], g2Nodes[3]);
        g2Nodes[3].neighbors = List.of(g2Nodes[2], g2Nodes[4]);
        g2Nodes[4].neighbors = List.of(g2Nodes[7], g2Nodes[6], g2Nodes[5]);
        g2Nodes[5].neighbors = List.of(g2Nodes[4], g2Nodes[6]);
        g2Nodes[6].neighbors = List.of(g2Nodes[4], g2Nodes[5], g2Nodes[7]);
        g2Nodes[7].neighbors = List.of(g2Nodes[4], g2Nodes[6]);

        g3Nodes[0].neighbors = List.of(g3Nodes[1]);
        g3Nodes[1].neighbors = List.of(g3Nodes[0], g3Nodes[2], g3Nodes[3]);
        g3Nodes[2].neighbors = List.of(g3Nodes[1], g3Nodes[4]);
        g3Nodes[3].neighbors = List.of(g3Nodes[1], g3Nodes[4]);
        g3Nodes[4].neighbors = List.of(g3Nodes[2], g3Nodes[3], g3Nodes[5]);
        g3Nodes[5].neighbors = List.of(g3Nodes[4]);

        graphs[0] = new AdjListGraph(g1Nodes);
        graphs[1] = new AdjListGraph(g2Nodes);
        graphs[2] = new AdjListGraph(g3Nodes);
    }

    public static void simpleNodesInit(Node[] gNodes) {
        for(int i = 0; i < gNodes.length; i++) {
            gNodes[i] = new Node(i+1);
        }
    }

    @Test
    public void compareGraphsTest() {
        assertFalse(
                AdjListGraph.compareGraphs(
                        graphs[0].adjList.get(0),
                        graphs[1].adjList.get(0),
                        new boolean[graphs[0].adjList.size() + 1]
                )
        );
        assertTrue(
                AdjListGraph.compareGraphs(
                        graphs[0].adjList.get(0),
                        graphs[0].adjList.get(0),
                        new boolean[graphs[0].adjList.size() + 1]
                )
        );
        assertFalse(
                AdjListGraph.compareGraphs(
                        graphs[0].adjList.get(0),
                        graphs[2].adjList.get(0),
                        new boolean[graphs[0].adjList.size() + 1]
                )
        );
    }



    @Test
    public void cloneGraphTest() {
        //AdjListGraph.compareGraphs(new Node(1, new ArrayList<>()), graphs[0].adjList.get(0), new boolean[100]);
        var clonedGraph = AdjListGraph.cloneGraph(graphs[0].adjList.get(0));
        assertTrue(AdjListGraph.compareGraphs(clonedGraph, graphs[0].adjList.get(0), new boolean[100]));
    }

}
