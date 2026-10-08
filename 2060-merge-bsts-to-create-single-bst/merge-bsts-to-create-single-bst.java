import java.util.*;

class Solution {
    public TreeNode canMerge(List<TreeNode> trees) {
        Map<Integer, TreeNode> rootMap = new HashMap<>();
        Map<Integer, Integer> leafCount = new HashMap<>();

        // 1. Record all root values and count all leaf occurrences
        for (TreeNode t : trees) {
            rootMap.put(t.val, t);
            if (t.left != null) {
                leafCount.put(t.left.val, leafCount.getOrDefault(t.left.val, 0) + 1);
            }
            if (t.right != null) {
                leafCount.put(t.right.val, leafCount.getOrDefault(t.right.val, 0) + 1);
            }
        }

        // 2. The global root must be a root that is NEVER a leaf in any tree
        TreeNode globalRoot = null;
        for (TreeNode t : trees) {
            if (!leafCount.containsKey(t.val)) {
                if (globalRoot != null) {
                    return null; // More than one candidate root -> disconnected forest
                }
                globalRoot = t;
            }
        }

        if (globalRoot == null) return null; // Cycle among roots

        // 3. Validate BST and recursively stitch trees
        if (isValidBSTAndMerge(globalRoot, rootMap, Long.MIN_VALUE, Long.MAX_VALUE) 
                && rootMap.size() == 1) {
            return globalRoot;
        }

        return null;
    }

    private boolean isValidBSTAndMerge(TreeNode node, Map<Integer, TreeNode> rootMap, long minVal, long maxVal) {
        if (node == null) return true;

        if (node.val <= minVal || node.val >= maxVal) return false;

        // If this is a leaf node and there exists a tree rooted at this value, graft it
        if (node.left == null && node.right == null) {
            if (rootMap.containsKey(node.val) && rootMap.get(node.val) != node) {
                TreeNode target = rootMap.get(node.val);
                node.left = target.left;
                node.right = target.right;
                rootMap.remove(node.val); // Mark tree as merged
            }
        }

        return isValidBSTAndMerge(node.left, rootMap, minVal, node.val)
            && isValidBSTAndMerge(node.right, rootMap, node.val, maxVal);
    }
}