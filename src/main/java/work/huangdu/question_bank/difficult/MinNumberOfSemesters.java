package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 1494. 并行课程 II
 * 给你一个整数 n 表示某所大学里课程的数目，编号为 1 到 n ，数组 relations 中， relations[i] = [xi, yi]  表示一个先修课的关系，也就是课程 xi 必须在课程 yi 之前上。同时你还有一个整数 k 。
 * 在一个学期中，你 最多 可以同时上 k 门课，前提是这些课的先修课在之前的学期里已经上过了。
 * 请你返回上完所有课最少需要多少个学期。题目保证一定存在一种上完所有课的方式。
 * 示例 1：
 * 输入：n = 4, relations = [[2,1],[3,1],[1,4]], k = 2
 * 输出：3
 * 解释：上图展示了题目输入的图。在第一个学期中，我们可以上课程 2 和课程 3 。然后第二个学期上课程 1 ，第三个学期上课程 4 。
 * 示例 2：
 * 输入：n = 5, relations = [[2,1],[3,1],[4,1],[1,5]], k = 2
 * 输出：4
 * 解释：上图展示了题目输入的图。一个最优方案是：第一学期上课程 2 和 3，第二学期上课程 4 ，第三学期上课程 1 ，第四学期上课程 5 。
 * 示例 3：
 * 输入：n = 11, relations = [], k = 2
 * 输出：6
 * 提示：
 * 1 <= n <= 15
 * 1 <= k <= n
 * 0 <= relations.length <= n * (n-1) / 2
 * relations[i].length == 2
 * 1 <= xi, yi <= n
 * xi != yi
 * 所有先修关系都是不同的，也就是说 relations[i] != relations[j] 。
 * 题目输入的图是个有向无环图。
 *
 * @author huangdu
 * @version 2023/6/16
 */
public class MinNumberOfSemesters {
    private int n;
    private int k;
    private int full;
    private int[] pre;
    private int[] memo;

    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        this.n = n;
        this.k = k;
        this.full = (1 << n) - 1;
        this.pre = new int[n];
        for (int[] relation : relations) {pre[relation[1] - 1] |= 1 << relation[0] - 1;}
        this.memo = new int[1 << n];
        Arrays.fill(memo, -1);
        return dfs(full);
    }

    private int dfs(int status) {
        if (memo[status] != -1) {return memo[status];}
        if (status == 0) {return 0;}
        int complete = full ^ status, option = 0;
        for (int i = 0; i < n; i++) {
            if ((status >> i & 1) != 0 && (pre[i] | complete) == complete) {option |= 1 << i;}
        }
        if (Integer.bitCount(option) <= k) {return dfs(status ^ option) + 1;}
        int min = n;
        for (int sub = option; sub > 0; sub = sub - 1 & option) {
            if (Integer.bitCount(sub) == k) {min = Math.min(min, dfs(status ^ sub));}
        }
        return memo[status] = min + 1;
    }

    public static void main(String[] args) {
        int n = 13;
        int[][] relations = {{12, 8}, {2, 4}, {3, 7}, {6, 8}, {11, 8}, {9, 4}, {9, 7}, {12, 4}, {11, 4}, {6, 4}, {1, 4}, {10, 7}, {10, 4}, {1, 7}, {1, 8}, {2, 7}, {8, 4}, {10, 8}, {12, 7}, {5, 4}, {3, 4}, {11, 7}, {7, 4}, {13, 4}, {9, 8},
            {13, 8}};
        int k = 9;
        MinNumberOfSemesters mnos = new MinNumberOfSemesters();
        System.out.println(mnos.minNumberOfSemesters(n, relations, k));
    }
}
