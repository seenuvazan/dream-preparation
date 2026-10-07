import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--;
                } else {
                    rightRem++;
                }
            }
        }

        Set<String> result = new HashSet<>();
        backtrack(s, 0, 0, leftRem, rightRem, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int openCount, int leftRem, int rightRem, StringBuilder sb, Set<String> result) {
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0 && openCount == 0) {
                result.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int len = sb.length();

        // Branch 1: Try deleting the current parenthesis
        if ((c == '(' && leftRem > 0) || (c == ')' && rightRem > 0)) {
            backtrack(s, index + 1, openCount, 
                      leftRem - (c == '(' ? 1 : 0), 
                      rightRem - (c == ')' ? 1 : 0), 
                      sb, result);
        }

        // Branch 2: Keep the current character
        sb.append(c);
        if (c != '(' && c != ')') {
            backtrack(s, index + 1, openCount, leftRem, rightRem, sb, result);
        } else if (c == '(') {
            backtrack(s, index + 1, openCount + 1, leftRem, rightRem, sb, result);
        } else if (openCount > 0) { // c == ')'
            backtrack(s, index + 1, openCount - 1, leftRem, rightRem, sb, result);
        }
        sb.setLength(len); // Backtrack
    }
}