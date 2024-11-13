import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // The map is now [[1,1]]
        myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
        System.out.println(myHashMap.get(1));    // return 1
        System.out.println(myHashMap.get(3));    // return -1 (i.e., not found)
        myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
        System.out.println(myHashMap.get(2));    // return 1 
        myHashMap.remove(2); // remove the mapping for 2
        System.out.println(myHashMap.get(2));    // return -1 (i.e., not found)
    }
}

class MyHashMap {
    private static final int SIZE = 10000;
    private ListNode[] buckets;

    private class ListNode {
        int key;
        int value;
        ListNode next;

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        buckets = new ListNode[SIZE];
    }
    
    private int hash(int key) {
        return key % SIZE;
    }
    
    public void put(int key, int value) {
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new ListNode(key, value);
            return;
        }
        ListNode curr = buckets[index];
        ListNode prev = null;
        while (curr != null) {
            if (curr.key == key) {
                curr.value = value;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
        prev.next = new ListNode(key, value);
    }
    
    public int get(int key) {
        int index = hash(key);
        ListNode curr = buckets[index];
        while (curr != null) {
            if (curr.key == key) {
                return curr.value;
            }
            curr = curr.next;
        }
        return -1;
    }
    
    public void remove(int key) {
        int index = hash(key);
        if (buckets[index] == null) {
            return;
        }
        if (buckets[index].key == key) {
            buckets[index] = buckets[index].next;
            return;
        }
        ListNode curr = buckets[index];
        ListNode prev = null;
        while (curr != null) {
            if (curr.key == key) {
                prev.next = curr.next;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }
}