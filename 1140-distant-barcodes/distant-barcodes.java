import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        
        // Count frequencies and track the maximum frequent barcode
        int[] count = new int[10001]; // Values are in range [1, 10000]
        int maxFreqBarcode = 0;
        int maxFreq = 0;

        for (int code : barcodes) {
            count[code]++;
            if (count[code] > maxFreq) {
                maxFreq = count[code];
                maxFreqBarcode = code;
            }
        }

        int[] result = new int[n];
        int index = 0;

        // Place the most frequent barcode at even indices first
        while (count[maxFreqBarcode] > 0) {
            result[index] = maxFreqBarcode;
            index += 2;
            count[maxFreqBarcode]--;
        }

        // Place the remaining barcodes
        for (int code = 1; code <= 10000; code++) {
            while (count[code] > 0) {
                if (index >= n) {
                    index = 1; // Switch to odd indices
                }
                result[index] = code;
                index += 2;
                count[code]--;
            }
        }

        return result;
    }
}