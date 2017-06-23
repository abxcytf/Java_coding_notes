/*
5. Longest Palindromic Substring

Given a string s, find the longest palindromic substring in s. 
You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"
*/

public class Solution {
    //using dp idea to find all the substring which are palindrome time O(n^2)
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int len = s.length();
        String result = "";
        
        boolean[][] isPalindrome = new boolean[len][len];
        
        for (int i = 0; i < len; i++) {
            isPalindrome[i][i] = true;
        }
        
        for (int i = 0; i < len - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }
        
        for (int i = 2; i < len; i++) {
            for (int j = i - 2; j >= 0; j--) {
                isPalindrome[j][i]= isPalindrome[j + 1][i - 1] && (s.charAt(i) == s.charAt(j));
            }
        }
        
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (isPalindrome[i][j] && (j - i + 1 > result.length())) {
                    result = s.substring(i, j + 1);
                }
            }
        }
        return result;
    }
    
    /***********************************************************************************************/
    
    
}
