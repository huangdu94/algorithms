package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 1681. 最小不兼容性
 * 给你一个整数数组 nums 和一个整数 k 。你需要将这个数组划分到 k 个相同大小的子集中，使得同一个子集里面没有两个相同的元素。
 * 一个子集的 不兼容性 是该子集里面最大值和最小值的差。
 * 请你返回将数组分成 k 个子集后，各子集 不兼容性 的 和 的 最小值 ，如果无法分成分成 k 个子集，返回 -1 。
 * 子集的定义是数组中一些数字的集合，对数字顺序没有要求。
 * 示例 1：
 * 输入：nums = [1,2,1,4], k = 2
 * 输出：4
 * 解释：最优的分配是 [1,2] 和 [1,4] 。
 * 不兼容性和为 (2-1) + (4-1) = 4 。
 * 注意到 [1,1] 和 [2,4] 可以得到更小的和，但是第一个集合有 2 个相同的元素，所以不可行。
 * 示例 2：
 * 输入：nums = [6,3,8,1,3,1,2,2], k = 4
 * 输出：6
 * 解释：最优的子集分配为 [1,2]，[2,3]，[6,8] 和 [1,3] 。
 * 不兼容性和为 (2-1) + (3-2) + (8-6) + (3-1) = 6 。
 * 示例 3：
 * 输入：nums = [5,3,3,6,3,3], k = 3
 * 输出：-1
 * 解释：没办法将这些数字分配到 3 个子集且满足每个子集里没有相同数字。
 * 提示：
 * 1 <= k <= nums.length <= 16
 * nums.length 能被 k 整除。
 * 1 <= nums[i] <= nums.length
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/7/3
 */
public class MinimumIncompatibility {
    private int n;
    private boolean[] valid;
    private int[] memo;
    private int[] nums;
    private int k;

    public int minimumIncompatibility(int[] nums, int k) {
        this.n = nums.length;
        this.nums = nums;
        this.k = k;
        this.valid = new boolean[1 << n];
        this.memo = new int[1 << n];
        Arrays.fill(memo, -2);
        for (int mask = (1 << n) - 1, sub = mask; sub > 0; sub = sub - 1 & mask) {
            int value = value(sub);
            if (value != -1) {
                valid[sub] = true;
                memo[sub] = value;
            }
        }
        return dfs((1 << n) - 1);
    }

    private int dfs(int status) {
        if (status == 0) {return 0;}
        if (memo[status] != -2) {return memo[status];}
        int sum = Integer.MAX_VALUE;
        for (int sub = status; sub > 0; sub = sub - 1 & status) {
            if (valid[sub] && dfs(status ^ sub) != -1) {
                sum = Math.min(sum, memo[sub] + dfs(status ^ sub));
            }
        }
        return memo[status] = sum == Integer.MAX_VALUE ? -1 : sum;
    }

    private int value(int status) {
        int count = 0, set = 0, min = n, max = 1;
        for (int i = 0; i < n; i++) {
            if ((status >> i & 1) != 0) {
                int num = nums[i];
                if ((set & 1 << num) != 0 || ++count > n / k) {return -1;}
                set |= 1 << num;
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
        }
        return count == n / k ? max - min : -1;
    }

    public static void main(String[] args) {
        int[] nums = {6, 3, 8, 1, 3, 1, 2, 2};
        int k = 4;
        MinimumIncompatibility mi = new MinimumIncompatibility();
        System.out.println(mi.minimumIncompatibility(nums, k));
    }
}
