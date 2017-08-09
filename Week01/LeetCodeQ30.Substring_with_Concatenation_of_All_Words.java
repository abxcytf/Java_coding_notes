/*
30. Substring with Concatenation of All Words

You are given a string, s, and a list of words, words, that are all of the same length. Find all starting 
indices of substring(s) in s that is a concatenation of each word in words exactly once and without any 
intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
*/

public class Solution {
    //time complexity: O((s.len - words.len) * words.len * words.len)
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        
        int len = words[0].length(); //length of each word
        
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        for (int start = 0; start <= s.length() - words.length * len; start++) {
            Map<String, Integer> copy = new HashMap<>(map);
            for (int i = 0; i < words.length; i++) {
                //substring[startIndex, endIndex)
                String toCheck = s.substring(start + i * len, start + i * len + len);
                if (copy.containsKey(toCheck)) {
                    int count = copy.get(toCheck);
                    if (count == 1) {
                        copy.remove(toCheck);
                    } else {
                        copy.put(toCheck, count - 1);
                    }
                    if (copy.isEmpty()) {
                        result.add(start);
                        break;
                    }
                } else {
                    break;
                }
            }    
        }
        return result;
    }
}
