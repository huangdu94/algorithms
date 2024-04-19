package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 827. 最大人工岛
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
 * 示例 1:
 * 输入: grid = [[1, 0], [0, 1]]
 * 输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 * 示例 2:
 * 输入: grid = [[1, 1], [1, 0]]
 * 输出: 4
 * 解释: 将一格0变成1，岛屿的面积扩大为 4。
 * 示例 3:
 * 输入: grid = [[1, 1], [1, 1]]
 * 输出: 4
 * 解释: 没有0可以让我们变成1，面积依然为 4。
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] 为 0 或 1
 *
 * @author huangdu
 * @version 2022/9/20
 */
public class LargestIsland {
    private int n;
    private int[] parent;
    private Map<Integer, Integer> areaMap;

    public int largestIsland(int[][] grid) {
        this.n = grid.length;
        this.parent = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int hash = hash(i, j);
                this.parent[hash] = hash;
            }
        }
        this.areaMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {continue;}
                if (i + 1 < n && grid[i + 1][j] == 1) {
                    union(hash(i, j), hash(i + 1, j));
                }
                if (j + 1 < n && grid[i][j + 1] == 1) {
                    union(hash(i, j), hash(i, j + 1));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {continue;}
                int parent = find(hash(i, j));
                areaMap.merge(parent, 1, Integer::sum);
            }
        }
        if (areaMap.size() == 1) {
            return Math.min(n * n, new ArrayList<>(areaMap.values()).get(0) + 1);
        }
        int ans = 0;
        int[] directions = new int[] {1, 0, -1, 0, 1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {continue;}
                int area = 1;
                Set<Integer> visited = new HashSet<>(4);
                for (int k = 0; k < 4; k++) {
                    int adjI = i + directions[k], adjJ = j + directions[k + 1];
                    if (adjI < 0 || adjI >= n || adjJ < 0 || adjJ >= n || grid[adjI][adjJ] == 0) {continue;}
                    int parent = find(hash(adjI, adjJ));
                    if (!visited.add(parent)) {continue;}
                    area += areaMap.get(parent);
                }
                ans = Math.max(ans, area);
            }
        }
        return ans;
    }

    private int hash(int x, int y) {
        return x * n + y;
    }

    private void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB) {return;}
        parent[parentB] = parentA;
    }

    private int find(int a) {
        if (a == parent[a]) {return a;}
        return parent[a] = find(parent[a]);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 0, 0, 1, 1},
            {1, 0, 0, 1, 0},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1},
            {0, 0, 0, 1, 0}
        };
        LargestIsland li = new LargestIsland();
        System.out.println(li.largestIsland(grid));
    }
}
