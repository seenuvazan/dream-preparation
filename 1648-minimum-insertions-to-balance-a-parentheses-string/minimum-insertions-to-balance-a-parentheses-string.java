class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededClosing = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // If neededClosing is odd, the previous '(' only received one ')'
                // We must insert one ')' before opening a new scope
                if (neededClosing % 2 != 0) {
                    insertions++;       // Add the missing ')'
                    neededClosing--;    // Satisfy that slot
                }
                
                // Each '(' requires two ')'
                neededClosing += 2;
            } else { // c == ')'
                neededClosing--;
                
                // If neededClosing drops below 0, we have an orphan ')'
                // We insert an opening '(' which provides 2 closing slots
                // Since this current ')' takes one, 1 remains needed
                if (neededClosing < 0) {
                    insertions++;       // Insert an opening '('
                    neededClosing = 1;
                }
            }
        }
        
        // Add any remaining ')' needed to balance the remaining '('
        return insertions + neededClosing;
    }
}