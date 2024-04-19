package work.huangdu.question_bank.easy;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 897. 递增顺序搜索树
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * 示例 1：
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * 示例 2：
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 * 提示：
 * 树中节点数的取值范围是 [1, 100]
 * 0 <= Node.val <= 1000
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2021/4/25
 */
public class IncreasingBST {
    public TreeNode increasingBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, prev = null, head = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur.left = null;
            if (prev != null) {
                prev.right = cur;
            } else {
                head = cur;
            }
            prev = cur;
            cur = cur.right;
        }
        return head;
    }

    public static void main(String[] args) {
        IncreasingBST ibst = new IncreasingBST();
        TreeNode root = new TreeNode(2);
        TreeNode rootLeft = new TreeNode(1);
        TreeNode rootRight = new TreeNode(4);
        TreeNode rootRightLeft = new TreeNode(3);
        root.left = rootLeft;
        root.right = rootRight;
        rootRight.left = rootRightLeft;
        TreeNode head = ibst.increasingBST(root);
        System.out.println("Done!");
    }
}
