/*
42. Trapping Rain Water

Given n non-negative integers representing an elevation map where the width 
of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
In this case, 6 units of rain water (blue section) are being trapped. 
*/

public class Solution {
    //time complexity O(n^2)
    //brutal force, calculate on each storage of trapping water on each height
    public int trap(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = 0;
            int rightMax = 0;
            for (int l = 0; l < i; l++) {
                leftMax = Math.max(leftMax, height[l]);
            }
            
            for (int r = i + 1; r < height.length; r++) {
                rightMax = Math.max(rightMax, height[r]);
            }
            result += Math.max(0, Math.min(leftMax, rightMax) - height[i]);
        }
        return result;
    }
    
    
    /************************************************************************************/
    //two pointers, optimized solution, time complexity O(n)
    public int trap(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int leftHeight = height[left];
        int rightHeight = height[right];
        int result = 0;
        while (left < right) {
            if (leftHeight < rightHeight) {
                left++;
                if (leftHeight > height[left]) {
                    //if leftHeight > height[left], update the result
                    result += leftHeight - height[left];
                } else {
                    //if leftHeight < height[left], update the leftHeight to larger one
                    leftHeight = height[left];
                }
            } else {
                right--;
                if (rightHeight > height[right]) {
                    //if rightHeight > height[right], update the result
                    result += rightHeight - height[right];
                } else {
                    //if rightHeight < height[right], update the rightHeight to larger one
                    rightHeight = height[right];
                }
            }
        }
        return result;
    }
}
