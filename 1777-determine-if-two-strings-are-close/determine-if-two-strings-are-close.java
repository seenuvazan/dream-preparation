import java.util.Arrays;

class Solution {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        for (int i = 0; i < word1.length(); i++) {
            count1[word1.charAt(i) - 'a']++;
            count2[word2.charAt(i) - 'a']++;
        }

        // Condition 2: Both strings must have the exact same set of characters
        for (int i = 0; i < 26; i++) {
            if ((count1[i] == 0 && count2[i] > 0) || (count1[i] > 0 && count2[i] == 0)) {
                return false;
            }
        }

        // Condition 3: Frequencies must match when sorted
        Arrays.sort(count1);
        Arrays.sort(count2);

        return Arrays.equals(count1, count2);
    }
}