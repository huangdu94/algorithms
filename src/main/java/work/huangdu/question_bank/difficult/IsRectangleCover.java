package work.huangdu.question_bank.difficult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 391. 完美矩形
 * 给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
 * 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
 * 输出：true
 * 解释：5 个矩形一起可以精确地覆盖一个矩形区域。
 * 示例 2：
 * 输入：rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
 * 输出：false
 * 解释：两个矩形之间有间隔，无法覆盖成一个矩形。
 * 示例 3：
 * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[3,2,4,4]]
 * 输出：false
 * 解释：图形顶端留有空缺，无法覆盖成一个矩形。
 * 示例 4：
 * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
 * 输出：false
 * 解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
 * 提示：
 * 1 <= rectangles.length <= 2 * 10^4
 * rectangles[i].length == 4
 * -10^5 <= x_i, y_i, a_i, b_i <= 10^5
 *
 * @author huangdu
 * @version 2021/11/18
 */
public class IsRectangleCover {
    private static final int MAX_LEN = 100000;

    public boolean isRectangleCover(int[][] rectangles) {
        int n = rectangles.length;
        if (n == 1) {return true;}
        int[] lb = {MAX_LEN, MAX_LEN}, ru = {-MAX_LEN, -MAX_LEN};
        int areas = 0;
        Map<Long, Integer> count = new HashMap<>();
        for (int[] rectangle : rectangles) {
            int lbx = rectangle[0], lby = rectangle[1], rux = rectangle[2], ruy = rectangle[3];
            areas += (ruy - lby) * (rux - lbx);
            if (lb[0] >= lbx && lb[1] >= lby) {
                lb[0] = lbx;
                lb[1] = lby;
            }
            if (ru[0] <= rux && ru[1] <= ruy) {
                ru[0] = rux;
                ru[1] = ruy;
            }
            count.merge(hash(lbx, lby), 1, Integer::sum);
            count.merge(hash(lbx, ruy), 1, Integer::sum);
            count.merge(hash(rux, lby), 1, Integer::sum);
            count.merge(hash(rux, ruy), 1, Integer::sum);
        }
        if (areas != (ru[1] - lb[1]) * (ru[0] - lb[0])) {
            return false;
        }
        if (!Objects.equals(count.remove(hash(lb[0], lb[1])), 1)
            || !Objects.equals(count.remove(hash(lb[0], ru[1])), 1)
            || !Objects.equals(count.remove(hash(ru[0], lb[1])), 1)
            || !Objects.equals(count.remove(hash(ru[0], ru[1])), 1)) {return false;}
        for (Map.Entry<Long, Integer> entry : count.entrySet()) {
            if (entry.getValue() != 2 && entry.getValue() != 4) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsRectangleCover irc = new IsRectangleCover();
        int[][] rectangles = {{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}};
        System.out.println(irc.isRectangleCover(rectangles));
    }

    private long hash(int x, int y) {
        return (long)x * (MAX_LEN * 2 + 1) + y;
    }
}
