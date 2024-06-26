package work.huangdu.exploration.start_from_scratch.tree.postorder;

import java.util.*;

/**
 * 590. N叉树的后序遍历
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 * 例如，给定一个 3叉树 :
 * 返回其后序遍历: [5,6,3,2,4,1].
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2021/2/6
 */
public class postorderN {
    // TODO 未提交
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(Node root, List<Integer> result) {
        if (root == null) return;
        // children不会为null
        // root.children.forEach((node) -> postorder(node, result));
        for (Node child : root.children) {
            postorder(child, result);
        }
        result.add(root.val);
    }

    public List<Integer> postorder2(Node root) {
        LinkedList<Integer> ans = new LinkedList<>();
        if(root==null){return ans;}
        Deque<Node> stack=new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur=stack.pop();
            ans.addFirst(cur.val);
            for(Node child : cur.children){
                // 放和拿的方法一定要配对使用
                stack.push(child);
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