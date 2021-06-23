package main.java.crucial.graphs;

public class BaseGraphAlgorithms {

    //Depth-first search is an algorithm which uses a stack in order to recursively traverse the
    //tree branches that come from the vertex given until they hit the bottom of the branch
    //So the algorithm is quite simple:
    //- Take a vertex
    //- Mark as visited
    //- Look at its connections
    //- for each connection:
    //   if(!connection.visited)
    //     recursive call
    //- Mark as done
    //- return

    //vN - vertex number
    //vTF - vertex to find
    public boolean DFS(int vN, int vTF, Graph graph, boolean[] algLog) {
        if(vTF == vN) {
            return true;
        }
        if(vN >= algLog.length) {
            return false;
        }
        algLog[vN] = true;
        boolean res = false;
        for(int i = 0; i < graph.adjM[vN].length; i++) {
            if(graph.adjM[vN][i] && !algLog[i]) {
                res = DFS(i, vTF, graph, algLog);
                if(res) {
                    break;
                }
            }
        }
        return res;
    }

}
