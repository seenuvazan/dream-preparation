/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Node curr = head;

        while (curr != null) {
            // Case 1: No child, just move forward
            if (curr.child == null) {
                curr = curr.next;
                continue;
            }

            // Case 2: Node has a child list
            Node child = curr.child;

            // Find the tail of the child sub-list
            Node childTail = child;
            while (childTail.next != null) {
                childTail = childTail.next;
            }

            // Splice the child list between curr and curr.next
            childTail.next = curr.next;
            if (curr.next != null) {
                curr.next.prev = childTail;
            }

            curr.next = child;
            child.prev = curr;

            // Reset child pointer to null
            curr.child = null;

            // Advance curr
            curr = curr.next;
        }

        return head;
    }
}