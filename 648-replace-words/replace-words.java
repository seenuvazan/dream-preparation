import java.util.List;

class Solution {

    private static class TrieNode {
        private final TrieNode[] children = new TrieNode[26];
        private boolean isEnd = false;
    }

    private final TrieNode root = new TrieNode();

    private void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }

    private String findShortestRoot(String word) {
        TrieNode curr = root;
        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int idx = ch - 'a';

            if (curr.children[idx] == null) {
                return word; // No matching root found
            }

            curr = curr.children[idx];
            prefix.append(ch);

            // Shortest root reached
            if (curr.isEnd) {
                return prefix.toString();
            }
        }

        return word;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        // Build the Trie with dictionary roots
        for (String word : dictionary) {
            insert(word);
        }

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                result.append(" ");
            }
            result.append(findShortestRoot(words[i]));
        }

        return result.toString();
    }
}