/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // 06/02-2023 
    // Recursion
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reversed = reverseList(head.next);
        // build head <- head.next
        head.next.next = head;
        // cut: head -> head.next;
        head.next = null;

        return reversed;
    }
    // Iterative
    public ListNode reverseList1(ListNode head) {
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
