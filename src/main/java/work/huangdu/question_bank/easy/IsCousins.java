package work.huangdu.question_bank.easy;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import work.huangdu.data_structure.TreeNode;

/**
 * 993. 二叉树的堂兄弟节点
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 * 示例 1：
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 * 示例 2：
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 * 示例 3：
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 * 提示：
 * 二叉树的节点数介于 2 到 100 之间。
 * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/17
 */
public class IsCousins {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root.val == x || root.val == y) {return false;}
        Map<Integer, Integer> fatherMap = new HashMap<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        Set<Integer> levelSet = new HashSet<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                add(cur.left, cur, fatherMap, levelSet, queue);
                add(cur.right, cur, fatherMap, levelSet, queue);
            }
            if (levelSet.contains(x) || levelSet.contains(y)) {
                if (!levelSet.contains(x) || !levelSet.contains(y)) {
                    return false;
                }
                return !fatherMap.get(x).equals(fatherMap.get(y));
            }
            levelSet.clear();
        }
        return false;
    }

    private void add(TreeNode son, TreeNode father, Map<Integer, Integer> fatherMap, Set<Integer> level, Queue<TreeNode> queue) {
        if (son != null) {
            fatherMap.put(son.val, father.val);
            level.add(son.val);
            queue.offer(son);
        }
    }
}
