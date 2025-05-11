package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 773. 滑动谜题
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 * 示例：
 * 输入：board = [[1,2,3],[4,0,5]]
 * 输出：1
 * 解释：交换 0 和 5 ，1 步完成
 * 输入：board = [[1,2,3],[5,4,0]]
 * 输出：-1
 * 解释：没有办法完成谜板
 * 输入：board = [[4,1,2],[5,0,3]]
 * 输出：5
 * 解释：
 * 最少完成谜板的最少移动次数是 5 ，
 * 一种移动路径:
 * 尚未移动: [[4,1,2],[5,0,3]]
 * 移动 1 次: [[4,1,2],[0,5,3]]
 * 移动 2 次: [[0,1,2],[4,5,3]]
 * 移动 3 次: [[1,0,2],[4,5,3]]
 * 移动 4 次: [[1,2,0],[4,5,3]]
 * 移动 5 次: [[1,2,3],[4,5,0]]
 * 输入：board = [[3,2,4],[1,5,0]]
 * 输出：14
 * 提示：
 * board 是一个如上所述的 2 x 3 的数组.
 * board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列.
 *
 * @author huangdu
 * @version 2021/6/27
 */
public class SlidingPuzzle {
    // TODO 可尝试启发式搜索
    private static final int ROW = 2;
    private static final int COJ = 3;
    private static final int SIZE = ROW * COJ;
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final Map<Integer, List<Integer>> GRAPH = new HashMap<>();

    static {
        int[][] board = new int[ROW][COJ];
        boolean[] used = new boolean[SIZE];
        int[] zero = new int[2];
        dfs(board, used, 0, zero);
    }

    private static void dfs(int[][] board, boolean[] used, int index, int[] zero) {
        if (index == SIZE) {
            GRAPH.put(getId(board), getNext(board, zero));
            return;
        }
        int row = index / COJ, coj = index % COJ;
        for (int num = 0; num < SIZE; num++) {
            if (used[num]) { continue;}
            if (num == 0) {
                zero[0] = row;
                zero[1] = coj;
            }
            board[row][coj] = num;
            used[num] = true;
            dfs(board, used, index + 1, zero);
            used[num] = false;
        }
    }

    private static int getId(int[][] board) {
        int id = 0;
        for (int row = 0; row < 2; row++) {
            for (int coj = 0; coj < 3; coj++) {
                id = id * 10 + board[row][coj];
            }
        }
        return id;
    }

    private static void swap(int[][] board, int row1, int coj1, int row2, int coj2) {
        int temp = board[row1][coj1];
        board[row1][coj1] = board[row2][coj2];
        board[row2][coj2] = temp;
    }

    private static List<Integer> getNext(int[][] board, int[] zero) {
        int zeroRow = zero[0], zeroCoj = zero[1];
        List<Integer> next = new ArrayList<>();
        for (int[] direction : DIRECTIONS) {
            int row = zeroRow + direction[0], coj = zeroCoj + direction[1];
            if (row >= 0 && row < ROW && coj >= 0 && coj < COJ) {
                if (row == zeroRow && coj == zeroCoj) {continue;}
                swap(board, zeroRow, zeroCoj, row, coj);
                next.add(getId(board));
                swap(board, zeroRow, zeroCoj, row, coj);
            }
        }
        return next;
    }

    public int slidingPuzzle(int[][] board) {
        int target = 123450, steps = 0, start = getId(board);
        if (start == target) { return steps; }
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int cur = queue.remove();
                for (int next : GRAPH.get(cur)) {
                    if (next == target) { return steps; }
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] board = {{3, 2, 4}, {1, 5, 0}};
        SlidingPuzzle sp = new SlidingPuzzle();
        System.out.println(sp.slidingPuzzle(board));
    }
}
