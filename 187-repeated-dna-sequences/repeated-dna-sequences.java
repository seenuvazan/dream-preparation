import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() <= 10) {
            return new ArrayList<>();
        }

        // Map characters to 2-bit values
        int[] charMap = new int[26];
        charMap['A' - 'A'] = 0; // 00
        charMap['C' - 'A'] = 1; // 01
        charMap['G' - 'A'] = 2; // 10
        charMap['T' - 'A'] = 3; // 11

        Set<Integer> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        int bitmask = (1 << 20) - 1; // 20 ones: 0xFFFFF
        int hash = 0;

        // Compute hash for the first 10 characters
        for (int i = 0; i < 10; i++) {
            hash = (hash << 2) | charMap[s.charAt(i) - 'A'];
        }
        seen.add(hash);

        // Slide the 10-char window across the string
        for (int i = 10; i < s.length(); i++) {
            hash = ((hash << 2) & bitmask) | charMap[s.charAt(i) - 'A'];
            if (!seen.add(hash)) {
                repeated.add(s.substring(i - 9, i + 1));
            }
        }

        return new ArrayList<>(repeated);
    }
}