package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import work.huangdu.data_structure.TreeNode;

/**
 * 1110. 删点成林
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * 输出：[[1,2,null,4],[6],[7]]
 * 示例 2：
 * 输入：root = [1,2,4,null,3], to_delete = [3]
 * 输出：[[1,2,4]]
 * 提示：
 * 树中的节点数最大为 1000。
 * 每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
 * to_delete.length <= 1000
 * to_delete 包含一些从 1 到 1000、各不相同的值。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/5/30
 */
public class DelNodes {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>();
        Set<Integer> deleteSet = new HashSet<>(to_delete.length);
        for (int node : to_delete) {deleteSet.add(node);}
        if (!deleteSet.contains(root.val)) {ans.add(root);}
        dfs(ans, root, deleteSet);
        return ans;
    }

    private void dfs(List<TreeNode> ans, TreeNode node, Set<Integer> deleteSet) {
        if (node == null) {return;}
        dfs(ans, node.left, deleteSet);
        dfs(ans, node.right, deleteSet);
        if (deleteSet.contains(node.val)) {
            if (node.left != null && !deleteSet.contains(node.left.val)) {ans.add(node.left);}
            if (node.right != null && !deleteSet.contains(node.right.val)) {ans.add(node.right);}
        } else {
            if (node.left != null && deleteSet.contains(node.left.val)) {node.left = null;}
            if (node.right != null && deleteSet.contains(node.right.val)) {node.right = null;}
        }
    }
}
