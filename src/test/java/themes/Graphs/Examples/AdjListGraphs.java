package test.java.themes.Graphs.Examples;

import main.java.themes.Graphs.ALGraph;
import main.java.themes.Graphs.ALNode;

import java.util.List;

public class AdjListGraphs {
    public static ALGraph[] graphs;

    public static ALGraph[] getAdjListGraphs() {
        graphs = new ALGraph[3];

        ALNode[] g1AdjListNodes = new ALNode[7];
        ALNode[] g2AdjListNodes = new ALNode[8];
        ALNode[] g3AdjListNodes = new ALNode[6];

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

        graphs[0] = new ALGraph(g1AdjListNodes);
        graphs[1] = new ALGraph(g2AdjListNodes);
        graphs[2] = new ALGraph(g3AdjListNodes);

        return graphs;
    }

    public static void simpleNodesInit(ALNode[] gAdjListNodes) {
        for(int i = 0; i < gAdjListNodes.length; i++) {
            gAdjListNodes[i] = new ALNode(i+1);
        }
    }
}
