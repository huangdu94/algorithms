package work.huangdu.competition.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 第 288 场周赛
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/4/10
 */
public class Solution288 {
    /**
     * 2231. 按奇偶性交换后的最大数字
     * 给你一个正整数 num 。你可以交换 num 中 奇偶性 相同的任意两位数字（即，都是奇数或者偶数）。
     * 返回交换 任意 次之后 num 的 最大 可能值。
     * 示例 1：
     * 输入：num = 1234
     * 输出：3412
     * 解释：交换数字 3 和数字 1 ，结果得到 3214 。
     * 交换数字 2 和数字 4 ，结果得到 3412 。
     * 注意，可能存在其他交换序列，但是可以证明 3412 是最大可能值。
     * 注意，不能交换数字 4 和数字 1 ，因为它们奇偶性不同。
     * 示例 2：
     * 输入：num = 65875
     * 输出：87655
     * 解释：交换数字 8 和数字 6 ，结果得到 85675 。
     * 交换数字 5 和数字 7 ，结果得到 87655 。
     * 注意，可能存在其他交换序列，但是可以证明 87655 是最大可能值。
     * 提示：
     * 1 <= num <= 10^9
     */
    class LargestInteger {
        public int largestInteger(int num) {
            List<Integer> bits = new ArrayList<>();
            while (num > 0) {
                bits.add(0, num % 10);
                num /= 10;
            }
            for (int i = 0, len = bits.size(); i < len; i++) {
                int n1 = bits.get(i), maxJ = -1;
                for (int j = i + 1; j < len; j++) {
                    int n2 = bits.get(j);
                    if ((n2 & 1) == (n1 & 1) && n2 > n1 && (maxJ == -1 || n2 > bits.get(maxJ))) {
                        maxJ = j;
                    }
                }
                if (maxJ != -1) {
                    Collections.swap(bits, i, maxJ);
                }
            }
            int ans = 0;
            for (int n : bits) {
                ans = ans * 10 + n;
            }
            return ans;
        }
    }

    /**
     * 2232. 向表达式添加括号后的最小结果
     * 给你一个下标从 0 开始的字符串 expression ，格式为 "<num1>+<num2>" ，其中 <num1> 和 <num2> 表示正整数。
     * 请你向 expression 中添加一对括号，使得在添加之后， expression 仍然是一个有效的数学表达式，并且计算后可以得到 最小 可能值。左括号 必须 添加在 '+' 的左侧，而右括号必须添加在 '+' 的右侧。
     * 返回添加一对括号后形成的表达式 expression ，且满足 expression 计算得到 最小 可能值。如果存在多个答案都能产生相同结果，返回任意一个答案。
     * 生成的输入满足：expression 的原始值和添加满足要求的任一对括号之后 expression 的值，都符合 32-bit 带符号整数范围。
     * 示例 1：
     * 输入：expression = "247+38"
     * 输出："2(47+38)"
     * 解释：表达式计算得到 2 * (47 + 38) = 2 * 85 = 170 。
     * 注意 "2(4)7+38" 不是有效的结果，因为右括号必须添加在 '+' 的右侧。
     * 可以证明 170 是最小可能值。
     * 示例 2：
     * 输入：expression = "12+34"
     * 输出："1(2+3)4"
     * 解释：表达式计算得到 1 * (2 + 3) * 4 = 1 * 5 * 4 = 20 。
     * 示例 3：
     * 输入：expression = "999+999"
     * 输出："(999+999)"
     * 解释：表达式计算得到 999 + 999 = 1998 。
     * 提示：
     * 3 <= expression.length <= 10
     * expression 仅由数字 '1' 到 '9' 和 '+' 组成
     * expression 由数字开始和结束
     * expression 恰好仅含有一个 '+'.
     * expression 的原始值和添加满足要求的任一对括号之后 expression 的值，都符合 32-bit 带符号整数范围
     */
    class MinimizeResult {
        public String minimizeResult(String expression) {
            int n = expression.length(), addIndex = expression.indexOf('+'), index1 = 0, index2 = n - 1;
            long min = Integer.MAX_VALUE;
            for (int i = 0; i < addIndex; i++) {
                for (int j = addIndex + 2; j <= n; j++) {
                    int part1 = i == 0 ? 1 : Integer.parseInt(expression.substring(0, i));
                    int part2 = Integer.parseInt(expression.substring(i, addIndex)) + Integer.parseInt(expression.substring(addIndex + 1, j));
                    int part3 = j == n ? 1 : Integer.parseInt(expression.substring(j));
                    long product = (long)part1 * part2 * part3;
                    if (min > product) {
                        min = product;
                        index1 = i;
                        index2 = j;
                    }
                }
            }
            StringBuilder ans = new StringBuilder(expression);
            ans.insert(index1, '(');
            ans.insert(index2 + 1, ')');
            return ans.toString();
        }
    }

