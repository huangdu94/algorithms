package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 757. 设置交集大小至少为2
 * 一个整数区间 [a, b]  ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
 * 给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
 * 输出这个最小集合S的大小。
 * 示例 1:
 * 输入: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
 * 输出: 3
 * 解释:
 * 考虑集合 S = {2, 3, 4}. S与intervals中的四个区间都有至少2个相交的元素。
 * 且这是S最小的情况，故我们输出3。
 * 示例 2:
 * 输入: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
 * 输出: 5
 * 解释:
 * 最小的集合S = {1, 2, 3, 4, 5}.
 * 注意:
 * 1. intervals 的长度范围为[1, 3000]。
 * 2. intervals[i] 长度为 2，分别代表左、右边界。
 * 3. intervals[i][j] 的值是 [0, 10^8]范围内的整数。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/9/7
 */
public class IntersectionSizeTwo {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[1]));
        int num2 = intervals[0][1], num1 = num2 - 1, ans = 2;
        for (int[] interval : intervals) {
            int left = interval[0];
            if (left <= num1) {continue;}
            if (left <= num2) {
                if (num2 == interval[1]) {
                    num1 = num2 - 1;
                } else {
                    num1 = num2;
                    num2 = interval[1];
                }
                ans += 1;
            } else {
                num2 = interval[1];
                num1 = num2 - 1;
                ans += 2;
            }
        }
        return ans;
    }
}
