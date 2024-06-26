package work.huangdu.question_bank.medium;

/**
 * 1130. 叶值的最小代价生成树
 * 给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：
 * 每个节点都有 0 个或是 2 个子节点。
 * 数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。
 * 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
 * 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。
 * 如果一个节点有 0 个子节点，那么该节点为叶节点。
 * 示例 1：
 * 输入：arr = [6,2,4]
 * 输出：32
 * 解释：有两种可能的树，第一种的非叶节点的总和为 36 ，第二种非叶节点的总和为 32 。
 * 示例 2：
 * 输入：arr = [4,11]
 * 输出：44
 * 提示：
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * 答案保证是一个 32 位带符号整数，即小于 2^31 。
 *
 * @author huangdu
 * @version 2023/5/31
 */
public class MctFromLeafValues {
    private int[][] max;
    private int[][] memo;

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        this.max = new int[n][n];
        this.memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            max[i][i] = arr[i];
            for (int j = i + 1; j < n; j++) {
                max[i][j] = Math.max(max[i][j - 1], arr[j]);
            }
        }
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i == j) {return 0;}
        if (memo[i][j] != 0) {return memo[i][j];}
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            min = Math.min(min, max[i][k] * max[k + 1][j] + dfs(i, k) + dfs(k + 1, j));
        }
        return memo[i][j] = min;
    }

    public static void main(String[] args) {
        MctFromLeafValues mflv = new MctFromLeafValues();
        int[] arr = {6, 2, 4};
        System.out.println(mflv.mctFromLeafValues(arr));
    }
}
