import java.util.ArrayList;
import java.util.List;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        // Collect all divisors of n
        List<Integer> divisors = new ArrayList<>();
        for (int d = 1; d * d <= n; d++) {
            if (n % d == 0) {
                divisors.add(d);
                if (d * d != n) {
                    divisors.add(n / d);
                }
            }
        }

        int totalSum = 0;
        for (int k : divisors) {
            if (isSortable(nums, n, k)) {
                totalSum += k;
            }
        }

        return totalSum;
    }

    private boolean isSortable(int[] nums, int n, int k) {
        int numBlocks = n / k;
        int prevMax = -1;

        for (int b = 0; b < numBlocks; b++) {
            int start = b * k;
            int drops = 0;
            int blockMin = nums[start];
            int blockMax = nums[start];

            for (int i = 0; i < k; i++) {
                int curr = nums[start + i];
                int next = nums[start + (i + 1) % k];

                if (curr > next) {
                    drops++;
                    if (drops > 1) {
                        return false;
                    }
                }

                if (curr < blockMin) {
                    blockMin = curr;
                }
                if (curr > blockMax) {
                    blockMax = curr;
                }
            }

            // Ensure previous block's maximum does not exceed current block's minimum
            if (prevMax != -1 && prevMax > blockMin) {
                return false;
            }

            prevMax = blockMax;
        }

        return true;
    }
}