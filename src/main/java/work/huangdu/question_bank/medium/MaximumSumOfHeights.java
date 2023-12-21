package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.List;

/**
 * 2866. 美丽塔 II
 * 给你一个长度为 n 下标从 0 开始的整数数组 maxHeights 。
 * 你的任务是在坐标轴上建 n 座塔。第 i 座塔的下标为 i ，高度为 heights[i] 。
 * 如果以下条件满足，我们称这些塔是 美丽 的：
 * 1 <= heights[i] <= maxHeights[i]
 * heights 是一个 山脉 数组。
 * 如果存在下标 i 满足以下条件，那么我们称数组 heights 是一个 山脉 数组：
 * 对于所有 0 < j <= i ，都有 heights[j - 1] <= heights[j]
 * 对于所有 i <= k < n - 1 ，都有 heights[k + 1] <= heights[k]
 * 请你返回满足 美丽塔 要求的方案中，高度和的最大值 。
 * 示例 1：
 * 输入：maxHeights = [5,3,4,1,1]
 * 输出：13
 * 解释：和最大的美丽塔方案为 heights = [5,3,3,1,1] ，这是一个美丽塔方案，因为：
 * - 1 <= heights[i] <= maxHeights[i]
 * - heights 是个山脉数组，峰值在 i = 0 处。
 * 13 是所有美丽塔方案中的最大高度和。
 * 示例 2：
 * 输入：maxHeights = [6,5,3,9,2,7]
 * 输出：22
 * 解释： 和最大的美丽塔方案为 heights = [3,3,3,9,2,2] ，这是一个美丽塔方案，因为：
 * - 1 <= heights[i] <= maxHeights[i]
 * - heights 是个山脉数组，峰值在 i = 3 处。
 * 22 是所有美丽塔方案中的最大高度和。
 * 示例 3：
 * 输入：maxHeights = [3,2,5,5,2,3]
 * 输出：18
 * 解释：和最大的美丽塔方案为 heights = [2,2,5,5,2,2] ，这是一个美丽塔方案，因为：
 * - 1 <= heights[i] <= maxHeights[i]
 * - heights 是个山脉数组，最大值在 i = 2 处。
 * 注意，在这个方案中，i = 3 也是一个峰值。
 * 18 是所有美丽塔方案中的最大高度和。
 * 提示：
 * 1 <= n == maxHeights <= 10^5
 * 1 <= maxHeights[i] <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class MaximumSumOfHeights {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size(), top = 0;
        long[] leftSum = new long[n], rightSum = new long[n];
        int[] stack = new int[n];
        for (int i = 0; i < n; i++) {
            long maxHeight = maxHeights.get(i);
            while (top > 0 && maxHeight <= maxHeights.get(stack[top - 1])) {top--;}
            int j = top == 0 ? -1 : stack[top - 1];
            stack[top++] = i;
            leftSum[i] = (j >= 0 ? leftSum[j] : 0) + maxHeight * (i - j);
        }
        top = 0;
        for (int i = n - 1; i >= 0; i--) {
            long maxHeight = maxHeights.get(i);
            while (top > 0 && maxHeight <= maxHeights.get(stack[top - 1])) {top--;}
            int j = top == 0 ? n : stack[top - 1];
            stack[top++] = i;
            rightSum[i] = (j < n ? rightSum[j] : 0) + maxHeight * (j - i);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {ans = Math.max(ans, leftSum[i] + rightSum[i] - maxHeights.get(i));}
        return ans;
    }

    public long maximumSumOfHeights2(List<Integer> maxHeights) {
        long ans = 0;
        for (int peak = 0, n = maxHeights.size(); peak < n; peak++) {
            int peakHeight = maxHeights.get(peak);
            long sum = peakHeight;
            for (int left = peak - 1, pre = peakHeight; left >= 0; left--) {
                sum += (pre = Math.min(maxHeights.get(left), pre));
            }
            for (int right = peak + 1, pre = peakHeight; right < n; right++) {
                sum += (pre = Math.min(maxHeights.get(right), pre));
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        MaximumSumOfHeights msh = new MaximumSumOfHeights();
        System.out.println(msh.maximumSumOfHeights(Arrays.asList(5, 3, 4, 1, 1)));
    }
}
