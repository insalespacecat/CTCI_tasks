package main.java.themes.Graphs;

import java.util.List;

public class ALDNode {
    public String val;
    public List<ALDNode> neighbors;

    ALDNode(String val) {
        this.val = val;
    }
}
