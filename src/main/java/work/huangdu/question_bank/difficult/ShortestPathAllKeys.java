package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 864. 获取所有钥匙的最短路径
 * 给定一个二维网格 grid ，其中：
 * '.' 代表一个空房间
 * '#' 代表一堵
 * '@' 是起点
 * 小写字母代表钥匙
 * 大写字母代表锁
 * 我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
 * 假设 k 为 钥匙/锁 的个数，且满足 1 <= k <= 6，字母表中的前 k 个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
 * 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回 -1 。
 * 示例 1：
 * 输入：grid = ["@.a.#","###.#","b.A.B"]
 * 输出：8
 * 解释：目标是获得所有钥匙，而不是打开所有锁。
 * 示例 2：
 * 输入：grid = ["@..aA","..B#.","....b"]
 * 输出：6
 * 示例 3:
 * 输入: grid = ["@Aa"]
 * 输出: -1
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 30
 * grid[i][j] 只含有 '.', '#', '@', 'a'-'f' 以及 'A'-'F'
 * 钥匙的数目范围是 [1, 6]
 * 每个钥匙都对应一个 不同 的字母
 * 每个钥匙正好打开一个对应的锁
 *
 * @author huangdu
 * @version 2022/11/10
 */
public class ShortestPathAllKeys {
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length(), startX = 0, startY = 0, key = 0;
        int[] directions = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = grid[i].charAt(j);
                if (ch == '@') {
                    startX = i;
                    startY = j;
                } else if (Character.isLowerCase(ch)) {
                    key++;
                }
            }
        }
        final int complete = (1 << key) - 1;
        boolean[][][] visited = new boolean[m][n][complete];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {startX, startY, 0});
        visited[startX][startY][0] = true;
        int ans = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] data = queue.poll();
                final int x = data[0], y = data[1], status = data[2];
                for (int p = 0; p < 4; p++) {
                    int nextX = x + directions[p], nextY = y + directions[p + 1], nextStatus = status;
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
                        char ch = grid[nextX].charAt(nextY);
                        if (ch == '#') {continue;}
                        if (Character.isUpperCase(ch)) {
                            if (((1 << (ch - 'A')) & nextStatus) == 0) {continue;}
                        } else if (Character.isLowerCase(ch)) {
                            if ((nextStatus |= 1 << (ch - 'a')) == complete) {return ans;}
                        }
                        if (visited[nextX][nextY][nextStatus]) {continue;}
                        visited[nextX][nextY][nextStatus] = true;
                        queue.offer(new int[] {nextX, nextY, nextStatus});
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestPathAllKeys spak = new ShortestPathAllKeys();
        System.out.println(spak.shortestPathAllKeys(new String[] {
            "...#.#C#....d.....##........##",
            "F.###........##...#.......###.",
            "##...#.#...#.##...............",
            "##...............#.......##...",
            "##......#.#.##..#.###.#...#.#.",
            "#....###..#..........#..##....",
            "###.....#...#..##.....D#......",
            "........##....#.....#.#.......",
            "...#........#.#............###",
            "..#..##B.#f.#.##..#....e......",
            ".....#.....#.#....####........",
            "#..#...###.......#...#.#......",
            "..#.....#...#..#..#....#.##...",
            "............#.#.....###....##.",
            "....#..#...#.#..#....#...#.##.",
            ".#...#.....#..#........#..#...",
            "..#..##..##......#.##.#......#",
            ".#..#...#..##.....#.#.#.#..#..",
            "...##....#....a#....#....#.#..",
            "..#..........#b.....#.........",
            "#...#....#...#.#..#...........",
            "..##.#...#.##.........#.##..##",
            "#........##.......#....#.##...",
            "........###....#..#..#..#....#",
            "...#..#.#.......#..#....#..#..",
            "#......###..#.....##.......#.#",
            "......#..#....#.#...#.#.###..#",
            "#.....#.#.#..........#......##",
            ".....#..#..E.#..A.......c.....",
            "....#...#........#....#...@.#."}));
    }
}
