/*
125. Valid Palindrome
Given a string, determine if it is a palindrome, 
considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.
*/
public class Solution {
    //常规方法
    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        s = s.toLowerCase();
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (i < j && !(Character.isDigit(s.charAt(i)) || Character.isLetter(s.charAt(i)))) i++;
            while (i < j && !(Character.isDigit(s.charAt(j)) || Character.isLetter(s.charAt(j)))) j--;
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
    
    //记忆化搜索的区间型动态规划思维：TLE 执行超时，但是速度很快，用空间换时间，在palindrome partition中可以提速。
    //这个具体题目用这个方法会超时。
    public boolean isPalindrome_DP(String s) {
        if (s == null || s.length() <= 1) return true; 
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch : chars) {
            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                sb.append(ch);
            }
        }
        s = sb.toString();
        s = s.toLowerCase();
        if (s.length() <= 1) return true; 
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];  //cost extra spaca
        //[i][j] 代表在这个string中字母位置i 到字母位置j(inclusive)是否是palindrome的substring
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i= 0; i + 1 < n; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }
        for (int i = 2; i < n; i++) {
            for (int j = i - 2; j >= 0; j--) {
                isPalindrome[j][i] = isPalindrome[j + 1][i - 1] && (s.charAt(j) == s.charAt(i));
            }
        }
        // for (int i = n - 3; i >= 0; i--) {
        //     for (int j = i + 2; j < n; j++) {
        //         isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
        //     }
        // }    
        return isPalindrome[0][n - 1];
    }
}
