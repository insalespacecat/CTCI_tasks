package main.java.themes.Graphs.java.Dijkstra;

public class CheapestFlightWithinKStops {
    class Solution {

        //Step 1: convert flights to directed weighted graph
        //Step 2: run dijkstra algorithm which stops if it hits k-th stop
        //Step 3: look at dijkstra algLog and see the price, if it's integer.max_value,
        //return -1, otherwise return the price

        class N {
            int val;
            ArrayList<NC> nbs;

            N(int val) {
                this.val = val;
                this.nbs = new ArrayList<NC>();
            }
        }

        class NC {
            N toN;
            int cost;

            NC(N toN, int cost) {
                this.toN = toN;
                this.cost = cost;
            }
        }

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            var g = generateGraph(flights);
            var cM = dst(n, g, src, dst, k);

            return cM[dst] == Integer.MAX_VALUE ? -1 : cM[dst];
        }

        public HashMap<Integer, N> generateGraph(int[][] f) {
            var g = new HashMap<Integer, N>();

            for(int[] fl : f) {
                var src = g.get(fl[0]);
                var dest = g.get(fl[1]);

                if(src == null) {
                    src = new N(fl[0]);
                    g.put(fl[0], src);
                }

                if(dest == null) {
                    dest = new N(fl[1]);
                    g.put(fl[1], dest);
                }

                var nc = new NC(dest, fl[2]);

                src.nbs.add(nc);
            }

            return g;
        }

        public int[] dst(int n, HashMap<Integer, N> g, int src, int dst, int k) {
            Queue<N> q = new LinkedList<N>();
            var algLog = new boolean[n];
            var i = g.get(src);
            //var sc = -1;
            //var steps = getSteps(n, src);
            var cM = getCostMap(n, src);

            while(i != null) {
                algLog[i.val] = true;

                for(NC nc : i.nbs) {
                    if(!algLog[nc.toN.val]) {
                        q.add(nc.toN);
                    }

                    if(cM[nc.toN.val].size() == 1) {
                        var dest = cM[nc.toN.val].get(0);

                        for(Integer[] src : cM[i.val]) {
                            if(src[0])
                        }
                    }


                    if(cM[i.val] + nc.cost < cM[nc.toN.val] &&
                            steps[nc.toN.val] + steps[i.val] - 1 <= k) {
                        cM[nc.toN.val] = cM[i.val] + nc.cost;
                        steps[nc.toN.val] += steps[i.val];
                    }
                }

                i = q.poll();
            }

            return cM;
        }

        public int[] getSteps(int n, int s) {
            var steps = new int[n];

            for(int i = 0; i < n; i++) {
                steps[i] = 1;

                if(i == s) {
                    steps[i] = 0;
                }
            }

            return steps;
        }

        public ArrayList<Integer[]>[] getCostMap(int n, int src) {
            var cM = new ArrayList<Integer[]>[n];

            for(int i = 0; i < n; i++) {
                var pair = new Integer[2];

                if(i == src) {
                    pair[0] = 0;
                    pair[1] = 0;
                } else {
                    pair[0] = Integer.MAX_VALUE;
                    pair[1] = 1;
                }

                cM[i] = new ArrayList(pair);
            }

            return cM;
        }
    }
}

/*
class Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        // We use two arrays for storing distances and keep swapping
        // between them to save on the memory
        long[][] distances = new long[2][n];
        Arrays.fill(distances[0], Integer.MAX_VALUE);
        Arrays.fill(distances[1], Integer.MAX_VALUE);
        distances[0][src] = distances[1][src] = 0;

        // K + 1 iterations of Bellman Ford
        for (int iterations = 0; iterations < K + 1; iterations++) {

            // Iterate over all the edges
            for (int[] edge : flights) {

                int s = edge[0], d = edge[1], wUV = edge[2];

                // Current distance of node "s" from src
                long dU = distances[1 - iterations&1][s];

                // Current distance of node "d" from src
                // Note that this will port existing values as
                // well from the "previous" array if they didn't already exist
                long dV = distances[iterations&1][d];

                // Relax the edge if possible
                if (dU + wUV < dV) {
                    distances[iterations&1][d] = dU + wUV;
                }
            }
        }

        return distances[K&1][dst] < Integer.MAX_VALUE ? (int)distances[K&1][dst] : -1;
    }
}
 */
