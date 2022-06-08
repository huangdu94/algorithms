package work.huangdu.question_bank.easy;

/**
 * 1037. 有效的回旋镖
 * 给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，如果这些点构成一个 回旋镖 则返回 true 。
 * 回旋镖 定义为一组三个点，这些点 各不相同 且 不在一条直线上 。
 * 示例 1：
 * 输入：points = [[1,1],[2,3],[3,2]]
 * 输出：true
 * 示例 2：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：false
 * 提示：
 * points.length == 3
 * points[i].length == 2
 * 0 <= xi, yi <= 100
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/6/8
 */
public class IsBoomerang {
    public boolean isBoomerang(int[][] points) {
        return triangleArea(points[0], points[1], points[2]) != 0;
    }

    // (x1y2+x2y3+x3y1-x1y3-x2y1-x3y2)/2
    private double triangleArea(int[] p1, int[] p2, int[] p3) {
        return Math.abs(p1[0] * p2[1] + p2[0] * p3[1] + p3[0] * p1[1] - p1[0] * p3[1] - p2[0] * p1[1] - p3[0] * p2[1]) / 2.0;
    }
}
