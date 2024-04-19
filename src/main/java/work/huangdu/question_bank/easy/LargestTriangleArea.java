package work.huangdu.question_bank.easy;

/**
 * 812. 最大三角形面积
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 * 示例:
 * 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * 输出: 2
 * 解释:
 * 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
 * 注意:
 * 3 <= points.length <= 50.
 * 不存在重复的点。
 * -50 <= points[i][j] <= 50.
 * 结果误差值在 10^-6 以内都认为是正确答案。
 *
 * @author huangdu
 * @version 2022/5/15
 */
public class LargestTriangleArea {
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    max = Math.max(max, triangleArea(points[i], points[j], points[k]));
                }
            }
        }
        return max;
    }

    // (x1y2+x2y3+x3y1-x1y3-x2y1-x3y2)/2
    private double triangleArea(int[] p1, int[] p2, int[] p3) {
        return Math.abs(p1[0] * p2[1] + p2[0] * p3[1] + p3[0] * p1[1] - p1[0] * p3[1] - p2[0] * p1[1] - p3[0] * p2[1]) / 2.0;
    }

    public static void main(String[] args) {
        int[][] points = {{1, 0}, {0, 0}, {0, 1}};
        LargestTriangleArea lta = new LargestTriangleArea();
        System.out.println(lta.largestTriangleArea(points));
    }
}
