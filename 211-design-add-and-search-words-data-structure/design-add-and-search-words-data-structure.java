class WordDictionary {

    private static class TrieNode {
        private final TrieNode[] children = new TrieNode[26];
        private boolean isEnd = false;
    }

    private final TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
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

    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }

    private boolean searchHelper(String word, int index, TrieNode curr) {
        if (curr == null) {
            return false;
        }
        if (index == word.length()) {
            return curr.isEnd;
        }

        char ch = word.charAt(index);

        if (ch == '.') {
            // Try every existing child branch
            for (int i = 0; i < 26; i++) {
                if (curr.children[i] != null && searchHelper(word, index + 1, curr.children[i])) {
                    return true;
                }
            }
            return false;
        } else {
            // Standard single-branch step
            int idx = ch - 'a';
            return searchHelper(word, index + 1, curr.children[idx]);
        }
    }
}