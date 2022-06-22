package work.huangdu.exploration.start_from_scratch.tree.level_order;

import java.util.ArrayDeque;
import java.util.Queue;

import work.huangdu.data_structure.TreeNode;

/**
 * 513. 找树左下角的值
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 * 示例 1:
 * 输入:
 * <p>
 * *     2
 * *    / \
 * *   1   3
 * 输出:
 * 1
 * 示例 2:
 * 输入:
 * *         1
 * *        / \
 * *       2   3
 * *      /   / \
 * *     4   5   6
 * *        /
 * *       7
 * 输出:
 * 7
 * 注意: 您可以假设树（即给定的根节点）不为 NULL。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/23
 */
public class FindBottomLeftValue {
    public int findBottomLeftValue2(TreeNode root) {
        int bottomLeftValue = root.val;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            bottomLeftValue = queue.peek().val;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return bottomLeftValue;
    }

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int ans = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    if (!flag) {
                        ans = node.left.val;
                        flag = true;
                    }
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    if (!flag) {
                        ans = node.right.val;
                        flag = true;
                    }
                }
            }
        }
        return ans;
    }
}
