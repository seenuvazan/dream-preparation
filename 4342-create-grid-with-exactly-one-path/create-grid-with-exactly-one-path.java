import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        
        // Fill all cells with obstacles '#'
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }
        
        // Single deterministic path: down column 0, then right along row (m - 1)
        for (int i = 0; i < m; i++) {
            grid[i][0] = '.';
        }
        for (int j = 0; j < n; j++) {
            grid[m - 1][j] = '.';
        }
        
        // Convert char array to String[]
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}