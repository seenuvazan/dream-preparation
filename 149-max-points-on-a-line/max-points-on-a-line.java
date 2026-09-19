import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;

        int maxPoints = 1;

        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeCounts = new HashMap<>();
            int x1 = points[i][0];
            int y1 = points[i][1];

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - x1;
                int dy = points[j][1] - y1;

                int g = gcd(Math.abs(dx), Math.abs(dy));
                dx /= g;
                dy /= g;

                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0) {
                    dy = 1;
                } else if (dy == 0) {
                    dx = 1;
                }

                String slopeKey = dy + "/" + dx;
                slopeCounts.put(slopeKey, slopeCounts.getOrDefault(slopeKey, 0) + 1);
            }

            int currentMax = slopeCounts.isEmpty() ? 0 : Collections.max(slopeCounts.values());
            maxPoints = Math.max(maxPoints, currentMax + 1);
        }

        return maxPoints;
    }
}