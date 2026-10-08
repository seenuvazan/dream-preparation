import java.util.*;

class Solution {
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> sortedVals = new ArrayList<>();
        inorder(root, sortedVals);

        int m = sortedVals.size();
        List<List<Integer>> result = new ArrayList<>(queries.size());

        for (int q : queries) {
            int mini = -1;
            int maxi = -1;

            // Binary search for insertion point
            int low = 0, high = m - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int val = sortedVals.get(mid);

                if (val == q) {
                    mini = val;
                    maxi = val;
                    break;
                } else if (val < q) {
                    mini = val;       // largest value <= q seen so far
                    low = mid + 1;
                } else {
                    maxi = val;       // smallest value >= q seen so far
                    high = mid - 1;
                }
            }

            result.add(Arrays.asList(mini, maxi));
        }

        return result;
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}