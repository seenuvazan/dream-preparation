import java.util.Arrays;

class Solution {
    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        int sum = amount[0] + amount[1] + amount[2];
        return Math.max(amount[2], (sum + 1) / 2);
    }
}