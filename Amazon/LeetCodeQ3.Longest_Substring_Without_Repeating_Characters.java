/*
3. Longest Substring Without Repeating Characters

Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. 
. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

public class Solution {
    //two pointers, time complexity O(n)
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        int[] count = new int[256];
        int longest = 1;
        // for (left = 0; left < s.length(); left++) {
        for (left = 0; left < s.length() - longest; left++) {
            while (right < s.length() && count[chars[right]] == 0) {
                count[chars[right]] = 1;
                right++;
            }
            longest = Math.max(longest, right - left);
            count[chars[left]]--;
        }
        return longest;
    }
}
