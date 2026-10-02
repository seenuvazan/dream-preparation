import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<List<Integer>, Integer> rowCounts = new HashMap<>();

        // 1. Store the frequency of each row
        for (int r = 0; r < n; r++) {
            List<Integer> row = new ArrayList<>(n);
            for (int c = 0; c < n; c++) {
                row.add(grid[r][c]);
            }
            rowCounts.put(row, rowCounts.getOrDefault(row, 0) + 1);
        }

        int pairs = 0;

        // 2. Extract each column and check against row patterns
        for (int c = 0; c < n; c++) {
            List<Integer> col = new ArrayList<>(n);
            for (int r = 0; r < n; r++) {
                col.add(grid[r][c]);
            }
            pairs += rowCounts.getOrDefault(col, 0);
        }

        return pairs;
    }
}