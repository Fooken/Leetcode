class Solution {
    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        while (b != 0) {
            // Calculate the carry using bitwise AND and left shift
            int carry = (a & b) << 1; 
            // Calculate the sum without considering the carry
            a = a ^ b;
            // Assign the carry to b for the next iteration
            b = carry;
        }
        return a;
    }
}
