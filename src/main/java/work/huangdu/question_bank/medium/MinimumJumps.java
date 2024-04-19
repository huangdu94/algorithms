package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 1654. 到家的最少跳跃次数
 * 有一只跳蚤的家在数轴上的位置 x 处。请你帮助它从位置 0 出发，到达它的家。
 * 跳蚤跳跃的规则如下：
 * 它可以 往前 跳恰好 a 个位置（即往右跳）。
 * 它可以 往后 跳恰好 b 个位置（即往左跳）。
 * 它不能 连续 往后跳 2 次。
 * 它不能跳到任何 forbidden 数组中的位置。
 * 跳蚤可以往前跳 超过 它的家的位置，但是它 不能跳到负整数 的位置。
 * 给你一个整数数组 forbidden ，其中 forbidden[i] 是跳蚤不能跳到的位置，同时给你整数 a， b 和 x ，请你返回跳蚤到家的最少跳跃次数。如果没有恰好到达 x 的可行方案，请你返回 -1 。
 * 示例 1：
 * 输入：forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
 * 输出：3
 * 解释：往前跳 3 次（0 -> 3 -> 6 -> 9），跳蚤就到家了。
 * 示例 2：
 * 输入：forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
 * 输出：-1
 * 示例 3：
 * 输入：forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
 * 输出：2
 * 解释：往前跳一次（0 -> 16），然后往回跳一次（16 -> 7），跳蚤就到家了。
 * 提示：
 * 1 <= forbidden.length <= 1000
 * 1 <= a, b, forbidden[i] <= 2000
 * 0 <= x <= 2000
 * forbidden 中所有位置互不相同。
 * 位置 x 不在 forbidden 中。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2023/8/30
 */
public class MinimumJumps {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        if (x == 0) {
            return 0;
        }
        if (x % gcd(a, b) != 0) {
            return -1;
        }
        int ans = 1, max = 0;
        Set<Integer> forbiddenSet = new HashSet<>(forbidden.length);
        for (int i : forbidden) {
            forbiddenSet.add(i);
            max = Math.max(max, i);
        }
        int upper = Math.max(max + a, x) + b;
        Queue<Integer> queue = new ArrayDeque<>();
        Queue<Boolean> flagQueue = new ArrayDeque<>();
        Set<Integer> visitedA = new HashSet<>();
        Set<Integer> visitedB = new HashSet<>();
        queue.offer(0);
        flagQueue.offer(true);
        visitedA.add(0);
        visitedB.add(0);
        while (!queue.isEmpty()) {
            for (int k = 0, size = queue.size(); k < size; k++) {
                int i = queue.remove(), nextA = i + a, nextB = i - b;
                boolean flag = flagQueue.remove();
                if (nextA == x || flag && nextB == x) {
                    return ans;
                }
                if (nextA <= upper && !forbiddenSet.contains(nextA) && visitedA.add(nextA)) {
                    queue.offer(nextA);
                    flagQueue.offer(true);
                }
                if (flag && nextB >= 0 && nextB <= upper && !forbiddenSet.contains(nextB) && !visitedA.contains(nextB) && visitedB.add(nextB)) {
                    queue.offer(nextB);
                    flagQueue.offer(false);
                }
            }
            ans++;
        }
        return -1;
    }

    private int gcd(int a, int b) {
        if (a == b) {
            return b;
        }
        boolean aEven = (a & 1) == 0;
        boolean bEven = (b & 1) == 0;
        if (aEven && bEven) {
            return gcd(a >> 1, b >> 1) << 1;
        } else if (aEven) {
            return gcd(a >> 1, b);
        } else if (bEven) {
            return gcd(a, b >> 1);
        } else {
            return gcd(Math.abs(a - b), Math.min(a, b));
        }
    }

    public static void main(String[] args) {
        MinimumJumps mj = new MinimumJumps();
        int[] forbidden = {1998};
        int a = 1999, b = 2000, x = 2000;
        System.out.println(mj.minimumJumps(forbidden, a, b, x));
    }
}
