class Solution {
    // V1: DFS with parent
    // 1. no cycle
    // 2. all connected
    public boolean validTree1(int n, int[][] edges) {
        // 构建图的邻接表表示 adjacencyList
        List<List<Integer>> graph = new ArrayList<>();
        // initial
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // build graph
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];

        // 检查是否存在环路，即判断图中是否有回边
        if (hasCycle(graph, 0, -1, visited)) {
            return false;
        }

        // 检查是否有孤立节点，即判断图中是否有非连通部分
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle(List<List<Integer>> graph, int curr, int parent, boolean[] visited) {
        visited[curr] = true;
        for (int neigh: graph.get(curr)) {
            if (!visited[neigh]) {
                if (hasCycle(graph, neigh, curr, visited)) {
                    return true;
                }
            } else if (neigh != parent) {
                return true; // 存在回边，说明图中有环路
            }
        }
        return false;
    }
    // DSU 
    public boolean validTree(int n, int[][] edges) {
        // 图中的边数等于节点数减1才能构成树
        if (edges.length != n - 1) {
            return false;
        }

        DSU dsu = new DSU(n);

        // 遍历每条边，进行合并操作
        for (int[] edge: edges) {
            int x = edge[0];
            int y = edge[1];
            if (dsu.find(x) == dsu.find(y)) {
                return false; // 出现环路，不是有效的树
            }
            dsu.union(x, y);
        }
        return true;
    }

}

class DSU{
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
