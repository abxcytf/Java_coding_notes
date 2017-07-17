/*
459. Repeated Substring Pattern

Given a non-empty string check if it can be constructed by taking a substring of it 
and appending multiple copies of the substring together. 
You may assume the given string consists of lowercase English letters only 
and its length will not exceed 10000.

Example 1:
Input: "abab"

Output: True

Explanation: It's the substring "ab" twice.
Example 2:
Input: "aba"

Output: False
Example 3:
Input: "abcabcabcabc"

Output: True

Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/

public class Solution {
    //brutal force, TLE
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        
        int len = s.length();
        for (int i = len / 2; i >= 1; i--) {
            if (len % i == 0) {
                int count = len / i;
                String unit = "";
                for (int j = 0; j < count; j++) {
                    unit += s.substring(0, i);
                }
                if (unit.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /*********************************************************************************************/
    /*
    维护的一位数组dp[i]表示，到位置i-1为止的重复字符串的字符个数，不包括被重复的那个字符串，
    我们举个例子，比如"abcabc"的dp数组为[0 0 0 0 1 2 3]，dp数组长度要比原字符串长度多一个。
    那么我们看最后一个位置数字为3，就表示重复的字符串的字符数有3个。如果是"abcabcabc"，
    那么dp数组为[0 0 0 0 1 2 3 4 5 6]，我们发现最后一个数字为6，那么表示重复的字符串为“abcabc”，
    有6个字符。那么怎么通过最后一个数字来知道原字符串是否由重复的子字符串组成的呢，
    首先当然是最后一个数字不能为0，而且还要满足dp[n] % (n - dp[n]) == 0才行，
    因为n - dp[n]是一个子字符串的长度，那么重复字符串的长度和肯定是一个子字符串的整数倍。
    */
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        
        int len = s.length();
        //dp[i] means till (i - 1) index location
        //the number of duplicates chars, not include(i - 1)
        int[] dp = new int[len + 1];
        int start = 0;
        int end = 1;
        while (end < len) {
            if (s.charAt(end) == s.charAt(start)) {
                dp[++end] = ++start;
            } else if (start == 0){
                ++end;
            } else {
                start = dp[start];
            }
        }
        return dp[len] != 0 && (dp[len] % (len - dp[len]) == 0);
    }
}
