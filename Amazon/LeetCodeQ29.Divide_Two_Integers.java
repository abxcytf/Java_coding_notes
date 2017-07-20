/*
29. Divide Two Integers

Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
*/

public class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            //分母为0的情况
            return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        
        if (dividend == 0) {
            //分子为0的情况
            return 0;
        }
        
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            //another special case need to discuss seperately
            return Integer.MAX_VALUE;
        }
        
        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        int result = 0;
        while (a >= b) {
            int shift = 0;
            while (a >= (b << shift)) {
                shift++;
            }
            a -= b << (shift - 1);
            result += 1 << (shift - 1);
        }
        return isNegative? -result : result;
    }
    
    /*****************************************************************************************************/
    //optimized solution
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            //分母为0的情况
            return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        
        if (dividend == 0) {
            //分子为0的情况
            return 0;
        }
        
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            //another special case need to discuss seperately
            return Integer.MAX_VALUE;
        }
        
        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            //比较a是否大于b的(1 << i)次方，避免将a与(b << i)比较
            //因为不确定b的(b << i)次方是否溢出
            if ((a >> i) >= b) {
                result += (1 << i);
                a -= (b << i);
            }
        }
        return isNegative? -result : result;
    }
}
