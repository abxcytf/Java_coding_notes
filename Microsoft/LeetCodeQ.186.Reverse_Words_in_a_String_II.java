/*
186. Reverse Words in a String II
iven an input string, reverse the string word by word. 
A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words 
are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?
*/

public class Solution {
    public void reverseWords(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        reverse(s, 0, s.length - 1);
        int i = 0;
        for (int j = 0; j < s.length; j++) {
            if (j == s.length - 1 || s[j] != ' ' && s[j + 1] == ' ') {
                reverse(s, i, j);
                i = j + 2;
            }
        }
    }
    
    private void reverse(char[] s, int i, int j) {
        while (i < j) {
            if (s[i] != s[j]) {
                s[i] ^= s[j];
                s[j] ^= s[i];
                s[i] ^= s[j];
            }
            i++;
            j--;
        }
    }
    
    /*************************************************************************************/
    
    
}
