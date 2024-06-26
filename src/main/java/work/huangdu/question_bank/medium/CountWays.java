package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2580. 统计将重叠区间合并成组的方案数
 * 给你一个二维整数数组 ranges ，其中 ranges[i] = [start_i, end_i] 表示 start_i 到 end_i 之间（包括二者）的所有整数都包含在第 i 个区间中。
 * 你需要将 ranges 分成 两个 组（可以为空），满足：
 * 每个区间只属于一个组。
 * 两个有 交集 的区间必须在 同一个 组内。
 * 如果两个区间有至少 一个 公共整数，那么这两个区间是 有交集 的。
 * 比方说，区间 [1, 3] 和 [2, 5] 有交集，因为 2 和 3 在两个区间中都被包含。
 * 请你返回将 ranges 划分成两个组的 总方案数 。由于答案可能很大，将它对 10^9 + 7 取余 后返回。
 * 示例 1：
 * 输入：ranges = [[6,10],[5,15]]
 * 输出：2
 * 解释：
 * 两个区间有交集，所以它们必须在同一个组内。
 * 所以有两种方案：
 * - 将两个区间都放在第 1 个组中。
 * - 将两个区间都放在第 2 个组中。
 * 示例 2：
 * 输入：ranges = [[1,3],[10,20],[2,5],[4,8]]
 * 输出：4
 * 解释：
 * 区间 [1,3] 和 [2,5] 有交集，所以它们必须在同一个组中。
 * 同理，区间 [2,5] 和 [4,8] 也有交集，所以它们也必须在同一个组中。
 * 所以总共有 4 种分组方案：
 * - 所有区间都在第 1 组。
 * - 所有区间都在第 2 组。
 * - 区间 [1,3] ，[2,5] 和 [4,8] 在第 1 个组中，[10,20] 在第 2 个组中。
 * - 区间 [1,3] ，[2,5] 和 [4,8] 在第 2 个组中，[10,20] 在第 1 个组中。
 * 提示：
 * 1 <= ranges.length <= 10^5
 * ranges[i].length == 2
 * 0 <= start_i <= end_i <= 10^9
 *
 * @author huangdu
 */
public class CountWays {
    private static final int MOD = 1000000007;

    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, Comparator.comparingInt((int[] o) -> o[0]));
        int count = 0, last = -1;
        for (int[] range : ranges) {
            if (range[0] > last) {count++;}
            last = Math.max(last, range[1]);
        }
        return pow(count);
    }

    private int pow(int n) {
        long ans = 1, base = 2;
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = ans * base % MOD;
            }
            base = base * base % MOD;
            n >>= 1;
        }
        return (int)ans;
    }

    public static void main(String[] args) {
        CountWays cw = new CountWays();
        int[][] ranges = {{34, 56}, {28, 29}, {12, 16}, {11, 48}, {28, 54}, {22, 55}, {28, 41}, {41, 44}};
        System.out.println(cw.countWays(ranges));
    }
}