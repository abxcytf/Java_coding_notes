/*
414. Third Maximum Number

Given a non-empty array of integers, return the third maximum number in this array. 
If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
*/

public class Solution {
    //three pointers, time complexity O(n)
    public int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        if (nums.length == 1) {
            return nums[0];
        }
        
        if (nums.length == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }
        
        Integer max1 = null; 
        Integer max2 = null;
        Integer max3 = null;
        for (Integer num : nums) {
            if (num.equals(max1) || num.equals(max2) || num.equals(max3)) {
                continue;
            }
            if (max1 == null || max1 < num) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (max2 == null || max2 < num) {
                max3 = max2;
                max2 = num;
            } else if (max3 == null || max3 < num) {
                max3 = num;
            }
        }
        return max3 == null ? max1 : max3;
    }
    
    /************************************************************************************/
    //same idea as above using long, 运行时间比之前用Integer refer的快!!!!
    public int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        if (nums.length == 1) {
            return nums[0];
        }
        
        if (nums.length == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }
        
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;
    
        for (int num : nums) {
            if (max1 == num || max2 == num || max3 == num) {
                continue;
            }
            if (max1 == Long.MIN_VALUE || max1 < num) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (max2 == Long.MIN_VALUE || max2 < num) {
                max3 = max2;
                max2 = num;
            } else if (max3 == Long.MIN_VALUE || max3 < num) {
                max3 = num;
            }
        }
        return max3 == Long.MIN_VALUE ? (int)max1 : (int)max3;
    }
}
