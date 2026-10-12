class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> reverseGraph = new ArrayList<>();
        int[] outDegree = new int[n];

        for (int i = 0; i < n; i++) {
            reverseGraph.add(new ArrayList<>());
        }

        // Build reversed graph and record out-degrees
        for (int u = 0; u < n; u++) {
            outDegree[u] = graph[u].length;
            for (int v : graph[u]) {
                reverseGraph.get(v).add(u);
            }
        }

        // Terminal nodes have outDegree == 0
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 0) {
                queue.offer(i);
            }
        }

        boolean[] isSafe = new boolean[n];
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            isSafe[curr] = true;

            for (int prev : reverseGraph.get(curr)) {
                outDegree[prev]--;
                if (outDegree[prev] == 0) {
                    queue.offer(prev);
                }
            }
        }

        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isSafe[i]) {
                safeNodes.add(i);
            }
        }

        return safeNodes;
    }
}