import java.util.*;

public class Solution {
    private int timer = 0;
    private int[][] up;
    private int[] depth;
    private int[] tin;
    private int[] tout;
    private int[] bit;
    private int N;

    // Binary Indexed Tree (Fenwick Tree) point update
    private void bitUpdate(int idx, int val) {
        for (; idx <= N; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    // Binary Indexed Tree (Fenwick Tree) point query
    private int bitQuery(int idx) {
        int res = 0;
        for (; idx > 0; idx -= idx & -idx) {
            res ^= bit[idx];
        }
        return res;
    }

    // Euler tour DFS and binary lifting table precomputation
    private void dfs(int u, int p, int d, List<List<Integer>> adj) {
        up[u][0] = p;
        depth[u] = d;
        for (int j = 1; j < 17; ++j) {
            up[u][j] = up[up[u][j - 1]][j - 1];
        }

        tin[u] = ++timer;
        for (int v : adj.get(u)) {
            if (v != p) {
                dfs(v, u, d + 1, adj);
            }
        }
        tout[u] = timer;
    }

    // Binary lifting to find Lowest Common Ancestor
    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        for (int j = 16; j >= 0; --j) {
            if (depth[u] - (1 << j) >= depth[v]) {
                u = up[u][j];
            }
        }

        if (u == v) return u;

        for (int j = 16; j >= 0; --j) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }

        return up[u][0];
    }

    private void updateSubtree(int u, int maskDelta) {
        bitUpdate(tin[u], maskDelta);
        bitUpdate(tout[u] + 1, maskDelta);
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.N = n;
        this.timer = 0;

        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        up = new int[n][17];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        bit = new int[n + 2];

        // Root tree at node 0
        dfs(0, 0, 0, adj);

        char[] chars = s.toCharArray();

        // Initialize characters into the tree
        for (int i = 0; i < n; ++i) {
            updateSubtree(i, 1 << (chars[i] - 'a'));
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                if (chars[u] != c) {
                    int delta = (1 << (chars[u] - 'a')) ^ (1 << (c - 'a'));
                    chars[u] = c;
                    updateSubtree(u, delta);
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v);
                int maskU = bitQuery(tin[u]);
                int maskV = bitQuery(tin[v]);
                int maskLcaNode = 1 << (chars[lca] - 'a');

                int pathMask = maskU ^ maskV ^ maskLcaNode;

                // At most one character with an odd frequency
                boolean canFormPalindrome = (pathMask == 0) || ((pathMask & (pathMask - 1)) == 0);
                answer.add(canFormPalindrome);
            }
        }

        return answer;
    }
}