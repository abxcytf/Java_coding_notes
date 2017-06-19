/*
151. Reverse Words in a String
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Clarification(Note):
. What constitutes a word?
  A sequence of non-space characters constitutes a word.
. Could the input string contain leading or trailing spaces?
  Yes. However, your reversed string should not contain leading or trailing spaces.
. How about multiple spaces between two words?
  Reduce them to a single space in the reversed string.
*/

public class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        s = s.trim();
        if (s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        reverse(chars, 0, chars.length - 1);
        StringBuilder result = new StringBuilder();
        int start = -1;
        int end = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                if (i == 0 || chars[i - 1] == ' ') {
                    start = i;
                }
                if (i == chars.length - 1 || chars[i + 1] == ' ') {
                    end = i;
                }
            }
            if (start != - 1 && end != -1) {
                reverse(chars, start, end);
                if (result.length() != 0) {
                    result.append(' ');
                }
                result.append(chars, start, end - start + 1);//!!!!!
                //StringBuilder.append(char[], offset, length)
                start = -1;
                end = -1;
            }
        }
        return result.toString();
    }
    
    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            if (chars[i] != chars[j]) {
                chars[i] ^= chars[j];
                chars[j] ^= chars[i];
                chars[i] ^= chars[j];
            }
            i++;
            j--;
        }
    }
}
