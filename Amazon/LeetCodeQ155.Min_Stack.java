/*
155. Min Stack

Design a stack that supports push, pop, top, 
and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
*/

public class MinStack {
    //implementation using two stacks
    private Deque<Integer> stack1;
    private Deque<Integer> stack2;
    
    /** initialize your data structure here. */
    public MinStack() {
        stack1 = new ArrayDeque<Integer>();
        stack2 = new ArrayDeque<Integer>();
    }
    
    public void push(int x) {
        stack1.offerLast(x);
        if (stack2.isEmpty() || x <= stack2.peekLast()) {
            stack2.offerLast(x);
        }
    }
    
    public void pop() {
        if (stack1.isEmpty()) {
            return;
        }
        int temp = stack1.pollLast();
        if (temp == stack2.peekLast()) {
            stack2.pollLast();
        }
    }
    
    public int top() {
        return stack1.peekLast();
    }
    
    public int getMin() {
        return stack2.peekLast();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
