package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import work.huangdu.data_structure.TreeNode;

/**
 * 919. 完全二叉树插入器
 * 完全二叉树 是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
 * 设计一种算法，将一个新节点插入到一个完整的二叉树中，并在插入后保持其完整。
 * 实现 CBTInserter 类:
 * CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v)  向树中插入一个值为 Node.val == val的新节点 TreeNode。使树保持完全二叉树的状态，并返回插入节点 TreeNode 的父节点的值；
 * CBTInserter.get_root() 将返回树的头节点。
 * 示例 1：
 * 输入
 * ["CBTInserter", "insert", "insert", "get_root"]
 * [[[1, 2]], [3], [4], []]
 * 输出
 * [null, 1, 2, [1, 2, 3, 4]]
 * 解释
 * CBTInserter cBTInserter = new CBTInserter([1, 2]);
 * cBTInserter.insert(3);  // 返回 1
 * cBTInserter.insert(4);  // 返回 2
 * cBTInserter.get_root(); // 返回 [1, 2, 3, 4]
 * 提示：
 * 树中节点数量范围为 [1, 1000]
 * 0 <= Node.val <= 5000
 * root 是完全二叉树
 * 0 <= val <= 5000
 * 每个测试用例最多调用 insert 和 get_root 操作 10^4 次
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/7/25
 */
public class CBTInserter {
    private List<TreeNode> cur;
    private List<TreeNode> next;
    private final TreeNode root;

    public CBTInserter(TreeNode root) {
        this.cur = new ArrayList<>();
        this.next = new ArrayList<>();
        this.root = root;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        cur.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left == null) {break;}
                queue.offer(node.left);
                next.add(node.left);
                if (node.right == null) {break;}
                queue.offer(node.right);
                next.add(node.right);
            }
            if (next.size() == 2 * cur.size()) {
                cur = next;
                next = new ArrayList<>();
            }
        }
    }

    public int insert(int val) {
        TreeNode node = new TreeNode(val);
        int idx = next.size();
        TreeNode parent = cur.get(idx / 2);
        if ((idx & 1) == 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        next.add(node);
        if (next.size() == 2 * cur.size()) {
            cur = next;
            next = new ArrayList<>();
        }
        return parent.val;
    }

    public TreeNode get_root() {
        return root;
    }
}
