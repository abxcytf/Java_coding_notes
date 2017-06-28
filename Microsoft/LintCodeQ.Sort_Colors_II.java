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
    public void sortColors2(int[] colors, int k) {
        // write your code here
        if (colors == null || colors.length <= 1 || k <= 1) return;
        int count = 0;
        
        int left = 0, right = colors.length - 1;
        while (count < k && left <= right) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = left; i <= right; i++) {
                min = Math.min(min, colors[i]);
                max = Math.max(max, colors[i]);
            }
            int cur = left;
            while (cur <= right) {
                if (colors[cur] == max) {
                    swap(colors, cur, right);
                    right--;
                } else if (colors[cur] == min) {
                    swap(colors, cur, left);
                    cur++;
                    left++;
                } else {
                    cur++;
                }
            }
            count += 2;
        }
    }

    private void swap(int[] colors, int i, int j) {
        int temp = colors[i];
        colors[i] = colors[j];
        colors[j] = temp;
    }
}
