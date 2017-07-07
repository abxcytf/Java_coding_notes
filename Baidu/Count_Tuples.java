/*
Count Tuples

You're given an array A where each element is one of 1, 2, or 3.
The elements are not sorted. For example, we might have something like:
 
  A = [2, 1, 1, 2, 3, 3, 2, 1, 3, 2, 1, 3]
 
Write an algorithm to determine how many tuples (i, j, k) there are
such that i < j < k and A[i] = 1, A[j] = 2, A[k] = 3.
 
In words: In how many ways can you find 1, 2, 3 in order in the array?
 
Some examples
 
A = [3] => 0
A = [2, 1, 3] => 0
A = [1, 2, 3] => 1
A = [1, 1, 2, 3, 2] => 2
A = [1, 1, 2, 2, 3, 3] => 8
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    /*
 * Complete the function below.
 
 A = [1, 1, 2, 2, 3, 3]
 
 {(i,j,k)} = {(0,2,4), (1,2,4), (0,3,4), (1,3,4),(0,2,5), (1,2,5), (0,3,5), (1,3,5)}
 
 */

    private static int count_ascending_tuples(int[] A) {
        if (A == null || A.length <= 2) {
            return 0;
        } 
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfsHelper(A, result, path, 0);
        // for (List<Integer> list : result) {
        //     for (Integer num : list) {
        //         System.out.println(num);
        //     }
        //     System.out.println("===============================");
        // }
        return result.size();
    }
    
    
    private static void dfsHelper(int[] A, List<List<Integer>> result, List<Integer> path, int pos) {
        if (path.size() == 3) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = pos; i < A.length; i++) {
            
            int size = path.size(); //size = 0
            
            if (path.size() == 0 && A[i] == 1) {
                path.add(i); //path = {0, } 
                dfsHelper(A, result, path, pos + 1); 
            } else if (path.size() > 0 && path.get(path.size() - 1) < i && A[i] == A[path.get(path.size() - 1)] + 1) {
                path.add(i); //{0, 2, }
                dfsHelper(A, result, path, pos + 1);
            }
            
            if (path.size() > size) {
                path.remove(path.size() - 1);
            }
        } 
    }

    public static void main(String[] args) throws IOException{
        int[] A = {1, 1, 2, 2, 3, 3};
        System.out.println(count_ascending_tuples(A));
    }
}
