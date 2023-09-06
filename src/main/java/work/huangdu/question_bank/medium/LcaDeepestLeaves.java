package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import work.huangdu.data_structure.TreeNode;

/**
 * 1123. 最深叶节点的最近公共祖先
 * 给你一个有根节点 root 的二叉树，返回它 最深的叶节点的最近公共祖先 。
 * 回想一下：
 * 叶节点 是二叉树中没有子节点的节点
 * 树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
 * 如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：我们返回值为 2 的节点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的节点。
 * 注意，节点 6、0 和 8 也是叶节点，但是它们的深度是 2 ，而节点 7 和 4 的深度是 3 。
 * 示例 2：
 * 输入：root = [1]
 * 输出：[1]
 * 解释：根节点是树中最深的节点，它是它本身的最近公共祖先。
 * 示例 3：
 * 输入：root = [0,1,3,null,2]
 * 输出：[2]
 * 解释：树中最深的叶节点是 2 ，最近公共祖先是它自己。
 * 提示：
 * 树中的节点数将在 [1, 1000] 的范围内。
 * 0 <= Node.val <= 1000
 * 每个节点的值都是独一无二的。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class LcaDeepestLeaves {
    private int max = 0;
    private final Queue<TreeNode> queue = new ArrayDeque<>();
    private final Map<TreeNode, TreeNode> map = new HashMap<>();

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 0);
        Set<TreeNode> visited = new HashSet<>();
        while (queue.size() > 1) {
            for (int i = 0, size = queue.size(); i < size; i++) {
                TreeNode node = queue.poll();
                if (visited.add(map.get(node))) {
                    queue.offer(map.get(node));
                }
            }
        }
        return queue.poll();
    }

    private void dfs(TreeNode node, int level) {
        if (level > max) {
            queue.clear();
            max = level;
        }
        if (level == max) {
            queue.offer(node);
        }
        if (node.left != null) {
            map.put(node.left, node);
            dfs(node.left, level + 1);
        }
        if (node.right != null) {
            map.put(node.right, node);
            dfs(node.right, level + 1);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(5), new TreeNode(1));
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2, new TreeNode(7), new TreeNode(4));
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        LcaDeepestLeaves ldl = new LcaDeepestLeaves();
        System.out.println(ldl.lcaDeepestLeaves(root));
    }
}
