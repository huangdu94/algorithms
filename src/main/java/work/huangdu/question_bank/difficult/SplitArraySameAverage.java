package work.huangdu.question_bank.difficult;

import java.util.HashSet;
import java.util.Set;

/**
 * 805. 数组的均值分割
 * 给定你一个整数数组 nums
 * 我们要将 nums 数组中的每个元素移动到 A 数组 或者 B 数组中，使得 A 数组和 B 数组不为空，并且 average(A) == average(B) 。
 * 如果可以完成则返回true ， 否则返回 false  。
 * 注意：对于数组 arr ,  average(arr) 是 arr 的所有元素的和除以 arr 长度。
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7,8]
 * 输出: true
 * 解释: 我们可以将数组分割为 [1,4,5,8] 和 [2,3,6,7], 他们的平均值都是4.5。
 * 示例 2:
 * 输入: nums = [3,1]
 * 输出: false
 * 提示:
 * 1 <= nums.length <= 30
 * 0 <= nums[i] <= 10^4
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/11/14
 */
public class SplitArraySameAverage {
    /**
     * 方法一：
     * 1. 把数组分成两份，如果数组长度是偶数则平均分配，如果数组长度是奇数，则一部分多1
     * 2. 分别对每一部分进行选择遍历 2^(n/2) 种可能（每一个元素都有选和不选两种可能性）
     * 3. 对其中一部分的每一种可能的和X，在另一部分找和是否为avg-X的（其中avg为整个数组的平均数）
     * 4. 如果有，且满足 不是两部分同时都全选，也不能同时都不选（同时全选或同时都不选，另一个就是空数组不满足题目要求）
     */
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length, na = n / 2 + n % 2, nb = n / 2, total = 0;
        for (int i = 0; i < n; i++) {nums[i] *= n;}
        for (int num : nums) {total += num;}
        int avg = total / n;
        for (int i = 0; i < n; i++) {nums[i] -= avg;}
        int fullA = 0;
        Set<Integer> aSumSet = new HashSet<>();
        aSumSet.add(0);
        for (int i = 0; i < na; i++) {fullA += nums[i];}
        for (int select = 1; select < (1 << na) - 1; select++) {
            int sum = 0;
            for (int k = 0, mask = 1; k < na; k++, mask <<= 1) {
                if ((select & mask) != 0) {
                    sum += nums[k];
                }
            }
            aSumSet.add(sum);
        }
        for (int select = 1; select < 1 << nb; select++) {
            int sum = 0;
            for (int k = 0, mask = 1; k < nb; k++, mask <<= 1) {
                if ((select & mask) != 0) {
                    sum += nums[k + na];
                }
            }
            if (aSumSet.contains(-sum) || select != (1 << nb) - 1 && fullA == -sum) {return true;}
        }
        return false;
    }

    public static void main(String[] args) {
        SplitArraySameAverage sasa = new SplitArraySameAverage();
        System.out.println(sasa.splitArraySameAverage(new int[] {6, 8, 18, 3, 1}));
    }
}
