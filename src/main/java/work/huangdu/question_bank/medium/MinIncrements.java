package work.huangdu.question_bank.medium;

/**
 * 2673. 使二叉树所有路径值相等的最小代价
 * 给你一个整数 n 表示一棵 满二叉树 里面节点的数目，节点编号从 1 到 n 。根节点编号为 1 ，树中每个非叶子节点 i 都有两个孩子，分别是左孩子 2 * i 和右孩子 2 * i + 1 。
 * 树中每个节点都有一个值，用下标从 0 开始、长度为 n 的整数数组 cost 表示，其中 cost[i] 是第 i + 1 个节点的值。每次操作，你可以将树中 任意 节点的值 增加 1 。你可以执行操作 任意 次。
 * 你的目标是让根到每一个 叶子结点 的路径值相等。请你返回 最少 需要执行增加操作多少次。
 * 注意：
 * 满二叉树 指的是一棵树，它满足树中除了叶子节点外每个节点都恰好有 2 个子节点，且所有叶子节点距离根节点距离相同。
 * 路径值 指的是路径上所有节点的值之和。
 * 示例 1：
 * 输入：n = 7, cost = [1,5,2,2,3,3,1]
 * 输出：6
 * 解释：我们执行以下的增加操作：
 * - 将节点 4 的值增加一次。
 * - 将节点 3 的值增加三次。
 * - 将节点 7 的值增加两次。
 * 从根到叶子的每一条路径值都为 9 。
 * 总共增加次数为 1 + 3 + 2 = 6 。
 * 这是最小的答案。
 * 示例 2：
 * 输入：n = 3, cost = [5,3,3]
 * 输出：0
 * 解释：两条路径已经有相等的路径值，所以不需要执行任何增加操作。
 * 提示：
 * 3 <= n <= 10^5
 * n + 1 是 2 的幂
 * cost.length == n
 * 1 <= cost[i] <= 10^4
 *
 * @author huangdu
 */
public class MinIncrements {
    /*
    1. 路径和最大的那条路径不用动。因为其它的每条路径都要增加节点的值直至与最大的相等，调整路径和最大的那条路径，其它每条路径也要跟着调。
    2. 其它的比最大的那条路径小的路径，调整值的时候优先从上层节点往下层节点调整，因为调整越上面的节点相当于下面的节点都调整了，但是需要遵循两个原则
        + 路径和最大的那条路径不能动
        + 调整完的值，不能使该条路径有路径和超过最大的
     */
    private int ans;
    private int max;
    private int[] sums;

    public int minIncrements(int n, int[] cost) {
        this.ans = 0;
        this.max = dfs(n, cost, 0);
        this.sums = new int[cost.length];
        dfs(n, cost, 0, 0);
        return ans;
    }

    private int dfs(int n, int[] cost, int idx) {
        if (idx >= n) {return 0;}
        return sums[idx] = Math.max(dfs(n, cost, idx * 2 + 1), dfs(n, cost, idx * 2 + 2)) + cost[idx];
    }

    private void dfs(int n, int[] cost, int idx, int sum) {
        if (idx >= n) {return;}
        int val = cost[idx];
        int add = max - ((idx * 2 + 2 < n ? Math.max(sums[idx * 2 + 1], sums[idx * 2 + 2]) : 0) + val + sum);
        this.ans += add;
        dfs(n, cost, idx * 2 + 1, sum + val + add);
        dfs(n, cost, idx * 2 + 2, sum + val + add);
    }
}
