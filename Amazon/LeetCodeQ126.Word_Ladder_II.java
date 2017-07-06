/*
126. Word Ladder II

Given two words (beginWord and endWord), and a dictionary's word list, 
find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
. Return an empty list if there is no such transformation sequence.
. All words have the same length.
. All words contain only lowercase alphabetic characters.
. You may assume no duplicates in the word list.
. You may assume beginWord and endWord are non-empty and are not the same.
*/

public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ladders = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        Set<String> dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }
        dict.add(beginWord);
        //dict.add(endWord);  //the endWord may be not in the dict
        
        bfsHelper(map, distance, beginWord, endWord, dict);
        
        List<String> path = new ArrayList<>();
        
        dfsHelper(ladders, path, endWord, beginWord, distance, map);
        
        return ladders;
    }
    
    private void bfsHelper(Map<String, List<String>> map, Map<String, Integer> distance, String start, String end, Set<String> dict) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);
        for (String word : dict) {
            map.put(word, new ArrayList<String>());
        }
        while (!queue.isEmpty()) {
            String current = queue.poll();
            List<String> nextList = generateNextList(current, dict);
            for (String next : nextList) {
                map.get(next).add(current);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(current) + 1);
                    queue.offer(next);
                }
            }
        }
    }
    
    private List<String> generateNextList(String word, Set<String> dict) {
        List<String> nextList = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == word.charAt(i)) {
                    continue;
                }
                char[] chars = word.toCharArray();
                chars[i] = ch;
                String nextWord = new String(chars);
                if (dict.contains(nextWord)) {
                    nextList.add(nextWord);
                }
            }
        }
        return nextList;
    }
    
    private void dfsHelper(List<List<String>> ladders, List<String> path, String current, String start, Map<String, Integer> distance, Map<String, List<String>> map) {
        path.add(current);
        if (current.equals(start)) {
            Collections.reverse(path);
            ladders.add(new ArrayList<>(path));
            Collections.reverse(path);
        } else {
            if (map.get(current) == null) {
                return;
            }
            for (String next : map.get(current)) {
                if (distance.containsKey(next) && distance.get(current) == distance.get(next) + 1) {
                    dfsHelper(ladders, path, next, start, distance, map);
                }
            }    
        }
        path.remove(path.size() - 1);
    }
}
