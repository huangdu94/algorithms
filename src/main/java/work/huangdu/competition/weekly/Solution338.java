package work.huangdu.competition.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 第338场周赛
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/3/25
 */
public class Solution338 {
    /**
     * 6354. K 件物品的最大和
     * 袋子中装有一些物品，每个物品上都标记着数字 1 、0 或 -1 。
     * 给你四个非负整数 numOnes 、numZeros 、numNegOnes 和 k 。
     * 袋子最初包含：
     * numOnes 件标记为 1 的物品。
     * numZeroes 件标记为 0 的物品。
     * numNegOnes 件标记为 -1 的物品。
     * 现计划从这些物品中恰好选出 k 件物品。返回所有可行方案中，物品上所标记数字之和的最大值。
     * 示例 1：
     * 输入：numOnes = 3, numZeros = 2, numNegOnes = 0, k = 2
     * 输出：2
     * 解释：袋子中的物品分别标记为 {1, 1, 1, 0, 0} 。取 2 件标记为 1 的物品，得到的数字之和为 2 。
     * 可以证明 2 是所有可行方案中的最大值。
     * 示例 2：
     * 输入：numOnes = 3, numZeros = 2, numNegOnes = 0, k = 4
     * 输出：3
     * 解释：袋子中的物品分别标记为 {1, 1, 1, 0, 0} 。取 3 件标记为 1 的物品，1 件标记为 0 的物品，得到的数字之和为 3 。
     * 可以证明 3 是所有可行方案中的最大值。
     * 提示：
     * 0 <= numOnes, numZeros, numNegOnes <= 50
     * 0 <= k <= numOnes + numZeros + numNegOnes
     */
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (numOnes >= k) {return k;}
        if (numOnes + numZeros >= k) {return numOnes;}
        return numOnes - (k - numOnes - numZeros);
    }

    private final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211,
        223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487,
        491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797,
        809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};

    /**
     * 6355. 质数减法运算
     * 给你一个下标从 0 开始的整数数组 nums ，数组长度为 n 。
     * 你可以执行无限次下述运算：
     * 选择一个之前未选过的下标 i ，并选择一个 严格小于 nums[i] 的质数 p ，从 nums[i] 中减去 p 。
     * 如果你能通过上述运算使得 nums 成为严格递增数组，则返回 true ；否则返回 false 。
     * 严格递增数组 中的每个元素都严格大于其前面的元素。
     * 示例 1：
     * 输入：nums = [4,9,6,10]
     * 输出：true
     * 解释：
     * 在第一次运算中：选择 i = 0 和 p = 3 ，然后从 nums[0] 减去 3 ，nums 变为 [1,9,6,10] 。
     * 在第二次运算中：选择 i = 1 和 p = 7 ，然后从 nums[1] 减去 7 ，nums 变为 [1,2,6,10] 。
     * 第二次运算后，nums 按严格递增顺序排序，因此答案为 true 。
     * 示例 2：
     * 输入：nums = [6,8,11,12]
     * 输出：true
     * 解释：nums 从一开始就按严格递增顺序排序，因此不需要执行任何运算。
     * 示例 3：
     * 输入：nums = [5,8,3]
     * 输出：false
     * 解释：可以证明，执行运算无法使 nums 按严格递增顺序排序，因此答案是 false 。
     * 提示：
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 1000
     * nums.length == n
     */
    public boolean primeSubOperation(int[] nums) {
        int n = nums.length;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {continue;}
            boolean flag = false;
            for (int p : primes) {
                if (nums[i] <= p) {break;}
                if (nums[i] - p < nums[i + 1]) {
                    flag = true;
                    nums[i] -= p;
                    break;
                }
            }
            if (!flag) {return false;}
        }
        return true;
    }

    /**
     * 6357. 使数组元素全部相等的最少操作次数 显示英文描述
     * 给你一个正整数数组 nums 。
     * 同时给你一个长度为 m 的整数数组 queries 。第 i 个查询中，你需要将 nums 中所有元素变成 queries[i] 。你可以执行以下操作 任意 次：
     * 将数组里一个元素 增大 或者 减小 1 。
     * 请你返回一个长度为 m 的数组 answer ，其中 answer[i]是将 nums 中所有元素变成 queries[i] 的 最少 操作次数。
     * 注意，每次查询后，数组变回最开始的值。
     * 示例 1：
     * 输入：nums = [3,1,6,8], queries = [1,5]
     * 输出：[14,10]
     * 解释：第一个查询，我们可以执行以下操作：
     * - 将 nums[0] 减小 2 次，nums = [1,1,6,8] 。
     * - 将 nums[2] 减小 5 次，nums = [1,1,1,8] 。
     * - 将 nums[3] 减小 7 次，nums = [1,1,1,1] 。
     * 第一个查询的总操作次数为 2 + 5 + 7 = 14 。
     * 第二个查询，我们可以执行以下操作：
     * - 将 nums[0] 增大 2 次，nums = [5,1,6,8] 。
     * - 将 nums[1] 增大 4 次，nums = [5,5,6,8] 。
     * - 将 nums[2] 减小 1 次，nums = [5,5,5,8] 。
     * - 将 nums[3] 减小 3 次，nums = [5,5,5,5] 。
     * 第二个查询的总操作次数为 2 + 4 + 1 + 3 = 10 。
     * 示例 2：
     * 输入：nums = [2,9,6,3], queries = [10]
     * 输出：[20]
     * 解释：我们可以将数组中所有元素都增大到 10 ，总操作次数为 8 + 1 + 4 + 7 = 20 。
     * 提示：
     * n == nums.length
     * m == queries.length
     * 1 <= n, m <= 10^5
     * 1 <= nums[i], queries[i] <= 10^9
     */
    public List<Long> minOperations(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        long[] prefixSum = new long[n + 1];
        List<Long> ans = new ArrayList<>(m);
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        for (long query : queries) {
            if (query < nums[0]) {
                ans.add(prefixSum[n] - query * n);
            } else if (query > nums[n - 1]) {
                ans.add(query * n - prefixSum[n]);
            } else {
                int result = binarySearch(nums, query);
                ans.add(query * (result + 1) - (prefixSum[result + 1] - prefixSum[0]) + (prefixSum[n] - prefixSum[result + 1]) - query * (n - result - 1));
            }
        }
        return ans;
    }

    private int binarySearch(int[] nums, long query) {
        int left = 0, right = nums.length - 1, result = 0;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (nums[mid] > query) {
                right = mid - 1;
            } else {
                result = mid;
                left = mid + 1;
            }
        }
        return result;
    }

    /**
     * 6356. 收集树中金币
     * 给你一个 n 个节点的无向无根树，节点编号从 0 到 n - 1 。给你整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间有一条边。再给你一个长度为 n 的数组 coins ，其中 coins[i] 可能为 0 也可能为 1 ，1 表示节点 i 处有一个金币。
     * 一开始，你需要选择树中任意一个节点出发。你可以执行下述操作任意次：
     * 收集距离当前节点距离为 2 以内的所有金币，或者
     * 移动到树中一个相邻节点。
     * 你需要收集树中所有的金币，并且回到出发节点，请你返回最少经过的边数。
     * 如果你多次经过一条边，每一次经过都会给答案加一。
     * 示例 1：
     * 输入：coins = [1,0,0,0,0,1], edges = [[0,1],[1,2],[2,3],[3,4],[4,5]]
     * 输出：2
     * 解释：从节点 2 出发，收集节点 0 处的金币，移动到节点 3 ，收集节点 5 处的金币，然后移动回节点 2 。
     * 示例 2：
     * 输入：coins = [0,0,0,1,1,0,0,1], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
     * 输出：2
     * 解释：从节点 0 出发，收集节点 4 和 3 处的金币，移动到节点 2 处，收集节点 7 处的金币，移动回节点 0 。
     * 提示：
     * n == coins.length
     * 1 <= n <= 3 * 10^4
     * 0 <= coins[i] <= 1
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * edges 表示一棵合法的树。
     */
    public int collectTheCoins(int[] coins, int[][] edges) {
        return -1;
    }

    public static void main(String[] args) {
        Solution338 solution338 = new Solution338();

    }
}
