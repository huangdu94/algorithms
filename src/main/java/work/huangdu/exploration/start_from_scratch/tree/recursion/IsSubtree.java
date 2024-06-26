package work.huangdu.exploration.start_from_scratch.tree.recursion;

import work.huangdu.data_structure.TreeNode;

/**
 * 572. 另一个树的子树
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 * 示例 1:
 * 给定的树 s:
 * *      3
 * *     / \
 * *    4   5
 * *   / \
 * *  1   2
 * 给定的树 t：
 * *    4
 * *   / \
 * *  1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 * 示例 2:
 * 给定的树 s：
 * *      3
 * *     / \
 * *    4   5
 * *   / \
 * *  1   2
 * *     /
 * *    0
 * 给定的树 t：
 * *    4
 * *   / \
 * *  1   2
 * 返回 false。
 *
 * @author huangdu
 * @version 2020/12/27 13:25
 */
public class IsSubtree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) return true;
        return dfs(s, t);
    }

    private boolean dfs(TreeNode s, TreeNode t) {
        if (s == null) return false;
        return isSame(s, t) || dfs(s.left, t) || dfs(s.right, t);
    }

    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null || s.val != t.val) return false;
        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}
