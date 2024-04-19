package work.huangdu.question_bank.easy;

import java.util.ArrayDeque;

import work.huangdu.data_structure.TreeNode;

/**
 * 938. 二叉搜索树的范围和
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 * 示例 1：
 * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
 * 输出：32
 * 示例 2：
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * 输出：23
 * 提示：
 * 树中节点数目在范围 [1, 2 * 104] 内
 * 1 <= Node.val <= 10^5
 * 1 <= low <= high <= 10^5
 * 所有 Node.val 互不相同
 *
 * @author huangdu
 * @version 2021/4/27
 */
public class RangeSumBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) { return 0; }
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }

    public int rangeSumBST2(TreeNode root, int low, int high) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        int sum = 0;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.remove();
            if (low <= cur.val && cur.val <= high) {
                sum += cur.val;
            }
            cur = cur.right;
        }
        return sum;
    }

    private int sum;
    private int low;
    private int high;

    public int rangeSumBST3(TreeNode root, int low, int high) {
        this.sum = 0;
        this.low = low;
        this.high = high;
        inorder(root);
        return sum;
    }

    private void inorder(TreeNode root) {
        if (root == null) {return;}
        inorder(root.left);
        if (low <= root.val && root.val <= high) {
            sum += root.val;
        }
        inorder(root.right);
    }
}
