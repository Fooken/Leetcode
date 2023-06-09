class Solution {
    // Topological Sort - BFS
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        for (int[] pre: prerequisites) {
            // pre[0] <- pre[1]
            indegree[pre[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            // 入度为零为必修课
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;
            for (int[] pre: prerequisites) {
                if (pre[1] == cur) {
                    indegree[pre[0]]--;
                    if (indegree[pre[0]] == 0) {
                        queue.offer(pre[0]);
                    }
                }
            }
        }
        return count == numCourses;
    }
    // DFS with HashMap
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph1 = new ArrayList<>();
        //initial
        for (int i = 0; i < numCourses; i++) {
            graph1.add(new ArrayList<>());
        }

        //build graph
        for (int[] pre: prerequisites) {
            // pre[0] <- pre[1]
            graph1.get(pre[1]).add(pre[0]);
        }

        HashMap<Integer, Boolean> visited = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            if (isCycle(graph1, i, visited)) {
                return false;
            }
        }
        return true;

    }

    private boolean isCycle(List<List<Integer>> graph, int start, HashMap<Integer, Boolean> visited) {
        if (visited.containsKey(start)) {
            return visited.get(start);
        }

        visited.put(start, true);

        for (int neigh: graph.get(start)) {
            if (isCycle(graph, neigh, visited)) {
                return true;
            }
        }
        visited.put(start, false);
        return false;
    }
    
    // DFS with double Array
    
    private List<List<Integer>> graph2;
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        graph2 = new ArrayList<>();
        // build graph
        for (int i = 0; i < numCourses; i++) {
            graph2.add(i, new ArrayList<>());
        }
        for (int[] pre: prerequisites) {
            graph2.get(pre[1]).add(pre[0]);
        }
        
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, recStack)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isCyclicUtil(int cur, boolean[] visited, boolean[] recStack) {
        if (recStack[cur]) return true;
        if (visited[cur]) return false;
        recStack[cur] = true;
        visited[cur] = true;
        for (int next_node: graph2.get(cur)) {
            if (isCyclicUtil(next_node, visited, recStack)) {
                return true;
            }
        }
        recStack[cur] = false;
        return false;
    }
    // DFS三色法拓扑，也就是DFS版本的拓扑
    
    private List<List<Integer>> graph;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        graph = new ArrayList<>();
        // build graph
        for (int i = 0; i < numCourses; i++) {
            graph.add(i, new ArrayList<>());
        }
        for (int[] pre: prerequisites) {
            graph.get(pre[1]).add(pre[0]);
        }
        
        // states: 0 = unknown; 1 = visiting; 2 = visited
        int[] visited = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            if (isCyclicUtil(i, visited)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isCyclicUtil(int cur, int[] visited) {
        if (visited[cur] == 1) return true;
        if (visited[cur] == 2) return false;
        
        visited[cur] = 1;
        for (int neigh: graph.get(cur)) {
            if (isCyclicUtil(neigh, visited)) {
                return true;
            }
        }
        visited[cur] = 2;
        return false;
    }
}
