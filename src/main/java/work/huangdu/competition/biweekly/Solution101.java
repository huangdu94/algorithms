package work.huangdu.competition.biweekly;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 第 101 场双周赛
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/4/1
 */
public class Solution101 {
    /**
     * 2605. 从两个数字数组里生成最小数字
     * 给你两个只包含 1 到 9 之间数字的数组 nums1 和 nums2 ，每个数组中的元素 互不相同 ，请你返回 最小 的数字，两个数组都 至少 包含这个数字的某个数位。
     * 示例 1：
     * 输入：nums1 = [4,1,3], nums2 = [5,7]
     * 输出：15
     * 解释：数字 15 的数位 1 在 nums1 中出现，数位 5 在 nums2 中出现。15 是我们能得到的最小数字。
     * 示例 2：
     * 输入：nums1 = [3,5,2,6], nums2 = [3,1,7]
     * 输出：3
     * 解释：数字 3 的数位 3 在两个数组中都出现了。
     * 提示：
     * 1 <= nums1.length, nums2.length <= 9
     * 1 <= nums1[i], nums2[i] <= 9
     * 每个数组中，元素 互不相同 。
     */
    public int minNumber(int[] nums1, int[] nums2) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, commonMin = Integer.MAX_VALUE;
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            min1 = Math.min(min1, num);
            set.add(num);
        }
        for (int num : nums2) {
            min2 = Math.min(min2, num);
            if (set.contains(num)) {
                commonMin = Math.min(commonMin, num);
            }
        }
        if (commonMin != Integer.MAX_VALUE) {return commonMin;}
        return min1 > min2 ? min2 * 10 + min1 : min1 * 10 + min2;
    }

    /**
     * 2606. 找到最大开销的子字符串
     * 给你一个字符串 s ，一个字符 互不相同 的字符串 chars 和一个长度与 chars 相同的整数数组 vals 。
     * 子字符串的开销 是一个子字符串中所有字符对应价值之和。空字符串的开销是 0 。
     * 字符的价值 定义如下：
     * 如果字符不在字符串 chars 中，那么它的价值是它在字母表中的位置（下标从 1 开始）。
     * 比方说，'a' 的价值为 1 ，'b' 的价值为 2 ，以此类推，'z' 的价值为 26 。
     * 否则，如果这个字符在 chars 中的位置为 i ，那么它的价值就是 vals[i] 。
     * 请你返回字符串 s 的所有子字符串中的最大开销。
     * 示例 1：
     * 输入：s = "adaa", chars = "d", vals = [-1000]
     * 输出：2
     * 解释：字符 "a" 和 "d" 的价值分别为 1 和 -1000 。
     * 最大开销子字符串是 "aa" ，它的开销为 1 + 1 = 2 。
     * 2 是最大开销。
     * 示例 2：
     * 输入：s = "abc", chars = "abc", vals = [-1,-1,-1]
     * 输出：0
     * 解释：字符 "a" ，"b" 和 "c" 的价值分别为 -1 ，-1 和 -1 。
     * 最大开销子字符串是 "" ，它的开销为 0 。
     * 0 是最大开销。
     * 提示：
     * 1 <= s.length <= 10^5
     * s 只包含小写英文字母。
     * 1 <= chars.length <= 26
     * chars 只包含小写英文字母，且 互不相同 。
     * vals.length == chars.length
     * -1000 <= vals[i] <= 1000
     */
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] map = new int[26];
        for (int i = 0; i < 26; i++) {map[i] = i + 1;}
        for (int i = 0, n = chars.length(); i < n; i++) {
            map[chars.charAt(i) - 'a'] = vals[i];
        }
        int sum = 0, min = 0, ans = 0;
        for (int i = 0, n = s.length(); i < n; i++) {
            sum += map[s.charAt(i) - 'a'];
            min = Math.min(sum, min);
            ans = Math.max(ans, sum - min);
        }
        return ans;
    }

    /**
     * 2607. 使子数组元素和相等
     * 给你一个下标从 0 开始的整数数组 arr 和一个整数 k 。数组 arr 是一个循环数组。换句话说，数组中的最后一个元素的下一个元素是数组中的第一个元素，数组中第一个元素的前一个元素是数组中的最后一个元素。
     * 你可以执行下述运算任意次：
     * 选中 arr 中任意一个元素，并使其值加上 1 或减去 1 。
     * 执行运算使每个长度为 k 的 子数组 的元素总和都相等，返回所需要的最少运算次数。
     * 子数组 是数组的一个连续部分。
     * 示例 1：
     * 输入：arr = [1,4,1,3], k = 2
     * 输出：1
     * 解释：在下标为 1 的元素那里执行一次运算，使其等于 3 。
     * 执行运算后，数组变为 [1,3,1,3] 。
     * - 0 处起始的子数组为 [1, 3] ，元素总和为 4
     * - 1 处起始的子数组为 [3, 1] ，元素总和为 4
     * - 2 处起始的子数组为 [1, 3] ，元素总和为 4
     * - 3 处起始的子数组为 [3, 1] ，元素总和为 4
     * 示例 2：
     * 输入：arr = [2,5,5,7], k = 3
     * 输出：5
     * 解释：在下标为 0 的元素那里执行三次运算，使其等于 5 。在下标为 3 的元素那里执行两次运算，使其等于 5 。
     * 执行运算后，数组变为 [5,5,5,5] 。
     * - 0 处起始的子数组为 [5, 5, 5] ，元素总和为 15
     * - 1 处起始的子数组为 [5, 5, 5] ，元素总和为 15
     * - 2 处起始的子数组为 [5, 5, 5] ，元素总和为 15
     * - 3 处起始的子数组为 [5, 5, 5] ，元素总和为 15
     * 提示：
     * 1 <= k <= arr.length <= 10^5
     * 1 <= arr[i] <= 10^9
     */
    public long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length;
        long ans = 0;
        k = gcd(k, n);
        for (int i = 0; i < k; i++) {
            List<Integer> numList = new ArrayList<>(n / k);
            for (int j = i; j < n; j += k) {
                numList.add(arr[j]);
            }
            numList.sort(Integer::compare);
            int target = numList.get(numList.size() / 2);
            for (int x : numList) {
                ans += Math.abs(x - target);
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

    /**
     * 2608. 图中的最短环
     * 现有一个含 n 个顶点的 双向 图，每个顶点按从 0 到 n - 1 标记。图中的边由二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示顶点 ui 和 vi 之间存在一条边。每对顶点最多通过一条边连接，并且不存在与自身相连的顶点。
     * 返回图中 最短 环的长度。如果不存在环，则返回 -1 。
     * 环 是指以同一节点开始和结束，并且路径中的每条边仅使用一次。
     * 示例 1：
     * 输入：n = 7, edges = [[0,1],[1,2],[2,0],[3,4],[4,5],[5,6],[6,3]]
     * 输出：3
     * 解释：长度最小的循环是：0 -> 1 -> 2 -> 0
     * 示例 2：
     * 输入：n = 4, edges = [[0,1],[0,2]]
     * 输出：-1
     * 解释：图中不存在循环
     * 提示：
     * 2 <= n <= 1000
     * 1 <= edges.length <= 1000
     * edges[i].length == 2
     * 0 <= ui, vi < n
     * ui != vi
     * 不存在重复的边
     */
    public int findShortestCycle(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, (o) -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            graph[x].add(y);
            graph[y].add(x);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int[] distances = new int[n];
            Arrays.fill(distances, -1);
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[] {i, -1});
            distances[i] = 0;
            while (!queue.isEmpty()) {
                int[] data = queue.poll();
                int cur = data[0], pre = data[1];
                for (int next : graph[cur]) {
                    if (distances[next] == -1) {
                        distances[next] = distances[cur] + 1;
                        queue.offer(new int[] {next, cur});
                    } else if (next != pre) {
                        ans = Math.min(ans, distances[next] + distances[cur] + 1);
                    }
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        Solution101 solution = new Solution101();
        int[] arr = {6, 2, 8, 5, 7, 10};
        int k = 4;
        System.out.println(solution.makeSubKSumEqual(arr, k));
    }
}
