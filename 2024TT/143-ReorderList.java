import  java.util.*;

/**
 * Definition for singly-linked list.
 */

public class Main {
    public static void main(String[] args) {
        Solution sl = new Solution();

        // Test Case 1:
        ListNode head1 = createList(new int[]{1,2,3,4});
        System.out.print("Test case 1 before: ");
        printList(head1);
        sl.reorderList(head1);
        System.out.print("Test case 1 after: ");
        printList(head1);
        // Expected: 1->4->2->3

    }

    private static ListNode createList(int[] nums) {
        ListNode root = new ListNode(-1);
        ListNode curr = root;

        for (int val: nums) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }

        return root.next;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }

}

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    // 20241103 ListNode prority
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        // find middle  - fast and slow pointer
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode secondList = slow.next;
        slow.next = null; // split two list
        
        // reverse - recursive 
        ListNode secondReversed = reverseList(secondList);
        
        // merge - merge then one by one
        while (secondReversed != null) {
            ListNode head_next = head.next;
            ListNode second_next = secondReversed.next;
            
            head.next = secondReversed;
            secondReversed.next = head_next;
            
            head = head_next;
            secondReversed = second_next;
        }
    }
    
    private ListNode reverseList(ListNode head) {
        ListNode reversed = null;
        
        while (head != null) {
            ListNode head_next = head.next;
            head.next = reversed;
            reversed = head;
            head = head_next;
        }
        return reversed;
    }
}
