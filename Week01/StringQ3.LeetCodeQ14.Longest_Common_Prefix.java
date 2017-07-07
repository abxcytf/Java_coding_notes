/*
14. Longest Common Prefix
Write a function to find the longest common prefix string amongst an array of strings.
*/

public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        char[] commonPrefix = strs[0].toCharArray();
        int minLen = commonPrefix.length;
        for (int k = 1; k < strs.length; k++) {
            int len = strs[k].length();
            minLen = Math.min(minLen, len);
            for (int i = 0; i < minLen; i++) {
                if (strs[k].charAt(i) != commonPrefix[i]) {
                    if (i == 0) return "";
                    minLen = i;
                    break;
                }
            }
        }
        return new String(commonPrefix, 0, minLen);
    }
}
