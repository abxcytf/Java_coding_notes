/*
4. Median of Two Sorted Arrays

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
*/

public class Solution {
    //binary search, time: O(log(Min(m, n))), m,n is the input array length
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if (len % 2 == 1) {//odd len
            return findKth(nums1, 0, nums2, 0, len / 2 + 1);
        } else { //even len
            return (findKth(nums1, 0, nums2, 0, len / 2) + findKth(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
        }
    }
    
    //find kth number of two sorted array
    private int findKth(int[] A, int AStart, int[] B, int BStart, int k) {
        if (AStart >= A.length) {
            return B[BStart + k - 1];
        }
        
        if (BStart >= B.length) {
            return A[AStart + k - 1];
        }
        
        if (k == 1) {
            return Math.min(A[AStart], B[BStart]);
        }
        
        // find the value of (k/2)th value in A
        int AKey = (AStart +  k / 2 - 1 < A.length) ? A[AStart + k / 2 - 1] : Integer.MAX_VALUE;
        
        //note: if AStart + k / 2 - 1 >= A.length, which mean A run out, the answer have to be in B, so make AKey max_value
        
        //find the value of (k/2)th value in B
        int BKey = (BStart + k / 2 - 1 < B.length) ? B[BStart + k / 2 - 1] : Integer.MAX_VALUE;
        
        if (AKey < BKey) { //get rid of the ones between index [Astart, AStart + k / 2 - 1] inclusive in A
            return findKth(A, AStart + k / 2, B, BStart, k - k / 2);
        } else {
            //get rid of the ones between index [BStart, BStart + k / 2 - 1] inclusive in B
            return findKth(A, AStart, B, BStart + k / 2, k - k / 2);
        }
    }
}
