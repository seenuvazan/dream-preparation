import java.util.Arrays;

class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] copy = nums.clone();
        Arrays.sort(copy);

        int right = n - 1;
        for (int i = 1; i < n; i += 2) {
            nums[i] = copy[right--];
        }
        for (int i = 0; i < n; i += 2) {
            nums[i] = copy[right--];
        }
    }
}