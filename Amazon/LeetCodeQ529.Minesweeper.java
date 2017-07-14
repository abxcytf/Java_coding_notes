/*
529. Minesweeper

Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 
'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no 
adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents 
how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), 
return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') 
and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change it to 
a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.
Example 1:
Input: 

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Example 2:
Input: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Note:
. The range of the input matrix's height and width is [1,50].
. The click position will only be an unrevealed square ('M' or 'E'), which also means the 
  input board contains at least one clickable square.
. The input board won't be a stage when game is over (some mines have been revealed).
. For simplicity, not mentioned rules should be ignored in this problem. For example, you 
  don't need to reveal all the unrevealed mines when the game is over, 
  consider any cases that you will win the game or flag any squares.
*/


public class Solution {
    //DFS solution
    public char[][] updateBoard(char[][] board, int[] click) {
        //asume inputs are all valid
        int m = board.length;
        int n = board[0].length;
        int row = click[0];
        int col = click[1];
        
        if (board[row][col] == 'M') {
            board[row][col] = 'X';
        } else {
            //Empty
            //get number of mines first
            int count = 0;
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    int r = row + i;
                    int c = col + j;
                    if (r < 0 || r >= m || c < 0 || c >= n) {
                        continue;
                    }
                    if (board[r][c] == 'M' || board[r][c] == 'X') {
                        count++;
                    }
                }
            }
            
            if (count > 0) {
                //If it is not a 'B', stop further DFS
                board[row][col] = (char)(count + '0');
            } else {
                // it is a 'B', continue DFS to adjacent cells
                board[row][col] = 'B';
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) {
                            continue;
                        } 
                        int r = row + i;
                        int c = col + j;
                        if (r < 0 || r >= m || c < 0 || c >= n) {
                            continue;
                        }
                        if (board[r][c] == 'E') {
                            updateBoard(board, new int[]{r, c});
                        }
                    }
                }
            }
        }
        return board;
    }
 
    /**************************************************************************************************/
    //optimized DFS solution
    private int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}; //8 directions
    private int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public char[][] updateBoard(char[][] board, int[] click) {
        //asume inputs are all valid
        int m = board.length;
        int n = board[0].length;
        int row = click[0];
        int col = click[1];
    
        if (board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        } 
        //if it is not a mine, find out if the mine around
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int r = row + dx[i];
            int c = col + dy[i];
            if (r < 0 || r >= m || c < 0 || c >= n) {
                continue;
            }
            if (board[r][c] == 'M') { 
            //if (board[r][c] == 'M' || board[r][c] == 'X') { 
                count++;
            }
        }
        if (count > 0) {
            //board[row][col] is next to a mine, so it should be a number
            board[row][col] = (char)(count + '0');
        } else {
            //board[row][col] is a 'E'
            //unreveal it to 'B'
            board[row][col] = 'B';
            //continue DFS to adjacent cells
            for (int i = 0; i < 8; i++) {
                int r = row + dx[i];
                int c = col + dy[i];
                if (r < 0 || r >= m || c < 0 || c >= n) {
                    continue;
                }
                if (board[r][c] == 'E') {
                    updateBoard(board, new int[]{r, c});
                }
            }
        }
        return board;
    }
 
 
    /*******************************************************************************************/
    //DFS solution
    private int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}; //8 directions
    private int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public char[][] updateBoard(char[][] board, int[] click) {
        dfsHelper(board, click);
        return board;
    }
    
    public void dfsHelper(char[][] board, int[] click) {
        //asume inputs are all valid
        int m = board.length;
        int n = board[0].length;
        int row = click[0];
        int col = click[1];
    
        if (board[row][col] == 'M') {
            board[row][col] = 'X';
            return;
        } 
        //if it is not a mine, find out if the mine around
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int r = row + dx[i];
            int c = col + dy[i];
            if (r < 0 || r >= m || c < 0 || c >= n) {
                continue;
            }
            if (board[r][c] == 'M') { 
            //if (board[r][c] == 'M' || board[r][c] == 'X') { 
                count++;
            }
        }
        if (count > 0) {
            //board[row][col] is next to a mine, so it should be a number
            board[row][col] = (char)(count + '0');
        } else {
            //board[row][col] is a 'E'
            //unreveal it to 'B'
            board[row][col] = 'B';
            //continue DFS to adjacent cells
            for (int i = 0; i < 8; i++) {
                int r = row + dx[i];
                int c = col + dy[i];
                if (r < 0 || r >= m || c < 0 || c >= n) {
                    continue;
                }
                if (board[r][c] == 'E') {
                    dfsHelper(board, new int[]{r, c});
                }
            }
        }
    }
    
    /************************************************************************************************/
 
    
}
