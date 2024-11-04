import java.util.*;

public class Main {
    public static void main(String[] args) {

        Solution sl = new Solution();


        // Test case 1
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("Test case 1: " + sl.minDistance(word1, word2));
        // Expected output: 3

        // Test case 2
        word1 = "intention";
        word2 = "execution";
        System.out.println("Test case 2: " + sl.minDistance(word1, word2));
        // Expected output: 5

    }
}

class Solution {
    // 20241103 DynamicProgramming, required to covert word1 to word2
    // insert: dp[i][j - 1]; // operated in word1
    // delete: dp[i - 1][j]; // operated in word1
    // replace: dp[i - 1][j - 1];
    
    /**
     * We create a 2D DP table where dp[i][j] represents the minimum number of operations 
     * to convert the first i characters of word1 to the first j characters of word2.
    
     * Insert operation: dp[i][j-1]
     *  1. When we insert a character, we're adding it to word1 to match word2.
     *  2. After insertion, we've matched one more character of word2 (the j-th character), but we haven't used any more characters from word1.
     *  3. So, we look at the subproblem where we've used all i characters of word1, but only j-1 characters of word2.
     *  4. That's why we use dp[i][j-1] for insertion.
     * 
     * Delete operation: dp[i-1][j]
     *  1. When we delete a character, we're removing it from word1 to try to match word2.
     *  2. After deletion, we've used one less character of word1 (we've "skipped" it), but we still need to match all j characters of word2.
     *  3. So, we look at the subproblem where we've used i-1 characters of word1, but still need to match all j characters of word2.
     *  4. That's why we use dp[i-1][j] for deletion.
     * 
     * 
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // create a DP table
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize first row:
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        // Initialize first column:
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; 
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], //Replace
                                            Math.min(dp[i][j - 1], // Insert
                                                     dp[i - 1][j])); // Delete
                }
            }
        }
        
        return dp[m][n];
    }
    
    // Top down
    Integer memo[][];

    public int minDistance1(String word1, String word2) {

        memo = new Integer[word1.length() + 1][word2.length() + 1];
        return minDistanceRecur(word1, word2, word1.length(), word2.length());
    }

    int minDistanceRecur(String word1, String word2, int word1Index, int word2Index) {
        if (word1Index == 0) {
            return word2Index;
        }
        if (word2Index == 0) {
            return word1Index;
        }
        if (memo[word1Index][word2Index] != null) {
            return memo[word1Index][word2Index];
        }
        int minEditDistance = 0;
        if (word1.charAt(word1Index - 1) == word2.charAt(word2Index - 1)) {
            minEditDistance = minDistanceRecur(word1, word2, word1Index - 1, word2Index - 1);
        } else {
            int insertOperation = minDistanceRecur(word1, word2, word1Index, word2Index - 1);
            int deleteOperation = minDistanceRecur(word1, word2, word1Index - 1, word2Index);
            int replaceOperation = minDistanceRecur(word1, word2, word1Index - 1, word2Index - 1);
            minEditDistance = Math.min(insertOperation, Math.min(deleteOperation, replaceOperation)) + 1;
        }
        memo[word1Index][word2Index] = minEditDistance;
        return minEditDistance;
    }
}