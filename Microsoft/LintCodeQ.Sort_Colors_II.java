/*
Given an array of n objects with k different colors (numbered from 1 to k), 
sort them so that objects of the same color are adjacent, 
with the colors in the order 1, 2, ... k.

. You are not suppose to use the library's sort function for this problem.
. k <= n

Example 
Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort. 
That will cost O(k) extra memory. Can you do it without using extra memory?
*/

class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    //TLE in lintcode
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length <= 1 || k <= 1) {
            return;
        }
        
        int left = 0;
        int right = colors.length - 1;
        int count = 0;
        while (count < k && left <= right) { //left < right works too
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = left; i <= right; i++) {
                min = Math.min(min, colors[i]);
                max = Math.max(max, colors[i]);
            }
            int current = left;
            while (current <= right) {
                if (colors[current] == max) {
                    swap(colors, current, right);
                    right--;
                } else if (colors[current] == min) {
                    swap(colors, current, left);
                    current++;
                    left++;
                } else {
                    current++;
                }
            }
            count += 2;
        }
    }
    
    
    private void swap(int[] colors, int i, int j) {
        if (colors[i] != colors[j]) {
            colors[i] ^= colors[j];
            colors[j] ^= colors[i];
            colors[i] ^= colors[j];
        }
    }
    
    /***************************************************************************************/
    //recursion implementation, TLE
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length <= 1 || k <= 1) {
            return;
        }
        helper(colors, 0, colors.length - 1, k);
    }
    
    private void helper(int[] colors, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        if (k <= 0) {
            return;
        }
        int current = left;
        while (current <= right) {
            if (colors[current] == k) {
                swap(colors, current, right);
                right--;
            } else {
                current++;
            }
        }
        
        helper(colors, left, right, k - 1);
    }
    
    
    private void swap(int[] colors, int i, int j) {
        if (colors[i] != colors[j]) {
            colors[i] ^= colors[j];
            colors[j] ^= colors[i];
            colors[i] ^= colors[j];
        }
    }
    
    /**********************************************************************************/
    //similar as above TLE
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length <= 1 || k <= 1) {
            return;
        }
        helper(colors, 0, colors.length - 1, k, 1);
    }
    
    private void helper(int[] colors, int left, int right, int k, int h) {
        if (left >= right) {
            return;
        }
        if (h >= k) {
            return;
        }
        int current = left;
        while (current <= right) {
            if (colors[current] == k) {
                swap(colors, current, right);
                right--;
            } else if (colors[current] == h) {
                swap(colors, current, left);
                current++;
                left++;
            } else {
                current++;
            }
        }
        
        helper(colors, left, right, k - 1, h + 1);
    }
    
    
    private void swap(int[] colors, int i, int j) {
        if (colors[i] != colors[j]) {
            colors[i] ^= colors[j];
            colors[j] ^= colors[i];
            colors[i] ^= colors[j];
        }
    }
    
    /************************************************************************************/
    // time complexity: O(nlogk), the best algorithm based on comparing
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length <= 1 || k <= 1) {
            return;
        }
        helper(colors, 0, colors.length - 1, 1, k);
    }
    
    private void helper(int[] colors, int left, int right, int colorFrom, int colorTo) {
        if (colorFrom >= colorTo) {
            return;
        }
        if (left >= right) {
            return;
        }
        
        int colorMid = (colorFrom + colorTo) >>> 1;
        int l = left;
        int r = right;
        while (l <= r) {
            while (l <= r && colors[l] <= colorMid) {
                l++;
            }
            while (l <= r && colors[r] > colorMid) {
                r--;
            }
            if (l <= r) {
                swap(colors, l++, r--);
            }
        }
        
        helper(colors, left, r, colorFrom, colorMid);
        helper(colors, l, right, colorMid + 1, colorTo);
    }
    
    private void swap(int[] colors, int i, int j) {
        if (colors[i] != colors[j]) {
            colors[i] ^= colors[j];
            colors[j] ^= colors[i];
            colors[i] ^= colors[j];
        }
    }
}
