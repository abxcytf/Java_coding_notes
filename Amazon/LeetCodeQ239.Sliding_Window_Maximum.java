/*
239. Sliding Window Maximum

Given an array nums, there is a sliding window of size k which is moving from 
the very left of the array to the very right. 
You can only see the k numbers in the window. 
Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
*/


public class Solution {
    //time complexity O(n), using monotonic queue/deque
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        //monotonic queue
        Deque<Integer> queue = new ArrayDeque<>();
        
        //need to calculate the size
        int[] result = new int[nums.length - k + 1]; 
        int index = 0;
        
        //compose window from 0 to k - 2
        for (int i = 0; i < k - 1; i++) {
            inQueue(queue, nums[i]);
        }
        
        for (int i = k - 1; i < nums.length; i++) {
            inQueue(queue, nums[i]); //now the window size is k
            result[index++] = queue.peekFirst();
            outQueue(queue, nums[i - (k - 1)]);
        }
        return result;
    }
    
    private void inQueue(Deque<Integer> queue, int num) {
        while (!queue.isEmpty() && queue.peekLast() < num) {
            queue.pollLast();
        }
        queue.offerLast(num);
    }
    
    private void outQueue(Deque<Integer> queue, int num) {
        if (queue.peekFirst() == num) {
            queue.pollFirst();
        }
    }
}
