class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[n];

        int currDay = 0;
        while (currDay < n) {
            int currentTemp = temperatures[currDay];
            // pop until the current day's temperature is not warmer 
            // than the temperature at the top of the stack
            while (!stack.isEmpty() && temperatures[stack.peek()] < currentTemp) {
                int prev = stack.pop();
                res[prev] = currDay - prev;
            }
            stack.push(currDay);
            currDay++;
        }
        return res;
        
    }
}
