class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[31];
        for (int num : nums) {
            for (int b = 0; b <= 30; b++) {
                if (((num >> b) & 1) == 1) {
                    bitCount[b]++;
                }
            }
        }

        long MOD = 1_000_000_007L;
        long totalSum = 0;

        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int b = 0; b <= 30; b++) {
                if (bitCount[b] > 0) {
                    currentNum |= (1L << b);
                    bitCount[b]--;
                }
            }
            totalSum = (totalSum + (currentNum % MOD) * (currentNum % MOD)) % MOD;
        }

        return (int) totalSum;
    }
}