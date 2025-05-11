package work.huangdu.question_bank.difficult;

import java.util.Stack;

import work.huangdu.data_structure.TreeNode;

/**
 * 99. 恢复二叉搜索树
 * 二叉搜索树中的两个节点被错误地交换。
 * 请在不改变其结构的情况下，恢复这棵树。
 * 示例 1:
 * 输入: [1,3,null,null,2]
 * *   1
 * *  /
 * * 3
 * *  \
 * *   2
 * 输出: [3,1,null,null,2]
 * *   3
 * *  /
 * * 1
 * *  \
 * *   2
 * 示例 2:
 * 输入: [3,1,4,null,null,2]
 * *  3
 * * / \
 * *1   4
 * *   /
 * *  2
 * 输出: [2,1,4,null,null,3]
 * *  2
 * * / \
 * *1   4
 * *   /
 * *  3
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 *
 * @author huangdu
 * @version 2020/8/8 10:57
 */
public class RecoverTree {
    /**
     * 核心分析：
     * 我们来看下如果在一个递增的序列中交换两个值会造成什么影响。假设有一个递增序列 a=[1,2,3,4,5,6,7]。
     * 如果我们交换两个不相邻的数字，例如 2 和 6，原序列变成了a=[1,6,3,4,5,2,7]，那么显然序列中有两个位置不满足 a(i)<a(i+1)，
     * 在这个序列中体现为 6>3，5>2，因此只要我们找到这两个位置，即可找到被错误交换的两个节点。
     * 如果我们交换两个相邻的数字，例如 2 和 3，此时交换后的序列只有一个位置不满足 a(i)<a(i+1)。
     * 因此整个值序列中不满足条件的位置或者有两个，或者有一个。
     * 莫里斯中序遍历
     */
    public void recoverTree(TreeNode root) {
        TreeNode a = null;
        TreeNode b = null;
        TreeNode pre = null;
        TreeNode prev;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                if (pre != null && pre.val > cur.val) {
                    b = cur;
                    if (a == null) { a = pre; }
                }
                pre = cur;
                cur = cur.right;
            } else {
                prev = cur.left;
                while (prev.right != null && prev.right != cur) { prev = prev.right; }
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    if (pre != null && pre.val > cur.val) {
                        b = cur;
                        if (a == null) { a = pre; }
                    }
                    pre = cur;
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        this.swap(a, b);
    }

    public void recoverTree2(TreeNode root) {
        TreeNode a = null;
        TreeNode b = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode cur = root;
        while (!stack.empty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (pre != null && pre.val > cur.val) {
                b = cur;
                if (a == null) { a = pre; } else { break; }
            }
            pre = cur;
            cur = cur.right;
        }
        this.swap(a, b);
    }

    /**
     * 交换a和b的值
     *
     * @param a 错误节点a
     * @param b 错误节点b
     */
    private void swap(TreeNode a, TreeNode b) {
        if (a != null && b != null) {
            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3, null, new TreeNode(2));
        RecoverTree recoverTree = new RecoverTree();
        recoverTree.recoverTree(root);
        System.out.println("...");
    }
}
