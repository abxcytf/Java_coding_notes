/*
80. Remove Duplicates from Sorted Array II

Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five 
elements of nums being 1, 1, 2, 2 and 3. It doesn't matter 
what you leave beyond the new length.
*/

public class Solution {
    //start is the location of the processed(not included)
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int start = 0;
        for (int end = 0; end < nums.length; end++) {
            if (end < 2 || nums[end] != nums[start - 2]) {
                nums[start++] = nums[end];
            }
        }
        return start;
    }
}
