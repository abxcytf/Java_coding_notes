/*
204. Count Primes

Description:

Count the number of prime numbers less than a non-negative number, n.
*/

public class Solution {
    public int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        int count = 0;
        boolean[] number = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!number[i]) {
                count++;
            }
            for (int j = 2; i * j < n; j++) {
                number[i * j] = true;
            }
        }
        return count;
    }
}
