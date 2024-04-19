package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 787. K 站中转内最便宜的航班
 * 有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
 * 示例 1：
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 * 解释:
 * 城市航班图如下
 * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
 * 示例 2：
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * 输出: 500
 * 解释:
 * 城市航班图如下
 * 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
 * 提示：
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= from_i, to_i < n
 * from_i != to_i
 * 1 <= price_i <= 10^4
 * 航班没有重复，且不存在自环
 * 0 <= src, dst, k < n
 * src != dst
 *
 * @author huangdu
 * @version 2021/8/24
 */
public class FindCheapestPrice {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int inf = 1000000;
        int[] mins = new int[n];
        Arrays.fill(mins, inf);
        mins[src] = 0;
        for (int i = 0; i <= k; i++) {
            int[] newMins = Arrays.copyOf(mins, mins.length);
            for (int[] flight : flights) {
                int cur = flight[0], next = flight[1], cost = flight[2];
                newMins[next] = Math.min(newMins[next], mins[cur] + cost);
            }
            mins = newMins;
        }
        return mins[dst] < inf ? mins[dst] : -1;
    }

    public static void main(String[] args) {
        FindCheapestPrice fcp = new FindCheapestPrice();
        //int n = 3;
        //int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        //int src = 0;
        //int dst = 2;
        //int k = 1;
        //int n = 4;
        //int[][] flights = {{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}};
        //int src = 0;
        //int dst = 3;
        //int k = 1;
        //int n = 5;
        //int[][] flights = {{0, 1, 5}, {1, 2, 5}, {0, 3, 2}, {3, 1, 2}, {1, 4, 1}, {4, 2, 1}};
        //int src = 0;
        //int dst = 2;
        //int k = 2;
        //int n = 2;
        //int[][] flights = {{0, 1, 2}};
        //int src = 1;
        //int dst = 0;
        //int k = 0;
        int n = 3;
        int[][] flights = {{0, 1, 2}, {1, 2, 1}, {2, 0, 10}};
        int src = 1;
        int dst = 2;
        int k = 1;
        // 7
        System.out.println(fcp.findCheapestPrice(n, flights, src, dst, k));
    }
}
