import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Long, Integer> map = new HashMap<>();
        int count = 0;

        for (List<Integer> row : wall) {
            long sum = 0; // Use long to prevent integer overflow
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
                count = Math.max(count, map.get(sum));
            }
        }

        return wall.size() - count;
    }
}