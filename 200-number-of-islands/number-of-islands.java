class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int m = grid.length;
        int n = grid[0].length;
        int islandCount = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '1') {
                    islandCount++;
                    dfs(grid, r, c, m, n);
                }
            }
        }

        return islandCount;
    }

    private void dfs(char[][] grid, int r, int c, int m, int n) {
        
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';

        dfs(grid, r + 1, c, m, n); 
        dfs(grid, r - 1, c, m, n); 
        dfs(grid, r, c + 1, m, n); 
        dfs(grid, r, c - 1, m, n); 
    }
}