/*
1. Two Sum
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/

public class Solution {
    //这个题目因为要返回indices，不能用排序后two pointers的方法因为不是返回个数和值
    public int[] twoSum(int[] numbers, int target) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        //can not change the original nums, since it need to return the indice
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                result[0] = map.get(nums[i]);
                result[1] = i;
            } else {
                map.put(target - nums[i], i);
            }
        }
        return result;
    }
}
