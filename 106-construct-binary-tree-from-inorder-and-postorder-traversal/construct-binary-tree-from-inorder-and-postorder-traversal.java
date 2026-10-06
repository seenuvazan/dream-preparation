import java.util.HashMap;
import java.util.Map;

public class Solution {
    private int postIndex;
    private Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postIndex = postorder.length - 1;
        this.inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return helper(postorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] postorder, int inLeft, int inRight) {
        if (inLeft > inRight) {
            return null;
        }

        // Pick root value from postorder from the back
        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        int mid = inorderMap.get(rootVal);

        // Build right subtree first, then left subtree
        root.right = helper(postorder, mid + 1, inRight);
        root.left = helper(postorder, inLeft, mid - 1);

        return root;
    }
}