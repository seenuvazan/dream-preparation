import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        Queue<int[]> queue = new ArrayDeque<>();
        int freshCount = 0;
        
        // Step 1: Initialize the queue with all rotten oranges and count fresh ones
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) {
            return 0;
        }
        
        int minutes = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty() && freshCount > 0) {
            int levelSize = queue.size();
            boolean rottedAny = false;
            
            for (int i = 0; i < levelSize; i++) {
                int[] curr = queue.poll();
                int row = curr[0];
                int col = curr[1];
                
                for (int[] dir : directions) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n 
                            && grid[nextRow][nextCol] == 1) {
                        grid[nextRow][nextCol] = 2; 
                        freshCount--;
                        queue.offer(new int[]{nextRow, nextCol});
                        rottedAny = true;
                    }
                }
            }
            
            if (rottedAny) {
                minutes++;
            }
        }
        
        return freshCount == 0 ? minutes : -1;
    }
}