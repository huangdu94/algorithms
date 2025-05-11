package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 1001. 网格照明
 * 在大小为 n x n 的网格 grid 上，每个单元格都有一盏灯，最初灯都处于 关闭 状态。
 * 给你一个由灯的位置组成的二维数组 lamps ，其中 lamps[i] = [rowi, coli] 表示 打开 位于 grid[rowi][coli] 的灯。即便同一盏灯可能在 lamps 中多次列出，不会影响这盏灯处于 打开 状态。
 * 当一盏灯处于打开状态，它将会照亮 自身所在单元格 以及同一 行 、同一 列 和两条 对角线 上的 所有其他单元格 。
 * 另给你一个二维数组 queries ，其中 queries[j] = [rowj, colj] 。对于第 j 个查询，如果单元格 [rowj, colj] 是被照亮的，则查询结果为 1 ，否则为 0 。在第 j 次查询之后 [按照查询的顺序] ，关闭 位于单元格 grid[rowj][colj] 上及相邻 8 个方向上（与单元格 grid[rowi][coli] 共享角或边）的任何灯。
 * 返回一个整数数组 ans 作为答案， ans[j] 应等于第 j 次查询 queries[j] 的结果，1 表示照亮，0 表示未照亮。
 * 示例 1：
 * 输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
 * 输出：[1,0]
 * 解释：最初所有灯都是关闭的。在执行查询之前，打开位于 [0, 0] 和 [4, 4] 的灯。第 0 次查询检查 grid[1][1] 是否被照亮（蓝色方框）。该单元格被照亮，所以 ans[0] = 1 。然后，关闭红色方框中的所有灯。
 * 第 1 次查询检查 grid[1][0] 是否被照亮（蓝色方框）。该单元格没有被照亮，所以 ans[1] = 0 。然后，关闭红色矩形中的所有灯。
 * 示例 2：
 * 输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
 * 输出：[1,1]
 * 示例 3：
 * 输入：n = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
 * 输出：[1,1,0]
 * 提示：
 * 1 <= n <= 10^9
 * 0 <= lamps.length <= 20000
 * 0 <= queries.length <= 20000
 * lamps[i].length == 2
 * 0 <= rowi, coli < n
 * queries[j].length == 2
 * 0 <= rowj, colj < n
 *
 * @author huangdu
 * @version 2022/2/8
 */
public class GridIllumination {
    // 另一种思路 计数。
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        Set<Integer> openSet = new HashSet<>(lamps.length);
        Map<Integer, Queue<int[]>> rowMap = new HashMap<>(), cojMap = new HashMap<>(), diagonalMap = new HashMap<>(), backDiagonalMap = new HashMap<>();
        for (int[] lamp : lamps) {
            int row = lamp[0], coj = lamp[1];
            if (openSet.add(row * n + coj)) {
                Queue<int[]> rowQueue = rowMap.computeIfAbsent(row, k -> new ArrayDeque<>());
                rowQueue.offer(lamp);
                Queue<int[]> cojQueue = cojMap.computeIfAbsent(coj, k -> new ArrayDeque<>());
                cojQueue.offer(lamp);
                Queue<int[]> diagonalQueue = diagonalMap.computeIfAbsent(n - 1 - row + coj, k -> new ArrayDeque<>());
                diagonalQueue.offer(lamp);
                Queue<int[]> backDiagonalQueue = backDiagonalMap.computeIfAbsent(row + coj, k -> new ArrayDeque<>());
                backDiagonalQueue.offer(lamp);
            }
        }
        int len = queries.length;
        int[] ans = new int[len];
        for (int k = 0; k < len; k++) {
            int row = queries[k][0], coj = queries[k][1];
            boolean flag = false;
            Queue<int[]> rowQueue = rowMap.get(row);
            if (rowQueue != null) {
                while (!rowQueue.isEmpty()) {
                    int[] lamp = rowQueue.peek();
                    if (!openSet.contains(lamp[0] * n + lamp[1])) {
                        rowQueue.poll();
                        continue;
                    }
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                Queue<int[]> cojQueue = cojMap.get(coj);
                if (cojQueue != null) {
                    while (!cojQueue.isEmpty()) {
                        int[] lamp = cojQueue.peek();
                        if (!openSet.contains(lamp[0] * n + lamp[1])) {
                            cojQueue.poll();
                            continue;
                        }
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                Queue<int[]> diagonalQueue = diagonalMap.get(n - 1 - row + coj);
                if (diagonalQueue != null) {
                    while (!diagonalQueue.isEmpty()) {
                        int[] lamp = diagonalQueue.peek();
                        if (!openSet.contains(lamp[0] * n + lamp[1])) {
                            diagonalQueue.poll();
                            continue;
                        }
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                Queue<int[]> backDiagonalQueue = backDiagonalMap.get(row + coj);
                if (backDiagonalQueue != null) {
                    while (!backDiagonalQueue.isEmpty()) {
                        int[] lamp = backDiagonalQueue.peek();
                        if (!openSet.contains(lamp[0] * n + lamp[1])) {
                            backDiagonalQueue.poll();
                            continue;
                        }
                        flag = true;
                        break;
                    }
                }
            }
            ans[k] = flag ? 1 : 0;
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = coj - 1; j <= coj + 1; j++) {
                    if (i < 0 || i >= n || j < 0 || j >= n) { continue; }
                    openSet.remove(i * n + j);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] lamps = {{0, 0}, {0, 4}};
        int[][] queries = {{0, 4}, {0, 1}, {1, 4}};
        GridIllumination gi = new GridIllumination();
        System.out.println(Arrays.toString(gi.gridIllumination(n, lamps, queries)));
    }
}