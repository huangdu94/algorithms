package work.huangdu.question_bank.medium;

import java.util.*;

/**
 * 377. 组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 * 示例 1：
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 示例 2：
 * 输入：nums = [9], target = 3
 * 输出：0
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 * 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2021/4/24
 * @see CombinationSum
 * @see CombinationSum2
 * @see CombinationSum3
 */
public class CombinationSum4 {
    private int n;
    private int target;
    private int[] nums;
    private Map<Integer, Integer> combination;
    private int count;

    // 回溯超时
    public int combinationSum42(int[] nums, int target) {
        Arrays.sort(nums);
        this.n = nums.length;
        this.target = target;
        this.nums = nums;
        this.combination = new HashMap<>();
        this.count = 0;
        helper(0, 0);
        return count;
    }

    private void helper(int sum, int index) {
        if (sum == target) {
            count += getCount();
            return;
        }
        for (int i = index; i < n; i++) {
            if (sum + nums[i] > target) {
                break;
            }
            combination.merge(nums[i], 1, Integer::sum);
            helper(sum + nums[i], i);
            if (combination.merge(nums[i], -1, Integer::sum) == 0) {
                combination.remove(nums[i]);
            }
        }
    }

    private int getCount() {
        Collection<Integer> values = combination.values();
        int n = 0, count = 1;
        for (int value : values) {
            n += value;
        }
        for (int value : values) {
            count *= c(value, n);
            n -= value;
        }
        return count;
    }

    private int c(int a, int n) {
        a = Math.min(a, n - a);
        int result = 1;
        for (int i = 0; i < a; i++) {
            result = result * (n - i) / (i + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        CombinationSum4 cs4 = new CombinationSum4();
        int[] nums = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 300, 310, 320, 330, 340, 350, 360, 370, 380, 390, 400, 410, 420, 430, 440, 450, 460, 470, 480, 490, 500, 510, 520, 530, 540, 550, 560, 570, 580, 590, 600, 610, 620, 630, 640, 650, 660, 670, 680, 690, 700, 710, 720, 730, 740, 750, 760, 770, 780, 790, 800, 810, 820, 830, 840, 850, 860, 870, 880, 890, 900, 910, 920, 930, 940, 950, 960, 970, 980, 990, 111};
        int target = 999;
        System.out.println(cs4.combinationSum4(nums, target));
    }

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num > i) {
                    break;
                }
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
}
