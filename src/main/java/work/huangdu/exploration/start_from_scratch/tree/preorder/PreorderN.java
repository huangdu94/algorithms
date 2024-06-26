package work.huangdu.exploration.start_from_scratch.tree.preorder;

import java.util.*;

/**
 * 589. N叉树的前序遍历
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * 例如，给定一个 3叉树 :
 * 返回其前序遍历: [1,3,5,6,2,4]。
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 *
 * @author huangdu
 * @version 2021/1/28
 */
public class PreorderN {
    // TODO 未提交
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        // 1. 递归
        // preorder(result, root);
        // 2. 迭代
        Deque<Node> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                result.add(root.val);
                stack.push(root);
                List<Node> children = root.children;
                if (children != null && !children.isEmpty()) {
                    root = children.remove(0);
                } else {
                    root = null;
                }
            }
            List<Node> children = stack.peek().children;
            if (children != null && !children.isEmpty()) {
                root = children.remove(0);
            } else {
                stack.pop();
                root = null;
            }
        }
        return result;
    }

    private void preorder(List<Integer> result, Node node) {
        if (node == null) { return; }
        result.add(node.val);
        if (node.children == null) { return; }
        for (Node child : node.children) {
            preorder(result, child);
        }
    }

    public List<Integer> preorder2(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {return ans;}
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            ans.add(cur.val);
            int n = cur.children.size();
            for (int i = n - 1; i >= 0; i--) {
                stack.push(cur.children.get(i));
            }
        }
        return ans;
    }
}

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}