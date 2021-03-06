/*
268. Missing Number
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, 
find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. 
Could you implement it using only constant extra space complexity?
*/

public class Solution {

    //time complexity: O(n^2), space complexity O(1)
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int i = 0;
        while (i < nums.length) {
            while (nums[i] != i && nums[i] < nums.length) {
                swap(nums, i, nums[i]);
            }
            i++;
        }
        
        for (i = 0; i < nums.length; i++) {
            if  (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }
    
    private void swap(int[] nums, int i, int j) {
        if (nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
    
    /********************************************************************************/
    //time O(n), space O(1), 数学方法，等差数列求和，首项加末项乘以项数除以2
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        //等差数列求和
        int sum = (0 + len) * (len + 1) / 2;
        for (int i = 0; i < len; i++) {
            sum -= nums[i];
        }
        return sum;
    }
    
    /********************************************************************************/
    //time O(n), space O(1),巧妙运用bit manipulation，相同数字异或为0
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result ^= i;
            result ^= nums[i];
        }
        return result;
    }
}
