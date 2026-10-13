class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source == destination) return true;

        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            union(edge[0], edge[1], parent, rank);
            // Early exit check
            if (find(source, parent) == find(destination, parent)) {
                return true;
            }
        }

        return find(source, parent) == find(destination, parent);
    }

    private int find(int i, int[] parent) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i], parent); // Path compression
    }

    private void union(int i, int j, int[] parent, int[] rank) {
        int rootI = find(i, parent);
        int rootJ = find(j, parent);

        if (rootI != rootJ) {
            if (rank[rootI] < rank[rootJ]) {
                parent[rootI] = rootJ;
            } else if (rank[rootI] > rank[rootJ]) {
                parent[rootJ] = rootI;
            } else {
                parent[rootJ] = rootI;
                rank[rootI]++;
            }
        }
    }
}