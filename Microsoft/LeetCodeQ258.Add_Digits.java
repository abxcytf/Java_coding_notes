/*
258. Add Digits

Given a non-negative integer num, repeatedly add all its digits until 
the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?
*/

public class Solution {
    public int addDigits(int num) {
        if (num >= 0 && num <= 9) {
            return num;
        }
        while (!(num >= 0 && num <= 9)) {
            int n = num;
            int newNum = 0;
            while (n > 0) {
                newNum += n % 10;
                n /= 10;
            }
            num = newNum;
        }
        return num;
    }
    
    /***************************************************************/
    if (num >= 0 && num <= 9) {
        return num;
    }
    return 1 + (num - 1) % 9;
    
    /**************************************************************/
    public int addDigits(int num) {
        //input is a non-negative num
        if (num >= 0 && num <= 9) {
            //num is only single digit, so return it
            return num;
        }
        
        while (!(num >= 0 && num <= 9)) {
            //the ending condition of the loop is num is single digit
            int digitalSum = 0;
            
            while (num > 0) {
                digitalSum += num % 10;
                num /= 10;
            }
            num = digitalSum;
        }
        
        return num;
    }
}
