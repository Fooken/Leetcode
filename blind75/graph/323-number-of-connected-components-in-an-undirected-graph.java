class Solution {
    // DFS adjust Array 无向图
    public int countComponents1(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];

        // initial
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // build graph
        for (int[] edge: edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int compontent = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                compontent++;
                dfs(graph, i, visited);
            }
        }
        return compontent;
    }

    private void dfs(List<Integer>[] g, int start, boolean[] visited) {
        visited[start] = true;

        for (int neigh: g[start]) {
            if (!visited[neigh]) {
                dfs(g, neigh, visited);
            }
        }
    }

    // Disjoint Set Union
    public int countComponents(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        int res = n;

        for (int[] edge: edges) {
            int x = edge[0];
            int y = edge[1];
            if (dsu.find(x) != dsu.find(y)) {
                dsu.union(x, y);
                res--;
            }
        }
        return res;
    }
}
class DSU {
    public int[] parent;
    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}
