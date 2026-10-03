import java.util.*;

class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        // Map: content -> list of full file paths
        Map<String, List<String>> contentToPaths = new HashMap<>();

        for (String pathInfo : paths) {
            String[] parts = pathInfo.split(" ");
            String directory = parts[0];

            for (int i = 1; i < parts.length; i++) {
                String file = parts[i];
                int openParen = file.indexOf('(');
                int closeParen = file.indexOf(')');

                String fileName = file.substring(0, openParen);
                String content = file.substring(openParen + 1, closeParen);
                String fullPath = directory + "/" + fileName;

                contentToPaths.computeIfAbsent(content, k -> new ArrayList<>()).add(fullPath);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> group : contentToPaths.values()) {
            if (group.size() > 1) {
                result.add(group);
            }
        }

        return result;
    }
}