import java.util.*;

class Solution {
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class State implements Comparable<State> {
        long dist;
        int node;
        int count;

        State(long dist, int node, int count) {
            this.dist = dist;
            this.node = node;
            this.count = count;
        }

        @Override
        public int compareTo(State other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        if (n == 1) {
            return 0;
        }

        List<List<Edge>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new Edge(edge[1], edge[2]));
        }

        long[][] dist = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        PriorityQueue<State> pq = new PriorityQueue<>();

        dist[0][1] = 0;
        pq.offer(new State(0, 0, 1));

        while (!pq.isEmpty()) {
            State current = pq.poll();
            long d = current.dist;
            int u = current.node;
            int count = current.count;

            if (u == n - 1) {
                return (int) d;
            }

            if (d > dist[u][count]) {
                continue;
            }

            char labelU = labels.charAt(u);

            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                int w = edge.weight;
                char labelV = labels.charAt(v);

                int nextCount = (labelU == labelV) ? count + 1 : 1;

                if (nextCount <= k) {
                    if (d + w < dist[v][nextCount]) {
                        dist[v][nextCount] = d + w;
                        pq.offer(new State(d + w, v, nextCount));
                    }
                }
            }
        }

        return -1;
    }
}