class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Check if the string is already sorted (0 operations)
        boolean isSorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            return 0;
        }

        // Cannot sort an unsorted 2-character string without selecting the entire string
        if (n == 2) {
            return -1;
        }

        // Find the global minimum and maximum characters
        char minChar = 'z';
        char maxChar = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }

        // 1 operation if the minimum is already at index 0 or maximum is at index n - 1
        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }

        // Check if minChar appears anywhere in the prefix s[0 ... n-2]
        boolean minInPrefix = false;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == minChar) {
                minInPrefix = true;
                break;
            }
        }

        // Check if maxChar appears anywhere in the suffix s[1 ... n-1]
        boolean maxInSuffix = false;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == maxChar) {
                maxInSuffix = true;
                break;
            }
        }

        // 3 operations if max is strictly at index 0 and min is strictly at index n - 1
        if (!minInPrefix && !maxInSuffix) {
            return 3;
        }

        // Otherwise, 2 operations are always sufficient
        return 2;
    }
}