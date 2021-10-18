package work.huangdu.specific.traversal;

import java.util.function.Consumer;

import work.huangdu.data_structure.TreeNode;

/**
 * 莫里斯
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/10/18
 */
public class Morris implements Traversal {
    private Consumer<TreeNode> operation;

    @Override
    public void preorder(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            TreeNode prev = cur.left;
            if (prev == null) {
                operation.accept(cur);
                cur = cur.right;
            } else {
                while (prev.right != null && prev.right != cur) {prev = prev.right;}
                if (prev.right == null) {
                    operation.accept(cur);
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
    }

    @Override
    public void inorder(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            TreeNode prev = cur.left;
            if (prev == null) {
                operation.accept(cur);
                cur = cur.right;
            } else {
                while (prev.right != null && prev.right != cur) {prev = prev.right;}
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    operation.accept(cur);
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
    }

    @Override
    public void postorder(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            TreeNode prev = cur.left;
            if (prev != null) {
                while (prev.right != null && prev.right != cur) {prev = prev.right;}
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    prev.right = null;
                    operation(cur.left);
                }
            }
            cur = cur.right;
        }
        operation(root);
    }

    private void operation(TreeNode node) {
        TreeNode tail = reverse(node);
        TreeNode cur = tail;
        while (cur != null) {
            operation.accept(cur);
            cur = cur.right;
        }
        reverse(tail);
    }

    private TreeNode reverse(TreeNode node) {
        TreeNode pre = null;
        while (node != null) {
            TreeNode next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    @Override
    public void setOperation(Consumer<TreeNode> operation) {
        this.operation = operation;
    }
}
