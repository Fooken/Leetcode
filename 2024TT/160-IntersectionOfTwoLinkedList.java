import java.util.*;

public class Main {
    public static void main(String[] args) {
        IntersectionOfTwoLinkedLists sl = new IntersectionOfTwoLinkedLists();

        // Test case 1: Intersection at node with value 8
        ListNode common = new ListNode(8);
        common.next = new ListNode(4);
        common.next.next = new ListNode(5);

        ListNode headA1 = new ListNode(4);
        headA1.next = new ListNode(1);
        headA1.next.next = common;
        
        ListNode headB1 = new ListNode(5);
        headB1.next = new ListNode(6);
        headB1.next.next = new ListNode(1);
        headB1.next.next.next = common;

        ListNode result1 = sl.getIntersectionNode(headA1, headB1);
        System.out.println("Test Case 1: " + (result1 != null ? result1.val : "null"));
        // Expected output: 8
        
    }
}

class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode p = headA;
        ListNode q = headB;

        while (p != q) {
            // If p reaches the end, redirect it to headB
            p = (p != null) ? p.next : headB;
             // If ptrB reaches the end, redirect it to headA
            q = (q != null) ? q.next : headA;
        }

        return p; // at this pointer, p and q either at the intersection or both null
    }
}

class ListNode {
    int val;
    ListNode next;
    public ListNode() {}
    public ListNode(int val) {
        this.val = val;
    }
}