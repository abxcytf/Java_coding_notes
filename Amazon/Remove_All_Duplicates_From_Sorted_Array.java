/*
Remove All Duplicates From Sorted Array

Given a sorted array, remove the duplicates in place such that the duplicated element is all removed.
Return the length of the modified array.
*/

public class Solution {
  //two pointers
  //[0, processed data, slow(not included), not cared data, fast, to be scanned data]
  public static int removeAllDuplicates(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums.length;
        }

        int slow = 0;
        int fast = 1;

        boolean duplicateFlag = false;
        for (; fast < nums.length; fast++) {
            if (nums[slow] == nums[fast]) {
                duplicateFlag = true;
            } else if (duplicateFlag) {
                nums[slow] = nums[fast];
                duplicateFlag = false;
            } else {
                nums[++slow] = nums[fast];
            }
        }
        return duplicateFlag ? slow : slow + 1;
   }
}
