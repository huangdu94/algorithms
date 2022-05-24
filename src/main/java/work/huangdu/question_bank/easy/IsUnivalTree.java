package work.huangdu.question_bank.easy;

import java.util.ArrayDeque;
import java.util.Deque;

import work.huangdu.data_structure.TreeNode;

/**
 * 965. 单值二叉树
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 * 示例 1：
 * 输入：[1,1,1,1,1,null,1]
 * 输出：true
 * 示例 2：
 * 输入：[2,2,2,5,2]
 * 输出：false
 * 提示：
 * 给定树的节点数范围是 [1, 100]。
 * 每个节点的值都是整数，范围为 [0, 99] 。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/5/24
 */
public class IsUnivalTree {
    public boolean isUnivalTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        int preVal = root.val;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                if (preVal != cur.val) {return false;}
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop().right;
        }
        return true;
    }
}
