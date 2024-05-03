package main.java.themes.Graphs.java.Dijkstra;

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

//This class contains MEH solution which might be improved on the 2nd run of the solving

//Evaluate division
//[Solved using simple dijkstra]
//https://leetcode.com/problems/evaluate-division/

//You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i]
//represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
//
//You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
//
//Return the answers to all queries. If a single answer cannot be determined, return -1.0.
//
//Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

//Example 1:
//Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
//Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
//Explanation:
//Given: a / b = 2.0, b / c = 3.0
//queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//return: [6.0, 0.5, -1.0, 1.0, -1.0 ]

//Example 2:
//Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//Output: [3.75000,0.40000,5.00000,0.20000]

//Example 3:
//Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
//Output: [0.50000,2.00000,-1.00000,-1.00000]

//Constraints:
//1 <= equations.length <= 20
//equations[i].length == 2
//1 <= Ai.length, Bi.length <= 5
//values.length == equations.length
//0.0 < values[i] <= 20.0
//1 <= queries.length <= 20
//queries[i].length == 2
//1 <= Cj.length, Dj.length <= 5
//Ai, Bi, Cj, Dj consist of lower case English letters and digits.

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

    //Determine values of queries.
    //SO we might start with just brute force determining and after we see how brute force works, we might see how we can use
    //shortest path algorithm and union find to check it.

    //-How to convert equations + values into directed weighted graph;
    //1. Define a graph. Map<String, List<Node>> g.
    //In this kind of representation, we a have map, to check the neighbors of a node we still have to query this map.
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
