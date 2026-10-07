/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        
        // 1. Search for the target node
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // 2. Node found: handle deletion cases
            
            // Case 1 & 2: 0 or 1 child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            
            // Case 3: 2 children
            // Find in-order successor (smallest value in right subtree)
            TreeNode successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            
            // Overwrite value with successor's value
            root.val = successor.val;
            
            // Recursively delete the successor node
            root.right = deleteNode(root.right, successor.val);
        }
        
        return root;
    }
}