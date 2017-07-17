/*
207. Course Schedule

There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have 
to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, 
is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you 
should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you 
should have finished course 0, and to take course 0 you should 
also have finished course 1. So it is impossible.

Note:
. The input prerequisites is a graph represented by a list of edges, 
  not adjacency matrices. Read more about how a graph is represented.
. You may assume that there are no duplicate edges in the input prerequisites.
*/

public class Solution {
    //BFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return false;
        }
        List[] edges = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]]++;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                queue.offerLast(i);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.pollFirst();
            count++;
            int n = edges[course].size();
            for (int i = 0; i < n; i++) {
                int pointer = (int)edges[course].get(i);
                degree[pointer]--;
                if (degree[pointer] == 0) {
                    queue.offerLast(pointer);
                }
            }
        }
        return count == numCourses;
    }
}
