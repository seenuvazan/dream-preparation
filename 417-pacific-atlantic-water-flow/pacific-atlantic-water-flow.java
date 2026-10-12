class Solution {
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Multi-source DFS from left & right borders
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0);       // Pacific (left edge)
            dfs(heights, atlantic, i, n - 1);  // Atlantic (right edge)
        }

        // Multi-source DFS from top & bottom borders
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j);       // Pacific (top edge)
            dfs(heights, atlantic, m - 1, j);  // Atlantic (bottom edge)
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] ocean, int r, int c) {
        ocean[r][c] = true;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            // In reverse flow, water moves to heights >= current height
            if (nr >= 0 && nr < heights.length && nc >= 0 && nc < heights[0].length
                    && !ocean[nr][nc] && heights[nr][nc] >= heights[r][c]) {
                dfs(heights, ocean, nr, nc);
            }
        }
    }
}