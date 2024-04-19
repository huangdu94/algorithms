package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.Random;

/**
 * 497. 非重叠矩形中的随机点
 * 给定一个由非重叠的轴对齐矩形的数组 rects ，其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi) 是第 i 个矩形的左下角点，(xi, yi) 是第 i 个矩形的右上角点。设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等概率被返回。
 * 在给定的矩形覆盖的空间内的任何整数点都有可能被返回。
 * 请注意 ，整数点是具有整数坐标的点。
 * 实现 Solution 类:
 * Solution(int[][] rects) 用给定的矩形数组 rects 初始化对象。
 * int[] pick() 返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。
 * 示例 1：
 * 输入:
 * ["Solution", "pick", "pick", "pick", "pick", "pick"]
 * [[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
 * 输出:
 * [null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]
 * 解释：
 * Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
 * solution.pick(); // 返回 [1, -2]
 * solution.pick(); // 返回 [1, -1]
 * solution.pick(); // 返回 [-1, -2]
 * solution.pick(); // 返回 [-2, -2]
 * solution.pick(); // 返回 [0, 0]
 * 提示：
 * 1 <= rects.length <= 100
 * rects[i].length == 4
 * -109 <= ai < xi <= 10^9
 * -109 <= bi < yi <= 10^9
 * xi - ai <= 2000
 * yi - bi <= 2000
 * 所有的矩形不重叠。
 * pick 最多被调用 10^4 次。
 *
 * @author huangdu
 * @version 2022/6/9
 */
public class Pick {
    static class Solution {
        private final int[] areaPrefix;
        private final int[][] rects;

        public Solution(int[][] rects) {
            int n = rects.length;
            this.areaPrefix = new int[n];
            for (int i = 0; i < n; i++) {
                int[] rect = rects[i];
                int area = (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
                areaPrefix[i] = (i == 0 ? 0 : areaPrefix[i - 1]) + area;
            }
            this.rects = rects;
        }

        public int[] pick() {
            Random random = new Random();
            int idx = binarySearch(random.nextInt(areaPrefix[areaPrefix.length - 1]));
            int[] rect = rects[idx];
            return new int[] {rect[0] + random.nextInt(rect[2] - rect[0] + 1), rect[1] + random.nextInt(rect[3] - rect[1] + 1)};
        }

        private int binarySearch(int number) {
            int left = 0, right = areaPrefix.length - 1;
            while (left < right) {
                int mid = left + ((right - left) >> 1);
                if (number < areaPrefix[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }
    }

    public static void main(String[] args) {
        int[][] rects = {{82918473, -57180867, 82918476, -57180863}, {83793579, 18088559, 83793580, 18088560}, {66574245, 26243152, 66574246, 26243153}, {72983930, 11921716, 72983934, 11921720}};
        Solution solution = new Solution(rects);
        System.out.println(Arrays.toString(solution.pick()));
    }
}