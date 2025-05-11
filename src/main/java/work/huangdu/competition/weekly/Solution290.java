package work.huangdu.competition.weekly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 第 290 场周赛
 *
 * @author huangdu
 * @version 2022/4/24
 */
public class Solution290 {
    /**
     * 6041. 多个数组求交集
     * 给你一个二维整数数组 nums ，其中 nums[i] 是由 不同 正整数组成的一个非空数组，按 升序排列 返回一个数组，数组中的每个元素在 nums 所有数组 中都出现过。
     * 示例 1：
     * 输入：nums = [[3,1,2,4,5],[1,2,3,4],[3,4,5,6]]
     * 输出：[3,4]
     * 解释：
     * nums[0] = [3,1,2,4,5]，nums[1] = [1,2,3,4]，nums[2] = [3,4,5,6]，在 nums 中每个数组中都出现的数字是 3 和 4 ，所以返回 [3,4] 。
     * 示例 2：
     * 输入：nums = [[1,2,3],[4,5,6]]
     * 输出：[]
     * 解释：
     * 不存在同时出现在 nums[0] 和 nums[1] 的整数，所以返回一个空列表 [] 。
     * 提示：
     * 1 <= nums.length <= 1000
     * 1 <= sum(nums[i].length) <= 1000
     * 1 <= nums[i][j] <= 1000
     * nums[i] 中的所有值 互不相同
     */
    public List<Integer> intersection(int[][] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        int[] counts = new int[1001];
        for (int[] num : nums) {
            for (int i : num) {
                counts[i]++;
            }
        }
        for (int i = 1; i <= 1000; i++) {
            if (counts[i] == n) {
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     * 6042. 统计圆内格点数目
     * 给你一个二维整数数组 circles ，其中 circles[i] = [xi, yi, ri] 表示网格上圆心为 (xi, yi) 且半径为 ri 的第 i 个圆，返回出现在 至少一个 圆内的 格点数目 。
     * 注意：
     * 格点 是指整数坐标对应的点。
     * 圆周上的点 也被视为出现在圆内的点。
     * 示例 1：
     * 输入：circles = [[2,2,1]]
     * 输出：5
     * 解释：
     * 给定的圆如上图所示。
     * 出现在圆内的格点为 (1, 2)、(2, 1)、(2, 2)、(2, 3) 和 (3, 2)，在图中用绿色标识。
     * 像 (1, 1) 和 (1, 3) 这样用红色标识的点，并未出现在圆内。
     * 因此，出现在至少一个圆内的格点数目是 5 。
     * 示例 2：
     * 输入：circles = [[2,2,2],[3,4,1]]
     * 输出：16
     * 解释：
     * 给定的圆如上图所示。
     * 共有 16 个格点出现在至少一个圆内。
     * 其中部分点的坐标是 (0, 2)、(2, 0)、(2, 4)、(3, 2) 和 (4, 4) 。
     * 提示：
     * 1 <= circles.length <= 200
     * circles[i].length == 3
     * 1 <= xi, yi <= 100
     * 1 <= ri <= min(xi, yi)
     */
    public int countLatticePoints(int[][] circles) {
        Set<Integer> set = new HashSet<>();
        for (int[] circle : circles) {
            int x = circle[0], y = circle[1], r = circle[2];
            for (int i = x - r; i <= x + r; i++) {
                for (int j = y - r; j <= y + r; j++) {
                    if ((i - x) * (i - x) + (j - y) * (j - y) <= r * r) {
                        set.add(i * 200 + j);
                    }
                }
            }
        }
        return set.size();
    }

    /**
     * 6043. 统计包含每个点的矩形数目
     * 给你一个二维整数数组 rectangles ，其中 rectangles[i] = [li, hi] 表示第 i 个矩形长为 li 高为 hi 。给你一个二维整数数组 points ，其中 points[j] = [xj, yj] 是坐标为 (xj, yj) 的一个点。
     * 第 i 个矩形的 左下角 在 (0, 0) 处，右上角 在 (li, hi) 。
     * 请你返回一个整数数组 count ，长度为 points.length，其中 count[j]是 包含 第 j 个点的矩形数目。
     * 如果 0 <= xj <= li 且 0 <= yj <= hi ，那么我们说第 i 个矩形包含第 j 个点。如果一个点刚好在矩形的 边上 ，这个点也被视为被矩形包含。
     * 示例 1：
     * 输入：rectangles = [[1,2],[2,3],[2,5]], points = [[2,1],[1,4]]
     * 输出：[2,1]
     * 解释：
     * 第一个矩形不包含任何点。
     * 第二个矩形只包含一个点 (2, 1) 。
     * 第三个矩形包含点 (2, 1) 和 (1, 4) 。
     * 包含点 (2, 1) 的矩形数目为 2 。
     * 包含点 (1, 4) 的矩形数目为 1 。
     * 所以，我们返回 [2, 1] 。
     * 示例 2：
     * 输入：rectangles = [[1,1],[2,2],[3,3]], points = [[1,3],[1,1]]
     * 输出：[1,3]
     * 解释：
     * 第一个矩形只包含点 (1, 1) 。
     * 第二个矩形只包含点 (1, 1) 。
     * 第三个矩形包含点 (1, 3) 和 (1, 1) 。
     * 包含点 (1, 3) 的矩形数目为 1 。
     * 包含点 (1, 1) 的矩形数目为 3 。
     * 所以，我们返回 [1, 3] 。
     * 提示：
     * 1 <= rectangles.length, points.length <= 5 * 10^4
     * rectangles[i].length == points[j].length == 2
     * 1 <= li, xj <= 10^9
     * 1 <= hi, yj <= 100
     * 所有 rectangles 互不相同 。
     * 所有 points 互不相同 。
     */
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        int n = points.length;
        Arrays.sort(rectangles, Comparator.comparingInt((int[] point) -> point[1]).thenComparingInt(point -> point[0]));
        Map<Integer, List<Integer>> rectangleMap = new LinkedHashMap<>(100);
        for (int[] rectangle : rectangles) {
            int h = rectangle[1], l = rectangle[0];
            List<Integer> lList = rectangleMap.computeIfAbsent(h, k -> new ArrayList<>());
            lList.add(l);
        }
        List<Integer> hList = new ArrayList<>(rectangleMap.keySet());
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int y = points[i][1], x = points[i][0];
            int left = 0, right = hList.size();
            while (left < right) {
                int mid = left + ((right - left) >> 1);
                if (hList.get(mid) < y) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            for (int hIndex = left; hIndex < hList.size(); hIndex++) {
                List<Integer> lList = rectangleMap.get(hList.get(hIndex));
                left = 0;
                right = lList.size();
                while (left < right) {
                    int mid = left + ((right - left) >> 1);
                    if (lList.get(mid) < x) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                ans[i] += lList.size() - left;
            }
        }
        return ans;
    }

    /**
     * 6044. 花期内花的数目
     * 给你一个下标从 0 开始的二维整数数组 flowers ，其中 flowers[i] = [starti, endi] 表示第 i 朵花的 花期 从 starti 到 endi （都 包含）。同时给你一个下标从 0 开始大小为 n 的整数数组 persons ，persons[i] 是第 i 个人来看花的时间。
     * 请你返回一个大小为 n 的整数数组 answer ，其中 answer[i]是第 i 个人到达时在花期内花的 数目 。
     * 示例 1：
     * 输入：flowers = [[1,6],[3,7],[9,12],[4,13]], persons = [2,3,7,11]
     * 输出：[1,2,2,2]
     * 解释：上图展示了每朵花的花期时间，和每个人的到达时间。
     * 对每个人，我们返回他们到达时在花期内花的数目。
     * 示例 2：
     * 输入：flowers = [[1,10],[3,3]], persons = [3,3,2]
     * 输出：[2,2,1]
     * 解释：上图展示了每朵花的花期时间，和每个人的到达时间。
     * 对每个人，我们返回他们到达时在花期内花的数目。
     * 提示：
     * 1 <= flowers.length <= 5 * 10^4
     * flowers[i].length == 2
     * 1 <= starti <= endi <= 10^9
     * 1 <= persons.length <= 5 * 10^4
     * 1 <= persons[i] <= 10^9
     */
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        Set<Integer> timeSet = new HashSet<>();
        for (int[] flow : flowers) {
            timeSet.add(flow[0]);
            timeSet.add(flow[1]);
        }
        for (int person : persons) {
            timeSet.add(person);
        }
        List<Integer> timeList = new ArrayList<>(timeSet);
        timeList.sort(Integer::compare);
        Map<Integer, Integer> timeIdMap = new HashMap<>();
        for (int id = 1; id <= timeList.size(); id++) {
            timeIdMap.put(timeList.get(id - 1), id);
        }
        int[] diffs = new int[timeList.size() + 2];
        for (int[] flow : flowers) {
            int startId = timeIdMap.get(flow[0]), endId = timeIdMap.get(flow[1]);
            diffs[startId]++;
            diffs[endId + 1]--;
        }
        for (int i = 1; i < diffs.length; i++) {
            diffs[i] += diffs[i - 1];
        }
        int n = persons.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = diffs[timeIdMap.get(persons[i])];
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution290 solution = new Solution290();

        int[][] flowers = {{1, 6}, {3, 7}, {9, 12}, {4, 13}};
        int[] persons = {2, 3, 7, 11};

        System.out.println(Arrays.toString(solution.fullBloomFlowers(flowers, persons)));
    }

}
