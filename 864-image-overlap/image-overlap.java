import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();

        // 1. Collect coordinates of all 1s in both matrices
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) ones1.add(new int[]{r, c});
                if (img2[r][c] == 1) ones2.add(new int[]{r, c});
            }
        }

        // 2. Count frequency of each displacement vector (dr, dc)
        Map<Integer, Integer> vectorCount = new HashMap<>();
        int maxOverlap = 0;

        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {
                int dr = p2[0] - p1[0];
                int dc = p2[1] - p1[1];
                
                // Encode (dr, dc) into a unique integer key since dr, dc in [-n, n]
                int key = dr * 100 + dc;
                
                int count = vectorCount.getOrDefault(key, 0) + 1;
                vectorCount.put(key, count);
                maxOverlap = Math.max(maxOverlap, count);
            }
        }

        return maxOverlap;
    }
}