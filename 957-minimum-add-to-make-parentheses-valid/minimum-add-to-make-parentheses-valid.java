class Solution {
    public int minAddToMakeValid(String s) {
        int openBrackets = 0;
        int insertions = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openBrackets++;
            } else { // c == ')'
                if (openBrackets > 0) {
                    openBrackets--;
                } else {
                    insertions++;
                }
            }
        }

        return insertions + openBrackets;
    }
}