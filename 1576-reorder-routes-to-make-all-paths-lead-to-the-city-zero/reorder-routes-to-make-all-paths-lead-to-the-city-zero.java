import java.util.*;

class Solution {
    public int minReorder(int n, int[][] connections) {
        // graph[u] stores pairs of (neighbor, cost)
        List<List<int[]>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : connections) {
            int u = edge[0];
            int v = edge[1];
            // Original direction u -> v (cost 1 if traversing outward from u to v)
            graph.get(u).add(new int[]{v, 1});
            // Reverse direction v -> u (cost 0 if traversing outward from v to u)
            graph.get(v).add(new int[]{u, 0});
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        
        queue.offer(0);
        visited[0] = true;
        int changes = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int[] next : graph.get(curr)) {
                int neighbor = next[0];
                int cost = next[1];

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    changes += cost;
                    queue.offer(neighbor);
                }
            }
        }

        return changes;
    }
}