package work.huangdu.exploration.start_from_scratch.tree.flatten;

import java.util.ArrayList;
import java.util.List;

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
 * @author huangdu
 * @version 2021/3/9
 */
public class Flatten {
    //原地算法 寻找前驱节点
    public void flatten(TreeNode root) {
        if (root == null) {return;}
        List<TreeNode> preorder = new ArrayList<>();
        while (root != null) {
            if (root.left == null) {
                preorder.add(root);
                root = root.right;
            } else {
                TreeNode prev = root.left;
                while (prev.right != null && prev.right != root) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    preorder.add(root);
                    prev.right = root;
                    root = root.left;
                } else {
                    prev.right = null;
                    root = root.right;
                }
            }
        }
        for (int i = 0, n = preorder.size() - 1; i < n; i++) {
            preorder.get(i).left = null;
            preorder.get(i).right = preorder.get(i + 1);
        }
    }

    public static void main(String[] args) {
        Flatten flatten = new Flatten();
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        flatten.flatten(root);
        System.out.println("done!");
    }
}
