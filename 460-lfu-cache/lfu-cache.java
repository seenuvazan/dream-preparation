import java.util.HashMap;
import java.util.Map;

class LFUCache {
    private static class Node {
        int key, val, freq;
        Node prev, next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    private static class DoublyLinkedList {
        Node head, tail;
        int size;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast() {
            if (size > 0) {
                Node lruNode = tail.prev;
                remove(lruNode);
                return lruNode;
            }
            return null;
        }
    }

    private final int capacity;
    private int minFreq;
    private final Map<Integer, Node> cache;
    private final Map<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        updateFreq(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        Node node = cache.get(key);
        if (node != null) {
            node.val = value;
            updateFreq(node);
            return;
        }

        // Evict LFU (and LRU on tie) if at capacity
        if (cache.size() >= capacity) {
            DoublyLinkedList minList = freqMap.get(minFreq);
            Node toRemove = minList.removeLast();
            cache.remove(toRemove.key);
        }

        // Insert new node
        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        minFreq = 1;
        freqMap.computeIfAbsent(1, k -> new DoublyLinkedList()).addFirst(newNode);
    }

    private void updateFreq(Node node) {
        int curFreq = node.freq;
        DoublyLinkedList curList = freqMap.get(curFreq);
        curList.remove(node);

        // If current frequency list is empty and was the minFreq, increment minFreq
        if (curFreq == minFreq && curList.size == 0) {
            minFreq++;
        }

        node.freq++;
        freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).addFirst(node);
    }
}