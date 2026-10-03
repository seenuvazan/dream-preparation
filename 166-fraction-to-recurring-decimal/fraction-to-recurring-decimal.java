import java.util.HashMap;
import java.util.Map;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        // Determine sign
        if ((numerator < 0) ^ (denominator < 0)) {
            sb.append("-");
        }

        // Convert to long to prevent overflow on Math.abs(Integer.MIN_VALUE)
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Integral part
        sb.append(num / den);
        long remainder = num % den;

        if (remainder == 0) {
            return sb.toString();
        }

        // Fractional part
        sb.append(".");
        Map<Long, Integer> remainderIndexMap = new HashMap<>();

        while (remainder != 0) {
            if (remainderIndexMap.containsKey(remainder)) {
                int openParenIndex = remainderIndexMap.get(remainder);
                sb.insert(openParenIndex, "(");
                sb.append(")");
                break;
            }

            remainderIndexMap.put(remainder, sb.length());
            remainder *= 10;
            sb.append(remainder / den);
            remainder %= den;
        }

        return sb.toString();
    }
}