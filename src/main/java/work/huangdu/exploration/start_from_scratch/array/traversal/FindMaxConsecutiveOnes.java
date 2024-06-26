package work.huangdu.exploration.start_from_scratch.array.traversal;

/**
 * 485. 最大连续1的个数
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * 示例 1:
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * 注意：
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 *
 * @author huangdu
 * @version 2020/9/13 13:53
 */
public class FindMaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, count = 0;
        for (int num : nums) {
            if (num == 0) {
                if (count > 0) {
                    if (count > max) max = count;
                    count = 0;
                }
            } else {
                count++;
            }
        }
        if (count > max) max = count;
        return max;
    }

    public int findMaxConsecutiveOnes2(int[] nums) {
        int n = nums.length, i = 0, max = 0;
        while (i < n) {
            while (i < n && nums[i] == 0) {
                i++;
            }
            int start = i;
            while (i < n && nums[i] == 1) {
                i++;
            }
            max = Math.max(max, i - start);
        }
        return max;
    }
}
