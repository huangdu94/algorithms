package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * 2258. 逃离火灾
 * 给你一个下标从 0 开始大小为 m x n 的二维整数数组 grid ，它表示一个网格图。每个格子为下面 3 个值之一：
 * 0 表示草地。
 * 1 表示着火的格子。
 * 2 表示一座墙，你跟火都不能通过这个格子。
 * 一开始你在最左上角的格子 (0, 0) ，你想要到达最右下角的安全屋格子 (m - 1, n - 1) 。每一分钟，你可以移动到 相邻 的草地格子。每次你移动 之后 ，着火的格子会扩散到所有不是墙的 相邻 格子。
 * 请你返回你在初始位置可以停留的 最多 分钟数，且停留完这段时间后你还能安全到达安全屋。如果无法实现，请你返回 -1 。如果不管你在初始位置停留多久，你 总是 能到达安全屋，请你返回 109 。
 * 注意，如果你到达安全屋后，火马上到了安全屋，这视为你能够安全到达安全屋。
 * 如果两个格子有共同边，那么它们为 相邻 格子。
 * 示例 1：
 * 输入：grid = [[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]
 * 输出：3
 * 解释：上图展示了你在初始位置停留 3 分钟后的情形。
 * 你仍然可以安全到达安全屋。
 * 停留超过 3 分钟会让你无法安全到达安全屋。
 * 示例 2：
 * 输入：grid = [[0,0,0,0],[0,1,2,0],[0,2,0,0]]
 * 输出：-1
 * 解释：上图展示了你马上开始朝安全屋移动的情形。
 * 火会蔓延到你可以移动的所有格子，所以无法安全到达安全屋。
 * 所以返回 -1 。
 * 示例 3：
 * 输入：grid = [[0,0,0],[2,2,0],[1,2,0]]
 * 输出：1000000000
 * 解释：上图展示了初始网格图。
 * 注意，由于火被墙围了起来，所以无论如何你都能安全到达安全屋。
 * 所以返回 10^9 。
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 300
 * 4 <= m * n <= 2 * 10^4
 * grid[i][j] 是 0 ，1 或者 2 。
 * grid[0][0] == grid[m - 1][n - 1] == 0
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class MaximumMinutes {
    private static final int[] DIRECTIONS = {0, 1, 0, -1, 0};
    private int m;
    private int n;
    private int total;
    private boolean[] isWall;
    private int[] timeFire;
    private int time;

    public int maximumMinutes(int[][] grid) {
        init(grid);
        if (!bfs((nextIdx) -> timeFire[nextIdx] == -1 || timeFire[nextIdx] > time || nextIdx == total - 1 && timeFire[nextIdx] == time)) {return -1;}
        if (bfs((nextIdx) -> timeFire[nextIdx] == -1)) {return 1000000000;}
        int left = 0, right = 0;
        for (int t : timeFire) {
            if (t != -1) {right = Math.max(right, t);}
        }
        int ans = 0;
        while (left < right) {
            int mid = left + (right - left >> 1);
            if (bfs((nextIdx) -> timeFire[nextIdx] == -1 || timeFire[nextIdx] > time + mid || nextIdx == total - 1 && timeFire[nextIdx] == time + mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return ans;
    }

    private void init(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.total = m * n;
        this.isWall = new boolean[total];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                if (val == 1) {
                    queue.offer(i * n + j);
                } else if (val == 2) {
                    isWall[i * n + j] = true;
                }
            }
        }
        this.time = 0;
        this.timeFire = new int[total];
        Arrays.fill(timeFire, -1);
        boolean[] visited = new boolean[total];
        while (!queue.isEmpty()) {
            for (int a = 0, size = queue.size(); a < size; a++) {
                int idx = queue.remove(), i = idx / n, j = idx % n;
                timeFire[idx] = time;
                visited[idx] = true;
                for (int k = 0; k < 4; k++) {
                    int nextI = i + DIRECTIONS[k], nextJ = j + DIRECTIONS[k + 1], nextIdx = nextI * n + nextJ;
                    if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && !isWall[nextIdx] && !visited[nextIdx]) {
                        queue.offer(nextIdx);
                    }
                }
            }
            time++;
        }
    }

    private boolean bfs(Predicate<Integer> condition) {
        this.time = 1;
        boolean[] visited = new boolean[total];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            for (int a = 0, size = queue.size(); a < size; a++) {
                int idx = queue.remove(), i = idx / n, j = idx % n;
                visited[idx] = true;
                for (int k = 0; k < 4; k++) {
                    int nextI = i + DIRECTIONS[k], nextJ = j + DIRECTIONS[k + 1], nextIdx = nextI * n + nextJ;
                    if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && !isWall[nextIdx] && !visited[nextIdx] && condition.test(nextIdx)) {
                        if (nextIdx == total - 1) {return true;}
                        queue.offer(nextIdx);
                    }
                }
            }
            time++;
        }
        return false;
    }

    public static void main(String[] args) {
        MaximumMinutes mm = new MaximumMinutes();
        int[][] gird = {
            {0, 2, 0, 0, 1},
            {0, 2, 0, 2, 2},
            {0, 2, 0, 0, 0},
            {0, 0, 2, 2, 0},
            {0, 0, 0, 0, 0}
        };
        System.out.println(mm.maximumMinutes(gird));
    }

    // 16, -1, 2, 1, 0,
    // 15, -1, 3, -1, -1,
    // 14, -1, 4, 5, 6,
    // 13, 12, -1, -1, 7,
    // 12, 11, 10, 9, 8
}
