class Solution {
    // 06/10-2023 Directed graph - Topological sorting using BFS
    public String alienOrder(String[] words) {
        int[] indegree = new int[26];
        Map<Character, Set<Character>> graph = new HashMap<>();

        buildGraph(words, graph, indegree);

        return bfs(graph, indegree);
    }

    private void buildGraph(String[] words, Map<Character, Set<Character>> graph, int[] indegree) {
        // ["wrt","wrf","er","ett","rftt"]
        // t -> f
        // w -> e
        // r -> t 
        // e -> r 

        // Initial
        for (String word: words) {
            for (char ch: word.toCharArray()) {
                graph.putIfAbsent(ch, new HashSet<>());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];

            // corner case ["abd", "ab"]
            if (first.length() > second.length() && first.startsWith(second)) {
                graph.clear();
                return;
            }

            int minLen = Math.min(first.length(), second.length());

            for (int j = 0; j < minLen; j++) {
                char out = first.charAt(j);
                char in = second.charAt(j);

                if (out != in) {
                    // Notice: add if not contain in set
                    if (!graph.get(out).contains(in)) {
                        // Add the edge to the graph
                        graph.get(out).add(in);
                        indegree[in - 'a']++;
                    }
                    // find one, then skip
                    break;
                }
            }
        }
    }

    private String bfs(Map<Character, Set<Character>> graph, int[] indegree) {
        StringBuilder sb = new StringBuilder();

        System.out.print(graph.size());

        Queue<Character> queue = new LinkedList<>();

        for (char out : graph.keySet()) {
            if (indegree[out - 'a'] == 0) {
                queue.offer(out);
                sb.append(out);
            }
        }

        while (!queue.isEmpty()) {
            char out = queue.poll();
            // notice: skip some single character
            if (graph.get(out) == null || graph.get(out).size() == 0) {
                continue;
            }

            // t -> f
            // w -> e
            // r -> t 
            // e -> r 

            for (char in: graph.get(out)) {
                indegree[in - 'a']--;
                if (indegree[in - 'a'] == 0) {
                    queue.offer(in);
                    sb.append(in);
                }
            }
        }
        // Invalid case: graph contains a cycle
        return graph.size() == sb.length() ? sb.toString() : "";

    }
}
