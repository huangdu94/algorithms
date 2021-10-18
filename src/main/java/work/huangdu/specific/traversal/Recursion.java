package work.huangdu.specific.traversal;

import java.util.function.Consumer;

import work.huangdu.data_structure.TreeNode;

/**
 * 递归
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/10/18
 */
public class Recursion implements Traversal {
    private Consumer<TreeNode> operation;

    @Override
    public void preorder(TreeNode root) {
        if (root == null) {return;}
        operation.accept(root);
        preorder(root.left);
        preorder(root.right);
    }

    @Override
    public void inorder(TreeNode root) {
        if (root == null) {return;}
        inorder(root.left);
        operation.accept(root);
        inorder(root.right);
    }

    @Override
    public void postorder(TreeNode root) {
        if (root == null) {return;}
        postorder(root.left);
        postorder(root.right);
        operation.accept(root);
    }

    @Override
    public void setOperation(Consumer<TreeNode> operation) {
        this.operation = operation;
    }
}
