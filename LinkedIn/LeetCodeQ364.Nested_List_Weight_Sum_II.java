/*
364. Nested List Weight Sum II

Given a nested list of integers, return the sum of all integers 
in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements 
may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, 
now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, 
and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 
6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    
    //DFS implementation
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int height = dfsHelper(nestedList);
        int result = getSum(nestedList, height);
        return result;
    }
    
    //get the height/depth of the solution tree
    private int dfsHelper(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int height = 0;
        for (NestedInteger nestedInt : nestedList) {
            if (nestedInt.isInteger()) {
                height = Math.max(height, 1);
            } else {
                height = Math.max(height, dfsHelper(nestedInt.getList()) + 1);
            }
        }
        return height;
    }
    
    //dfs recursion to get the sum result
    private int getSum(List<NestedInteger> nestedList, int height) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int sum = 0;
        for (NestedInteger nestedInt : nestedList) {
            if (nestedInt.isInteger()) {
                sum += nestedInt.getInteger() * height;
            } else {
                sum += getSum(nestedInt.getList(), height - 1);
            }
        }
        return sum;
    }
    
    /************************************************************************************************************/
    //BFS implementation
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        Deque<NestedInteger> queue = new LinkedList<>();
        int prev = 0;
        int total = 0;
        for (NestedInteger nestedInt : nestedList) {
            queue.offerLast(nestedInt);
        }
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger current = queue.pollFirst();
                if (current.isInteger()) {
                    levelSum += current.getInteger();
                }
                List<NestedInteger> nextList = current.getList();
                if (nextList != null) {
                    for (NestedInteger next : nextList) {
                        queue.offerLast(next);
                    }
                }
            }
            prev += levelSum;
            total += prev;
        }
        return total;
    }
    
    /*********************************************************************************************/
    //same idea as above but optimized solution, 时间复杂度如何分析？
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int unweighted = 0;
        int weighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger nestedInt : nestedList) {
                if (nestedInt.isInteger()) {
                    unweighted += nestedInt.getInteger();
                } else {
                    nextLevel.addAll(nestedInt.getList());
                }
            }
            weighted += unweighted;
            nestedList = nextLevel;
        }
        return weighted;
    }
}
