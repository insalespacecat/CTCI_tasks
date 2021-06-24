package test.java.crucial.graphs;

import main.java.crucial.graphs.BaseGraphAlgorithms;
import main.java.crucial.graphs.Graph;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseGraphAlgorithmsTest {

    private static Graph[] graphs;
    private final BaseGraphAlgorithms graphAlg = new BaseGraphAlgorithms();

    @BeforeAll
    public static void graphsInit() {
        graphs = new Graph[3];

        graphs[0] = new Graph(5);
        graphs[1] = new Graph(4);
        graphs[2] = new Graph(5);

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
        assertTrue(graphAlg.DFS(4, 3, graphs[0], new boolean[5]));
        assertFalse(graphAlg.DFS(5, 2, graphs[0], new boolean[5]));

        assertTrue(graphAlg.DFS(2, 3, graphs[1], new boolean[4]));
        assertFalse(graphAlg.DFS(0, 5, graphs[1], new boolean[4]));

        assertTrue(graphAlg.DFS(2, 4, graphs[2], new boolean[5]));
        assertFalse(graphAlg.DFS(3, 5, graphs[2], new boolean[5]));
    }

    @Test
    public void BFSTest() {
        assertTrue(graphAlg.BFS(4, 3, graphs[0], new boolean[5]));
        assertFalse(graphAlg.BFS(5, 2, graphs[0], new boolean[5]));

        assertTrue(graphAlg.BFS(2, 3, graphs[1], new boolean[4]));
        assertFalse(graphAlg.BFS(0, 5, graphs[1], new boolean[4]));

        assertTrue(graphAlg.BFS(2, 4, graphs[2], new boolean[5]));
        assertFalse(graphAlg.BFS(3, 5, graphs[2], new boolean[5]));
    }

}
