/*
232. Implement Queue using Stacks

Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
. You must use only standard operations of a stack -- which means only push to top, 
  peek/pop from top, size, and is empty operations are valid.
. Depending on your language, stack may not be supported natively. 
  You may simulate a stack by using a list or deque (double-ended queue), 
  as long as you use only standard operations of a stack.
. You may assume that all operations are valid (for example, no pop or peek operations 
  will be called on an empty queue).
*/

public class MyQueue {
    //using two stacks one for push and one for pop
    Deque<Integer> stack1;
    Deque<Integer> stack2;
    
    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new ArrayDeque<Integer>(); //for push
        stack2 = new ArrayDeque<Integer>(); //for pop
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.offerLast(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (stack2.isEmpty()) {
            moveFrom1To2();
        }
        return stack2.pollLast();
    }
    
    /** Get the front element. */
    public int peek() {
        if (stack2.isEmpty()) {
            moveFrom1To2();
        }
        return stack2.peekLast();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
    
    private void moveFrom1To2() {
        while(!stack1.isEmpty()) {
            stack2.offerLast(stack1.pollLast());
        }
    }
    
    
    /**************************************************************************************/
    //another implementation is to use a temp stack
    private Deque<Integer> stack;
    //private Deque<Integer> temp;
  
    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new ArrayDeque<Integer>();
        //temp = new ArrayDeque<Integer>();
    }
    
    /** Push element x to the back of queue. */
    //always make the first element into the stack on the top, by using the temp stack
    public void push(int x) {
        Deque<Integer> temp = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            temp.offerLast(stack.pollLast());
        }
        stack.offerLast(x);
        while (!temp.isEmpty()) {
            stack.offerLast(temp.pollLast());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack.pollLast();
    }
    
    /** Get the front element. */
    public int peek() {
        return stack.peekLast();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
