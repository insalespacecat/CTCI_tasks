package test.java.themes.Graphs;

import main.java.themes.Graphs.AdjMGraph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdjMGraphAlgorithmsTest {

    private static AdjMGraph[] graphs;

    @BeforeAll
    public static void graphsInit() {
        graphs = new AdjMGraph[3];

        graphs[0] = new AdjMGraph(5);
        graphs[1] = new AdjMGraph(4);
        graphs[2] = new AdjMGraph(5);

        graphs[0].adjM[0] = new boolean[]{false, true, false, true, true};
        graphs[0].adjM[1] = new boolean[]{true, false, true, true, false};
        graphs[0].adjM[2] = new boolean[]{false, true, false, false, false};
        graphs[0].adjM[3] = new boolean[]{true, true, false, false, true};
        graphs[0].adjM[4] = new boolean[]{true, false, false, true, false};

        graphs[1].adjM[0] = new boolean[]{false, true, true, true};
        graphs[1].adjM[1] = new boolean[]{true, false, true, false};
        graphs[1].adjM[2] = new boolean[]{true, true, false, false};
        graphs[1].adjM[3] = new boolean[]{true, false, false, false};

        graphs[2].adjM[0] = new boolean[]{false, true, true, true, true};
        graphs[2].adjM[1] = new boolean[]{true, false, true, false, true};
        graphs[2].adjM[2] = new boolean[]{true, true, false, true, false};
        graphs[2].adjM[3] = new boolean[]{true, false, true, false, true};
        graphs[2].adjM[4] = new boolean[]{true, true, false, true, false};
    }

    @Test
    public void DFSTest() {
        assertTrue(AdjMGraph.DFS(4, 3, graphs[0], new boolean[5]));
        assertFalse(AdjMGraph.DFS(5, 2, graphs[0], new boolean[5]));

        assertTrue(AdjMGraph.DFS(2, 3, graphs[1], new boolean[4]));
        assertFalse(AdjMGraph.DFS(0, 5, graphs[1], new boolean[4]));

        assertTrue(AdjMGraph.DFS(2, 4, graphs[2], new boolean[5]));
        assertFalse(AdjMGraph.DFS(3, 5, graphs[2], new boolean[5]));
    }

    @Test
    public void BFSTest() {
        assertTrue(AdjMGraph.BFS(4, 3, graphs[0], new boolean[5]));
        assertFalse(AdjMGraph.BFS(5, 2, graphs[0], new boolean[5]));

        assertTrue(AdjMGraph.BFS(2, 3, graphs[1], new boolean[4]));
        assertFalse(AdjMGraph.BFS(0, 5, graphs[1], new boolean[4]));

        assertTrue(AdjMGraph.BFS(2, 4, graphs[2], new boolean[5]));
        assertFalse(AdjMGraph.BFS(3, 5, graphs[2], new boolean[5]));
    }

}
