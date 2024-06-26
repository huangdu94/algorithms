package work.huangdu.exploration.start_from_scratch.tree.level_order;

import java.util.ArrayDeque;
import java.util.Queue;

import work.huangdu.data_structure.TreeNode;

/**
 * 671. 二叉树中第二小的节点
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 * 示例 1：
 * 输入：root = [2,2,5,null,null,5,7]
 * 输出：5
 * 解释：最小的值是 2 ，第二小的值是 5 。
 * 示例 2：
 * 输入：root = [2,2,2]
 * 输出：-1
 * 解释：最小的值是 2, 但是不存在第二小的值。
 * 提示：
 * 树中节点数目在范围 [1, 25] 内
 * 1 <= Node.val <= 2^31 - 1
 * 对于树中每个节点 root.val == min(root.left.val, root.right.val)
 *
 * @author huangdu
 * @version 2021/1/27
 */
public class FindSecondMinimumValue {
    public int findSecondMinimumValue(TreeNode root) {
        int rootVal = root.val, secondMin = -1;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null) {
                int rootLeftVal = root.left.val, rootRightVal = root.right.val;
                if (rootLeftVal != rootVal) {
                    if (secondMin == -1 || secondMin > rootLeftVal) {
                        secondMin = rootLeftVal;
                    }
                } else {
                    queue.offer(root.left);
                }
                if (rootRightVal != rootVal) {
                    if (secondMin == -1 || secondMin > rootRightVal) {
                        secondMin = rootRightVal;
                    }
                } else {
                    queue.offer(root.right);
                }
            }
        }
        return secondMin;
    }

    private int min;
    private int secondMin;

    public int findSecondMinimumValue2(TreeNode root) {
        this.min = root.val;
        this.secondMin = -1;
        dfs(root);
        return this.secondMin;
    }

    private void dfs(TreeNode node) {
        if (node == null) {return;}
        if (secondMin != -1 && node.val >= secondMin) {
            return;
        }
        if (node.val > min) {
            secondMin = node.val;
        }
        dfs(node.left);
        dfs(node.right);
    }
}
