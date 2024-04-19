package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.List;

/**
 * 1595. 连通两组点的最小成本
 * 给你两组点，其中第一组中有 size1 个点，第二组中有 size2 个点，且 size1 >= size2 。
 * 任意两点间的连接成本 cost 由大小为 size1 x size2 矩阵给出，其中 cost[i][j] 是第一组中的点 i 和第二组中的点 j 的连接成本。如果两个组中的每个点都与另一组中的一个或多个点连接，则称这两组点是连通的。换言之，第一组中的每个点必须至少与第二组中的一个点连接，且第二组中的每个点必须至少与第一组中的一个点连接。
 * 返回连通两组点所需的最小成本。
 * 示例 1：
 * 输入：cost = [[15, 96], [36, 2]]
 * 输出：17
 * 解释：连通两组点的最佳方法是：
 * 1--A
 * 2--B
 * 总成本为 17 。
 * 示例 2：
 * 输入：cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]]
 * 输出：4
 * 解释：连通两组点的最佳方法是：
 * 1--A
 * 2--B
 * 2--C
 * 3--A
 * 最小成本为 4 。
 * 请注意，虽然有多个点连接到第一组中的点 2 和第二组中的点 A ，但由于题目并不限制连接点的数目，所以只需要关心最低总成本。
 * 示例 3：
 * 输入：cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]]
 * 输出：10
 * 提示：
 * size1 == cost.length
 * size2 == cost[i].length
 * 1 <= size1, size2 <= 12
 * size1 >= size2
 * 0 <= cost[i][j] <= 100
 *
 * @author huangdu
 * @version 2023/6/20
 */
public class ConnectTwoGroups {
    private List<List<Integer>> cost;
    private int size2;
    private int[] minCost;
    private int[][] memo;

    public int connectTwoGroups(List<List<Integer>> cost) {
        this.cost = cost;
        this.size2 = cost.get(0).size();
        this.minCost = new int[size2];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        for (List<Integer> row : cost) {
            for (int j = 0; j < size2; j++) {
                minCost[j] = Math.min(minCost[j], row.get(j));
            }
        }
        this.memo = new int[cost.size()][1 << size2];
        for (int[] row : memo) {Arrays.fill(row, -1);}
        return dfs(cost.size() - 1, (1 << size2) - 1);
    }

    private int dfs(int i, int mask) {
        if (i < 0) {
            if (mask == 0) {return 0;}
            int cost = 0;
            for (int j = 0; j < size2; j++) {
                if ((mask >> j & 1) == 1) {
                    cost += minCost[j];
                }
            }
            return cost;
        }
        if (memo[i][mask] != -1) {return memo[i][mask];}
        List<Integer> costList = cost.get(i);
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < size2; j++) {
            min = Math.min(min, dfs(i - 1, mask & ~(1 << j)) + costList.get(j));
        }
        return memo[i][mask] = min;
    }

    public static void main(String[] args) {
        ConnectTwoGroups.Solution ctg = new ConnectTwoGroups.Solution();
        List<List<Integer>> cost = Arrays.asList(Arrays.asList(1, 3, 5), Arrays.asList(4, 1, 1), Arrays.asList(1, 5, 3));
        System.out.println(ctg.connectTwoGroups(cost));
    }

    static class Solution {
        public int connectTwoGroups(List<List<Integer>> cost) {
            int n = cost.get(0).size(), limit = 1 << n;
            int[] minCost = new int[n];
            Arrays.fill(minCost, Integer.MAX_VALUE);
            for (List<Integer> row : cost) {
                for (int j = 0; j < n; j++) {
                    minCost[j] = Math.min(minCost[j], row.get(j));
                }
            }
            int[] f0 = new int[limit], f1 = null;
            for (int status = 0; status < limit; status++) {
                for (int j = 0; j < n; j++) {
                    if ((status >> j & 1) == 1) {
                        f0[status] += minCost[j];
                    }
                }
            }
            for (List<Integer> costI : cost) {
                f1 = new int[limit];
                Arrays.fill(f1, Integer.MAX_VALUE);
                for (int status = 0; status < limit; status++) {
                    for (int j = 0; j < n; j++) {
                        f1[status] = Math.min(f1[status], f0[status & ~(1 << j)] + costI.get(j));
                    }
                }
                f0 = f1;
            }
            return f1[limit - 1];
        }
    }
}
