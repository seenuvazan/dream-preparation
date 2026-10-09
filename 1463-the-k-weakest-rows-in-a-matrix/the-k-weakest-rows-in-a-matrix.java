import java.util.Arrays;

class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        // Each entry: [soldierCount, rowIndex]
        int[][] rowStrengths = new int[m][2];

        for (int i = 0; i < m; i++) {
            rowStrengths[i][0] = countSoldiers(mat[i]);
            rowStrengths[i][1] = i;
        }

        // Sort by soldier count ascending; if equal, by row index ascending
        Arrays.sort(rowStrengths, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = rowStrengths[i][1];
        }

        return result;
    }

    // Binary search for the first 0 (which equals the number of 1s)
    private int countSoldiers(int[] row) {
        int low = 0, high = row.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (row[mid] == 1) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}