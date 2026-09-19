import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class AllOne {

    private static class Node {
        int count;
        Set<String> keys;
        Node prev;
        Node next;

        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    private final Node head;
    private final Node tail;
    private final Map<String, Node> keyToNode;

    public AllOne() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        keyToNode = new HashMap<>();
    }

    public void inc(String key) {
        if (!keyToNode.containsKey(key)) {
            if (head.next == tail || head.next.count > 1) {
                insertNodeAfter(new Node(1), head);
            }
            head.next.keys.add(key);
            keyToNode.put(key, head.next);
        } else {
            Node curr = keyToNode.get(key);
            int nextCount = curr.count + 1;

            if (curr.next == tail || curr.next.count > nextCount) {
                insertNodeAfter(new Node(nextCount), curr);
            }

            Node nextNode = curr.next;
            nextNode.keys.add(key);
            keyToNode.put(key, nextNode);

            curr.keys.remove(key);
            if (curr.keys.isEmpty()) {
                removeNode(curr);
            }
        }
    }

    public void dec(String key) {
        Node curr = keyToNode.get(key);
        int prevCount = curr.count - 1;

        curr.keys.remove(key);

        if (prevCount == 0) {
            keyToNode.remove(key);
        } else {
            if (curr.prev == head || curr.prev.count < prevCount) {
                insertNodeAfter(new Node(prevCount), curr.prev);
            }
            Node prevNode = curr.prev;
            prevNode.keys.add(key);
            keyToNode.put(key, prevNode);
        }

        if (curr.keys.isEmpty()) {
            removeNode(curr);
        }
    }

    public String getMaxKey() {
        if (tail.prev == head) {
            return "";
        }
        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        if (head.next == tail) {
            return "";
        }
        return head.next.keys.iterator().next();
    }

    private void insertNodeAfter(Node newNode, Node prevNode) {
        newNode.next = prevNode.next;
        newNode.prev = prevNode;
        prevNode.next.prev = newNode;
        prevNode.next = newNode;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}