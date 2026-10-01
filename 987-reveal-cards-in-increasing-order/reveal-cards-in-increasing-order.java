import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        Arrays.sort(deck);

        int[] result = new int[n];
        Queue<Integer> indexQueue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            indexQueue.offer(i);
        }

        for (int card : deck) {
 
            result[indexQueue.poll()] = card;

            if (!indexQueue.isEmpty()) {
                indexQueue.offer(indexQueue.poll());
            }
        }

        return result;
    }
}