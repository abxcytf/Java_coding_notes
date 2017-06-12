/*
47. Permutations II
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
*/

public class Solution {
    //DFS, optimized solution
    public List<List<Integer>> permuteUnique(int[] nums) {
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
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1] || visited[i]) {
                continue;
            }
            solution.add(nums[i]);
            visited[i] = true;
            helper(nums, result, solution, visited);
            solution.remove(solution.size() - 1);
            visited[i] = false;
        }
    }
}
