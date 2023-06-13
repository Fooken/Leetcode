public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {

        int reverseBits = 0;
        for (int i = 0; i < 32; i++) {
            // Left shift by 1 position
            reverseBits <<= 1;
            if ((n & 1) == 1) {
                reverseBits |= 1; // Set the least significant bit to 1
            }
            n >>= 1; // Right shift by 1 position
        }
        return reverseBits;
    }
}
