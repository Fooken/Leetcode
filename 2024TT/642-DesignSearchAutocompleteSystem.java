import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "iroman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        
        AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
        
        System.out.println(obj.input('i')); // ["i love you", "island", "i love leetcode"]
        System.out.println(obj.input(' ')); // ["i love you", "i love leetcode"]
        System.out.println(obj.input('a')); // []
        System.out.println(obj.input('#')); // []
        
        System.out.println(obj.input('i')); // ["i love you", "island", "i love leetcode"]
        System.out.println(obj.input(' ')); // ["i love you", "i love leetcode", "i a"]
        System.out.println(obj.input('a')); // ["i a"]
        System.out.println(obj.input('#')); // []
        
        System.out.println(obj.input('i')); // ["i love you", "island", "i love leetcode"]
        System.out.println(obj.input(' ')); // [i love you, i a, i love leetcode]
        System.out.println(obj.input('a')); // ["i a"]
        System.out.println(obj.input('#')); // []
    }
}


class AutocompleteSystem {
    // Trie data structure combined with a priority queue 
    // Time Complexity:
    //. Constructor: O(k * L), where k is the number of sentences and L is the average length of sentences.
    //. Input: O(p + q * log(3)), where p is the length of the prefix and q is the number of sentences with the prefix.
    
    TrieNode root;
    String prefix;

    public AutocompleteSystem(String[] sentences, int[] times) {
        // initialize the root of this trie and an empty prefix string.
        root = new TrieNode();
        prefix = "";
        
        // add all initial sentences to the trie with their counts
        for (int i = 0; i < sentences.length; i++) {
            addSentence(sentences[i], times[i]);
        }
    }
    
    // add method: adds a sentence to the Trie and update counts at each node along the path
    private void addSentence(String s, int count) {
        TrieNode node = root;
        for (char c: s.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.counts.put(s, node.counts.getOrDefault(s, 0) + count);
        }
    }
    
    /**
     * The input method:
     *   - If the input is '#', we add the current prefix as a complete sentence and reset the prefix.
     *   - Otherwise, we append the character to the prefix and traverse the Trie.
     */
    public List<String> input(char c) {
        if (c == '#') {
            addSentence(prefix, 1);
            prefix = "";
            return new ArrayList<>();
        }
        
        prefix += c;
        
        TrieNode node = root;
        for (char ch: prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return new ArrayList<>();
            }
            node = node.children.get(ch);
        }
        
        // We use a priority queue to keep track of the top 3 sentences 
        // (sorted by count and then lexicographically).
        
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> 
            a.c == b.c ? b.s.compareTo(a.s) : a.c - b.c);
        
        for (String s: node.counts.keySet()) {
            minHeap.offer(new Pair(s, node.counts.get(s)));
            if (minHeap.size() > 3) {
                minHeap.poll();
            }
        }
        
        // We return the top 3 sentences as the autocomplete suggestions.
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(0, minHeap.poll().s);
        }
        
        return result;
    }
    
    
    /**
     * We define a TrieNode class to represent nodes in our Trie. Each node has:
     *  - A map of children nodes
     *  - A map of sentence counts for sentences that pass through this node
     */
    class TrieNode {
        Map<Character, TrieNode> children;
        Map<String, Integer> counts; 
        public TrieNode() {
            children = new HashMap<>();
            counts = new HashMap<>();
        }
    }
    
    // We also define a Pair class to store sentences and their counts for sorting.
    class Pair {
        String s;
        int c;
        public Pair(String s, int c) {
            this.s = s;
            this.c = c;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */