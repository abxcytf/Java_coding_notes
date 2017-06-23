/*
162. Find Peak Element

A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

Note:
Your solution should be in logarithmic complexity.
*/

public class Solution {
    public int findPeakElement(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    
    private int helper(int[] nums, int start, int end) {
        if (start == end) {
            return start;
        } else if (start + 1 == end) { //-00, start, end, -00
            if (nums[start] > nums[end]) {
                return start;
            } else {
                return end;
            }
        } else {
            int mid = (start + end) >>> 1;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid - 1] > nums[mid] && nums[mid] > nums[mid + 1]) { //start > mid - 1 > mid > mid + 1
                //peak will appear on the left side
                return helper(nums, start, mid - 1);
            } else { //start < mid - 1 < mid < mid + 1
                return helper(nums, mid + 1, end);
            }
        }
    }
}
