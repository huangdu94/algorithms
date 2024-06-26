package work.huangdu.exploration.start_from_scratch.tree.level_order;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import work.huangdu.data_structure.TreeNode;

/**
 * 515. 在每个树行中找最大值
 * 您需要在二叉树的每一行中找到最大的值。
 * 示例：
 * 输入:
 * *           1
 * *          / \
 * *        3   2
 * *        / \   \
 * *       5   3   9
 * 输出: [1, 3, 9]
 *
 * @author huangdu
 * @version 2021/1/23
 */
public class LargestValues {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {return ans;}
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(), max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(max);
        }
        return ans;
    }

    public List<Integer> largestValues2(TreeNode root) {
        if (root == null) {return new ArrayList<>();}
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(), max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (max < node.val) {
                    max = node.val;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(max);
        }
        return result;
    }
}
