import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        char[][] board1 = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        String[] words1 = {"oath","pea","eat","rain"};
        System.out.println("Test case 1: " + solution.findWords(board1, words1));
        // Expected output: [oath, eat]

        // Test case 2
        char[][] board2 = {
            {'a','b'},
            {'c','d'}
        };
        String[] words2 = {"abcb"};
        System.out.println("Test case 2: " + solution.findWords(board2, words2));
        // Expected output: []

        // Test case 3
        char[][] board3 = {
            {'o','a','b','n'},
            {'o','t','a','e'},
            {'a','h','k','r'},
            {'a','f','l','v'}
        };
        String[] words3 = {"oa","oaa"};
        System.out.println("Test case 3: " + solution.findWords(board3, words3));
        // Expected output: [oa, oaa]
    }
}

class Solution {
    // Trie Tree With BackTracking
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode trieTree = buildTrieTree(words);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (trieTree.children[board[i][j] - 'a'] != null) {
                    backtracking(board, trieTree, i, j, res);
                }
            }
        }
        return res;
    }
    
    private TrieNode buildTrieTree(String[] words) {
        TrieNode root = new TrieNode();
        for (String word: words) {
            TrieNode node = root;
            for (char c: word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.word = word;
        }
        
        return root;
    }
    
    private void backtracking(char[][] board, TrieNode tree, int row, int col, List<String> res) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == '#') {
            return;
        }
        
        char c = board[row][col];
        
        TrieNode node = tree.children[c - 'a'];
        if (node == null) {
            return;
        }
        if (node.word != null) {
            res.add(node.word);
            node.word = null; // de-duplicate word
        }
        
        
        board[row][col] = '#'; // visiting
        int[][] dire = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int[] dir: dire) {
            int x = dir[0] + row;
            int y = dir[1] + col;
            backtracking(board, node, x, y, res);
        }
        
        board[row][col] = c;
    }
}

class TrieNode {
    TrieNode[] children;
    String word;
    public TrieNode() {
        children = new TrieNode[26];
    }
}