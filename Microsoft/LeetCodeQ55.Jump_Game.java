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
    //backtracking, TLE
    public boolean canJump(int[] nums) {
        return canJumpFromPosition(nums, 0);
    }
    
    private boolean canJumpFromPosition(int[] nums, int pos) {
        if (pos == nums.length - 1) {
            return true;
        }
        int furthestJump = Math.min(pos + nums[pos], nums.length - 1);
        
        for (int nextPos = pos + 1; nextPos <= furthestJump; nextPos++) {
            if (canJumpFromPosition(nums, nextPos)) {
                return true;
            }
        }
        return false;
    }
    
    /**********************************************************************************/
    
    
}
