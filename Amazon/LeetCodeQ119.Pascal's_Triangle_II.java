/*
119. Pascal's Triangle II

Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
. Could you optimize your algorithm to use only O(k) extra space?
*/

public class Solution {
    
    /*
    e.g. k = 4
    0,1,0,0,0,0  i = 0
    0,1,1,0,0,0  i = 1
    0,1,2,1,0,0  i = 2
    0,1,3,3,1,0  i = 3
    0,1,4,6,4,1  i = 4
    */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>(rowIndex + 2);
        for (int i = 0; i < rowIndex + 2; i++) {
            result.add(0);
        }
        result.set(1, 1);
        for (int i = 0; i < rowIndex; i++) {
            for (int j = rowIndex + 1; j > 0; j--) {
                result.set(j, result.get(j) + result.get(j - 1));
            }
        }
        result.remove(0);
        return result;
    }
    
    /*********************************************************************/
    /*
    e.g. rowIndex = 4
    1,1,1,1,1
    1,2,1,1,1
    1,3,3,1,1
    1,4,6,4,1 
    */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>(rowIndex + 1);
        for (int i = 0; i < rowIndex + 1; i++) {
            result.add(1);
        }
        for (int i = 1; i <= rowIndex; i++) {
            int prev = result.get(0);
            for (int j = 1; j < i; j++) {
                int temp = result.get(j);
                result.set(j, result.get(j) + prev);
                prev = temp;
            }
        }
        return result;
    }
    
    /******************************************************************/
    /*
                1
              1,1   =>  1,1,1
            1,2,1  =>  1,1,2,1
          1,3,3,1 =>  1,1,3,3,1
        1,4,6,4,1 ...
    */
    //space complexity O(k)
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        if (rowIndex < 0) {
            return result;
        }
        for (int i = 0; i < rowIndex + 1; i++) {
            result.add(0, 1);
            for (int j = 1; j < result.size() - 1; j++) {
                result.set(j, result.get(j) + result.get(j + 1));
            }
        }
        return result;
    }
}
