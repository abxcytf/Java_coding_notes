/*
46. Permutations
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/

public class Solution {
  
  //brutal force
  public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      if (nums == null || nums.length == 0) {
          return result;
      }
      Arrays.sort(nums);
      result.add(new ArrayList<Integer>());
      for(int i = 0; i < nums.length; i++) {
          List<List<Integer>> newResult = new ArrayList<>();
          for (int j = 0; j <= i; j++) {
              for (List<Integer> list : result) {
                  List<Integer> newList = new ArrayList<>(list);
                  newList.add(j, nums[i]);
                  newResult.add(newList);
              }
          }
          result = newResult;
       }
       return result;
   }
  
  /*****************************************************************************************************************/
  //brutal force
  public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      if (nums == null || nums.length == 0) {
          return result;
      }
      Arrays.sort(nums);
      result.add(new ArrayList<Integer>());
      for (int i = 0; i < nums.length; i++) {
          List<List<Integer>> newResult = new ArrayList<>();
          for (List<Integer> list : result) {
              for (int j = 0; j <= i; j++) {
                  List<Integer> newList = new ArrayList<>(list);
                  newList.add(j, nums[i]);
                  newResult.add(newList);   
              }
          }
          result = newResult;
      }
      return result;
  }
  
  /*****************************************************************************************************************/
  //DFS
  public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        helper(nums, result, new ArrayList<Integer>(), visited);
        return result;
    }
    
    private void helper(int[] nums, List<List<Integer>> result, List<Integer> solution, boolean[] visited) {
        if (solution.size() == nums.length) {
            result.add(new ArrayList<>(solution));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            solution.add(nums[i]);
            helper(nums, result, solution, visited);
            solution.remove(solution.size() - 1);
            visited[i] = false;
        }
    }
}
