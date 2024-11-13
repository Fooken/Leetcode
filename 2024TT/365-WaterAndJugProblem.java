import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();
        // Test case 1:
        int x1 = 3, y1 = 5;
        int target1 = 4;

        System.out.println("Test 1: " + sl.canMeasureWater(x1, y1, target1));

        // Input:  x = 2, y = 6, target = 5 
        int x2 = 2, y2 = 6;
        int target2 = 5;

        System.out.println("Test 1: " + sl.canMeasureWater(x2, y2, target2));
    }
}

class Solution {
    // Bézout's identity
    public boolean canMeasureWater (int x, int y, int target) {
        if (x + y < target) {
            return false;
        }

        if (target == 0 || target == x || target == y || target == x + y) {
            return true;
        }

        int gcd = findGCD(x, y);

        return target % gcd == 0;
    }

    private int findGCD(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return findGCD(y, x % y);
        }
    }
}