    /**
     * 2233. K 次增加后的最大乘积
     * 给你一个非负整数数组 nums 和一个整数 k 。每次操作，你可以选择 nums 中 任一 元素并将它 增加 1 。
     * 请你返回 至多 k 次操作后，能得到的 nums的 最大乘积 。由于答案可能很大，请你将答案对 10^9 + 7 取余后返回。
     * 示例 1：
     * 输入：nums = [0,4], k = 5
     * 输出：20
     * 解释：将第一个数增加 5 次。
     * 得到 nums = [5, 4] ，乘积为 5 * 4 = 20 。
     * 可以证明 20 是能得到的最大乘积，所以我们返回 20 。
     * 存在其他增加 nums 的方法，也能得到最大乘积。
     * 示例 2：
     * 输入：nums = [6,3,3,2], k = 2
     * 输出：216
     * 解释：将第二个数增加 1 次，将第四个数增加 1 次。
     * 得到 nums = [6, 4, 3, 3] ，乘积为 6 * 4 * 3 * 3 = 216 。
     * 可以证明 216 是能得到的最大乘积，所以我们返回 216 。
     * 存在其他增加 nums 的方法，也能得到最大乘积。
     * 提示：
     * 1 <= nums.length, k <= 10^5
     * 0 <= nums[i] <= 10^6
     */
    class MaximumProduct {
        public int maximumProduct(int[] nums, int k) {
            final int mod = 1000000007;
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int num : nums) {
                queue.offer(num);
            }
            while (k > 0) {
                queue.offer(queue.poll() + 1);
                k--;
            }
            long ans = 1L;
            for (int num : queue) {
                if (num == 0) {return 0;}
                ans = ans * num % mod;
            }
            return (int)ans;
        }
    }

    /**
     * 2234. 花园的最大总美丽值
     * Alice 是 n 个花园的园丁，她想通过种花，最大化她所有花园的总美丽值。
     * 给你一个下标从 0 开始大小为 n 的整数数组 flowers ，其中 flowers[i] 是第 i 个花园里已经种的花的数目。已经种了的花 不能 移走。同时给你 newFlowers ，表示 Alice 额外可以种花的 最大数目 。同时给你的还有整数 target ，full 和 partial 。
     * 如果一个花园有 至少 target 朵花，那么这个花园称为 完善的 ，花园的 总美丽值 为以下分数之 和 ：
     * 完善 花园数目乘以 full.
     * 剩余 不完善 花园里，花的 最少数目 乘以 partial 。如果没有不完善花园，那么这一部分的值为 0 。
     * 请你返回 Alice 种最多 newFlowers 朵花以后，能得到的 最大 总美丽值。
     * 示例 1：
     * 输入：flowers = [1,3,1,1], newFlowers = 7, target = 6, full = 12, partial = 1
     * 输出：14
     * 解释：Alice 可以按以下方案种花
     * - 在第 0 个花园种 2 朵花
     * - 在第 1 个花园种 3 朵花
     * - 在第 2 个花园种 1 朵花
     * - 在第 3 个花园种 1 朵花
     * 花园里花的数目为 [3,6,2,2] 。总共种了 2 + 3 + 1 + 1 = 7 朵花。
     * 只有 1 个花园是完善的。
     * 不完善花园里花的最少数目是 2 。
     * 所以总美丽值为 1 * 12 + 2 * 1 = 12 + 2 = 14 。
     * 没有其他方案可以让花园总美丽值超过 14 。
     * 示例 2：
     * 输入：flowers = [2,4,5,3], newFlowers = 10, target = 5, full = 2, partial = 6
     * 输出：30
     * 解释：Alice 可以按以下方案种花
     * - 在第 0 个花园种 3 朵花
     * - 在第 1 个花园种 0 朵花
     * - 在第 2 个花园种 0 朵花
     * - 在第 3 个花园种 2 朵花
     * 花园里花的数目为 [5,4,5,5] 。总共种了 3 + 0 + 0 + 2 = 5 朵花。
     * 有 3 个花园是完善的。
     * 不完善花园里花的最少数目为 4 。
     * 所以总美丽值为 3 * 2 + 4 * 6 = 6 + 24 = 30 。
     * 没有其他方案可以让花园总美丽值超过 30 。
     * 注意，Alice可以让所有花园都变成完善的，但这样她的总美丽值反而更小。
     * 提示：
     * 1 <= flowers.length <= 10^5
     * 1 <= flowers[i], target <= 10^5
     * 1 <= newFlowers <= 10^10
     * 1 <= full, partial <= 10^5
     */
    class MaximumBeauty {
        public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
            long n = flowers.length;
            Arrays.sort(flowers);
            // 情况一：所有花园本来就是完善的
            if (flowers[0] >= target) {return n * full;}
            long remainFlowers = newFlowers; // 填充后缀后，剩余可以种植的花
            for (int i = 0; i < n && flowers[i] < target; i++) {
                remainFlowers -= (target - flowers[i]);
            }
            // 情况二：不是所有花园都是完善的，但是可种的花足够多（足够使所有的花园变成完善的）
            if (remainFlowers >= 0) {
                return Math.max(n * full, (n - 1) * full + (long)(target - 1) * partial);
            }
            // 情况三：不是所有花园都是完善的，而且可种的花并不足够多（不能使所有的花园变成完善的）
            long ans = 0, prefixSum = 0;
            for (int i = 0, x = 0; i <= n; ++i) {
                if (remainFlowers >= 0) {
                    // 计算最长前缀的长度
                    while (x < i && (long)flowers[x] * x - prefixSum <= remainFlowers) {
                        // 注意 x 只增不减，二重循环的时间复杂度为 O(n)
                        prefixSum += flowers[x++];
                    }
                    // 计算总美丽值
                    long beauty = (n - i) * full + (remainFlowers + prefixSum) / x * partial;
                    ans = Math.max(ans, beauty);
                }
                if (i < n) {
                    if (flowers[i] >= target) {break;}
                    remainFlowers += target - flowers[i];
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution288 solution = new Solution288();
        System.out.println(solution.new LargestInteger().largestInteger(3159));
        System.out.println(solution.new MinimizeResult().minimizeResult("999+999"));
        System.out.println(solution.new MaximumProduct().maximumProduct(new int[] {6, 3, 3, 2}, 2));
        int[] flowers = {1, 3, 1, 1};
        int newFlowers = 7;
        int target = 6;
        int full = 12;
        int partial = 1;
        System.out.println(solution.new MaximumBeauty().maximumBeauty(flowers, newFlowers, target, full, partial));
    }
}
