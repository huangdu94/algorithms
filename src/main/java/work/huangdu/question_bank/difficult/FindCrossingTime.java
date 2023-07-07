package work.huangdu.question_bank.difficult;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2532. 过桥的时间
 * 共有 k 位工人计划将 n 个箱子从旧仓库移动到新仓库。给你两个整数 n 和 k，以及一个二维整数数组 time ，数组的大小为 k x 4 ，其中 time[i] = [leftToRight_i, pickOld_i, rightToLeft_i, putNew_i] 。
 * 一条河将两座仓库分隔，只能通过一座桥通行。旧仓库位于河的右岸，新仓库在河的左岸。开始时，所有 k 位工人都在桥的左侧等待。为了移动这些箱子，第 i 位工人（下标从 0 开始）可以：
 * 从左岸（新仓库）跨过桥到右岸（旧仓库），用时 leftToRight_i 分钟。
 * 从旧仓库选择一个箱子，并返回到桥边，用时 pickOld_i 分钟。不同工人可以同时搬起所选的箱子。
 * 从右岸（旧仓库）跨过桥到左岸（新仓库），用时 rightToLeft_i 分钟。
 * 将箱子放入新仓库，并返回到桥边，用时 putNew_i 分钟。不同工人可以同时放下所选的箱子。
 * 如果满足下面任一条件，则认为工人 i 的 效率低于 工人 j ：
 * leftToRight_i + rightToLeft_i > leftToRight_j + rightToLeft_j
 * leftToRight_i + rightToLeft_i == leftToRight_j + rightToLeft_j 且 i > j
 * 工人通过桥时需要遵循以下规则：
 * 如果工人 x 到达桥边时，工人 y 正在过桥，那么工人 x 需要在桥边等待。
 * 如果没有正在过桥的工人，那么在桥右边等待的工人可以先过桥。如果同时有多个工人在右边等待，那么 效率最低 的工人会先过桥。
 * 如果没有正在过桥的工人，且桥右边也没有在等待的工人，同时旧仓库还剩下至少一个箱子需要搬运，此时在桥左边的工人可以过桥。如果同时有多个工人在左边等待，那么 效率最低 的工人会先过桥。
 * 所有 n 个盒子都需要放入新仓库，请你返回最后一个搬运箱子的工人 到达河左岸 的时间。
 * 示例 1：
 * 输入：n = 1, k = 3, time = [[1,1,2,1],[1,1,3,1],[1,1,4,1]]
 * 输出：6
 * 解释：
 * 从 0 到 1 ：工人 2 从左岸过桥到达右岸。
 * 从 1 到 2 ：工人 2 从旧仓库搬起一个箱子。
 * 从 2 到 6 ：工人 2 从右岸过桥到达左岸。
 * 从 6 到 7 ：工人 2 将箱子放入新仓库。
 * 整个过程在 7 分钟后结束。因为问题关注的是最后一个工人到达左岸的时间，所以返回 6 。
 * 示例 2：
 * 输入：n = 3, k = 2, time = [[1,9,1,8],[10,10,10,10]]
 * 输出：50
 * 解释：
 * 从 0 到 10 ：工人 1 从左岸过桥到达右岸。
 * 从 10 到 20 ：工人 1 从旧仓库搬起一个箱子。
 * 从 10 到 11 ：工人 0 从左岸过桥到达右岸。
 * 从 11 到 20 ：工人 0 从旧仓库搬起一个箱子。
 * 从 20 到 30 ：工人 1 从右岸过桥到达左岸。
 * 从 30 到 40 ：工人 1 将箱子放入新仓库。
 * 从 30 到 31 ：工人 0 从右岸过桥到达左岸。
 * 从 31 到 39 ：工人 0 将箱子放入新仓库。
 * 从 39 到 40 ：工人 0 从左岸过桥到达右岸。
 * 从 40 到 49 ：工人 0 从旧仓库搬起一个箱子。
 * 从 49 到 50 ：工人 0 从右岸过桥到达左岸。
 * 从 50 到 58 ：工人 0 将箱子放入新仓库。
 * 整个过程在 58 分钟后结束。因为问题关注的是最后一个工人到达左岸的时间，所以返回 50 。
 * 提示：
 * 1 <= n, k <= 10^4
 * time.length == k
 * time[i].length == 4
 * 1 <= leftToRight_i, pickOld_i, rightToLeft_i, putNew_i <= 1000
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/7/7
 */
public class FindCrossingTime {
    public int findCrossingTime(int n, int k, int[][] time) {
        Comparator<Integer> efficiencyComparator = (i1, i2) -> {
            int s1 = time[i1][0] + time[i1][2], s2 = time[i2][0] + time[i2][2];
            if (s1 == s2) {return Integer.compare(i2, i1);}
            return Integer.compare(s2, s1);
        };
        PriorityQueue<Integer> leftWait = new PriorityQueue<>(efficiencyComparator), rightWait = new PriorityQueue<>(efficiencyComparator);
        PriorityQueue<int[]> putComplete = new PriorityQueue<>(Comparator.comparingInt(data -> data[0])), pickComplete = new PriorityQueue<>(Comparator.comparingInt(data -> data[0]));
        for (int i = 0; i < k; i++) {leftWait.offer(i);}
        int ans = 0;
        while (n > 0 || !rightWait.isEmpty()) {
            while (!putComplete.isEmpty() && putComplete.peek()[0] <= ans) {
                leftWait.offer(putComplete.poll()[1]);
            }
            while (!pickComplete.isEmpty() && pickComplete.peek()[0] <= ans) {
                rightWait.offer(pickComplete.poll()[1]);
                n--;
            }
            if (rightWait.isEmpty()) {
                if (n == pickComplete.size() || leftWait.isEmpty()) {
                    int min = Integer.MAX_VALUE;
                    if (!putComplete.isEmpty()) {
                        min = Math.min(min, putComplete.peek()[0]);
                    }
                    if (!pickComplete.isEmpty()) {
                        min = Math.min(min, pickComplete.peek()[0]);
                    }
                    ans = min;
                    continue;
                }
                int idx = leftWait.poll();
                ans += time[idx][0];
                pickComplete.offer(new int[] {ans + time[idx][1], idx});
            } else {
                int idx = rightWait.poll();
                ans += time[idx][2];
                putComplete.offer(new int[] {ans + time[idx][3], idx});
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 2;
        int[][] time = {{1, 9, 1, 8}, {10, 10, 10, 10}};
        FindCrossingTime fct = new FindCrossingTime();
        System.out.println(fct.findCrossingTime(n, k, time));
    }
}
