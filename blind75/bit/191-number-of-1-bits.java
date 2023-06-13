public class Solution {
    // you need to treat n as an unsigned value
    // you need to treat n as an unsigned value
    // 此题 有两个技巧，解决这个问题
    // V1. 用一个数  a & 1 判断a的末尾是不是1；
    public int hammingWeight1(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += n & 1;
            n = n >> 1;
        }
        return res;
    }
    // V2: 用 n & (n - 1) 可以消除 n 最右边的1
    // Taking the bitwise AND of 'n' and 'n - 1' results in a number 
    // where the rightmost set bit in 'n' is cleared (set to 0). 
    // All the other bits remain unchanged. 
    // This operation effectively removes the rightmost set bit from 'n'.
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= n - 1;
            res++;
        }

        return res;
    }
}
