import java.util.BitSet;

public class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // dp[j] stores reachable XOR sums at column j
        BitSet[] dp = new BitSet[n];
        for (int j = 0; j < n; j++) {
            dp[j] = new BitSet(1024);
        }

        // Base case: top-left cell
        dp[0].set(grid[0][0]);

        // Initialize the first row
        for (int j = 1; j < n; j++) {
            int val = grid[0][j];
            for (int k = dp[j - 1].nextSetBit(0); k >= 0; k = dp[j - 1].nextSetBit(k + 1)) {
                dp[j].set(k ^ val);
            }
        }

        // Process remaining rows
        for (int i = 1; i < m; i++) {
            // Update first column (can only come from directly above)
            int val0 = grid[i][0];
            BitSet nextCol0 = new BitSet(1024);
            for (int k = dp[0].nextSetBit(0); k >= 0; k = dp[0].nextSetBit(k + 1)) {
                nextCol0.set(k ^ val0);
            }
            dp[0] = nextCol0;

            // Update the rest of the columns
            for (int j = 1; j < n; j++) {
                int val = grid[i][j];

                // Union reachable values from top cell dp[j] and left cell dp[j - 1]
                BitSet combined = (BitSet) dp[j].clone();
                combined.or(dp[j - 1]);

                BitSet current = new BitSet(1024);
                for (int k = combined.nextSetBit(0); k >= 0; k = combined.nextSetBit(k + 1)) {
                    current.set(k ^ val);
                }
                dp[j] = current;
            }
        }

        // Find the smallest set bit at destination dp[n - 1]
        return dp[n - 1].nextSetBit(0);
    }
}