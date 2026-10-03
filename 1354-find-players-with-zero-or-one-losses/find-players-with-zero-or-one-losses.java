import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        int[] losses = new int[100001];
        Arrays.fill(losses, -1);

        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];

            // Record winner if not seen before
            if (losses[winner] == -1) {
                losses[winner] = 0;
            }

            // Record or increment loser's count
            if (losses[loser] == -1) {
                losses[loser] = 1;
            } else {
                losses[loser]++;
            }
        }

        List<Integer> zeroLoss = new ArrayList<>();
        List<Integer> oneLoss = new ArrayList<>();

        for (int i = 1; i <= 100000; i++) {
            if (losses[i] == 0) {
                zeroLoss.add(i);
            } else if (losses[i] == 1) {
                oneLoss.add(i);
            }
        }

        return Arrays.asList(zeroLoss, oneLoss);
    }
}