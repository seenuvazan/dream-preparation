class Solution {
    public int reverseDegree(String s) {
        int total = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            int reversedAlphabetVal = 26 - (c - 'a');
            int stringIndex = i + 1; // 1-indexed
            
            total += reversedAlphabetVal * stringIndex;
        }
        
        return total;
    }
}