/*
118. Pascal's Triangle

Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

public class Solution {

    /*
    e.g. k = 5
    1
    1,1
    1,2,1
    1,3,3,1
    1,4,6,4,1
    */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows < 1) {
            return result;
        }
        List<Integer> row = new ArrayList<>();
        row.add(1);
        result.add(new ArrayList<>(row));
        
        for (int i = 2; i <= numRows; i++) { 
            //i is current the size of each row
            int prev = 1;
            for (int j = 1; j < i - 1; j++) {
                int temp = row.get(j);
                row.set(j, row.get(j) + prev);
                prev = temp;
            }
            row.add(1);
            result.add(new ArrayList<>(row));
        }
        return result;
    }
    
    /*************************************************************/
    /*
    i = 0 {1} =>              {1}
    i = 1 {1,1} =>           {1,1}
    i = 2 {1,1,1} =>        {1,2,1}
    i = 3 {1,1,2,1} =>     {1,3,3,1}
    i = 4 {1,1,3,3,1} =>  {1,4,6,4,1}
    */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows < 1) {
            return result;
        }
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
            result.add(new ArrayList<>(row));
        } 
        return result;
    }
}
