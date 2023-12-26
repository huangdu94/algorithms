package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 1349. 参加考试的最大学生数
 * 给你一个 m * n 的矩阵 seats 表示教室中的座位分布。如果座位是坏的（不可用），就用 '#' 表示；否则，用 '.' 表示。
 * 学生可以看到左侧、右侧、左上、右上这四个方向上紧邻他的学生的答卷，但是看不到直接坐在他前面或者后面的学生的答卷。请你计算并返回该考场可以容纳的同时参加考试且无法作弊的 最大 学生人数。
 * 学生必须坐在状况良好的座位上。
 * 示例 1：
 * 输入：seats = [["#",".","#","#",".","#"],
 * [".","#","#","#","#","."],
 * ["#",".","#","#",".","#"]]
 * 输出：4
 * 解释：教师可以让 4 个学生坐在可用的座位上，这样他们就无法在考试中作弊。
 * 示例 2：
 * 输入：seats = [[".","#"],
 * ["#","#"],
 * ["#","."],
 * ["#","#"],
 * [".","#"]]
 * 输出：3
 * 解释：让所有学生坐在可用的座位上。
 * 示例 3：
 *
 * 输入：seats = [["#",".",".",".","#"],
 * [".","#",".","#","."],
 * [".",".","#",".","."],
 * [".","#",".","#","."],
 * ["#",".",".",".","#"]]
 * 输出：10
 * 解释：让学生坐在第 1、3 和 5 列的可用座位上。
 * 提示：
 * seats 只包含字符 '.' 和'#'
 * m == seats.length
 * n == seats[i].length
 * 1 <= m <= 8
 * 1 <= n <= 8
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class MaxStudents {
    public int maxStudents(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        Map<Integer, Integer> memo = new HashMap<>();
        for (int status : list(status(seats[0]))) {
            memo.put(status, count(status));
        }
        for (int i = 1; i < m; i++) {
            List<Integer> candidates = list(status(seats[i]));
            Map<Integer, Integer> curMemo = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : memo.entrySet()) {
                int pre = entry.getKey(), count = entry.getValue();
                for (int cur : candidates) {
                    int mergeStatus = merge(pre, cur);
                    curMemo.put(mergeStatus, Math.max(curMemo.getOrDefault(mergeStatus, 0), count + count(mergeStatus)));
                }
            }
            memo = curMemo;
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : memo.entrySet()) {ans = Math.max(ans, entry.getValue());}
        return ans;
    }

    private int status(char[] seats) {
        int status = 0;
        for (char seat : seats) {
            status <<= 1;
            if (seat == '.') {status |= 1;}
        }
        return status;
    }

    private int merge(int pre, int cur) {
        int mask = 0, i = 0;
        while (pre > 0) {
            if ((pre & 1) == 1) {
                if (i > 0) {mask |= 1 << i - 1;}
                mask |= 1 << i + 1;
            }
            pre >>= 1;
            i++;
        }
        mask = ~mask;
        return mask & cur;
    }

    private List<Integer> list(int status) {
        int i = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        while (status > 0) {
            if ((status & 1) == 1) {
                for (int k = 0, size = queue.size(); k < size; k++) {
                    int s = queue.remove();
                    if (i == 0 || (s & 1 << i - 1) == 0) {queue.offer(s | 1 << i);}
                    queue.offer(s);
                }
            }
            status >>= 1;
            i++;
        }
        return new ArrayList<>(queue);
    }

    private int count(int status) {
        return Integer.bitCount(status);
    }

    public static void main(String[] args) {
        MaxStudents ms = new MaxStudents();
        char[][] seats = {{'#', '.', '.', '.', '#'}, {'.', '#', '.', '#', '.'}, {'.', '.', '#', '.', '.'}, {'.', '#', '.', '#', '.'}, {'#', '.', '.', '.', '#'}};
        // test(ms, seats);
        System.out.println(ms.maxStudents(seats));
    }

    private static void test(MaxStudents ms, char[][] seats) {
        for (char[] row : seats) {System.out.println(Integer.toBinaryString(ms.status(row)));}
        System.out.println("---");
        System.out.println(Integer.toBinaryString(ms.merge(0B10101, 0B01010)));
        System.out.println("---");
        List<Integer> list = ms.list(0B11111);
        for (int s : list) {
            System.out.println(Integer.toBinaryString(s));
        }
    }
}
