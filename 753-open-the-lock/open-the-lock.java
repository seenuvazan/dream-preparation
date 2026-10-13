import java.util.*;

class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        
        // If starting point is a deadend
        if (dead.contains("0000")) {
            return -1;
        }
        if (target.equals("0000")) {
            return 0;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>(dead); // Deadends count as visited
        
        queue.offer("0000");
        visited.add("0000");
        
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int k = 0; k < size; k++) {
                String curr = queue.poll();
                
                if (curr.equals(target)) {
                    return steps;
                }

                char[] chars = curr.toCharArray();
                for (int i = 0; i < 4; i++) {
                    char orig = chars[i];
                    
                    // Turn forward (+1)
                    chars[i] = (orig == '9') ? '0' : (char) (orig + 1);
                    String nextForward = new String(chars);
                    if (!visited.contains(nextForward)) {
                        visited.add(nextForward);
                        queue.offer(nextForward);
                    }

                    // Turn backward (-1)
                    chars[i] = (orig == '0') ? '9' : (char) (orig - 1);
                    String nextBackward = new String(chars);
                    if (!visited.contains(nextBackward)) {
                        visited.add(nextBackward);
                        queue.offer(nextBackward);
                    }

                    // Restore character
                    chars[i] = orig;
                }
            }
            steps++;
        }

        return -1;
    }
}