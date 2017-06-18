/*
53. Maximum Subarray
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
*/

public class Solution {
    //brutal force, TLE in the leetcode, time: O(n^2), space: O(n)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = nums[i - 1] + preSum[i - 1]; 
        }
        
        for (int i = 0; i <= nums.length; i++) {
            for (int j = 0; j < i; j++) {
                max = Math.max(max, preSum[i] - preSum[j]);
            }
        }
        return max;
    }
  
    /***************************************************************************************/
    
    //optimized solution, time O(n), space: O(1)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int currentMax = nums[0];
        int globalMax = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(currentMax + nums[i], nums[i]);
            globalMax = Math.max(globalMax, currentMax);
        }
        return globalMax;
    }
    
    /*****************************************************************************************/
    //for follow up question, need to record the maximum subArray Index(start, end)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int currentMax = nums[0];
        int globalMax = nums[0];
        int startIndex = 0;
        int endIndex = 0;
        int globalStart = 0;
        int globalEnd = 0;
        for (int i = 1; i < nums.length; i++) {
            if (currentMax + nums[i] < nums[i]) {
                currentMax = nums[i];
                startIndex = i;
                endIndex = i;
            } else {
                currentMax = currentMax + nums[i];
                endIndex = i;
            }
            if (globalMax < currentMax) {
                globalMax = currentMax;
                globalStart = startIndex;
                globalEnd = endIndex;
            }
        }
        System.out.println(globalStart);
        System.out.println(globalEnd);
        return globalMax;
    }
}
