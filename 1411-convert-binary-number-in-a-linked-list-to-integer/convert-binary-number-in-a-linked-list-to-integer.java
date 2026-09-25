class Solution {
    public int getDecimalValue(ListNode head) {
        int ans = 0;
        ListNode curr = head;
        while (curr != null) {
            ans = (ans << 1) | curr.val;
            curr = curr.next;
        }
        return ans;
    }
}