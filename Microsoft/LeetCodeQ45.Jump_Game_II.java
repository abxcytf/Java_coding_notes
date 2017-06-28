/*
45. Jump Game II

Given an array of non-negative integers, 
you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. 
(Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Note:
. You can assume that you can always reach the last index.
*/

public class Solution {
    //DP implementation, time complexity: O(n^2), TLE in leetcode
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //state
        int[] steps = new int[nums.length];
        
        //initialize
        steps[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            steps[i] = Integer.MAX_VALUE; 
        }
        
        //function
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (steps[j] != Integer.MAX_VALUE && j + nums[j] >= i) {
                    steps[i] = Math.min(steps[i], steps[j] + 1);
                }
            }
        }
        
        //answer
        return steps[nums.length - 1];
    }
    
    /****************************************************************************/
    //Greedy
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int jumps = 0;
        while (end < nums.length - 1) { 
            //when end >= nums.length - 1
            //means already reach the target
            jumps++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (i + nums[i] > farthest) {
                    farthest = i + nums[i];
                }
            }
            start = end + 1;
            end = farthest;
        }
        return jumps;
    }
}
