package work.huangdu.question_bank.medium;

/**
 * 1828. 统计一个圆中点的数目
 * 给你一个数组 points ，其中 points[i] = [xi, yi] ，表示第 i 个点在二维平面上的坐标。多个点可能会有 相同 的坐标。
 * 同时给你一个数组 queries ，其中 queries[j] = [xj, yj, rj] ，表示一个圆心在 (xj, yj) 且半径为 rj 的圆。
 * 对于每一个查询 queries[j] ，计算在第 j 个圆 内 点的数目。如果一个点在圆的 边界上 ，我们同样认为它在圆 内 。
 * 请你返回一个数组 answer ，其中 answer[j]是第 j 个查询的答案。
 * 示例 1：
 * 输入：points = [[1,3],[3,3],[5,3],[2,2]], queries = [[2,3,1],[4,3,1],[1,1,2]]
 * 输出：[3,2,2]
 * 解释：所有的点和圆如上图所示。
 * queries[0] 是绿色的圆，queries[1] 是红色的圆，queries[2] 是蓝色的圆。
 * 示例 2：
 * 输入：points = [[1,1],[2,2],[3,3],[4,4],[5,5]], queries = [[1,2,2],[2,2,2],[4,3,2],[4,3,3]]
 * 输出：[2,3,2,4]
 * 解释：所有的点和圆如上图所示。
 * queries[0] 是绿色的圆，queries[1] 是红色的圆，queries[2] 是蓝色的圆，queries[3] 是紫色的圆。
 * 提示：
 * 1 <= points.length <= 500
 * points[i].length == 2
 * 0 <= x_i, y_i <= 500
 * 1 <= queries.length <= 500
 * queries[j].length == 3
 * 0 <= x_j, y_j <= 500
 * 1 <= r_j <= 500
 * 所有的坐标都是整数。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2023/1/28
 */
public class CountPoints {
    public int[] countPoints(int[][] points, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int[] query = queries[i];
            for (int[] point : points) {
                if ((point[0] - query[0]) * (point[0] - query[0]) + (point[1] - query[1]) * (point[1] - query[1]) <= query[2] * query[2]) {
                    ans[i]++;
                }
            }
        }
        return ans;
    }
}
