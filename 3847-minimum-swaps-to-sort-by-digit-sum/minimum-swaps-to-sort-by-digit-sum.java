import java.util.*;

class Solution {

    // Helper class to store value, digit sum, and original index
    static class Element {
        int val;
        int digitSum;
        int originalIdx;

        Element(int val, int originalIdx) {
            this.val = val;
            this.originalIdx = originalIdx;
            this.digitSum = computeDigitSum(val);
        }

        private int computeDigitSum(int n) {
            int sum = 0;
            while (n > 0) {
                sum += n % 10;
                n /= 10;
            }
            return sum;
        }
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        Element[] arr = new Element[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Element(nums[i], i);
        }

        // Sort by digitSum asc, then by value asc
        Arrays.sort(arr, (a, b) -> {
            if (a.digitSum != b.digitSum) {
                return Integer.compare(a.digitSum, b.digitSum);
            }
            return Integer.compare(a.val, b.val);
        });

        // targetPos[i] indicates the index in sorted order where nums[i] should go
        // arr[i].originalIdx represents where the element currently at sorted position i came from
        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            // If already visited or already in the correct sorted spot
            if (visited[i] || arr[i].originalIdx == i) {
                continue;
            }

            // Find the size of the permutation cycle
            int cycleSize = 0;
            int curr = i;

            while (!visited[curr]) {
                visited[curr] = true;
                curr = arr[curr].originalIdx;
                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }
}