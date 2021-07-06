package main.java.themes.Graphs.Dijkstra;
//source; https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/

//There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti]
//represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.
//
//Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold,
//If there are multiple such cities, return the city with the greatest number.
//
//Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.

//Example 1:
//Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
//Output: 3
//Explanation: The figure above describes the graph.
//The neighboring cities at a distanceThreshold = 4 for each city are:
//City 0 -> [City 1, City 2]
//City 1 -> [City 0, City 2, City 3]
//City 2 -> [City 0, City 1, City 3]
//City 3 -> [City 1, City 2]
//Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3

//Example 2:
//Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
//Output: 0
//Explanation: The figure above describes the graph.
//The neighboring cities at a distanceThreshold = 2 for each city are:
//City 0 -> [City 1]
//City 1 -> [City 0, City 4]
//City 2 -> [City 3, City 4]
//City 3 -> [City 2, City 4]
//City 4 -> [City 1, City 2, City 3]
//The city 0 has 1 neighboring city at a distanceThreshold = 2.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindCityDijkstraThreshold {
    //Overview:
    //1) Caculate the number of neighbors for each city that are reachable within dT
    //2) Pick cities with least neighbors
    //3) Return the one with bigger number.

    //Detalized:
    //0) Convert martix of unordered edges into graph
    //- Use adjMatrix graph (because there is max 100 elements that are fairly interconnected. (Save runtime avoiding collections))
    //- Just iterate through edges list and fill the matrix.

    //1) To calculate number of neighbors we will have to use Dijkstra algorithm
    //- We can run unmodified algorithm to get the output of distances to each city from starting city, and then filter the output
    //- We can modify algorithm to stop searching if we passed dT (more efficient)
    //- Run algorithm for each city, (filtering and) collecting result into matrix
    //- Store only cities numbers that match. For example d[0] -> [1,2,3];

    //2) After we run the algorithm for each city we will have a matrix d.
    //- Go over it: use mL (minLength) var to check the length of d[i]
    //- Collect results into arrayList.
    //- If d[i].length === mL then append i to list
    //- If d[i].length < mL, then drop the list and append i

    //3) Among the collected res, iterate through the list and pick one that's just bigger (probably the last value of the list)


    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        var aM = getAdjMatrixFromEdges(n, edges);
        var cN = getCityNavigator(aM, n, distanceThreshold);
        return findBiggestCityWithLeastNeighbors(cN);
    }

    public int[][] getAdjMatrixFromEdges(int n, int[][] ed) {
        var aM = new int[n][n];

        for(int i = 0; i < ed.length; i++) {
            aM[ed[i][0]][ed[i][1]] = ed[i][2];
            aM[ed[i][1]][ed[i][0]] = ed[i][2];
        }

        return aM;
    }

    public int findBiggestCityWithLeastNeighbors(List<Integer>[] cN) {
        var city = -1;
        var mL = Integer.MAX_VALUE;

        for(int i = 0; i < cN.length; i++) {
            if(cN[i].size() == mL) {
                city = i;
            }

            if(cN[i].size() < mL) {
                city = i;
                mL = cN[i].size();
            }
        }

        return city;
    }

    //City navigator i.e. matrix which shows for each city, which cities are within threshold
    public List<Integer>[] getCityNavigator(int[][] aM, int n, int tD) {
        var cN = new ArrayList[n];

        for(int i = 0; i < aM.length; i++) {
            var aC = dijkstraWithThreshold(aM, n, i, tD);
            cN[i] = new ArrayList<Integer>();

            for(int j = 0; j < aC.length; j++) {
                if(aC[j] < Integer.MAX_VALUE) {
                    cN[i].add(j);
                }
            }
        }

        return cN;
    }

    //Dijkstra algorithm
    //Modified BFS
    //creates int[] costs
    //Writes to costs shortest path from the start
    //Iterates as BFS. Keeps track of visited nodes.
    //If node was already visited and cost of coming into it is not less then already found, do not append it to queue.
    public int[] dijkstraWithThreshold(int[][] aM, int n, int start, int tD) {
        Queue<Integer> q = new LinkedList<>();
        var aL = new boolean[n];
        var costs = createCosts(n, start);
        Integer step = start;

        while(step != null) {
            var c = costs[step];
            aL[step] = true;

            for(int i = 0; i < aM[step].length; i++) {
                if(aM[step][i] > 0) {
                    var sC = c + aM[step][i];

                    if(sC > tD) {
                        continue;
                    }

                    if(sC < costs[i]) {
                        costs[i] = sC;

                        if(aL[i]) {
                            q.add(i);
                        }
                    }

                    if(!aL[i]) {
                        q.add(i);
                    }
                }
            }

            step = q.poll();
        }

        return costs;
    }

    private int[] createCosts(int n, int start) {
        var costs = new int[n];

        for(int i = 0; i < n; i++) {
            costs[i] = Integer.MAX_VALUE;
        }

        costs[start] = 0;
        return costs;
    }
}
