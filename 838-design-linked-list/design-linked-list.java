class MyLinkedList {

    private static class Node {
        int val;
        Node prev;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    private final Node head;
    private final Node tail;
    private int size;

    public MyLinkedList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        Node curr = getNode(index);
        return curr.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }

        // Find the node currently at 'index' (or 'tail' if index == size)
        Node succ = (index == size) ? tail : getNode(index);
        Node pred = succ.prev;

        Node newNode = new Node(val);
        newNode.prev = pred;
        newNode.next = succ;
        pred.next = newNode;
        succ.prev = newNode;

        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        Node toDelete = getNode(index);
        Node pred = toDelete.prev;
        Node succ = toDelete.next;

        pred.next = succ;
        succ.prev = pred;

        size--;
    }

    // Helper to find the node at a given valid 0-based index
    private Node getNode(int index) {
        // Optimize traversal direction based on index position
        if (index < size / 2) {
            Node curr = head.next;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            return curr;
        } else {
            Node curr = tail.prev;
            for (int i = size - 1; i > index; i--) {
                curr = curr.prev;
            }
            return curr;
        }
    }
}