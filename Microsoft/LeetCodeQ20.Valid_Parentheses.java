/*
20. Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
determine if the input string is valid.

The brackets must close in the correct order, 
"()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

public class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        Deque<Character> stack = new ArrayDeque<>();
        for (Character ch : s.toCharArray()) {
            if ("([{".contains(String.valueOf(ch))) { 
                //always can write a function to check
                stack.offerLast(ch);
            } else {
                if (!stack.isEmpty() && isMatch(stack.peekLast(), ch)) {
                    stack.pollLast();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty(); //corner case: "([{"
    }
    
    private boolean isMatch(char a, char b) {
        return a == '(' && b == ')' || a == '[' && b == ']' || a == '{' && b == '}';
    }
}
