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
    
    /********************************************************************************************************/
    //two pointers, i is the location of the processed(not included)
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int i = 0;
        int j = 0;
        
        while (j < nums.length) {
            while (j > 1 && i > 1 && j < nums.length && nums[j] == nums[i - 1] && nums[j] == nums[i - 2] ) { 
                j++;
            }
            
            if (j >= nums.length) {
                break;
            }
            nums[i++] = nums[j++];
        }
        return i;
    }
    
    /**********************************************************************************************************/
    public int removeDuplicates(int[] nums) {
        return duplicatesAllowedAtMostKTimes(nums, 2);
    }
    
    private int duplicatesAllowedAtMostKTimes(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (k >= nums.length) {
            return nums.length;
        }
        int i = 1;
        int j = 1;
        int count = 1;
        while (j < nums.length) {
            if (nums[j] != nums[j - 1]) {
                count = 1;
                nums[i++] = nums[j];
            } else {
                if (count < k) {
                    nums[i++] = nums[j];
                    count++;
                }
            }
            j++;
        }
        return i;
    }
}
