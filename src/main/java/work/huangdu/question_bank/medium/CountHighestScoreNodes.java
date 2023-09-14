package work.huangdu.question_bank.medium;

/**
 * 2049. 统计最高分的节点数目
 * 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。
 * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
 * 请你返回有 最高得分 节点的 数目 。
 * 示例 1:
 * example-1
 * 输入：parents = [-1,2,0,2,0]
 * 输出：3
 * 解释：
 * - 节点 0 的分数为：3 * 1 = 3
 * - 节点 1 的分数为：4 = 4
 * - 节点 2 的分数为：1 * 1 * 2 = 2
 * - 节点 3 的分数为：4 = 4
 * - 节点 4 的分数为：4 = 4
 * 最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
 * 示例 2：
 * example-2
 * 输入：parents = [-1,2,0]
 * 输出：2
 * 解释：
 * - 节点 0 的分数为：2 = 2
 * - 节点 1 的分数为：2 = 2
 * - 节点 2 的分数为：1 * 1 = 1
 * 最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
 * 提示：
 * n == parents.length
 * 2 <= n <= 10^5
 * parents[0] == -1
 * 对于 i != 0 ，有 0 <= parents[i] <= n - 1
 * parents 表示一棵二叉树。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/3/16
 */
public class CountHighestScoreNodes {
    // TODO 未提交 每日一题
    static class TreeNode {
        int val;
        int count = 1;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length, ans = 0;
        TreeNode[] nodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {nodes[i] = new TreeNode(i);}
        for (int i = 1; i < n; i++) {
            TreeNode parentNode = nodes[parents[i]];
            if (parentNode.left == null) {
                parentNode.left = nodes[i];
            } else {
                parentNode.right = nodes[i];
            }
        }
        dfs(nodes[0]);
        long max = 0;
        for (int i = 0; i < n; i++) {
            TreeNode cur = nodes[i], left = cur.left, right = cur.right;
            int parentCount = n - cur.count;
            if (parentCount == 0) {parentCount = 1;}
            int leftSonCount = left == null ? 1 : left.count;
            int rightSonCount = right == null ? 1 : right.count;
            long product = (long)parentCount * leftSonCount * rightSonCount;
            if (max < product) {
                max = product;
                ans = 1;
            } else if (max == product) {
                ans++;
            }
        }
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node.left != null) {
            dfs(node.left);
            node.count += node.left.count;
        }
        if (node.right != null) {
            dfs(node.right);
            node.count += node.right.count;
        }
    }
}