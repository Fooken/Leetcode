import java.util.*;

/**
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, 
 * return the kth smallest element in the matrix.
 * 
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * You must find a solution with a memory complexity better than O(n2).
 */
/**
 * 
 *  [[1,5,9],  
 *  [10,11,13],
 *  [12,13,15]]
 * 
 *   k = 8;
 *   Output: 13;
 */
// Binary search Nlog(max-min)
public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();

        // Test case 1:
        int[][] matrix1 = {{1,5,9}, {10,11,13}, {12,13,15}};
        int k1 = 8;

        System.out.println("Test case 1: " + sl.kthSmallest(matrix1, k1));

        // Test case 2:
        int[][] matrix2 = {{-5}};
        int k2 = 1;
        System.out.println("Test case 2: " + sl.kthSmallest(matrix2, k2));
    }
}

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return -1;
        }
        int n = matrix.length;

        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (countLessOrEqual(matrix, mid) < k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (countLessOrEqual(matrix, left) <= k) {
            return left;
        }
        return right;
    } 

    private int countLessOrEqual(int[][] matrix, int target) {
        int n = matrix.length;
        int row = n - 1, col = 0;
        int count = 0;

        while (row >= 0 && col < n) {
            if (matrix[row][col] < target) {
                count += row + 1; // row start index is 0;
                col++;
            } else {
                row--;
            }
        }
        return count;
    }
}