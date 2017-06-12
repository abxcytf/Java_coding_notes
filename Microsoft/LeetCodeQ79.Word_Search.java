/*
79. Word Search
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

public class Solution {
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == chars[0]) {
                    if (helper(board, chars, i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean helper(char[][] board, char[] chars, int i, int j, int index) {
        if (index == chars.length) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (board[i][j] != chars[index]) {
            return false;
        }
        board[i][j] ^= 256;
        boolean result = helper(board, chars, i + 1, j, index + 1)
                        || helper(board, chars, i - 1, j, index + 1)
                        || helper(board, chars, i, j + 1, index + 1)
                        || helper(board, chars, i, j - 1, index + 1); 
        board[i][j] ^= 256;
        return result;
    }
}
