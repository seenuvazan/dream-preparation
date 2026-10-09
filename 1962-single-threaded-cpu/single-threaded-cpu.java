import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        
        // Step 1: Create an array with [enqueueTime, processingTime, originalIndex]
        int[][] sortedTasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            sortedTasks[i][0] = tasks[i][0];
            sortedTasks[i][1] = tasks[i][1];
            sortedTasks[i][2] = i;
        }

        // Step 2: Sort tasks chronologically by enqueueTime
        Arrays.sort(sortedTasks, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 3: Min-heap prioritizing shortest processing time, then lowest index
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]); // Shortest processing time
            }
            return Integer.compare(a[2], b[2]);     // Tie-break: lowest original index
        });

        int[] result = new int[n];
        long currentTime = 0;
        int taskIdx = 0;
        int resultIdx = 0;

        // Step 4: Process tasks until all are completed
        while (resultIdx < n) {
            // If the CPU is idle and no tasks are ready, fast-forward time to the next task's arrival
            if (minHeap.isEmpty() && currentTime < sortedTasks[taskIdx][0]) {
                currentTime = sortedTasks[taskIdx][0];
            }

            // Enqueue all tasks that have arrived by currentTime
            while (taskIdx < n && sortedTasks[taskIdx][0] <= currentTime) {
                minHeap.offer(sortedTasks[taskIdx]);
                taskIdx++;
            }

            // Pick the best task from the ready queue and process it
            int[] currentTask = minHeap.poll();
            result[resultIdx++] = currentTask[2];
            currentTime += currentTask[1];
        }

        return result;
    }
}