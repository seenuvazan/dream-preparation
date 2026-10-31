import java.util.Arrays;

public class Solution {
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // memo[r][c][d] stores the packed landing coordinate (r' * n + c')
        // d = 0: moving Right, d = 1: moving Down
        // -1 represents uncomputed, -2 represents out of bounds.
        int[][][] memo = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;

        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if ((r == m - 1 && c == n - 1) || grid[r][c] == 1) {
                    continue;
                }

                long totalPaths = 0;

                // Move Right -> enters (r, c + 1) with direction Right (d = 0)
                int rightDest = trace(r, c + 1, 0, grid, memo, m, n);
                if (rightDest != -2) {
                    int destR = rightDest / n;
                    int destC = rightDest % n;
                    totalPaths = (totalPaths + dp[destR][destC]) % MOD;
                }

                // Move Down -> enters (r + 1, c) with direction Down (d = 1)
                int downDest = trace(r + 1, c, 1, grid, memo, m, n);
                if (downDest != -2) {
                    int destR = downDest / n;
                    int destC = downDest % n;
                    totalPaths = (totalPaths + dp[destR][destC]) % MOD;
                }

                dp[r][c] = (int) totalPaths;
            }
        }

        return dp[0][0];
    }

    private int trace(int r, int c, int d, int[][] grid, int[][][] memo, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return -2;
        }

        if (grid[r][c] == 0) {
            return r * n + c;
        }

        if (memo[r][c][d] != -1) {
            return memo[r][c][d];
        }

        int destination;
        if (d == 0) {
            destination = trace(r + 1, c, 1, grid, memo, m, n);
        } else {
            destination = trace(r, c + 1, 0, grid, memo, m, n);
        }

        memo[r][c][d] = destination;
        return destination;
    }
}