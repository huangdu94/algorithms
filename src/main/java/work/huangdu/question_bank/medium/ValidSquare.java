package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 593. 有效的正方形
 * 给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
 * 点的坐标 pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。
 * 一个 有效的正方形 有四条等边和四个等角(90度角)。
 * 示例 1:
 * 输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * 输出: True
 * 示例 2:
 * 输入：p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
 * 输出：false
 * 示例 3:
 * 输入：p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
 * 输出：true
 * 提示:
 * p1.length == p2.length == p3.length == p4.length == 2
 * -10^4 <= xi, yi <= 10^4
 *
 * @author huangdu
 * @version 2022/7/29
 */
public class ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] array = {d2(p1, p2), d2(p1, p3), d2(p1, p4), d2(p2, p3), d2(p2, p4), d2(p3, p4)};
        Arrays.sort(array);
        return array[0] != 0 && array[0] == array[3] && array[4] == array[5] && 2 * array[0] == array[4];
    }

    private int d2(int[] p1, int[] p2) {
        return (p2[0] - p1[0]) * (p2[0] - p1[0]) + (p2[1] - p1[1]) * (p2[1] - p1[1]);
    }
}
