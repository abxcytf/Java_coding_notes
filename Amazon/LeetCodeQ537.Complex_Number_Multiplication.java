/*
537. Complex Number Multiplication

Given two strings representing two complex numbers.

You need to return a string representing their multiplication. 
Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, 
and you need convert it to the form of 0+2i.
Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, 
and you need convert it to the form of 0+-2i.
Note:

1. The input strings will not have extra blank.
2. The input strings will be given in the form of a+bi, 
   where the integer a and b will both belong to the range of [-100, 100]. 
   And the output should be also in this form.
*/

public class Solution {
    public String complexNumberMultiply(String a, String b) {
        //a: a_real + a_img * i
        //b : b_real + b_img * i
        //a * b => (a_real * b_real - a_img * b_img) + (a_real * b_img + a_img * b_real) * i
        //Time complexity : O(1)O(1). Here splitting takes constant time 
        //as length of the string is very small (<20)(<20).
        //Space complexity : O(1)O(1). Constant extra space is used.
        String x[] = a.split("\\+|i"); //split with "+" or i;
        String y[] = b.split("\\+|i");
        int a_real = Integer.parseInt(x[0]);
        int a_img = Integer.parseInt(x[1]);
        int b_real = Integer.parseInt(y[0]);
        int b_img = Integer.parseInt(y[1]);
        return (a_real * b_real - a_img * b_img) + "+" + (a_real * b_img + a_img * b_real)  + "i";
    }
}
