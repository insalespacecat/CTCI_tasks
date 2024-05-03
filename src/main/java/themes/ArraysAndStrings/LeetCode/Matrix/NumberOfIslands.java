package main.java.themes.ArraysAndStrings.LeetCode.Matrix;

public class NumberOfIslands {
    class Solution {
        public int numIslands(char[][] grid) {
            int c = 0;

            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[0].length; j++) {
                    if(grid[i][j] == '1') {
                        c++;
                        di(grid, i, j);
                    }
                }
            }

            return c;
        }

        //bfs discover island
        private void di(char[][] g, int i, int j) {
            Queue<Integer> q = new LinkedList();
            q.add(i * g[0].length + j);

            while(!q.isEmpty()) {
                //Current value, current i, current j
                var cur = q.poll();
                var ci = cur / g[0].length;
                var cj = cur % g[0].length;

                g[ci][cj] = '-';

                if(ci > 0 && g[ci - 1][cj] == '1') {
                    q.add((ci - 1) * g[0].length + cj);
                }

                if(ci < g.length - 1 && g[ci + 1][cj] == '1') {
                    q.add((ci + 1) * g[0].length + cj);
                }

                if(cj > 0 && g[ci][cj - 1] == '1') {
                    q.add(ci * g[0].length + cj - 1);
                }

                if(cj < g[0].length - 1 && g[ci][cj + 1] == '1') {
                    q.add(ci * g[0].length + cj + 1);
                }
            }
        }
    }

    class Solution {
        public int numIslands(char[][] grid) {
            int c = 0;

            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[0].length; j++) {
                    if(grid[i][j] == '1') {
                        c++;
                        di(grid, i, j);
                    }
                }
            }

            return c;
        }

        //bfs discover island
        private void di(char[][] g, int i, int j) {
            Queue<Pair<Integer,Integer>> q = new LinkedList();
            q.add(new Pair<Integer, Integer>(i,j));
            g[i][j] = '-';

            while(!q.isEmpty()) {
                //Current value, current i, current j
                var cur = q.poll();
                var ci = cur.getKey();
                var cj = cur.getValue();

                if(ci > 0 && g[ci - 1][cj] == '1') {
                    q.add(new Pair<Integer, Integer>(ci - 1, cj));
                    g[ci - 1][cj] = '-';
                }

                if(ci < g.length - 1 && g[ci + 1][cj] == '1') {
                    q.add(new Pair<Integer, Integer>(ci + 1, cj));
                    g[ci + 1][cj] = '-';
                }

                if(cj > 0 && g[ci][cj - 1] == '1') {
                    q.add(new Pair<Integer, Integer>(ci, cj - 1));
                    g[ci][cj - 1] = '-';
                }

                if(cj < g[0].length - 1 && g[ci][cj + 1] == '1') {
                    q.add(new Pair<Integer, Integer>(ci, cj + 1));
                    g[ci][cj + 1] = '-';
                }
            }
        }
    }
}
