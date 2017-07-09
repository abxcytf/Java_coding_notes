/*
349. Intersection of Two Arrays

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
. Each element in the result must be unique.
. The result can be in any order.
*/

public class Solution {

    //Set implementation, time complexity O(n), space complexity O(n)
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                intersect.add(nums2[i]);
            }
        }
        
        int[] result = new int[intersect.size()];
        int index = 0;
        for (Integer num : intersect) {
            result[index++] = num;
        }
        return result;
    }
    
    /***************************************************************************************/
    
    
}
