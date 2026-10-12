class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[0] = true;
        queue.offer(0);
        int visitedCount = 1;

        while (!queue.isEmpty()) {
            int currentRoom = queue.poll();

            for (int key : rooms.get(currentRoom)) {
                if (!visited[key]) {
                    visited[key] = true;
                    visitedCount++;
                    queue.offer(key);
                }
            }
        }

        return visitedCount == n;
    }
}