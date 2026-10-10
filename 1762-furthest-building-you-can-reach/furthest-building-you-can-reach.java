import java.util.PriorityQueue;

class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        // Min-heap storing the largest climbs allocated to ladders
        PriorityQueue<Integer> ladderAllocations = new PriorityQueue<>();

        for (int i = 0; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];

            if (diff > 0) {
                ladderAllocations.offer(diff);

                // If we need more ladders than available, convert the smallest climb to bricks
                if (ladderAllocations.size() > ladders) {
                    bricks -= ladderAllocations.poll();
                }

                // If bricks run out, we cannot reach building i + 1
                if (bricks < 0) {
                    return i;
                }
            }
        }

        return heights.length - 1;
    }
}