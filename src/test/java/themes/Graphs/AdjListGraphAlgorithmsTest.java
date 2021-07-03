package test.java.themes.Graphs;

import main.java.themes.Graphs.AdjListGraph;
import main.java.themes.Graphs.AdjListNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdjListGraphAlgorithmsTest {

    public static AdjListGraph[] graphs;

    @BeforeAll
    public static void GraphsInit() {
        graphs = new AdjListGraph[3];

        AdjListNode[] g1AdjListNodes = new AdjListNode[7];
        AdjListNode[] g2AdjListNodes = new AdjListNode[8];
        AdjListNode[] g3AdjListNodes = new AdjListNode[6];

        simpleNodesInit(g1AdjListNodes);
        simpleNodesInit(g2AdjListNodes);
        simpleNodesInit(g3AdjListNodes);

        g1AdjListNodes[0].neighbors = List.of(g1AdjListNodes[1]);
        g1AdjListNodes[1].neighbors = List.of(g1AdjListNodes[0], g1AdjListNodes[2], g1AdjListNodes[3]);
        g1AdjListNodes[2].neighbors = List.of(g1AdjListNodes[1], g1AdjListNodes[3], g1AdjListNodes[4]);
        g1AdjListNodes[3].neighbors = List.of(g1AdjListNodes[1], g1AdjListNodes[2]);
        g1AdjListNodes[4].neighbors = List.of(g1AdjListNodes[2], g1AdjListNodes[6]);
        g1AdjListNodes[5].neighbors = List.of(g1AdjListNodes[6]);
        g1AdjListNodes[6].neighbors = List.of(g1AdjListNodes[4], g1AdjListNodes[5]);

        g2AdjListNodes[0].neighbors = List.of(g2AdjListNodes[1], g2AdjListNodes[2]);
        g2AdjListNodes[1].neighbors = List.of(g2AdjListNodes[0], g2AdjListNodes[3]);
        g2AdjListNodes[2].neighbors = List.of(g2AdjListNodes[0], g2AdjListNodes[3]);
        g2AdjListNodes[3].neighbors = List.of(g2AdjListNodes[2], g2AdjListNodes[4]);
        g2AdjListNodes[4].neighbors = List.of(g2AdjListNodes[7], g2AdjListNodes[6], g2AdjListNodes[5]);
        g2AdjListNodes[5].neighbors = List.of(g2AdjListNodes[4], g2AdjListNodes[6]);
        g2AdjListNodes[6].neighbors = List.of(g2AdjListNodes[4], g2AdjListNodes[5], g2AdjListNodes[7]);
        g2AdjListNodes[7].neighbors = List.of(g2AdjListNodes[4], g2AdjListNodes[6]);

        g3AdjListNodes[0].neighbors = List.of(g3AdjListNodes[1]);
        g3AdjListNodes[1].neighbors = List.of(g3AdjListNodes[0], g3AdjListNodes[2], g3AdjListNodes[3]);
        g3AdjListNodes[2].neighbors = List.of(g3AdjListNodes[1], g3AdjListNodes[4]);
        g3AdjListNodes[3].neighbors = List.of(g3AdjListNodes[1], g3AdjListNodes[4]);
        g3AdjListNodes[4].neighbors = List.of(g3AdjListNodes[2], g3AdjListNodes[3], g3AdjListNodes[5]);
        g3AdjListNodes[5].neighbors = List.of(g3AdjListNodes[4]);

        graphs[0] = new AdjListGraph(g1AdjListNodes);
        graphs[1] = new AdjListGraph(g2AdjListNodes);
        graphs[2] = new AdjListGraph(g3AdjListNodes);
    }

    public static void simpleNodesInit(AdjListNode[] gAdjListNodes) {
        for(int i = 0; i < gAdjListNodes.length; i++) {
            gAdjListNodes[i] = new AdjListNode(i+1);
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
