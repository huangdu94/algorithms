package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 850. 矩形面积 II
 * 我们给出了一个（轴对齐的）二维矩形列表 rectangles 。 对于 rectangle[i] = [x1, y1, x2, y2]，其中（x1，y1）是矩形 i 左下角的坐标， (xi1, yi1) 是该矩形 左下角 的坐标， (xi2, yi2) 是该矩形 右上角 的坐标。
 * 计算平面中所有 rectangles 所覆盖的 总面积 。任何被两个或多个矩形覆盖的区域应只计算 一次 。
 * 返回 总面积 。因为答案可能太大，返回 10^9 + 7 的 模 。
 * 示例 1：
 * 输入：rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
 * 输出：6
 * 解释：如图所示，三个矩形覆盖了总面积为6的区域。
 * 从(1,1)到(2,2)，绿色矩形和红色矩形重叠。
 * 从(1,0)到(2,3)，三个矩形都重叠。
 * 示例 2：
 * 输入：rectangles = [[0,0,1000000000,1000000000]]
 * 输出：49
 * 解释：答案是 10^18 对 (10^9 + 7) 取模的结果， 即 49 。
 * 提示：
 * 1 <= rectangles.length <= 200
 * rectanges[i].length = 4
 * 0 <= xi1, yi1, xi2, yi2 <= 10^9
 * 矩形叠加覆盖后的总面积不会超越 2^63 - 1 ，这意味着可以用一个 64 位有符号整数来保存面积结果。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/9/16
 */
public class RectangleArea {
    /**
     * 切块&暴力判断 O(n^3)
     */
    static class Solution1 {
        public int rectangleArea(int[][] rectangles) {
            Set<Integer> xSet = new HashSet<>(rectangles.length << 1), ySet = new HashSet<>(rectangles.length << 1);
            for (int[] rectangle : rectangles) {
                xSet.add(rectangle[0]);
                xSet.add(rectangle[2]);
                ySet.add(rectangle[1]);
                ySet.add(rectangle[3]);
            }
            List<Integer> xList = new ArrayList<>(xSet), yList = new ArrayList<>(ySet);
            xList.sort(Integer::compare);
            yList.sort(Integer::compare);
            long ans = 0;
            for (int i = 0, m = xList.size() - 1; i < m; i++) {
                double centerX = (xList.get(i + 1) + xList.get(i)) / 2.0;
                for (int j = 0, n = yList.size() - 1; j < n; j++) {
                    double centerY = (yList.get(j + 1) + yList.get(j)) / 2.0;
                    if (check(rectangles, centerX, centerY)) {
                        ans += (long)(xList.get(i + 1) - xList.get(i)) * (yList.get(j + 1) - yList.get(j));
                    }
                }
            }
            return (int)(ans % 1000000007);
        }

        private boolean check(int[][] rectangles, double centerX, double centerY) {
            for (int[] rectangle : rectangles) {
                if (rectangle[0] < centerX && centerX < rectangle[2] && rectangle[1] < centerY && centerY < rectangle[3]) {
                    return true;
                }
            }
            return false;
        }

        public static void main(String[] args) {
            RectangleArea.Solution1 solution = new RectangleArea.Solution1();
            System.out.println(solution.rectangleArea(new int[][] {{0, 0, 1, 1}, {2, 2, 3, 3}}));
        }
    }

    /**
     * 离散&扫描线 O(n^2)
     */
    static class Solution2 {
        public int rectangleArea(int[][] rectangles) {
            List<Integer> xList = new ArrayList<>(rectangles.length << 1);
            List<int[]> yList = new ArrayList<>(rectangles.length << 1);
            for (int i = 0, n = rectangles.length; i < n; i++) {
                int[] rectangle = rectangles[i];
                xList.add(rectangle[0]);
                xList.add(rectangle[2]);
                yList.add(new int[] {rectangle[1], i, 1});
                yList.add(new int[] {rectangle[3], i, -1});
            }
            xList = xList.stream().distinct().sorted(Integer::compare).collect(Collectors.toList());
            yList.sort((o1, o2) -> {
                if (o1[0] != o2[0]) {return Integer.compare(o1[0], o2[0]);}
                if (o1[1] != o2[1]) {return Integer.compare(o1[1], o2[1]);}
                return Integer.compare(o1[2], o2[2]);
            });
            int m = xList.size(), n = yList.size(), i = 0;
            int[] segment = new int[m - 1];
            long ans = 0;
            while (i < n) {
                int j = i, pivot = yList.get(i)[0];
                while (j < n && yList.get(j)[0] == pivot) {j++;}
                if (j == n) {break;}
                for (int k = i; k < j; k++) {
                    int[] data = yList.get(k);
                    int diff = data[2];
                    int[] rectangle = rectangles[data[1]];
                    int left = rectangle[0], right = rectangle[2];
                    for (int x = 0; x < m - 1; x++) {
                        if (left <= xList.get(x) && xList.get(x + 1) <= right) {
                            segment[x] += diff;
                        }
                    }
                }
                long length = 0;
                for (int x = 0; x < m - 1; x++) {
                    if (segment[x] > 0) {length += xList.get(x + 1) - xList.get(x);}
                }
                ans += length * (yList.get(j)[0] - yList.get(i)[0]);
                i = j;
            }
            return (int)(ans % 1000000007);
        }

        public static void main(String[] args) {
            RectangleArea.Solution2 solution = new RectangleArea.Solution2();
            System.out.println(solution.rectangleArea(new int[][] {{0, 0, 2, 2}, {1, 0, 2, 3}, {1, 0, 3, 1}}));
        }
    }
}