class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] target = new int[128];
        for (char c : t.toCharArray()) {
            target[c]++;
        }

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int startIndex = 0;
        int requiredCount = t.length();

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);

            if (target[rightChar] > 0) {
                requiredCount--;
            }
            target[rightChar]--;

            while (requiredCount == 0) {
                int currentLen = right - left + 1;
                if (currentLen < minLen) {
                    minLen = currentLen;
                    startIndex = left;
                }

                char leftChar = s.charAt(left);
                target[leftChar]++;

                if (target[leftChar] > 0) {
                    requiredCount++;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLen);
    }
}