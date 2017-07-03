/*
438. Find All Anagrams in a String

Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s 
and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/

public class Solution {
    //the idea is to use the sliding window, time complexity: O(n)
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }
        int[] map = new int[256]; //character hashmap
        //creating the sliding window
        //record each char in p into map
        for (char ch : p.toCharArray()) {
            map[ch]++;
        }
        int left = 0;
        int right = 0; 
        int count = p.length();
        //two pointers, intialize count to p's length
        while (right < s.length()) {
            //move right everytime, if the character existing in p's hashmap, decrease count
            if (map[s.charAt(right++)]-- > 0) {
                count--;
            }
            //when the count is donw to 0, means we found the right anagram
            //then we add the window left edge into the result list
            if (count == 0) {
                result.add(left);
            }
            
            //if we find the window side is equal to p's length
            //we have to move the left of the window to narrow the window to find the new match window
            //only increase the count if the character existing in p
            //when the map value >= 0 indiate it was existing in the p hashmap, cause it won't go below 0
            if (right - left == p.length() && map[s.charAt(left++)]++ >= 0) {
                //right is already incremented, so here is not right - left + 1!!!
                count++;
            }
        }
        return result;
    }
}
