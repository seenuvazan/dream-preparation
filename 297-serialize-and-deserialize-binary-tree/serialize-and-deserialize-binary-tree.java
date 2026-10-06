import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeDfs(root, sb);
        return sb.toString();
    }

    private void serializeDfs(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,");
            return;
        }
        sb.append(node.val).append(",");
        serializeDfs(node.left, sb);
        serializeDfs(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] tokens = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(tokens));
        return deserializeDfs(queue);
    }

    private TreeNode deserializeDfs(Queue<String> queue) {
        String token = queue.poll();
        if (token.equals("#")) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(token));
        node.left = deserializeDfs(queue);
        node.right = deserializeDfs(queue);
        return node;
    }
}