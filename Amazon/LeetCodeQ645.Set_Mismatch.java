/*
645. Set Mismatch

The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, 
one of the numbers in the set got duplicated to another number in the set, which results in 
repetition of one number and loss of another number.

Given an array nums representing the data status of this set after the error. Your task is to 
firstly find the number occurs twice and then find the number that is missing. 
Return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]
Note:
. The given array size will in the range [2, 10000].
. The given array's numbers won't have any order.
*/


public class Solution {
    //mathematically implementation, using HashSet
    //subtracting the elements from it
    //time O(n), space O(n)
    //sum = (a + b + c + d)
    //nums = {a, b, c, a}
    //duplicate = a;
    //sum - sum of nums = (d - a);
    //=> d = sum + a(duplicate)
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        int duplicate = 0; 
        int n = nums.length;
        long sum = (n * (n + 1)) / 2; //Equal difference series sum
        for (int num : nums) {
            if (set.contains(num)) {
                duplicate = num;
            }
            sum -= num;
            set.add(num);
        }
        return new int[]{duplicate, (int)sum + duplicate};
    }
    
    /***************************************************************************************************/
    //using Array Indexing,
    //time O(n), space O(n)
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] count = new int[nums.length + 1];
        for (int num : nums) {
            count[num]++;
        }
        int duplicated = -1;
        int missingNumber = -1;
        for (int i = 1; i < count.length; i++) {
            if (count[i] == 2) {
                duplicated = i;
            }
            if (count[i] == 0) {
                missingNumber = i;
            }
        }
        return new int[]{duplicated, missingNumber};
    }
    
    /***************************************************************************************************/
    //put each element k to the k-1 th position 
    //unless k-1th is occupied by k.
    //time O(n), space O(1)
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return new int[]{nums[i], i + 1};
            }
        }
        return null;
    }
    
    private void swap(int[] nums, int i ,int j) {
        if (nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
    
    /***************************************************************************************************/
    //using sign to mark
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] result = new int[2];
        for (int i : nums) {
            if (nums[Math.abs(i) - 1] < 0) {
                result[0] = Math.abs(i);
            } else {
                nums[Math.abs(i) - 1] *= -1;
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result[1] = i + 1;
            }
        }
        return result;
    }
}
