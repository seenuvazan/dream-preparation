import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) return paths;
        
        List<String> currentPath = new ArrayList<>();
        dfs(root, currentPath, paths);
        return paths;
    }

    private void dfs(TreeNode node, List<String> currentPath, List<String> paths) {
        currentPath.add(String.valueOf(node.val));

        // Leaf node reached
        if (node.left == null && node.right == null) {
            paths.add(String.join("->", currentPath));
        } else {
            if (node.left != null) {
                dfs(node.left, currentPath, paths);
            }
            if (node.right != null) {
                dfs(node.right, currentPath, paths);
            }
        }

        // Backtrack
        currentPath.remove(currentPath.size() - 1);
    }
}