/*
90. Subsets II
Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

public class Solution {

    //DFS, back tracking
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) return result;
        if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        Arrays.sort(nums);
        helper(nums, 0, result, new ArrayList<Integer>());
        return result;
    }
    
    private void helper(int[] nums, int index, List<List<Integer>> result, List<Integer> path) {
        result.add(new ArrayList<>(path));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) continue;
            path.add(nums[i]);
            helper(nums, i + 1, result, path);
            path.remove(path.size() - 1);
        }
    }
  
    /*****************************************************/
    //iteration
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) return result;
        result.add(new ArrayList<Integer>());
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int dupCount = 0;
            while (i + 1 < nums.length && nums[i] == nums[i+1]) { //count the duplicates number
                dupCount++;
                i++;
            }
            int prevNum = result.size();
            for (int j = 0; j < prevNum; j++) {
                List<Integer> subset = new ArrayList(result.get(j));
                for (int k = 0; k <= dupCount; k++) { //treat the duplicates as a whole component add into previous result
                    subset.add(nums[i]);
                    result.add(new ArrayList<>(subset));
                }
            }
        }
        return result;
    }
}
