/*
646. Maximum Length of Pair Chain

You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can 
be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. 
You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]
Note:
. The number of given pairs will be in the range [1, 1000].
*/

public class Solution {
    //dp implementation, time O(n^2), space O(n)
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0) {
            return 0;
        }
    
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int result = 0;
        int[] dp = new int[pairs.length];
        //initialization
        for (int i = 0; i < pairs.length; i++) {
            dp[i] = 1;
        }
        
        for (int i = 1; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            result = Math.max(result, dp[i]);
        }
        
        return result;
    }
    
    /*********************************************************************************************************/
    //optimized solution, the idea is from merge intervals, time: O(nlogn), space: O(1)
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0) {
            return 0;
        }
        
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]); //O(nlogn);
        int result = 1;
        int currentEnd = pairs[0][1];
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > currentEnd) {
                result++;
                currentEnd = pairs[i][1];
            }
        }
        return result;
    }
}
