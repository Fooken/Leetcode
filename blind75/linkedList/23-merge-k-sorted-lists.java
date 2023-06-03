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
    // V1: Divide & Conquer O(nLogK)
    /**
    知道lists的长度， 我们可以将lists分为两等份； 
    每次分别将 (list[i], list[i + k]) 两两merge，重复最后都merge到lists[0]处；
     */
    

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int n = lists.length;

        while (n > 1) {
            int k = (n + 1) / 2;

            for (int i = 0; i < n / 2; i++) {
                lists[i] = mergeTwoList(lists[i], lists[i + k]);
            }
            n = k;
        }
        return lists[0];
    }
    
    private ListNode mergeTwoList(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
            
        }
        cur.next = list1 == null ? list2 : list1;
        
        return dummy.next;
    }
    // V2: minHeap, PriorityQueue 
    
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
        
        for (ListNode list: lists) {
            if (list != null) {
                pq.offer(list);
            }
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            pre.next = cur;
            pre = pre.next;
            if (cur.next != null) {
                pq.offer(cur.next);
            }
        }
        
        return dummy.next;
    }
}
