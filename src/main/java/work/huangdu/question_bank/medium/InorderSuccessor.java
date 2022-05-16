package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.Deque;

import work.huangdu.data_structure.TreeNode;

/**
 * 面试题 04.06. 后继者
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 * 示例 1:
 * 输入: root = [2,1,3], p = 1
 * *   2
 * *  / \
 * * 1   3
 * 输出: 2
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 * *       5
 * *      / \
 * *     3   6
 * *    / \
 * *   2   4
 * *  /
 * * 1
 * 输出: null
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/5/16
 */
public class InorderSuccessor {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        boolean flag = false;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (flag) {return root;}
            if (root == p) {flag = true;}
            root = root.right;
        }
        return null;
    }
}
