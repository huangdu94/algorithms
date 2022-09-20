package work.huangdu.question_bank.easy;

import java.util.Arrays;

/**
 * 1636. 按照频率将数组升序排序
 * 给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。
 * 请你返回排序后的数组。
 * 示例 1：
 * 输入：nums = [1,1,2,2,2,3]
 * 输出：[3,1,1,2,2,2]
 * 解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
 * 示例 2：
 * 输入：nums = [2,3,1,3,2]
 * 输出：[1,3,3,2,2]
 * 解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
 * 示例 3：
 * 输入：nums = [-1,1,-6,4,5,-6,1,4,1]
 * 输出：[5,-1,4,4,-6,-6,1,1,1]
 * 提示：
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/9/19
 */
public class FrequencySort {
    public int[] frequencySort(int[] nums) {
        int n = nums.length;
        int[] freqs = new int[201];
        Integer[] inums = new Integer[n];
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            freqs[num + 100]++;
            inums[i] = num;
        }
        Arrays.sort(inums, (num1, num2) -> {
            int compareFreqs = Integer.compare(freqs[num1 + 100], freqs[num2 + 100]);
            return compareFreqs == 0 ? Integer.compare(num2, num1) : compareFreqs;
        });
        for (int i = 0; i < n; i++) {
            ans[i] = inums[i];
        }
        return ans;
    }
}
