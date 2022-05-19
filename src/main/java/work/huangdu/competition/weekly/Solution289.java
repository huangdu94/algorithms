package work.huangdu.competition.weekly;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 第 289 场周赛
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/4/17
 */
public class Solution289 {
    /**
     * 6070. 计算字符串的数字和
     * 给你一个由若干数字（0 - 9）组成的字符串 s ，和一个整数。
     * 如果 s 的长度大于 k ，则可以执行一轮操作。在一轮操作中，需要完成以下工作：
     * 将 s 拆分 成长度为 k 的若干 连续数字组 ，使得前 k 个字符都分在第一组，接下来的 k 个字符都分在第二组，依此类推。注意，最后一个数字组的长度可以小于 k 。
     * 用表示每个数字组中所有数字之和的字符串来 替换 对应的数字组。例如，"346" 会替换为 "13" ，因为 3 + 4 + 6 = 13 。
     * 合并 所有组以形成一个新字符串。如果新字符串的长度大于 k 则重复第一步。
     * 返回在完成所有轮操作后的 s 。
     * 示例 1：
     * 输入：s = "11111222223", k = 3
     * 输出："135"
     * 解释：
     * - 第一轮，将 s 分成："111"、"112"、"222" 和 "23" 。
     * 接着，计算每一组的数字和：1 + 1 + 1 = 3、1 + 1 + 2 = 4、2 + 2 + 2 = 6 和 2 + 3 = 5 。
     * 这样，s 在第一轮之后变成 "3" + "4" + "6" + "5" = "3465" 。
     * - 第二轮，将 s 分成："346" 和 "5" 。
     * 接着，计算每一组的数字和：3 + 4 + 6 = 13 、5 = 5 。
     * 这样，s 在第二轮之后变成 "13" + "5" = "135" 。
     * 现在，s.length <= k ，所以返回 "135" 作为答案。
     * 示例 2：
     * 输入：s = "00000000", k = 3
     * 输出："000"
     * 解释：
     * 将 "000", "000", and "00".
     * 接着，计算每一组的数字和：0 + 0 + 0 = 0 、0 + 0 + 0 = 0 和 0 + 0 = 0 。
     * s 变为 "0" + "0" + "0" = "000" ，其长度等于 k ，所以返回 "000" 。
     * 提示：
     * 1 <= s.length <= 100
     * 2 <= k <= 100
     * s 仅由数字（0 - 9）组成。
     */
    public String digitSum(String s, int k) {
        int n = s.length();
        while (n > k) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i += k) {
                int sum = 0;
                for (int j = 0; j < k && i + j < n; j++) {
                    sum += s.charAt(j + i) - '0';
                }
                sb.append(sum);
            }
            s = sb.toString();
            n = s.length();
        }
        return s;
    }

    /**
     * 6071. 完成所有任务需要的最少轮数
     * 给你一个下标从 0 开始的整数数组 tasks ，其中 tasks[i] 表示任务的难度级别。在每一轮中，你可以完成 2 个或者 3 个 相同难度级别 的任务。
     * 返回完成所有任务需要的 最少 轮数，如果无法完成所有任务，返回 -1 。
     * 示例 1：
     * 输入：tasks = [2,2,3,3,2,4,4,4,4,4]
     * 输出：4
     * 解释：要想完成所有任务，一个可能的计划是：
     * - 第一轮，完成难度级别为 2 的 3 个任务。
     * - 第二轮，完成难度级别为 3 的 2 个任务。
     * - 第三轮，完成难度级别为 4 的 3 个任务。
     * - 第四轮，完成难度级别为 4 的 2 个任务。
     * 可以证明，无法在少于 4 轮的情况下完成所有任务，所以答案为 4 。
     * 示例 2：
     * 输入：tasks = [2,3,3]
     * 输出：-1
     * 解释：难度级别为 2 的任务只有 1 个，但每一轮执行中，只能选择完成 2 个或者 3 个相同难度级别的任务。因此，无法完成所有任务，答案为 -1 。
     * 提示：
     * 1 <= tasks.length <= 10^5
     * 1 <= tasks[i] <= 10^9
     */
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int task : tasks) {
            freq.merge(task, 1, Integer::sum);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int count = entry.getValue();
            if (count == 1) {return -1;}
            if (count % 3 == 0) {
                ans += count / 3;
            } else {
                ans += count / 3 + 1;
            }
        }
        return ans;
    }

    /**
     * 6072. 转角路径的乘积中最多能有几个尾随零
     * 给你一个二维整数数组 grid ，大小为 m x n，其中每个单元格都含一个正整数。
     * 转角路径 定义为：包含至多一个弯的一组相邻单元。具体而言，路径应该完全 向水平方向 或者 向竖直方向 移动过弯（如果存在弯），而不能访问之前访问过的单元格。在过弯之后，路径应当完全朝 另一个 方向行进：如果之前是向水平方向，那么就应该变为向竖直方向；反之亦然。当然，同样不能访问之前已经访问过的单元格。
     * 一条路径的 乘积 定义为：路径上所有值的乘积。
     * 请你从 grid 中找出一条乘积中尾随零数目最多的转角路径，并返回该路径中尾随零的数目。
     * 注意：
     * 水平 移动是指向左或右移动。
     * 竖直 移动是指向上或下移动。
     * 示例 1：
     * 输入：grid = [[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]
     * 输出：3
     * 解释：左侧的图展示了一条有效的转角路径。
     * 其乘积为 15 * 20 * 6 * 1 * 10 = 18000 ，共计 3 个尾随零。
     * 可以证明在这条转角路径的乘积中尾随零数目最多。
     * 中间的图不是一条有效的转角路径，因为它有不止一个弯。
     * 右侧的图也不是一条有效的转角路径，因为它需要重复访问已经访问过的单元格。
     * 示例 2：
     * 输入：grid = [[4,3,2],[7,6,1],[8,8,8]]
     * 输出：0
     * 解释：网格如上图所示。
     * 不存在乘积含尾随零的转角路径。
     * 提示：
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 10^5
     * 1 <= m * n <= 10^5
     * 1 <= grid[i][j] <= 1000
     */
    public int maxTrailingZeros(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] freq5 = new int[m + 1][n + 1];
        int[][] freq2 = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = grid[i][j];
                int cnt2 = 0, cnt5 = 0;
                while ((num & 1) == 0) {
                    cnt2++;
                    num >>= 1;
                }
                num = grid[i][j];
                while (num % 5 == 0) {
                    cnt5++;
                    num /= 5;
                }
                freq2[i + 1][j + 1] = freq2[i + 1][j] + freq2[i][j + 1] - freq2[i][j] + cnt2;
                freq5[i + 1][j + 1] = freq5[i + 1][j] + freq5[i][j + 1] - freq5[i][j] + cnt5;
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int leftTop2 = freq2[i + 1][j + 1] - freq2[i][j];
                int leftTop5 = freq5[i + 1][j + 1] - freq5[i][j];
                int cnt0 = Math.min(leftTop2, leftTop5);
                ans = Math.max(ans, cnt0);
                cnt0 = Math.min(freq2[i + 1][n] - freq2[i + 1][j] - (freq2[i][n] - freq2[i][j + 1]), freq5[i + 1][n] - freq5[i + 1][j] - (freq5[i][n] - freq5[i][j + 1]));
                ans = Math.max(ans, cnt0);
                cnt0 = Math.min(freq2[m][j + 1] - freq2[i][j + 1] - (freq2[m][j] - freq2[i + 1][j]), freq5[m][j + 1] - freq5[i][j + 1] - (freq5[m][j] - freq5[i + 1][j]));
                ans = Math.max(ans, cnt0);
                cnt0 = Math.min(freq2[m][j + 1] - freq2[m][j] + freq2[i + 1][n] - freq2[i][n] - leftTop2, freq5[m][j + 1] - freq5[m][j] + freq5[i + 1][n] - freq5[i][n] - leftTop5);
                ans = Math.max(ans, cnt0);
            }
        }
        return ans;
    }

    /**
     * 6073. 相邻字符不同的最长路径
     * 给你一棵 树（即一个连通、无向、无环图），根节点是节点 0 ，这棵树由编号从 0 到 n - 1 的 n 个节点组成。用下标从 0 开始、长度为 n 的数组 parent 来表示这棵树，其中 parent[i] 是节点 i 的父节点，由于节点 0 是根节点，所以 parent[0] == -1 。
     * 另给你一个字符串 s ，长度也是 n ，其中 s[i] 表示分配给节点 i 的字符。
     * 请你找出路径上任意一对相邻节点都没有分配到相同字符的 最长路径 ，并返回该路径的长度。
     * 示例 1：
     * 输入：parent = [-1,0,0,1,1,2], s = "abacbe"
     * 输出：3
     * 解释：任意一对相邻节点字符都不同的最长路径是：0 -> 1 -> 3 。该路径的长度是 3 ，所以返回 3 。
     * 可以证明不存在满足上述条件且比 3 更长的路径。
     * 示例 2：
     * 输入：parent = [-1,0,0,0], s = "aabc"
     * 输出：3
     * 解释：任意一对相邻节点字符都不同的最长路径是：2 -> 0 -> 3 。该路径的长度为 3 ，所以返回 3 。
     * 提示：
     * n == parent.length == s.length
     * 1 <= n <= 10^5
     * 对所有 i >= 1 ，0 <= parent[i] <= n - 1 均成立
     * parent[0] == -1
     * parent 表示一棵有效的树
     * s 仅由小写英文字母组成
     */
    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != s.charAt(parent[i])) {
                edges[i].add(parent[i]);
                edges[parent[i]].add(i);
            }
        }
        List<Integer> startList = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {continue;}
            startList.add(i);
            Queue<Integer> queue = new ArrayDeque<>();
            visited[i] = true;
            queue.offer(i);
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int next : edges[cur]) {
                    if (visited[next]) {continue;}
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        int ans = 0;
        for (int start : startList) {
            visited = new boolean[n];
            Queue<Integer> queue = new ArrayDeque<>();
            visited[start] = true;
            queue.offer(start);
            int far = start;
            while (!queue.isEmpty()) {
                far = queue.poll();
                for (int next : edges[far]) {
                    if (visited[next]) {continue;}
                    visited[next] = true;
                    queue.offer(next);
                }
            }
            queue.clear();
            visited = new boolean[n];
            visited[far] = true;
            queue.offer(far);
            int len = 0;
            while (!queue.isEmpty()) {
                len++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int cur = queue.poll();
                    for (int next : edges[cur]) {
                        if (visited[next]) {continue;}
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            ans = Math.max(ans, len);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution289 solution = new Solution289();

        int[][] grid = {{1, 5, 2, 4, 25}};
        System.out.println(solution.maxTrailingZeros(grid));

        int[] parent = {-1, 0, 0, 0};
        String s = "aabc";
        System.out.println(solution.longestPath(parent, s));
    }
}
