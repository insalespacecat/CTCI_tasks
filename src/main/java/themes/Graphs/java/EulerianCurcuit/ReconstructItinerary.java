package main.java.themes.Graphs.java.EulerianCurcuit;

public class ReconstructItinerary {
    /*
    class Solution {
    //Step 1: construct a graph from edges "tickets"
    //Step 2: since we know the starting point and graph is guaranteed to have atleast 1 euler path,
    //we can skip checking of the graph.
    //Step 3: fire toposort-like checking to determine the path.
    //DFS algorithm
    //on return writes node that it just returned from to the array.
    //Greedy algorithm.
    //Checks all the neighbors and selects the neighbor that
    //has "cheapest" lexical order in comparison to others
    //How to check lexical order
    //Take node
    //Get letter of the node, convert to ASCII and compare two numbers
    //if ascii1 < ascii2, we immidiately found node which is smaller in lexical order.
    //Run the check similarly to how we find min number in the array.
    //We don't need to check extremes here because we are guaranteed that ALL nodes create a viable euler path.
    //We need to check if everything is fine on disconnected subgraphs
    //We need to check if everything is fine when we have standalone nodes
    //We can go for each node in the result. Cross out all nodes that are present in the result - they are fine.
    //If after that we still have nodes that aren't crossed out we have to check for each node if it has
    //any connections. If node has no connections then it's standalone and we are practically fine.
    //If it has connection with another node which is not present in the result, then we have disconnected subgraph.
    //and it's impossible to construct a path here.

    class N {
        String val;
        ArrayList<N> nb;
        N[] sortedNb;

        N(String val) {
            this.val = val;
            this.nb = new ArrayList<N>();
        }
    }

    //Full Time: O(ElogE), Space: O(V+E);
    public List<String> findItinerary(List<List<String>> tickets) {
        //Time: O(E), Space: O(V+E)
        var g = createGraph(tickets);
        //Space: O(V+E)
        var it = new ArrayList<String>();

        //Time: O(ElogE)
        //Space: O(E)
        heirholzers(g.get("JFK"), it);
        it.add("JFK");

        //Time: O(E), Space: O(V+E)
        return reverseList(it);
    }

    public List<String> reverseList(List<String> l) {
        var lr = new ArrayList<String>();

        for(int i = l.size() - 1; i >= 0; i--) {
            lr.add(l.get(i));
        }

        return lr;
    }

    //Time: O(E), Space: O(V+E)
    public HashMap<String, N> createGraph(List<List<String>> cn) {
        var g = new HashMap<String, N>();

        for(List<String> c : cn) {
            var n1 = g.get(c.get(0));
            var n2 = g.get(c.get(1));

            if(n1 == null) {
                n1 = new N(c.get(0));
                g.put(c.get(0), n1);
            }

            if(n2 == null) {
                n2 = new N(c.get(1));
                g.put(c.get(1), n2);
            }

            n1.nb.add(n2);
        }

        return g;
    }

    //Visit all verticies O(V),
    //For each vertex V, sort neighbors Nb with complexity O(ElogE)
    //Visit all edges O(E)
    //If eulerian path exists, E > V for connected part of the graph
    //So complexity of DFS-like algorithm is O(E)
    //Sort all edges will take max time O(ElogE)
    //Time: O(E + ElogE) = O(ElogE).
    //Space: O(E)
    public void heirholzers(N s, List<String> it) {
        if(s.sortedNb == null) {
            var snb = new N[s.nb.size()];
            s.sortedNb = sortNeighborsByCost(s.nb.toArray(snb));
        }

        for(int i = 0; i < s.sortedNb.length; i++) {
            if(s.sortedNb[i] != null) {
                var nb = s.sortedNb[i];
                s.sortedNb[i] = null;

                heirholzers(nb, it);

                it.add(nb.val);
            }
        }
    }

    //O(NblogNb) complexity, where Nb is neighbors of the node.
    //Nb is max E/2 in the worst
    //Thus, we have O((E/2)log(E/2)) == O(ElogE)
    //Funciton sort can sort strings by literal complexity even without custom comparator
    public N[] sortNeighborsByCost(N[] nbs) {
        Arrays.sort(nbs, (a, b) -> {
            for(int i = 0; i < 3; i++) {
                var n1 = Character.getNumericValue(a.val.charAt(i));
                var n2 = Character.getNumericValue(b.val.charAt(i));

                if(n1 > n2) {
                    return 1;
                }

                if(n2 > n1) {
                    return -1;
                }
            }

            return 0;
        });

        return nbs;
    }
}
     */
}
