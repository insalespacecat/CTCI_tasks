package test.java.themes.Graphs.Examples;

import main.java.themes.Graphs.ALDWNode;
import main.java.themes.Graphs.ALDWNodeConnection;

import java.util.HashMap;

public class DWLGGraphs {
    public static HashMap<String, ALDWNode> graph;

    public static HashMap<String, ALDWNode> getDWLGGraph() {
        graph = new HashMap<String, ALDWNode>();

        graph.put("a", new ALDWNode("a"));
        graph.put("b", new ALDWNode("b"));
        graph.put("c", new ALDWNode("c"));

        graph.get("a").neighbors.put("b", new ALDWNodeConnection(graph.get("b"), 2.0d));
        graph.get("b").neighbors.put("a", new ALDWNodeConnection(graph.get("a"), 1.0d/2.0d));
        graph.get("b").neighbors.put("c", new ALDWNodeConnection(graph.get("c"), 3.0d));
        graph.get("c").neighbors.put("b", new ALDWNodeConnection(graph.get("b"), 1.0d/3.0d));

        return graph;
    }
}
