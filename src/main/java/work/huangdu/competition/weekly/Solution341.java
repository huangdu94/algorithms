package work.huangdu.competition.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 第 341 场周赛
 *
 * @author huangdu
 * @version 2023/4/16
 */
public class Solution341 {
    /**
     * 6376. 一最多的行
     * 给你一个大小为 m x n 的二进制矩阵 mat ，请你找出包含最多 1 的行的下标（从 0 开始）以及这一行中 1 的数目。
     * 如果有多行包含最多的 1 ，只需要选择 行下标最小 的那一行。
     * 返回一个由行下标和该行中 1 的数量组成的数组。
     * 示例 1：
     * 输入：mat = [[0,1],[1,0]]
     * 输出：[0,1]
     * 解释：两行中 1 的数量相同。所以返回下标最小的行，下标为 0 。该行 1 的数量为 1 。所以，答案为 [0,1] 。
     * 示例 2：
     * 输入：mat = [[0,0,0],[0,1,1]]
     * 输出：[1,2]
     * 解释：下标为 1 的行中 1 的数量最多。该行 1 的数量为 2 。所以，答案为 [1,2] 。
     * 示例 3：
     * 输入：mat = [[0,0],[1,1],[0,0]]
     * 输出：[1,2]
     * 解释：下标为 1 的行中 1 的数量最多。该行 1 的数量为 2 。所以，答案为 [1,2] 。
     * 提示：
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 100
     * mat[i][j] 为 0 或 1
     */
    public int[] rowAndMaximumOnes(int[][] mat) {
        int m = mat.length, n = mat[0].length, max = 0, ans = 0;
        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    count++;
                }
            }
            if (max < count) {
                max = count;
                ans = i;
            }
        }
        return new int[] {ans, max};
    }

    /**
     * 6350. 找出可整除性得分最大的整数
     * 给你两个下标从 0 开始的整数数组 nums 和 divisors 。
     * divisors[i] 的 可整除性得分 等于满足 nums[j] 能被 divisors[i] 整除的下标 j 的数量。
     * 返回 可整除性得分 最大的整数 divisors[i] 。如果有多个整数具有最大得分，则返回数值最小的一个。
     * 示例 1：
     * 输入：nums = [4,7,9,3,9], divisors = [5,2,3]
     * 输出：3
     * 解释：divisors 中每个元素的可整除性得分为：
     * divisors[0] 的可整除性得分为 0 ，因为 nums 中没有任何数字能被 5 整除。
     * divisors[1] 的可整除性得分为 1 ，因为 nums[0] 能被 2 整除。
     * divisors[2] 的可整除性得分为 3 ，因为 nums[2]、nums[3] 和 nums[4] 都能被 3 整除。
     * 因此，返回 divisors[2] ，它的可整除性得分最大。
     * 示例 2：
     * 输入：nums = [20,14,21,10], divisors = [5,7,5]
     * 输出：5
     * 解释：divisors 中每个元素的可整除性得分为：
     * divisors[0] 的可整除性得分为 2 ，因为 nums[0] 和 nums[3] 都能被 5 整除。
     * divisors[1] 的可整除性得分为 2 ，因为 nums[1] 和 nums[2] 都能被 7 整除。
     * divisors[2] 的可整除性得分为 2 ，因为 nums[0] 和 nums[3] 都能被5整除。
     * 由于 divisors[0]、divisors[1] 和 divisors[2] 的可整除性得分都是最大的，因此，我们返回数值最小的一个，即 divisors[2] 。
     * 示例 3：
     * 输入：nums = [12], divisors = [10,16]
     * 输出：10
     * 解释：divisors 中每个元素的可整除性得分为：
     * divisors[0] 的可整除性得分为 0 ，因为 nums 中没有任何数字能被 10 整除。
     * divisors[1] 的可整除性得分为 0 ，因为 nums 中没有任何数字能被 16 整除。
     * 由于 divisors[0] 和 divisors[1] 的可整除性得分都是最大的，因此，我们返回数值最小的一个，即 divisors[0] 。
     * 提示：
     * 1 <= nums.length, divisors.length <= 1000
     * 1 <= nums[i], divisors[i] <= 10^9
     */
    public int maxDivScore(int[] nums, int[] divisors) {
        int maxScore = 0, ans = divisors[0];
        for (int divisor : divisors) {
            int score = 0;
            for (int num : nums) {
                if (num % divisor == 0) {
                    score++;
                }
            }
            if (score > maxScore || score == maxScore && divisor < ans) {
                maxScore = score;
                ans = divisor;
            }
        }
        return ans;
    }

    /**
     * 6375. 构造有效字符串的最少插入数
     * 给你一个字符串 word ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，返回使 word 有效 需要插入的最少字母数。
     * 如果字符串可以由 "abc" 串联多次得到，则认为该字符串 有效 。
     * 示例 1：
     * 输入：word = "b"
     * 输出：2
     * 解释：在 "b" 之前插入 "a" ，在 "b" 之后插入 "c" 可以得到有效字符串 "abc" 。
     * 示例 2：
     * 输入：word = "aaa"
     * 输出：6
     * 解释：在每个 "a" 之后依次插入 "b" 和 "c" 可以得到有效字符串 "abcabcabc" 。
     * 示例 3：
     * 输入：word = "abc"
     * 输出：0
     * 解释：word 已经是有效字符串，不需要进行修改。
     * 提示：
     * 1 <= word.length <= 50
     * word 仅由字母 "a"、"b" 和 "c" 组成。
     */
    public int addMinimum(String word) {
        int n = word.length(), i = 0, ans = 0;
        while (i < n) {
            char ch = word.charAt(i);
            if (ch == 'a') {
                if (i < n - 1) {
                    if (word.charAt(i + 1) == 'a') {
                        ans += 2;
                    } else if (word.charAt(i + 1) == 'b') {
                        if (i < n - 2) {
                            if (word.charAt(i + 2) != 'c') {
                                ans++;
                            } else {
                                i++;
                            }
                        } else {
                            ans++;
                        }
                        i++;
                    } else {
                        ans += 1;
                        i++;
                    }
                } else {
                    ans += 2;
                }
            } else if (ch == 'b') {
                ans++;
                if (i < n - 1) {
                    if (word.charAt(i + 1) != 'c') {
                        ans++;
                    } else {
                        i++;
                    }
                } else {
                    ans++;
                }
            } else {
                ans += 2;
            }
            i++;
        }
        return ans;
    }

    /**
     * 6378. 最小化旅行的价格总和
     * 现有一棵无向、无根的树，树中有 n 个节点，按从 0 到 n - 1 编号。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。
     * 每个节点都关联一个价格。给你一个整数数组 price ，其中 price[i] 是第 i 个节点的价格。
     * 给定路径的 价格总和 是该路径上所有节点的价格之和。
     * 另给你一个二维整数数组 trips ，其中 trips[i] = [starti, endi] 表示您从节点 starti 开始第 i 次旅行，并通过任何你喜欢的路径前往节点 endi 。
     * 在执行第一次旅行之前，你可以选择一些 非相邻节点 并将价格减半。
     * 返回执行所有旅行的最小价格总和。
     * 示例 1：
     * 输入：n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
     * 输出：23
     * 解释：
     * 上图表示将节点 2 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 、2 和 3 并使其价格减半后的树。
     * 第 1 次旅行，选择路径 [0,1,3] 。路径的价格总和为 1 + 2 + 3 = 6 。
     * 第 2 次旅行，选择路径 [2,1] 。路径的价格总和为 2 + 5 = 7 。
     * 第 3 次旅行，选择路径 [2,1,3] 。路径的价格总和为 5 + 2 + 3 = 10 。
     * 所有旅行的价格总和为 6 + 7 + 10 = 23 。可以证明，23 是可以实现的最小答案。
     * 示例 2：
     * 输入：n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
     * 输出：1
     * 解释：
     * 上图表示将节点 0 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 并使其价格减半后的树。
     * 第 1 次旅行，选择路径 [0] 。路径的价格总和为 1 。
     * 所有旅行的价格总和为 1 。可以证明，1 是可以实现的最小答案。
     * 提示：
     * 1 <= n <= 50
     * edges.length == n - 1
     * 0 <= a_i, b_i <= n - 1
     * edges 表示一棵有效的树
     * price.length == n
     * price[i] 是一个偶数
     * 1 <= price[i] <= 1000
     * 1 <= trips.length <= 100
     * 0 <= start_i, end_i <= n - 1
     */
    private List<Integer>[] graph;
    private int[] freq;
    private int[] price;

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        this.price = price;
        this.graph = new List[n];
        Arrays.setAll(graph, (o) -> new ArrayList<>(n));
        for (int[] edge : edges) {
            int f = edge[0], t = edge[1];
            graph[f].add(t);
            graph[t].add(f);
        }
        this.freq = new int[n];
        for (int[] trip : trips) {
            int start = trip[0], end = trip[1];
            path(-1, start, end);
        }
        int[] result = dfs(-1, 0);
        return Math.min(result[0], result[1]);
    }

    private boolean path(int pre, int cur, int target) {
        if (cur == target) {
            freq[cur]++;
            return true;
        }
        for (int next : graph[cur]) {
            if (next == pre) {continue;}
            if (path(cur, next, target)) {
                freq[cur]++;
                return true;
            }
        }
        return false;
    }

    private int[] dfs(int pre, int cur) {
        int notSelected = freq[cur] * price[cur];
        int selected = notSelected / 2;
        for (int next : graph[cur]) {
            if (next == pre) {continue;}
            int[] result = dfs(cur, next);
            notSelected += Math.min(result[0], result[1]);
            selected += result[0];
        }
        return new int[] {notSelected, selected};
    }

    public static void main(String[] args) {
        Solution341 solution = new Solution341();
        int n = 2;
        int[][] edges = {{0, 1}};
        int[] price = {2, 2};
        int[][] trips = {{0, 0}};
        System.out.println(solution.minimumTotalPrice(n, edges, price, trips));
    }
}
