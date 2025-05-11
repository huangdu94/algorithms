package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 447. 回旋镖的数量
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * 返回平面上所有回旋镖的数量。
 * 示例 1：
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 示例 2：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * 示例 3：
 * 输入：points = [[1,1]]
 * 输出：0
 * 提示：
 * n == points.length
 * 1 <= n <= 500
 * points[i].length == 2
 * -10^4 <= xi, yi <= 10^4
 * 所有点都 互不相同
 *
 * @author huangdu
 * @version 2021/9/13
 */
public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        // value: map(key: 与其它点的距离的平方 value: 该距离的点数量)
        List<Map<Integer, Integer>> countList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int[] current = points[i];
            Map<Integer, Integer> count = new HashMap<>();
            countList.add(count);
            for (int j = 0; j < n; j++) {
                if (j == i) {continue;}
                count.merge(squareDistance(current, points[j]), 1, Integer::sum);
            }
        }
        int ans = 0;
        for (Map<Integer, Integer> map : countList) {
            for (Integer count : map.values()) {
                if (count > 1) {
                    ans += count * (count - 1);
                }
            }
        }
        return ans;
    }

    private int squareDistance(int[] a, int[] b) {
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }
}
