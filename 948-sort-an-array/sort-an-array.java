class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(nums, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;

            siftDown(nums, i, 0);
        }

        return nums;
    }

    private void siftDown(int[] nums, int n, int i) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && nums[left] > nums[largest]) {
                largest = left;
            }

            if (right < n && nums[right] > nums[largest]) {
                largest = right;
            }

            if (largest != i) {
                int swap = nums[i];
                nums[i] = nums[largest];
                nums[largest] = swap;

                i = largest; 
            } else {
                break;
            }
        }
    }
}