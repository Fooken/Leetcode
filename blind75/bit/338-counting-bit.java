class Solution {
    // DP： f[i]: 表示i的二进制表示中有几个1；
    public int[] countBits(int n) {
        int[] f = new int[n + 1];

        // initial
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            // To count the number of set bits in 'i', 
            // we can use the count of set bits in 'i / 2' and add 1 if 'i' is odd
            // f[i] = f[i / 2] + (i % 2);
            f[i] = f[i >> 1] + (i & 1); // bit manipulation

        }
        return f;
    }
}
