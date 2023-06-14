package work.huangdu.question_bank.difficult;

/**
 * 1483. 树节点的第 K 个祖先
 * 给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。树的根节点是编号为 0 的节点。
 * 树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。
 * 实现 TreeAncestor 类：
 * TreeAncestor（int n， int[] parent） 对树和父数组中的节点数初始化对象。
 * getKthAncestor(int node, int k) 返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。
 * 示例 1：
 * 输入：
 * ["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
 * [[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]
 * 输出：
 * [null,1,0,-1]
 * 解释：
 * TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
 * treeAncestor.getKthAncestor(3, 1);  // 返回 1 ，它是 3 的父节点
 * treeAncestor.getKthAncestor(5, 2);  // 返回 0 ，它是 5 的祖父节点
 * treeAncestor.getKthAncestor(6, 3);  // 返回 -1 因为不存在满足要求的祖先节点
 * 提示：
 * 1 <= k <= n <= 5 * 10^4
 * parent[0] == -1 表示编号为 0 的节点是根节点。
 * 对于所有的 0 < i < n ，0 <= parent[i] < n 总成立
 * 0 <= node < n
 * 至多查询 5 * 10^4 次
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/6/14
 */
public class TreeAncestor {
    private int maxDepth = 0;
    private int m = 0;
    private final int[] depth;
    private final int[][] dp;

    public TreeAncestor(int n, int[] parent) {
        this.depth = new int[n];
        for (int i = 0; i < n; i++) {dfs(parent, i);}
        while (1 << m <= maxDepth) {m++;}
        this.dp = new int[n][m];
        for (int i = 0; i < n; i++) {dp[i][0] = parent[i];}
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                int ancestor = dp[i][j - 1];
                if (ancestor == -1) {
                    dp[i][j] = -1;
                } else {
                    dp[i][j] = dp[ancestor][j - 1];
                }
            }
        }
    }

    private int dfs(int[] parent, int i) {
        if (i == -1) {return 0;}
        if (depth[i] == 0) {
            depth[i] = dfs(parent, parent[i]) + 1;
            maxDepth = Math.max(maxDepth, depth[i]);
        }
        return depth[i];
    }

    public int getKthAncestor(int node, int k) {
        if (k >= depth[node]) {return -1;}
        int j = m;
        while (k > 0) {
            while (1 << j > k) {j--;}
            node = dp[node][j];
            k -= 1 << j;

        }
        return node;
    }

    public static void main(String[] args) {
        int n = 7;
        int[] parent = {-1, 0, 0, 1, 1, 2, 2};
        TreeAncestor ta = new TreeAncestor(n, parent);
        System.out.println(ta.getKthAncestor(3, 1));
        System.out.println(ta.getKthAncestor(5, 2));
        System.out.println(ta.getKthAncestor(6, 3));
    }
}
