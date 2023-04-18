package work.huangdu.question_bank.medium;

import work.huangdu.data_structure.TreeNode;

/**
 * 1026. 节点与其祖先之间的最大差值
 * 给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
 * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 * 示例 1：
 * 输入：root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * 输出：7
 * 解释：
 * 我们有大量的节点与其祖先的差值，其中一些如下：
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * 在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
 * 示例 2：
 * 输入：root = [1,null,2,null,0,3]
 * 输出：3
 * 提示：
 * 树中的节点数在 2 到 5000 之间。
 * 0 <= Node.val <= 10^5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/4/18
 */
public class MaxAncestorDiff {
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root)[0];
    }

    // 0 ans 1 min 2 max
    private int[] dfs(TreeNode root) {
        if (root == null) {return new int[] {0, Integer.MAX_VALUE, Integer.MIN_VALUE};}
        int[] left = dfs(root.left), right = dfs(root.right);
        int val = root.val, ans = Math.max(left[0], right[0]), min = Math.min(left[1], right[1]), max = Math.max(left[2], right[2]);
        return new int[] {Math.max(ans, min < Integer.MAX_VALUE ? Math.max(Math.abs(val - min), Math.abs(val - max)) : 0), Math.min(min, val), Math.max(max, val)};
    }
}
