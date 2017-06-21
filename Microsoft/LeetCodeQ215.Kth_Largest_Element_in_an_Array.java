/*
215. Kth Largest Element in an Array

Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, 
not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int left = 0;
        int right = nums.length - 1;
        while (k > 0) {
            int index = partition(nums, left, right);
            int count = index - left + 1; 
            //how many element on the left after partition
            if (count == k) {
                return nums[index];
            } else if (count < k) {
                k -= count;
                left = index + 1;
            } else { //index is on the right side of the Kth element
                right = index - 1;
            }
        }
        return nums[left];
    }
    
    private int partition(int[] nums, int left, int right) {
        if (left == right) {
            return left;
        }
        int pivotId = (left + right) >>> 1;
        int pivot = nums[pivotId];
        int l = left;
        int r = right;
        swap(nums, pivotId, left);
        while (l <= r) {
            if (nums[l] < pivot && nums[r] > pivot) {
                swap(nums, l++, r--);
            }
            if (nums[l] >= pivot) {
                l++;
            }
            if (nums[r] <= pivot) {
                r--;
            }
        }
        swap(nums, left, r);
        return r;
    }

    
    private void swap(int[] nums, int i, int j) {
        if (nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
}
