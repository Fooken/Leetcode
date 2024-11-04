import java.util.*;

public class Main {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        
        // Test case 1
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Median after [1, 2]: " + medianFinder.findMedian());
        // Expected: 1.5
        
        medianFinder.addNum(3);
        System.out.println("Median after [1, 2, 3]: " + medianFinder.findMedian());
        // Expected: 2.0
        
        // Test case 2
        MedianFinder medianFinder2 = new MedianFinder();
        medianFinder2.addNum(-1);
        System.out.println("Median after [-1]: " + medianFinder2.findMedian());
        // Expected: -1.0
        
        medianFinder2.addNum(-2);
        System.out.println("Median after [-1, -2]: " + medianFinder2.findMedian());
        // Expected: -1.5
        
        medianFinder2.addNum(-3);
        System.out.println("Median after [-1, -2, -3]: " + medianFinder2.findMedian());
        // Expected: -2.0
        
        medianFinder2.addNum(-4);
        System.out.println("Median after [-1, -2, -3, -4]: " + medianFinder2.findMedian());
        // Expected: -2.5
        
        medianFinder2.addNum(-5);
        System.out.println("Median after [-1, -2, -3, -4, -5]: " + medianFinder2.findMedian());
        // Expected: -3.0
    }
}

class MedianFinder {
    // 20240917
    /**
     * The max heap will store the smaller half of the numbers, and the min heap will store the larger half of the numbers.
     * Time:
     *  Constructor: O(1)
     *  addNum: O(logN)
     *  findMedian: O(1)
     * Space: O(N)
     * minHeap: A max heap to store the smaller half of the numbers.
     * maxHeap: A min heap to store the larger half of the numbers.
     */
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        return 0.5 * (maxHeap.peek() + minHeap.peek());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */