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
    // V1: Stack 
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        int cnt = stack.size() / 2; 

        // reset cur
        cur = head;
        while (cnt-- > 0) {
            ListNode new_node = stack.pop();
            ListNode cur_next = cur.next;
            // head -> new_node
            cur.next = new_node;
            // new_node -> cur_next;
            new_node.next = cur_next;
            // cur = cur_next
            cur = cur_next;
        }
        cur.next = null;
    }
    // V2: 三部曲：T:O(N) S:O(1)
    // 1. 分开两半 -> 快慢指针
    // 2. 反转后半段 -> Reverse list
    // 3. merge在一起 -> Merge list
    public void reorderList1(ListNode head) {
        if (head == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head.next; // 如果中间有两个element， 取后者

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        // 断开后半段
        slow.next = null;

        // 反转后半段 
        ListNode second = reverse(mid);

        // 交替 merge 两个list
        while (second != null) {
            ListNode head_next = head.next;
            head.next = second;
            head = head_next;

            ListNode second_next = second.next;
            second.next = head;
            second = second_next;
        }
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reversed = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return reversed;
    }
}
