/*
127. Word Ladder

Given two words (beginWord and endWord), and a dictionary's word list, 
find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. 
Note that beginWord is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
. Return 0 if there is no such transformation sequence.
. All words have the same length.
. All words contain only lowercase alphabetic characters.
. You may assume no duplicates in the word list.
. You may assume beginWord and endWord are non-empty and are not the same.
*/

public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }
        
        HashSet<String> hash = new HashSet<>(); //to remove duplicates
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord); 
        hash.add(beginWord);
        
        int length = 1;
        while (!queue.isEmpty()) {
            length++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String nextWord : getNextWords(word, dict)) {
                    if (hash.contains(nextWord)) {
                        continue;
                    }
                    if (nextWord.equals(endWord)) {
                        return length;
                    }
                    hash.add(nextWord);
                    queue.offer(nextWord);
                }
            }
        }
        return 0;
    }
    
    private List<String> getNextWords(String word, Set<String> dict) {
        List<String> nextWords = new ArrayList<>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < word.length(); i++) {
                if (ch == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, ch);
                if (dict.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }
    
    private String replace(String s, int index, char ch) {
        char[] chars = s.toCharArray();
        chars[index] = ch;
        return new String(chars);
    }
}
