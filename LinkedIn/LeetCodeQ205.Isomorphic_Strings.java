/*
205. Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while 
preserving the order of characters. No two characters may map to the same character 
but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
*/

public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) {
            return true;
        }
        if (s.length() != t.length()) {
            return false;
        }
        int n = s.length();
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int count = 1;
        for (int i = 0; i < n; i++) {
            if (m1[sc[i]] != m2[tc[i]]) {
                return false;
            }
            m1[sc[i]] = count;
            m2[tc[i]] = count;
            count++;
        }
        return true;
    }
}
