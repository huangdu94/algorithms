package work.huangdu.exploration.intermediate_algorithms.backtrack;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 示例:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 * @author huangdu
 * @version 2020/7/9 0:24
 */
public class Exist {
    static class Solution {
        public boolean exist(char[][] board, String word) {
            int m = board.length, n = board[0].length, len = word.length();
            int[] directions = {0, 1, 0, -1, 0};
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        if (dfs(board, word, m, n, len, directions, visited, 1, i, j)) {return true;}
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][] board, String word, int m, int n, int len, int[] directions, boolean[][] visited, int i, int x, int y) {
            if (i == len) {return true;}
            visited[x][y] = true;
            for (int k = 0; k < 4; k++) {
                int nextX = x + directions[k], nextY = y + directions[k + 1];
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextX][nextY] && board[nextX][nextY] == word.charAt(i)) {
                    if (dfs(board, word, m, n, len, directions, visited, i + 1, nextX, nextY)) {return true;}
                }
            }
            visited[x][y] = false;
            return false;
        }

        public static void main(String[] args) {
            Exist.Solution exist = new Exist.Solution();
            char[][] board = {{'a', 'a'}};
            String word = "aaa";
            System.out.println(exist.exist(board, word));
        }
    }

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int coj = board[0].length;
        int len = word.length();
        char[] wordArr = word.toCharArray();
        boolean[][] haveStep = new boolean[row][coj];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < coj; j++) {
                if (board[i][j] == wordArr[0]) {
                    if (this.dfs(board, haveStep, row, coj, i, j, wordArr, len, 0)) {return true;}
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] haveStep, int row, int coj, int i, int j, char[] word, int len, int index) {
        if (i < 0 || i > row - 1 || j < 0 || j > coj - 1 || haveStep[i][j] || board[i][j] != word[index]) {return false;}
        if (index == len - 1) {return true;}
        haveStep[i][j] = true;
        if (this.dfs(board, haveStep, row, coj, i - 1, j, word, len, index + 1) || this.dfs(board, haveStep, row, coj, i + 1, j, word, len, index + 1) || this.dfs(board, haveStep, row, coj, i, j - 1, word, len, index + 1) || this.dfs(board,
            haveStep, row, coj, i, j + 1, word, len, index + 1)) {return true;}
        haveStep[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        char[][] board2 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        long start = System.currentTimeMillis();
        boolean result = false;
        //result = new Exist().exist(board, word);
        result = new Exist().exist(board2, word3);
        long end = System.currentTimeMillis();
        System.out.println("结果：" + result);
        System.out.println("时间(ms)：" + (end - start));
    }
}
