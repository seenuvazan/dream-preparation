class Solution {
    public int subsetXORSum(int[] nums) {
        int bitwiseOr = 0;
        for (int num : nums) {
            bitwiseOr |= num;
        }
        return bitwiseOr << (nums.length - 1);
    }
}