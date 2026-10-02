class MyHashMap {
    private static class Node {
        int key;
        int val;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private static final int BASE = 769; 
    private final Node[] buckets;

    public MyHashMap() {
        buckets = new Node[BASE];
        for (int i = 0; i < BASE; i++) {

            buckets[i] = new Node(-1, -1);
        }
    }

    private int hash(int key) {
        return key % BASE;
    }

    public void put(int key, int value) {
        int index = hash(key);
        Node prev = findPrev(buckets[index], key);

        if (prev.next == null) {
            prev.next = new Node(key, value);
        } else {
            prev.next.val = value;
        }
    }

    public int get(int key) {
        int index = hash(key);
        Node prev = findPrev(buckets[index], key);

        if (prev.next == null) {
            return -1;
        }
        return prev.next.val;
    }

    public void remove(int key) {
        int index = hash(key);
        Node prev = findPrev(buckets[index], key);

        if (prev.next != null) {
            
            prev.next = prev.next.next;
        }
    }

    private Node findPrev(Node head, int key) {
        Node curr = head;
        while (curr.next != null && curr.next.key != key) {
            curr = curr.next;
        }
        return curr;
    }
}