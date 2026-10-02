import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int openCount, int closeCount, int max) {

        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        if (openCount < max) {
            current.append('(');
            backtrack(result, current, openCount + 1, closeCount, max);
            current.deleteCharAt(current.length() - 1); 
        }

        if (closeCount < openCount) {
            current.append(')');
            backtrack(result, current, openCount, closeCount + 1, max);
            current.deleteCharAt(current.length() - 1); 
        }
    }
}