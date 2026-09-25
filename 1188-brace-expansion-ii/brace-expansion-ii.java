import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        int[] index = new int[]{0};
        Set<String> resultSet = parseUnion(expression, index);
        
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

    // Handles comma-separated items: e1, e2, ...
    private Set<String> parseUnion(String s, int[] idx) {
        Set<String> res = new HashSet<>();
        while (idx[0] < s.length()) {
            res.addAll(parseConcat(s, idx));
            if (idx[0] < s.length() && s.charAt(idx[0]) == ',') {
                idx[0]++; // Consume ','
            } else {
                break;
            }
        }
        return res;
    }

    // Handles adjacent factors concatenated together: f1 f2 ...
    private Set<String> parseConcat(String s, int[] idx) {
        Set<String> res = new HashSet<>();
        res.add(""); // Identity element for concatenation

        while (idx[0] < s.length() && s.charAt(idx[0]) != ',' && s.charAt(idx[0]) != '}') {
            Set<String> nextSet = parseFactor(s, idx);
            Set<String> combined = new HashSet<>();
            for (String a : res) {
                for (String b : nextSet) {
                    combined.add(a + b);
                }
            }
            res = combined;
        }
        return res;
    }

    // Handles a single character or a brace block: { ... }
    private Set<String> parseFactor(String s, int[] idx) {
        Set<String> res = new HashSet<>();
        char c = s.charAt(idx[0]);

        if (c == '{') {
            idx[0]++; // Consume '{'
            res = parseUnion(s, idx);
            idx[0]++; // Consume '}'
        } else {
            res.add(String.valueOf(c));
            idx[0]++; // Consume letter
        }
        return res;
    }
}