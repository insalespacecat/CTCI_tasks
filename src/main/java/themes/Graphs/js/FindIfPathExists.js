//https://leetcode.com/problems/find-if-path-exists-in-graph/

/*
There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 */

/*
Constraints:

    1 <= n <= 2 * 105
    0 <= edges.length <= 2 * 105
    edges[i].length == 2
    0 <= ui, vi <= n - 1
    ui != vi
    0 <= source, destination <= n - 1
    There are no duplicate edges.
    There are no self edges.

 */

/**
 * @param {number} n
 * @param {number[][]} edges
 * @param {number} source
 * @param {number} destination
 * @return {boolean}
 */

//Step 1: convert edge list to adjList
//Step 2: run dfs on that adjList
//Step 3: break dfs whenever we found destination value.
//Complexity. Space: O(V+E), Time: O(V+E)

//Alternatively just loop through the edges and see if edge[0] and edge[1] match destination and source lyl
//but this is no fun at all
//Complexity: Time: O(E), Space: O(1)

//Edge cases:
//-No edges possible
var validPath = function(n, edges, source, destination) {
    if(source === destination) {
        return true;
    }

    if(!edges.length) {
        return false;
    }

    const adjList = getAdjList(edges, n);

    return checkConnectivityDFS(adjList, source, destination, new Array(n));
};

function checkConnectivityDFS(adjList, current, target, visited) {
    visited[current] = true;

    const nbs = Object.keys(adjList[current]);

    for(let i = 0; i < nbs.length; i++) {
        if(parseInt(nbs[i], 10) === target) {
            return true;
        }

        if(!visited[nbs[i]]) {
            if(checkConnectivityDFS(adjList, nbs[i], target, visited)) {
                return true;
            }
        }
    }

    return false;
}

function getAdjList(edges, n) {
    const adjList = new Array(n);

    edges.forEach((edge) => {
        if(!adjList[edge[0]]) {
            adjList[edge[0]] = {};
        }

        if(!adjList[edge[1]]) {
            adjList[edge[1]] = {};
        }

        adjList[edge[0]][edge[1]] = true;
        adjList[edge[1]][edge[0]] = true;
    });

    return adjList;
}
