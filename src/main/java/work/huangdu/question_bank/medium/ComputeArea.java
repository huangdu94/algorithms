package work.huangdu.question_bank.medium;

/**
 * 223. 矩形面积
 * 给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。
 * 每个矩形由其 左下 顶点和 右上 顶点坐标表示：
 * 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
 * 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
 * 示例 1：
 * Rectangle Area
 * 输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
 * 输出：45
 * 示例 2：
 * 输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
 * 输出：16
 * 提示：
 * -10^4 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 10^4
 *
 * @author huangdu
 * @version 2021/9/30
 */
public class ComputeArea {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int aWidth = ax2 - ax1, aHeight = ay2 - ay1, bWidth = bx2 - bx1, bHeight = by2 - by1;
        return aWidth * aHeight + bWidth * bHeight - computeCrossArea(ax1, ay2, aWidth, aHeight, bx1, by2, bWidth, bHeight);
    }

    private int computeCrossArea(int ax, int ay, int aWidth, int aHeight, int bx, int by, int bWidth, int bHeight) {
        int width = ax <= bx ? Math.min(aWidth - (bx - ax), bWidth) : Math.min(bWidth - (ax - bx), aWidth);
        int height = ay <= by ? Math.min(bHeight - (by - ay), aHeight) : Math.min(aHeight - (ay - by), bHeight);
        if (width <= 0 || height <= 0) {return 0;}
        return width * height;
    }

    public static void main(String[] args) {
        ComputeArea ca = new ComputeArea();
        System.out.println(ca.computeArea(-2, -2, 2, 2, -1, 4, 1, 6));
    }
}