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
    // Floyd's Cycle Finding Algorithm:
    // Two Pointer: slow and fast
    // 快慢指针初始在head， 先让fast 指针走N步， 此时我们可以清楚的知道 slow 到 fast距离为N步；】
    // 只需要此时slow和fast用相同的步调走，直到fast.next == null， slow.next 就是我们想要remove的node
    // Notice: corner case: 当fast走N步之后, fast.next 如果为null, 此时想要remove的就是head；
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(n);
        dummy.next = head;
        
        ListNode slow = head, fast = head;
        // Advances first pointer so that the gap between first and second is n nodes apart
        while(n-- > 0) {
            fast = fast.next;
        }
        // if fast = null, the n is the length of list
        if (fast == null) {
            return head.next;
        }
        
        // Move fast to the end, maintaining the gap
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // skip the n node from the left
        slow.next = slow.next.next;
        
        return dummy.next;
    }
}


