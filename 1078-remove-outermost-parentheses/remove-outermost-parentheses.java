class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If depth > 0, this '(' is not the outermost one
                if (depth > 0) {
                    result.append(c);
                }
                depth++;
            } else { // c == ')'
                depth--;
                // If depth > 0 after decrementing, this ')' is not the outermost one
                if (depth > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}