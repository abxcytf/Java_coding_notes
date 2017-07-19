/*
640. Solve the Equation

Solve a given equation and return the value of x in the form of string "x=#value". 
The equation contains only '+', '-' operation, the variable x and its coefficient.

If there is no solution for the equation, return "No solution".

If there are infinite solutions for the equation, return "Infinite solutions".

If there is exactly one solution for the equation, we ensure that the value of x is an integer.

Example 1:
Input: "x+5-3+x=6+x-2"
Output: "x=2"
Example 2:
Input: "x=x"
Output: "Infinite solutions"
Example 3:
Input: "2x=x"
Output: "x=0"
Example 4:
Input: "2x+3x-6x=x+2"
Output: "x=-1"
Example 5:
Input: "x=x+2"
Output: "No solution"
*/

public class Solution {
    public String solveEquation(String equation) {
        if (equation == null || equation.length() == 0) {
            return equation;
        }
        String[] parts = equation.split("=");
        //left side of the equation
        String leftSide = parts[0];
        String rightSide = parts[1];
        
        //coefficient array, Ax + B
        //[0]: A, [1]: B
        int[] leftCo = simplified(leftSide);
        int[] rightCo = simplified(rightSide);
        
        //now we have, A_l * x + B_l = A_r * + B_r
        //=> (A_l - A_r) * x = (B_r - B_l);
        //=> A = A_l - A_r, B = B_r - B_l;
        //=> A* x = B;
        int A = leftCo[0] - rightCo[0];
        int B = rightCo[1] - leftCo[1];
        
        if (A == 0) {
            if (B == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        } else if (B == 0) {
            return "x=0";
        } else {
            return "x=" + String.valueOf((int)(B / A));
        }
    }
    
    private int[] simplified(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        int sign = 1;
        int A = 0;
        int B = 0;
        int num = 0;
        
        for (int i = 0; i < n; i++) {
            if (chars[i] != 'x') {
                if (chars[i] == '-') {
                    sign = -1;
                } else if (chars[i] == '+') {
                    sign = 1;
                } else {
                    //reset the previous num to 0
                    num = 0;
                    while (i < n && chars[i] != '+' && chars[i] != '-' && chars[i] != 'x') {
                        //if chars[i] are digit, cumulate it as number
                        num = num * 10 + (chars[i] - '0');
                        i++;
                    }
                    //if after the number is 'x', means it belongs to A
                    if (i < n && chars[i] == 'x') {
                        A += sign * num;
                    } else {
                        //chars[i] is '-' or '+'
                        B += sign * num;
                        i--; //make sure next round is still on current '+' and '-'
                    }
                }
            } else {
                //this is only for cases ...+x or ...-x cases, i currently on x's index
                A += sign;
            }
        }
        return new int[]{A, B};
    }
}
