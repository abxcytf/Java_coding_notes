/*
8. String to Integer (atoi)

Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. 
If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
You are responsible to gather all the input requirements up front.

Requirements for atoi:
1. The function first discards as many whitespace characters as necessary until the first 
  non-whitespace character is found. Then, starting from this character, 
  takes an optional initial plus or minus sign followed by as many numerical digits as possible, 
  and interprets them as a numerical value.

2. The string can contain additional characters after those that form the integral number, 
   which are ignored and have no effect on the behavior of this function.

3. If the first sequence of non-whitespace characters in str is not a valid integral number, 
   or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

4. If no valid conversion could be performed, a zero value is returned. 
   If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/

public class Solution {
    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        str = str.trim(); //remove leading and trailing whitespace
        if (str.length() == 0) {
            return 0;
        }
        int sign = 1; //1 is pos, -1 is neg
        int index = 0;
        
        char[] chars = str.toCharArray();
        
        if (chars[index] == '+') {
            index++;
        } else if (chars[index] == '-') {
            sign = -1;
            index++;
        }
        
        long num = 0; //double also works
        for (; index < chars.length; index++) { //while works as well
            if (chars[index] < '0' || chars[index] > '9') {
                break;
            } //according to the requirement only retrive the value part, not care the later numbers
            num = num * 10 + chars[index] - '0';
            if (num > Integer.MAX_VALUE) { //if larger than maximum of Integer limit
                break;
            }
        }
        
        num *= sign;
        if (num >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (num <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int)num;
        }
    }
}
