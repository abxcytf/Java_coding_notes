/*
146. LRU Cache
Design and implement a data structure for Least Recently Used (LRU) cache. 
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists 
in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently 
used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/

public class LRUCache {
    
    class Node {
        public int key;
        public int val;
        public Node next;
        public Node prev;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
    
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    private int capacity;
    
    public LRUCache(int capacity) {
        map = new HashMap<Integer, Node>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        
        Node current = map.get(key);
        //delete current node from original location
        current.prev.next = current.next;
        current.next.prev = current.prev;
        moveToHead(current);
        return current.val;
    }
    
    public void put(int key, int value) {
        //if existing, just undate the value
        //if (map.containsKey(key)) {
        if (get(key) != -1) {
            map.get(key).val = value;
            return;
        }
        
        //if it is already full, delete the node from the tail
        if (map.size() == this.capacity) {
            map.remove(tail.prev.key); //delete from the map
            tail = tail.prev;
            tail.next = null;
            tail.key = -1;
            tail.val = -1;
        }
        
        //then put the new added entry
        Node newNode = new Node(key, value);
        map.put(key, newNode);
        //add the newNode to head, means the most recently called
        moveToHead(newNode);
    }
    
    private void moveToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        
        head.next = node;
        node.next.prev = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
