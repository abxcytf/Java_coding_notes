/*
189. Rotate Array

Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
*/

public class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k < 1) {
            return;
        }
        
        int len = nums.length;
        k = k % len;
        
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k , len - 1);
    }
    
    private void reverse(int[] nums, int i, int j) {
        while (i <= j) {
            if (nums[i] != nums[j]) {
                nums[i] ^= nums[j];
                nums[j] ^= nums[i];
                nums[i] ^= nums[j];
            }
            i++;
            j--;
        }
    }
}
