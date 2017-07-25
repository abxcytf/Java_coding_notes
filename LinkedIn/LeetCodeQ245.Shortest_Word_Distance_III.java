/*
245. Shortest Word Distance III

This is a follow up of Shortest Word Distance. The only difference is now 
word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest 
distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.
*/

public class Solution {
    //same idea as Shortest Word Distance I, two pointers
    //time O(n), space O(1)
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (words == null || words.length <= 1) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        int p1 = -1;
        int p2 = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                p1 = i;
            } 
            if (words[i].equals(word2)) {
                if (word1.equals(word2)) {
                    p1 = p2; //update previous pointer
                }
                p2 = i; //update current point;
            }
            if (p1 != -1 && p2 != -1) {
                result = Math.min(result, Math.abs(p1 - p2));
            }
        }
        return result;
    }

    /******************************************************************************************/
    //optimized
    //only compare word1 and word2 once, time O(n), space O(1)
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (words == null || words.length <= 1) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        boolean same = word1.equals(word2); //use boolean to check only compare once
        int p1 = -1;
        int p2 = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (same) {
                    p1 = p2;
                    p2 = i;
                } else {
                    p1 = i;
                }
            } else if (words[i].equals(word2)) {
                p2 = i;
            }
            if (p1 != -1 && p2 != -1) {
                result = Math.min(result, Math.abs(p1 - p2));
            }
        }
        return result;
    }
}
