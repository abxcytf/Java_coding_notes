/*
171. Excel Sheet Column Number
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
*/

public class Solution {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        char[] chars = s.toCharArray();
        int result = 0;
        int digit = 0;
        for (int i = 0; i < chars.length; i++) {
            digit = chars[i] - 'A' + 1;
            result = result * 26 + digit;
        }
        return result;
    }
}
