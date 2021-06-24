package main.java.crucial.graphs;

import java.util.LinkedList;
import java.util.Queue;

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

    //Breadth-first search is a search that accepts a graph node and checks all node's
    //neighbors with one-step depth, slicing graph layer by layer in order to find a value
    //Algorithm
    //- Take vN
    //- Check its neighbors:
    //    for each neighbor
    //      if neighbor is not visited
    //       mark as visited and append to the queue
    //- Take a vertex from the queue
    //- Check its neighbors ...
    //The thing with the algorithm is that at the first step we have 1
    //node to start with and we just check its neighbors for each neighbor.
    //- that's pretty simple
    //But when we go one layer deeper this is where the fun begins
    //we have to:
    //* Step into the node 1
    //* Check its neighbors
    //* Step back out
    //* Check the neighbors of the next node 2
    //...
    //* Check the neighbors of the last node
    //* Return to the node 1
    //* Step in node 1, step in first neighbor of node 1
    //* Check all neighbors of that node
    //* Step back out
    //* Go to second neighbor of node 1
    //* Check all neighbors of that node
    //....
    //This is where queue comes handy.
    //Because you need to travel that much between nodes,
    //It is easy for you to just *schedule* nodes to check
    //starting with first step.
    //When you are on the first layer you append to queue
    //all unvisited neighbors of first node
    //and then on the second ... and finally on the last.
    //By doing that you actually form a 2nd layer to check
    //So you don't need to actually "step back up" the graph
    //you can directly go into the vertex you scheduled

    public boolean BFS(int vN, int vTF, Graph graph, boolean[] algLog) {
        if(vTF >= graph.adjM.length || vN >= graph.adjM.length) {
            return false;
        }

        Queue<Integer> queue = new LinkedList<>();
        Integer i = vN;
        boolean res = false;

        while(i != null) {
            res = checkNbs(i, vTF, graph, algLog, queue);
            if(res) {
                break;
            }
            i = queue.poll();
        }

        return res;
    }

    private boolean checkNbs(int vN, int vTF, Graph graph, boolean[] algLog, Queue<Integer> queue) {
        for(int i = 0; i < graph.adjM[vN].length; i++) {
            if(graph.adjM[vN][i] && !algLog[i]) {
                algLog[i] = true;
                queue.add(i);
            }

            if(graph.adjM[vN][i] && i == vTF) {
                return true;
            }
        }

        return false;
    }

}
