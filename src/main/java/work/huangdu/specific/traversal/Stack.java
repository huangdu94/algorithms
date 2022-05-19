package work.huangdu.specific.traversal;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

import work.huangdu.data_structure.TreeNode;

/**
 * 使用栈
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/10/18
 */
public class Stack implements Traversal {
    private Consumer<TreeNode> operation;

    public void preorder(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            operation.accept(cur);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    public void inorder(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            operation.accept(cur);
            cur = cur.right;
        }
    }

    public void postorder(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, prev = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || cur.right == prev) {
                operation.accept(cur);
                prev = stack.pop();
                cur = null;
            } else {
                cur = cur.right;
            }
        }
    }

    /*
    方法一：
    前序是 中 左 右
    后序是 左 右 中 倒过来就是 中 右 左
    所以对前序的遍历稍微改一下，但是list最后倒序就可以达到一样的效果
    */
    public void postorder2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            operation.accept(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
    }

    @Override
    public void setOperation(Consumer<TreeNode> operation) {
        this.operation = operation;
    }
}
