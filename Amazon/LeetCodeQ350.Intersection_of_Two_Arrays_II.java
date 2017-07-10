/*
350. Intersection of Two Arrays II

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
. Each element in the result should appear as many times as it shows in both arrays.
. The result can be in any order.
Follow up:
. What if the given array is already sorted? How would you optimize your algorithm?
. What if nums1's size is small compared to nums2's size? Which algorithm is better?
. What if elements of nums2 are stored on disk, and the memory is limited such 
  that you cannot load all elements into the memory at once?
*/

/*
Ideas to answer the follow up questions:

What if the given array is already sorted? How would you optimize your algorithm?
==>two pointers

What if nums1's size is small compared to nums2's size? Which algorithm is better?
==>hash map

What if elements of nums2 are stored on disk, and the memory is limited such that you 
cannot load all elements into the memory at once?
==> binary search
. If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, 
  read chunks of array that fit into the memory, and record the intersections.

. If both nums1 and nums2 are so huge that neither fit into the memory, 
  sort them individually (external sort), then read 2 elements from 
  each array at a time in memory, record intersections.
*/

public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        
        int[] result = new int[list.size()];
        int index = 0;
        for (Integer num : list) {
            result[index++] = num;
        }
        return result;
    }
}
