import java.util.Arrays;

class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Sort indices descending based on athlete scores
        Arrays.sort(indices, (a, b) -> Integer.compare(score[b], score[a]));

        String[] result = new String[n];
        for (int rank = 0; rank < n; rank++) {
            int originalIdx = indices[rank];
            if (rank == 0) {
                result[originalIdx] = "Gold Medal";
            } else if (rank == 1) {
                result[originalIdx] = "Silver Medal";
            } else if (rank == 2) {
                result[originalIdx] = "Bronze Medal";
            } else {
                result[originalIdx] = String.valueOf(rank + 1);
            }
        }

        return result;
    }
}