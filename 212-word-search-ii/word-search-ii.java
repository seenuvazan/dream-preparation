import java.util.ArrayList;
import java.util.List;

class Solution {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
        int refs = 0; // Tracks active words under this node
    }

    private void insert(TrieNode root, String word) {
        TrieNode curr = root;
        curr.refs++;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
            curr.refs++;
        }
        curr.word = word;
    }

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            insert(root, w);
        }

        List<String> result = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, m, n, root, result);
            }
        }

        return result;
    }

    private int dfs(char[][] board, int r, int c, int m, int n, TrieNode parent, List<String> result) {
        char ch = board[r][c];
        if (ch == '#' || parent.children[ch - 'a'] == null) {
            return 0;
        }

        TrieNode curr = parent.children[ch - 'a'];
        if (curr.refs == 0) {
            return 0; // Branch already pruned
        }

        int wordsFound = 0;
        if (curr.word != null) {
            result.add(curr.word);
            curr.word = null; // Prevent duplicate additions
            wordsFound++;
        }

        board[r][c] = '#'; // Mark cell visited

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] != '#') {
                wordsFound += dfs(board, nr, nc, m, n, curr, result);
            }
        }

        board[r][c] = ch; // Backtrack restore

        // Decrement references to prune dead Trie paths
        curr.refs -= wordsFound;
        return wordsFound;
    }
}