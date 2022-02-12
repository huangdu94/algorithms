package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 1036. 逃离大迷宫
 * 在一个 10^6 x 10^6 的网格中，每个网格上方格的坐标为 (x, y) 。
 * 现在从源方格 source = [sx, sy] 开始出发，意图赶往目标方格 target = [tx, ty] 。数组 blocked 是封锁的方格列表，其中每个 blocked[i] = [xi, yi] 表示坐标为 (xi, yi) 的方格是禁止通行的。
 * 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表 blocked 上。同时，不允许走出网格。
 * 只有在可以通过一系列的移动从源方格 source 到达目标方格 target 时才返回 true。否则，返回 false。
 * 示例 1：
 * 输入：blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
 * 输出：false
 * 解释：
 * 从源方格无法到达目标方格，因为我们无法在网格中移动。
 * 无法向北或者向东移动是因为方格禁止通行。
 * 无法向南或者向西移动是因为不能走出网格。
 * 示例 2：
 * 输入：blocked = [], source = [0,0], target = [999999,999999]
 * 输出：true
 * 解释：
 * 因为没有方格被封锁，所以一定可以到达目标方格。
 * 提示：
 * 0 <= blocked.length <= 200
 * blocked[i].length == 2
 * 0 <= xi, yi < 10^6
 * source.length == target.length == 2
 * 0 <= sx, sy, tx, ty < 10^6
 * source != target
 * 题目数据保证 source 和 target 不在封锁列表内
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/2/9
 */
public class IsEscapePossible {
    private static final long LENGTH = (int)1e6;
    private static final int[] DIRECTION = {1, 0, -1, 0, 1};

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        final int n = blocked.length, limit = n * (n - 1) / 2;
        Set<Long> blockedSet = new HashSet<>(n), visited = new HashSet<>(limit);
        for (int[] b : blocked) { blockedSet.add(b[0] * LENGTH + b[1]); }
        Queue<Long> queue = new ArrayDeque<>(limit);
        queue.offer(source[0] * LENGTH + source[1]);
        visited.add(source[0] * LENGTH + source[1]);
        while (!queue.isEmpty() && visited.size() <= limit) {
            long curIndex = queue.poll();
            int curX = (int)(curIndex / LENGTH), curY = (int)(curIndex % LENGTH);
            for (int k = 0; k < 4; k++) {
                int nextX = curX + DIRECTION[k], nextY = curY + DIRECTION[k + 1];
                if (nextX == target[0] && nextY == target[1]) {return true;}
                if (nextX >= 0 && nextX < LENGTH && nextY >= 0 && nextY < LENGTH && !blockedSet.contains(nextX * LENGTH + nextY) && visited.add(nextX * LENGTH + nextY)) {
                    queue.offer(nextX * LENGTH + nextY);
                }
            }
        }
        if (visited.size() <= limit) {return false;}
        visited.clear();
        queue.clear();
        queue.offer(target[0] * LENGTH + target[1]);
        visited.add(target[0] * LENGTH + target[1]);
        while (!queue.isEmpty() && visited.size() <= limit) {
            long curIndex = queue.poll();
            int curX = (int)(curIndex / LENGTH), curY = (int)(curIndex % LENGTH);
            for (int k = 0; k < 4; k++) {
                int nextX = curX + DIRECTION[k], nextY = curY + DIRECTION[k + 1];
                if (nextX >= 0 && nextX < LENGTH && nextY >= 0 && nextY < LENGTH && !blockedSet.contains(nextX * LENGTH + nextY) && visited.add(nextX * LENGTH + nextY)) {
                    queue.offer(nextX * LENGTH + nextY);
                }
            }
        }
        return visited.size() > limit;
    }

    public static void main(String[] args) {
        IsEscapePossible iep = new IsEscapePossible();
        int[][] blocked = {{691938, 300406}, {710196, 624190}, {858790, 609485}, {268029, 225806}, {200010, 188664}, {132599, 612099}, {329444, 633495}, {196657, 757958}, {628509, 883388}};
        int[] source = {655988, 180910};
        int[] target = {267728, 840949};
        System.out.println(iep.isEscapePossible(blocked, source, target));
    }
}
