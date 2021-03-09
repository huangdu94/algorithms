package work.huangdu.exploration.start_from_scratch.tree.flatten;

import work.huangdu.data_structure.TreeNode;

/**
 * 114. 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * 输入：root = [0]
 * 输出：[0]
 * 提示：
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/3/9
 */
public class Flatten {
    // TODO
    public void flatten(TreeNode root) {
        if (root == null || root.left == null && root.right == null) { return; }
        TreeNode cur = root, dummy = new TreeNode(), tail = dummy;
        while (cur != null) {
            if (cur.left == null) {
                tail.right = new TreeNode(cur.val);
                tail = tail.right;
                cur = cur.right;
            } else {
                TreeNode prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    tail.right = new TreeNode(cur.val);
                    tail = tail.right;
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        root.val = dummy.right.val;
        root.left = null;
        root.right = dummy.right.right;
    }

    public static void main(String[] args) {
        Flatten flatten = new Flatten();
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        flatten.flatten(root);
        System.out.println("done!");
    }
}
