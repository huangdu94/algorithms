package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import work.huangdu.data_structure.TreeNode;

/**
 * 863. 二叉树中所有距离为 K 的结点
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 * 提示：
 * 给定的树是非空的。
 * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 * 目标结点 target 是树上的结点。
 * 0 <= K <= 1000.
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/7/28
 */
public class DistanceK {
    private List<Integer> nodeList;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        this.nodeList = new ArrayList<>();
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode parent = queue.poll();
            if (addSon(queue, parentMap, parent, parent.left, target)) {break;}
            if (addSon(queue, parentMap, parent, parent.right, target)) {break;}
        }
        TreeNode son = null, cur = target;
        while (cur != null && k > 0) {
            if (son == null) {
                dfs(cur, k);
            } else if (son == cur.left) {
                dfs(cur.right, k - 1);
            } else {
                dfs(cur.left, k - 1);
            }
            son = cur;
            cur = parentMap.get(son);
            k--;
        }
        if (cur != null && k == 0) {
            this.nodeList.add(cur.val);
        }
        return this.nodeList;
    }

    private boolean addSon(Queue<TreeNode> queue, Map<TreeNode, TreeNode> parentMap, TreeNode parent, TreeNode son, TreeNode target) {
        if (son != null) {
            parentMap.put(son, parent);
            queue.offer(son);
            return son == target;
        }
        return false;
    }

    private void dfs(TreeNode node, int distance) {
        if (node == null) {return;}
        if (distance == 0) {
            this.nodeList.add(node.val);
            return;
        }
        dfs(node.left, distance - 1);
        dfs(node.right, distance - 1);
    }

    public static void main(String[] args) {
        DistanceK dk = new DistanceK();
        TreeNode zero = new TreeNode(0);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        zero.left = one;
        one.left = three;
        one.right = two;
        System.out.println(dk.distanceK(zero, two, 1));
    }
}
