package main.java.themes.Graphs.Dijkstra;

import java.util.*;

class Node {
    public String val;
    public Map<String, Connection> neighbors;

    public Node(String val) {
        this.val = val;
        this.neighbors = new HashMap<String, Connection>();
    }
}

class Connection {
    public Node toNode;
    public double weight;

    public Connection(Node toNode, double weight) {
        this.toNode = toNode;
        this.weight = weight;
    }
}

class Solution {

    //Algorithm:
    //Step 1: convert an equations + values to a directed weighted graph.
    //Where nodes are letters [a,b,c,d...] and cost of going from letter to letter is cost of division of start letter by end letter
    //For example a ->(2) b is a/b = 2.
    //How to create a graph:
    //Firstly, look at simple equations like a/b, b/c. Init the nodes and init the edges a -> b, b -> c.
    //Secondly, init the reverse edges to those we already have, like b -> a and c -> b. Weight of reverse edge is 1/edge weight.
    //Thirdly, take care of complex edges like bc/cd. Those edges like b->c->d. Those might be up to 6 nodes? bcefj/cefjp = b -> c -> e -> f -> j -> p?
    //We might not have direct linkage like shown in example 2 bc/cd = b -> c -> d. We might just have

    //Determine values of queiries.
    //SO we might start with just brute force determining and after we see how brute force works, we might see how we can use
    //shortest path algorithm and union find to check it.

    //-How to convert equations + values into directed weighted graph;
    //1. Define a graph. Map<String, List<Node>> g.
    //In this kind of represenatation, we a have map, to check the neighbors of a node we still have to query this map.
    //Node will store the cost of coming into it from Key.
    //This won't be an interconnected graph representation but it will serve us fine

    //For each equation + value
    //if(map.get(eq[0]) == null) map.put(eq[0], new ArrayList<Node>);
    //if(map.get(eq[1]) == null) map.put(eq[1], new ArrayList<Node>);
    //map.put(map.get(eq[0]).add(new Node(eq[1], v[i])));
    //map.put(map.get(eq[1]).add(new Node(eq[0], 1/v[i]));

    //Implement Dijkstra algorithm on the given graph to find shortest path.
    //Dijkstra algorithm:
    //- Start with node s; get map of costs, where all unknown distances = infinity, distance to start is 0.
    //- Start BFS: for each unvisited neighbor
    //- cost to visit a neighbor is cost to node s + edge weight.
    //- if cost is less then present in a map -> update the cost.
    //

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        var map = cG(equations, values);
        return pQ(map, queries);
    }

    //cG = create graph
    public Map<String, Node> cG(List<List<String>> eq, double[] v) {
        var map = new HashMap<String, Node>();
        var it = eq.iterator();
        var i = 0;

        while(it.hasNext()) {
            var e = it.next();

            if(map.get(e.get(0)) == null) {
                map.put(e.get(0), new Node(e.get(0)));
            }

            if(map.get(e.get(1)) == null) {
                map.put(e.get(1), new Node(e.get(1)));
            }

            Node s = map.get(e.get(0));
            s.neighbors.put(
                    e.get(1),
                    new Connection(
                            map.get(e.get(1)),
                            v[i]
                    )
            );

            s = map.get(e.get(1));
            s.neighbors.put(
                    e.get(0),
                    new Connection(
                            map.get(e.get(0)),
                            1 / v[i]
                    )
            );

            i++;
        }

        return map;
    }

    //pQ = process queries
    public double[] pQ(Map<String, Node> map, List<List<String>> qrs) {
        var res = new double[qrs.size()];
        var i = 0;
        var itQ = qrs.iterator();

        while(itQ.hasNext()) {
            var q = itQ.next();
            var start = map.get(q.get(0));
            var finish = map.get(q.get(1));

            if(start == null || finish == null) {
                res[i] = -1.0;
                i++;
                continue;
            }

            if(q.get(0).equals(q.get(1))) {
                res[i] = 1.0;
                i++;
                continue;
            }

            res[i] = getMinCost(start, finish, costsInit(start.val, map));

            i++;
        }

        return res;
    }

    private HashMap<String, Double> costsInit(String s, Map<String, Node> map) {
        var costs = new HashMap<String, Double>();

        for(String key : map.keySet()) {
            costs.put(key, Double.MAX_VALUE);
        }

        costs.put(s, 0.0d);

        return costs;
    }


    public static double getMinCost(Node start, Node finish, HashMap<String, Double> costs) {
        var shortestPaths = dijkstra(start, costs, new HashMap<String, Boolean>());
        var res = shortestPaths.get(finish.val);
        if(res == Double.MAX_VALUE) {
            return -1.0d;
        } else {
            return res;
        }
    }

    public static Map<String, Double> dijkstra(
            Node node,
            Map<String, Double> costs,
            Map<String, Boolean> algLog
    ) {
        Queue<Node> queue = new LinkedList<>();
        //s = step
        var s = node;

        while(s != null) {
            //sC = step cost;
            var sC = costs.get(s.val);
            algLog.put(s.val, true);

            for(Connection c : s.neighbors.values()) {
                //cC = connection cost
                double cC;

                if(sC != 0.0d) {
                    cC = c.weight * sC;
                } else {
                    cC = c.weight;
                }
                if(cC < costs.get(c.toNode.val)) {
                    costs.put(c.toNode.val, cC);
                    if(algLog.get(c.toNode.val) != null) {
                        queue.add(c.toNode);
                    }
                }
                if(algLog.get(c.toNode.val) == null) {
                    queue.add(c.toNode);
                }
            }

            s = queue.poll();
        }

        return costs;
    }

}
