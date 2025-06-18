package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 2966. 划分数组并满足最大差限制
 * DivideArray
 *
 * @author huangdu
 * @version 2025/6/18
 */
public class DivideArray {
    /**
     * 前置处理：
     * 统计数字的数量
     * 贪心算法：
     * 最小的数字找到小于等于K的最大的数字与之一一对应，然后中间的数字补位（尽可能用小数字补位）。
     * 找的过程中发现数字个数不够，则返回空
     * （以上贪心算法是错误的，[11,13,24,11,9,23,16,19,13] 8 这个例子通过不了：通过840）
     * 贪心算法2：
     * 都用最小的
     */
    public int[][] divideArray(int[] nums, int k) {
        int n = nums.length;
        int[][] ans = new int[n / 3][];
        Arrays.sort(nums);
        for (int i = 0, j = 0; i < n; i += 3, j++) {
            if (nums[i + 2] - nums[i] > k) {
                return new int[0][];
            }
            ans[j] = new int[]{nums[i], nums[i + 1], nums[i + 2]};
        }
        return ans;
    }

    public int[][] divideArray2(int[] nums, int k) {
        int n = nums.length, m = n / 3, i = 0, num = 1;
        int[][] ans = new int[m][3];
        int[] count = new int[100001];
        for (int number : nums) {
            count[number]++;
        }
        while (i < m && num <= 100000) {
            if (count[num] == 0) {
                num++;
                continue;
            }
            int min = num, max = num + k;
            ans[i][0] = min;
            count[min]--;
            while (min <= max && count[min] == 0) {
                min++;
            }
            if (min > max) {
                return new int[0][];
            }
            ans[i][2] = min;
            count[min]--;
            while (min <= max && count[min] == 0) {
                min++;
            }
            if (min > max) {
                return new int[0][];
            }
            ans[i][1] = min;
            count[min]--;
            i++;
        }
        return ans;
    }
}
