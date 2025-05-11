package work.huangdu.question_bank.difficult;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 407. 接雨水 II
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * 示例 1:
 * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * 输出: 4
 * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
 * 示例 2:
 * 输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * 输出: 10
 * 提示:
 * m == heightMap.length
 * n == heightMap[i].length
 * 1 <= m, n <= 200
 * 0 <= heightMap[i][j] <= 2 * 10^4
 *
 * @author huangdu
 * @version 2021/11/6
 */
public class TrapRainWater {
    public int trapRainWater2(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {return 0;}
        int m = heightMap.length, n = heightMap[0].length, count = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    visited[i][j] = true;
                    priorityQueue.offer(new int[] {i, j, heightMap[i][j]});
                }
            }
        }
        int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        while (!priorityQueue.isEmpty()) {
            int[] data = priorityQueue.poll();
            int i0 = data[0], j0 = data[1], val = data[2];
            for (int k = 0; k < 4; k++) {
                int i = i0 + directions[k][0], j = j0 + directions[k][1];
                if (i >= 0 && i < m && j >= 0 && j < n && !visited[i][j]) {
                    if (val > heightMap[i][j]) {count += val - heightMap[i][j];}
                    priorityQueue.offer(new int[] {i, j, Math.max(heightMap[i][j], val)});
                    visited[i][j] = true;
                }
            }
        }
        return count;
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {return 0;}
        int m = heightMap.length, n = heightMap[0].length, max = Integer.MIN_VALUE;
        for (int[] row : heightMap) {
            for (int element : row) {
                if (element > max) {
                    max = element;
                }
            }
        }
        int[][] water = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    water[i][j] = heightMap[i][j];
                    queue.offer(new int[] {i, j, water[i][j]});
                } else {
                    water[i][j] = max;
                }
            }
        }
        int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            int i0 = data[0], j0 = data[1], val = data[2];
            for (int k = 0; k < 4; k++) {
                int i = i0 + directions[k][0], j = j0 + directions[k][1];
                if (i >= 0 && i < m && j >= 0 && j < n) {
                    if (water[i][j] > heightMap[i][j] && water[i][j] > val) {
                        water[i][j] = Math.max(heightMap[i][j], val);
                        queue.offer(new int[] {i, j, water[i][j]});
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                count += water[i][j] - heightMap[i][j];
            }
        }
        return count;
    }
}
