package test.java.themes.Graphs;

import main.java.themes.Graphs.java.AMGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test.java.themes.Graphs.Examples.AdjMGraphs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdjMGraphAlgorithmsTest {

    private static AMGraph[] graphs;

    @BeforeAll
    public static void graphsInit() {
        graphs = AdjMGraphs.getAdjMGraphs();
    }

    @Test
    public void DFSTest() {
        assertTrue(AMGraph.DFS(4, 3, graphs[0], new boolean[5]));
        assertFalse(AMGraph.DFS(5, 2, graphs[0], new boolean[5]));

        assertTrue(AMGraph.DFS(2, 3, graphs[1], new boolean[4]));
        assertFalse(AMGraph.DFS(0, 5, graphs[1], new boolean[4]));

        assertTrue(AMGraph.DFS(2, 4, graphs[2], new boolean[5]));
        assertFalse(AMGraph.DFS(3, 5, graphs[2], new boolean[5]));
    }

    @Test
    public void BFSTest() {
        assertTrue(AMGraph.BFS(4, 3, graphs[0], new boolean[5]));
        assertFalse(AMGraph.BFS(5, 2, graphs[0], new boolean[5]));

        assertTrue(AMGraph.BFS(2, 3, graphs[1], new boolean[4]));
        assertFalse(AMGraph.BFS(0, 5, graphs[1], new boolean[4]));

        assertTrue(AMGraph.BFS(2, 4, graphs[2], new boolean[5]));
        assertFalse(AMGraph.BFS(3, 5, graphs[2], new boolean[5]));
    }

}
