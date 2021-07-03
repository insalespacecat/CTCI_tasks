package test.java.themes.Graphs;

import main.java.themes.Graphs.DWLGNode;
import main.java.themes.Graphs.DWLGNodeConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Directed weighted letter graph node.
//a ->(2) b ->(1) c
public class DWLGNodeAlgorithmsTest {

    public static HashMap<String, DWLGNode> graph;

    @BeforeAll
    public static void init() {
        graph = new HashMap<String, DWLGNode>();

        graph.put("a", new DWLGNode("a"));
        graph.put("b", new DWLGNode("b"));
        graph.put("c", new DWLGNode("c"));

        graph.get("a").neighbors.put("b", new DWLGNodeConnection(graph.get("b"), 2.0d));
        graph.get("b").neighbors.put("a", new DWLGNodeConnection(graph.get("a"), 1.0d/2.0d));
        graph.get("b").neighbors.put("c", new DWLGNodeConnection(graph.get("c"), 3.0d));
        graph.get("c").neighbors.put("b", new DWLGNodeConnection(graph.get("b"), 1.0d/3.0d));
    }

    @Test
    public void getMinCostTest() {
        var costs = new HashMap<String, Double>();

        costs.put("a", 0.0d);
        costs.put("b", Double.MAX_VALUE);
        costs.put("c", Double.MAX_VALUE);
        //assertEquals(5.0d, DWLGNode.getMinCost(graph.get("a"), graph.get("c"), costs));

        costs.put("a", Double.MAX_VALUE);
        costs.put("c", Double.MAX_VALUE);
        costs.put("b", 0.0d);
        //assertEquals(1.0d/2.0d, DWLGNode.getMinCost(graph.get("b"), graph.get("a"), costs));

        costs.put("c", 0.0d);
        costs.put("b", Double.MAX_VALUE);
        costs.put("a", Double.MAX_VALUE);
        assertEquals(1.0d/2.0d + 1.0d/3.0d, DWLGNode.getMinCost(graph.get("c"), graph.get("a"), costs));
    }

}
