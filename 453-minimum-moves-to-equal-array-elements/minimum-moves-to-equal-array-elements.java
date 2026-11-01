class Solution {
    public int minMoves(int[] nums) {
        int minVal = nums[0];
        for (int num : nums) {
            if (num < minVal) {
                minVal = num;
            }
        }
        
        long moves = 0;
        for (int num : nums) {
            moves += (num - minVal);
        }
        
        return (int) moves;
    }
}