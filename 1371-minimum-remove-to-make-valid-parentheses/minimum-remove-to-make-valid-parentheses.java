import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String minRemoveToMakeValid(String s) {
        char[] chars = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else if (chars[i] == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    // Mark unmatched ')' for removal
                    chars[i] = '*';
                }
            }
        }

        // Any '(' remaining in the stack is unmatched
        while (!stack.isEmpty()) {
            chars[stack.pop()] = '*';
        }

        // Build the final valid string
        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            if (c != '*') {
                result.append(c);
            }
        }

        return result.toString();
    }
}