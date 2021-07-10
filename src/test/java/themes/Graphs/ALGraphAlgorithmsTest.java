package test.java.themes.Graphs;

import main.java.themes.Graphs.ALGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test.java.themes.Graphs.Examples.AdjListGraphs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ALGraphAlgorithmsTest {

    public static ALGraph[] graphs;

    @BeforeAll
    public static void GraphsInit() {
      graphs = AdjListGraphs.getAdjListGraphs();
    }

    @Test
    public void compareGraphsTest() {
        assertFalse(
                ALGraph.compareGraphs(
                        graphs[0].adjList.get(0),
                        graphs[1].adjList.get(0),
                        new boolean[graphs[0].adjList.size() + 1]
                )
        );
        assertTrue(
                ALGraph.compareGraphs(
                        graphs[0].adjList.get(0),
                        graphs[0].adjList.get(0),
                        new boolean[graphs[0].adjList.size() + 1]
                )
        );
        assertFalse(
                ALGraph.compareGraphs(
                        graphs[0].adjList.get(0),
                        graphs[2].adjList.get(0),
                        new boolean[graphs[0].adjList.size() + 1]
                )
        );
    }



    @Test
    public void cloneGraphTest() {
        //AdjListGraph.compareGraphs(new Node(1, new ArrayList<>()), graphs[0].adjList.get(0), new boolean[100]);
        var clonedGraph = ALGraph.cloneGraph(graphs[0].adjList.get(0));
        assertTrue(ALGraph.compareGraphs(clonedGraph, graphs[0].adjList.get(0), new boolean[100]));
    }

}
