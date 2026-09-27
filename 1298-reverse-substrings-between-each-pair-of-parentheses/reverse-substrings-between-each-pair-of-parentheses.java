import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String reverseParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == ')') {
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();

                for (int i = 0; i < sb.length(); i++) {
                    stack.push(sb.charAt(i));
                }
            } else {
                stack.push(c);
            }
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.removeLast());
        }
        return result.toString();
    }
}