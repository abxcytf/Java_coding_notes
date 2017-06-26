/*
55. Jump Game

Given an array of non-negative integers, you are initially positioned at 
the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
*/

public class Solution {
    //backtracking, TLE, time: O(2^n), space O(n)
    public boolean canJump(int[] nums) {
        return canJumpFromPosition(nums, 0);
    }
    
    private boolean canJumpFromPosition(int[] nums, int pos) {
        if (pos == nums.length - 1) {
            return true;
        }
        int furthestJump = Math.min(pos + nums[pos], nums.length - 1);
        
        //for (int nextPos = pos + 1; nextPos <= furthestJump; nextPos++) {
        for (int nextPos = furthestJump; nextPos >= pos + 1; nextPos--) { 
            //start from the furthest is faster the previous implementation
            if (canJumpFromPosition(nums, nextPos)) {
                return true;
            }
        }
        return false;
    }
    
    /**********************************************************************************/
    //DP implementation
    public boolean canJump(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        dp[nums.length - 1] = 1;
        return canJumpFromPosition(nums, 0, dp);
    }
    
    private boolean canJumpFromPosition(int[] nums, int pos, int[] dp) {
        if (dp[pos] != -1) {
            return dp[pos] == 1;
        }
        
        int furthestJump = Math.min(pos + nums[pos], nums.length - 1);
        for (int nextPos = pos + 1; nextPos <= furthestJump; nextPos++) {
            if (canJumpFromPosition(nums, nextPos, dp)) {
                dp[pos] = 1;
                return true;
            }
        }
        dp[pos] = 0;
        return false;
    }
    
    /********************************************************************************/
    //optimized greedy solution, time O(n)
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
    
    /*********************************************************************************/
    public boolean canJump(int[] nums) {
        boolean[] can = new boolean[nums.length];
        can[0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (can[j] && j + nums[j] >= i) {
                    can[i] = true;
                    break;
                }
            }
        }
        return can[nums.length - 1];
    }
}
