import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot","dot","dog","lot","log","cog");

        System.out.println("Test case 1: " + solution.ladderLength(beginWord1, endWord1, wordList1));
        // Expected output: 5


        // Test case 2
        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot","dot","dog","lot","log");
        System.out.println("Test case 2: " + solution.ladderLength(beginWord2, endWord2, wordList2));
        // Expected output: 0

        // Test case 3
        String beginWord3 = "a";
        String endWord3 = "c";
        List<String> wordList3 = Arrays.asList("a","b","c");
        System.out.println("Test case 3: " + solution.ladderLength(beginWord3, endWord3, wordList3));
        // Expected output: 2
    }
}

class Solution {
    // 20241104  BFS resolve Graph problem;
    // The challenge is to find the shortest transformation sequence from a start word to an end word
    // Time Complexity: O(N * M * 26), where N is the number of words in wordList, M is the length of each word, and 26 is the number of lowercase English letters. 
    // Space Complexity: O(N), for the queue and visited set.
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList); // O(1)
        
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(beginWord);
        visited.add(beginWord);
        
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            while (size-- > 0) {
                String currentWord = queue.poll();
                if (currentWord.equals(endWord)) {
                    return level;
                }
                
                for (int i = 0; i < currentWord.length(); i++) {
                    char[] charWord = currentWord.toCharArray();
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        charWord[i] = c;
                        String newWord = new String(charWord);
                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            queue.offer(newWord);
                            visited.add(newWord);
                        }
                    }
                }
            }
            
            level++;
        }
        return 0;
    }
    
    
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }

        Map<String, List<String>> graph = buildGraph(wordList);

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(beginWord);
        queue.offer(beginWord);

        int cost = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                
                if (cur.equals(endWord)) {
                    return cost;
                }

                for (String neigh: graph.getOrDefault(cur, new ArrayList<>())) {
                    if (!visited.contains(neigh)) {
                        queue.offer(neigh);
                        visited.add(neigh);
                    }
                }
            }
            cost++;
        }
        return 0;
        
    }

    private Map<String, List<String>> buildGraph(List<String> wordList) {
        Map<String, List<String>> graph = new HashMap<>();

        for (int i = 0; i < wordList.size() - 1; i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                String w1 = wordList.get(i);
                String w2 = wordList.get(j);
                if (oneWayPath(w1, w2)) {
                    // add edge for both words
                    graph.computeIfAbsent(w1, k -> new ArrayList<>()).add(w2);
                    graph.computeIfAbsent(w2, k -> new ArrayList<>()).add(w1);
                }
            }
        }
        return graph;
    }

    private boolean oneWayPath(String w1, String w2) {
        int diff = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }
}
