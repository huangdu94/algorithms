package work.huangdu.question_bank.difficult;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 871. 最低加油次数
 * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
 * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
 * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
 * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
 * 示例 1：
 * 输入：target = 1, startFuel = 1, stations = []
 * 输出：0
 * 解释：我们可以在不加油的情况下到达目的地。
 * 示例 2：
 * 输入：target = 100, startFuel = 1, stations = [[10,100]]
 * 输出：-1
 * 解释：我们无法抵达目的地，甚至无法到达第一个加油站。
 * 示例 3：
 * 输入：target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * 输出：2
 * 解释：
 * 我们出发时有 10 升燃料。
 * 我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
 * 然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
 * 并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
 * 我们沿途在1两个加油站停靠，所以返回 2 。
 * 提示：
 * 1 <= target, startFuel, stations[i][1] <= 10^9
 * 0 <= stations.length <= 500
 * 0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target
 *
 * @author huangdu
 * @version 2022/8/26
 */
public class MinRefuelStops {
    // 贪心
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int distance = startFuel, ans = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int[] station : stations) {
            int loc = station[0], fuel = station[1];
            while (distance < loc && !queue.isEmpty()) {
                distance += queue.poll();
                ans++;
            }
            if (distance < loc) {return -1;}
            if (distance >= target) {break;}
            queue.offer(fuel);
        }
        while (distance < target && !queue.isEmpty()) {
            distance += queue.poll();
            ans++;
        }
        return distance >= target ? ans : -1;
    }

    // 动态规划
    public int minRefuelStops2(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        // 到第i个站台，加油j次，最远行驶距离
        int[] dp = new int[n + 1];
        dp[0] = startFuel;
        for (int i = 0; i < n; i++) {
            int loc = stations[i][0], fuel = stations[i][1];
            for (int j = i + 1; j >= 1; j--) {
                if (dp[j - 1] >= loc) {
                    dp[j] = Math.max(dp[j], dp[j - 1] + fuel);
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            if (dp[i] >= target) {return i;}
        }
        return -1;
    }

    // 动态规划2
    public int minRefuelStops3(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        if (startFuel >= target) {return 0;}
        if (n == 0) {return -1;}
        // 到第i个站台，加油j次，最远行驶距离
        int[][] dp = new int[n][n + 1];
        for (int i = 0; i < n; i++) {dp[i][0] = startFuel;}
        if (startFuel >= stations[0][0]) {dp[0][1] = startFuel + stations[0][1];}
        for (int i = 1; i < n; i++) {
            int loc = stations[i][0], fuel = stations[i][1];
            for (int j = 1; j <= i + 1; j++) {
                if (dp[i - 1][j - 1] >= loc) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + fuel, dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            if (dp[n - 1][i] >= target) {return i;}
        }
        return -1;
    }

    public static void main(String[] args) {
        MinRefuelStops mrs = new MinRefuelStops();
        int target = 100;
        int startFuel = 10;
        int[][] stations = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
        System.out.println(mrs.minRefuelStops(target, startFuel, stations));
    }
}
