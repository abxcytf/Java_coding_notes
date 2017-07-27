/*
254. Factor Combinations

Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return 
all possible combinations of its factors.

Note: 
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples: 
input: 1
output: 
[]
input: 37
output: 
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
*/

public class Solution {
    //dfs 
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>>  result = new ArrayList<>();
        dfsHelper(result, new ArrayList<Integer>(), n, 2); 
        //2 is the first factor to try
        return result;
    }
    
    private void dfsHelper(List<List<Integer>> result, List<Integer> answer, int n, int start) {
        if (n <= 1) {
            //eliminate itself or 1
            if (answer.size() > 1) {
                result.add(new ArrayList<Integer>(answer));
            }
            return;
        }
        
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                answer.add(i);
                dfsHelper(result, answer, n / i, i);
                answer.remove(answer.size() - 1);
            }
        }
    }
}
