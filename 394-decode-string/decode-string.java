import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String decodeString(String s) {
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack = new ArrayDeque<>();
        
        StringBuilder currStr = new StringBuilder();
        int currNum = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                currNum = currNum * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(currNum);
                stringStack.push(currStr);
                
                // Reset for the content inside brackets
                currNum = 0;
                currStr = new StringBuilder();
            } else if (c == ']') {
                int repeatCount = countStack.pop();
                StringBuilder decodedPart = stringStack.pop();
                
                while (repeatCount-- > 0) {
                    decodedPart.append(currStr);
                }
                currStr = decodedPart;
            } else {
                currStr.append(c);
            }
        }

        return currStr.toString();
    }
}