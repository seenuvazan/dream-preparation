class Solution {
    private int moves = 0;

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return moves;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftExcess = dfs(node.left);
        int rightExcess = dfs(node.right);

        // Moves across the left and right child edges
        moves += Math.abs(leftExcess) + Math.abs(rightExcess);

        // Net excess from this subtree passed up to the parent
        return node.val - 1 + leftExcess + rightExcess;
    }
}