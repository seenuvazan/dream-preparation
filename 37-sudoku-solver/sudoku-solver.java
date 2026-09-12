class Solution {
    private boolean[][] rows = new boolean[9][10];
    private boolean[][] cols = new boolean[9][10];
    private boolean[][] boxes = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        // Record existing numbers
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    int d = board[r][c] - '0';
                    int boxIdx = (r / 3) * 3 + (c / 3);
                    rows[r][d] = true;
                    cols[c][d] = true;
                    boxes[boxIdx][d] = true;
                }
            }
        }

        solve(board, 0, 0);
    }

    private boolean solve(char[][] board, int r, int c) {
        if (r == 9) return true; // Solved entire board
        if (c == 9) return solve(board, r + 1, 0); // Move to next row
        if (board[r][c] != '.') return solve(board, r, c + 1); // Skip filled cells

        int boxIdx = (r / 3) * 3 + (c / 3);

        for (int d = 1; d <= 9; d++) {
            if (!rows[r][d] && !cols[c][d] && !boxes[boxIdx][d]) {
                // Place digit
                board[r][c] = (char) ('0' + d);
                rows[r][d] = true;
                cols[c][d] = true;
                boxes[boxIdx][d] = true;

                if (solve(board, r, c + 1)) {
                    return true;
                }

                // Backtrack
                board[r][c] = '.';
                rows[r][d] = false;
                cols[c][d] = false;
                boxes[boxIdx][d] = false;
            }
        }

        return false;
    }
}