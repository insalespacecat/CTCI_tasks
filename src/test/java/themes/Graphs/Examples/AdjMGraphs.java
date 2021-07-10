package test.java.themes.Graphs.Examples;

import main.java.themes.Graphs.AMGraph;

public class AdjMGraphs {
    public static AMGraph[] graphs;

    public static AMGraph[] getAdjMGraphs() {
        graphs = new AMGraph[3];

        graphs[0] = new AMGraph(5);
        graphs[1] = new AMGraph(4);
        graphs[2] = new AMGraph(5);

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

        return graphs;
    }
}
