package test.java.themes.Graphs;

import main.java.themes.Graphs.java.ALDWNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test.java.themes.Graphs.Examples.DWLGGraphs;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Directed weighted letter graph node.
//a ->(2) b ->(1) c
public class DWLGNodeAlgorithmsTest {

    public static HashMap<String, ALDWNode> graph;

    @BeforeAll
    public static void init() {
        graph = DWLGGraphs.getDWLGGraph();
    }

    @Test
    public void getMinCostTest() {
        var costs = new HashMap<String, Double>();

        costs.put("a", 0.0d);
        costs.put("b", Double.MAX_VALUE);
        costs.put("c", Double.MAX_VALUE);
        assertEquals(5.0d, ALDWNode.getMinCost(graph.get("a"), graph.get("c"), costs));

        costs.put("a", Double.MAX_VALUE);
        costs.put("c", Double.MAX_VALUE);
        costs.put("b", 0.0d);
        assertEquals(1.0d/2.0d, ALDWNode.getMinCost(graph.get("b"), graph.get("a"), costs));

        costs.put("c", 0.0d);
        costs.put("b", Double.MAX_VALUE);
        costs.put("a", Double.MAX_VALUE);
        assertEquals(1.0d/2.0d + 1.0d/3.0d, ALDWNode.getMinCost(graph.get("c"), graph.get("a"), costs));
    }

}
