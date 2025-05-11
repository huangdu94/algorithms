package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * 2392. 给定条件下构造矩阵
 * 给你一个 正 整数 k ，同时给你：
 * 一个大小为 n 的二维整数数组 rowConditions ，其中 rowConditions[i] = [above_i, below_i] 和
 * 一个大小为 m 的二维整数数组 colConditions ，其中 colConditions[i] = [left_i, right_i] 。
 * 两个数组里的整数都是 1 到 k 之间的数字。
 * 你需要构造一个 k x k 的矩阵，1 到 k 每个数字需要 恰好出现一次 。剩余的数字都是 0 。
 * 矩阵还需要满足以下条件：
 * 对于所有 0 到 n - 1 之间的下标 i ，数字 above_i 所在的 行 必须在数字 below_i 所在行的上面。
 * 对于所有 0 到 m - 1 之间的下标 i ，数字 left_i 所在的 列 必须在数字 right_i 所在列的左边。
 * 返回满足上述要求的 任意 矩阵。如果不存在答案，返回一个空的矩阵。
 * 示例 1：
 * 输入：k = 3, rowConditions = [[1,2],[3,2]], colConditions = [[2,1],[3,2]]
 * 输出：[[3,0,0],[0,0,1],[0,2,0]]
 * 解释：上图为一个符合所有条件的矩阵。
 * 行要求如下：
 * - 数字 1 在第 1 行，数字 2 在第 2 行，1 在 2 的上面。
 * - 数字 3 在第 0 行，数字 2 在第 2 行，3 在 2 的上面。
 * 列要求如下：
 * - 数字 2 在第 1 列，数字 1 在第 2 列，2 在 1 的左边。
 * - 数字 3 在第 0 列，数字 2 在第 1 列，3 在 2 的左边。
 * 注意，可能有多种正确的答案。
 * 示例 2：
 * 输入：k = 3, rowConditions = [[1,2],[2,3],[3,1],[2,3]], colConditions = [[2,1]]
 * 输出：[]
 * 解释：由前两个条件可以得到 3 在 1 的下面，但第三个条件是 3 在 1 的上面。
 * 没有符合条件的矩阵存在，所以我们返回空矩阵。
 * 提示：
 * 2 <= k <= 400
 * 1 <= rowConditions.length, colConditions.length <= 10^4
 * rowConditions[i].length == colConditions[i].length == 2
 * 1 <= above_i, below_i, left_i, right_i <= k
 * above_i != below_i
 * left_i != right_i
 *
 * @author huangdu
 * @version 2023/5/5
 */
public class BuildMatrix {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[][] ans = new int[k][k];
        List<Integer> rowOrderedList = topologicalSort(k, rowConditions);
        if (rowOrderedList.size() < k) {return new int[0][0];}
        List<Integer> colOrderedList = topologicalSort(k, colConditions);
        if (colOrderedList.size() < k) {return new int[0][0];}
        int[][] idxs = new int[k][2];
        for (int i = 0; i < k; i++) {
            idxs[rowOrderedList.get(i)][0] = i;
            idxs[colOrderedList.get(i)][1] = i;
        }
        for (int i = 0; i < k; i++) {
            ans[idxs[i][0]][idxs[i][1]] = i + 1;
        }
        return ans;
    }

    private List<Integer> topologicalSort(int k, int[][] conditions) {
        List<Integer> orderedList = new ArrayList<>(k);
        int[] degree = new int[k];
        List<Integer>[] graph = new List[k];
        Arrays.setAll(graph, (o) -> new ArrayList<>());
        for (int[] condition : conditions) {
            int from = condition[0] - 1, to = condition[1] - 1;
            graph[from].add(to);
            degree[to]++;
        }
        Queue<Integer> queue = new ArrayDeque<>(k);
        for (int i = 0; i < k; i++) {
            if (degree[i] == 0) {queue.offer(i);}
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            orderedList.add(cur);
            for (int next : graph[cur]) {
                if (--degree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return orderedList;
    }

    public static void main(String[] args) {
        BuildMatrix bm = new BuildMatrix();
        int k = 3;
        int[][] rowConditions = {{1, 2}, {3, 2}};
        int[][] colConditions = {{2, 1}, {3, 2}};
        System.out.println(Arrays.deepToString(bm.buildMatrix(k, rowConditions, colConditions)));
    }
}
