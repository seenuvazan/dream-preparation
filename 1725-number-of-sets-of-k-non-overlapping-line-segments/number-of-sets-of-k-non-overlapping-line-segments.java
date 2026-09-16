class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        int totalN = n + k - 1;
        int totalR = 2 * k;

        if (totalR > totalN) {
            return 0;
        }
        if (totalR > totalN - totalR) {
            totalR = totalN - totalR;
        }

        long numerator = 1;
        long denominator = 1;

        for (int i = 1; i <= totalR; i++) {
            numerator = (numerator * (totalN - i + 1)) % MOD;
            denominator = (denominator * i) % MOD;
        }
        return (int) ((numerator * modInverse(denominator, MOD)) % MOD);
    }

    private long modInverse(long base, int mod) {
        return power(base, mod - 2, mod);
    }

    private long power(long base, int exp, int mod) {
        long result = 1;
        base %= mod;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }

        return result;
    }
}