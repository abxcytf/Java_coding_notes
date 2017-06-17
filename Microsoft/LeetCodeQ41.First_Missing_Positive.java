/*
41. First Missing Positive
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/

public class Solution {
    //time O(n^2), space O(1)
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            // while (nums[i] - 1 >= 0 && nums[i] - 1 < nums.length && nums[i] != nums[nums[i] - 1]) {
            //     swap(nums, i, nums[i] - 1);
            // }
            
            //can not be nums[i] != i + 1 因为有重复e.g.([1, 1, 1])的话会造成无线循环
            if (nums[i] - 1 >= 0 && nums[i] - 1 < nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
                i--;
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
    
    private void swap(int[]nums, int i, int j) {
        if (nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
    
    /***********************************************************************************/
    //time O(n), space O(1)
    //partition first, then use the scan
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        
        //partition, 
        //make nums between [0, nums.length - 1] to the beginning of the input array
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            while (start < nums.length && nums[start] > 0) {
                start++;
            }
            while (end >= 0 && nums[end] <= 0) {
                end--;
            }
            
            if (start <= end) {
                swap(nums, start, end);
            }
        }
        
        for (int i = 0; i < start; i++) {
            int abs = Math.abs(nums[i]);
            // if (abs <= nums.length) {
            //     nums[abs - 1] = - Math.abs(nums[abs - 1]);
            // }
            if (abs <= nums.length && nums[abs - 1] > 0) {
                nums[abs - 1] = -nums[abs - 1];
            }
            
        }
        
        for (int i = 0; i < start; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return start + 1;
    }
    
    /***********************************************************************************/
    //optimized solution, time O(n), space O(1), but need to change the signature input array
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        //change input array, to mark the inrelevant number
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) {
                nums[i] = nums.length + 1;
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            int abs = Math.abs(nums[i]);
            if (abs <= nums.length) {
                nums[abs - 1] = -Math.abs(nums[abs - 1]);
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
