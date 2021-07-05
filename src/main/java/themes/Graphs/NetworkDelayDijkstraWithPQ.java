package main.java.themes.Graphs;

//src: https://leetcode.com/problems/network-delay-time/
//You are given a network of n nodes, labeled from 1 to n.
//You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
//where ui is the source node, vi is the target node,
//and wi is the time it takes for a signal to travel from source to target.
//
//We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal.
//If it is impossible for all the n nodes to receive the signal, return -1.

//Example 1:
//Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//Output: 2

//Example 2:
//Input: times = [[1,2,1]], n = 2, k = 1
//Output: 1

//Example 3:
//Input: times = [[1,2,1]], n = 2, k = 2
//Output: -1
public class NetworkDelayDijkstraWithPQ {
    //Step 1 -> convert times into adjMatrix graph
    //Step 2 -> fire Dijkstra algorithm on it
    //Step 3 -> check the output. Find the most distant node, if distance is Integer.MAX_VALUE return -1, else return distance
    public int networkDelayTime(int[][] times, int n, int k) {
        var graph = getGraph(times, n);
        var distances = dijkstra(graph, n, k);
        return getMaxDistance(distances);
    }

    public int[][] getGraph(int[][] times, int n) {
        var graph = new int[n+1][n+1];

        for(int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                graph[i][j] = -1;
            }
        }

        for(int i = 0; i < times.length; i++) {
            graph[times[i][0]][times[i][1]] = times[i][2];
        }

        return graph;
    }

    //Dijkstra is algorithm based on BFS
    //it uses BFS log of visited values
    //it uses it's own log of distances from start to the node
    public int[] dijkstra(int[][] graph, int n, int k) {
        var vis = new boolean[n+1];
        var dis = getDisArr(n, k);
        PriorityQueue<Pair<Integer, Integer>> q = getPriorityQueue();
        Pair<Integer, Integer> step = new Pair(0, k);

        while(step != null) {
            vis[step.getValue()] = true;

            for(int i = 1; i < n + 1; i++) {
                if(graph[step.getValue()][i] > -1) {
                    var sD = step.getKey() + graph[step.getValue()][i];

                    if(sD < dis[i]) {
                        dis[i] = sD;

                        if(vis[i]) {
                            q.add(new Pair(sD, i));
                        }
                    }

                    if(!vis[i]) {
                        q.add(new Pair(sD, i));
                    }
                }
            }

            step = q.poll();
        }

        return dis;
    }

    public int[] getDisArr(int n, int k) {
        var dis = new int[n+1];

        for(int i = 1; i < n + 1; i++) {
            dis[i] = Integer.MAX_VALUE;
        }

        dis[k] = 0;
        return dis;
    }


    private PriorityQueue<Pair<Integer, Integer>> getPriorityQueue() {
        return new PriorityQueue<Pair<Integer, Integer>>(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                return p1.getKey() - p2.getKey();
            }
        });
    }

    public int getMaxDistance(int[] dis) {
        var max = 0;

        for(int i = 1; i < dis.length; i++) {
            if(dis[i] == Integer.MAX_VALUE) {
                return -1;
            }

            if(dis[i] > max) {
                max = dis[i];
            }
        }

        return max;
    }


}
