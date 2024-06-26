package work.huangdu.exploration.start_from_scratch.tree.level_order;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 129. 求根到叶子节点数字之和
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * 计算从根到叶子节点生成的所有数字之和。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例 1:
 * 输入: [1,2,3]
 * *     1
 * *    / \
 * *   2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 * 输入: [4,9,0,5,1]
 * *     4
 * *    / \
 * *   9   0
 * *  / \
 * * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 *
 * @author huangdu
 * @version 2020/10/29 0:09
 */
public class SumNumbers {
    private int sum = 0;

    public int sumNumbers3(TreeNode root) {
        if (root == null) return sum;
        help(root, 0);
        return sum;
    }

    private void help(TreeNode node, int val) {
        if (node.left == null && node.right == null) {
            sum += (val + node.val);
            return;
        }
        val = (val + node.val) * 10;
        if (node.left != null) {
            help(node.left, val);
        }
        if (node.right != null) {
            help(node.right, val);
        }
    }

    // 迭代
    public int sumNumbers2(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        Queue<Integer> sumQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        sumQueue.offer(0);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            Integer val = sumQueue.poll() * 10 + node.val;
            if (node.left == null && node.right == null) {
                sum += val;
            } else {
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    sumQueue.offer(val);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    sumQueue.offer(val);
                }
            }
        }
        return sum;
    }

    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }

    private int sumNumbers(TreeNode node, int number) {
        if (node == null) return 0;
        number = number * 10 + node.val;
        if (node.left == null && node.right == null) {
            return number;
        }
        int left = sumNumbers(node.left, number);
        int right = sumNumbers(node.right, number);
        return left + right;
    }
}
