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
    //优化过后更加精简的写法
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int len = s.length();
        String result = "";
        
        boolean[][] isPalindrome = new boolean[len][len];
        
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                isPalindrome[i][j] = s.charAt(i) == s.charAt(j) 
                                    && (j - i < 3 || isPalindrome[i + 1][j - 1]);
                
                if (isPalindrome[i][j] && j - i + 1 > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }
        return result;
    }
    
    /**************************************************************************************/
    //more optimized solution
    private int pos;
    private int maxLen;
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int len = s.length();
        maxLen = 0;
        pos = 0;
        for (int i = 0; i < len - 1; i++) {
            //从中间向两边延伸
            extendPalindrome(s, i, i); //for odd cases
            //assume odd length, try to extend Palindrome as possible
            if (s.charAt(i) == s.charAt(i + 1)) { //for even cases
                extendPalindrome(s, i, i + 1); 
                //assume even length
            }
        }
        return s.substring(pos, pos + maxLen);
    }
    
    private void extendPalindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) { //if it is palindrome, extend from both side
            i--;
            j++;
        }
        int currentLen = (j - 1) - (i + 1) + 1;
        if (currentLen > maxLen) {
            maxLen = currentLen;
            pos = i + 1; //start index
        }
    }
}
