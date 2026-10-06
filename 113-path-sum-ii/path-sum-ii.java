import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(TreeNode node, int remainingSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        // Choose
        currentPath.add(node.val);

        // Check if it's a leaf node matching the target sum
        if (node.left == null && node.right == null && remainingSum == node.val) {
            result.add(new ArrayList<>(currentPath));
        } else {
            // Explore
            backtrack(node.left, remainingSum - node.val, currentPath, result);
            backtrack(node.right, remainingSum - node.val, currentPath, result);
        }

        // Backtrack
        currentPath.remove(currentPath.size() - 1);
    }
}