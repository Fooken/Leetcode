/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    // DFS
    private Map<Node, Node> clone;
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        clone = new HashMap<>();
        return dfs(node);
    }

    private Node dfs(Node node) {
        if (clone.containsKey(node)) {
            return clone.get(node);
        }

        clone.put(node, new Node(node.val, new ArrayList<>()));

        for (Node neigh: node.neighbors) {
            clone.get(node).neighbors.add(dfs(neigh));
        }

        return clone.get(node);
    }
    // BFS
    public Node cloneGraph1(Node node) {
        if (node == null) {
            return null;
        }
        HashMap<Node, Node> clone = new HashMap<>();
        Node new_node = new Node(node.val, new ArrayList<>());
        clone.put(node, new_node);

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node neigh: cur.neighbors) {
                if (!clone.containsKey(neigh)) {
                    clone.put(neigh, new Node(neigh.val, new ArrayList<>()));
                    queue.offer(neigh);
                }
                clone.get(cur).neighbors.add(clone.get(neigh));
            }
        }

        return clone.get(node);
    }
}
