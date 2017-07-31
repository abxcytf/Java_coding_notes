/*
26. Remove Duplicates from Sorted Array

Given a sorted array, remove the duplicates in place such that each element appear 
only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 
respectively. It doesn't matter what you leave beyond the new length.
*/

public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums.length;
        }
        
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[index]) {
                nums[++index] = nums[i];
            }
        }
        
        return index + 1;  //index is current the last index, +1 becomes the length
    }
    
    /*************************************************************************************************/
    //two pointers, before i(not included is processed)
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            while (j > 0 && j < nums.length && nums[j] == nums[j - 1]) {
                j++;
            }
            if (j >= nums.length) {
                break;
            }
            nums[i++] = nums[j++];
        }
        return i;
    }
}
