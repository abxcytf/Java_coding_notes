/*
153. Find Minimum in Rotated Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

public class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }
        int left = 0;
        int right = nums.length - 1;
        
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            //mean after rotate, the min is on the right side of the array
            if (nums[mid] > nums[left] && nums[mid] > nums[right]) {
                left = mid;
            } else { //min is on the left side
                right = mid;
            }
        }
        
        return nums[left] < nums[right] ? nums[left] : nums[right];
    }
}
