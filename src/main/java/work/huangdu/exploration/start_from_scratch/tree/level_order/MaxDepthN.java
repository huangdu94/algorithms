package work.huangdu.exploration.start_from_scratch.tree.level_order;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 559. N 叉树的最大深度
 * 给定一个 N 叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 * 示例 1：
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：3
 * 示例 2：
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：5
 * 提示：
 * 树的深度不会超过 1000 。
 * 树的节点数目位于 [0, 104] 之间。
 *
 * @author huangdu
 * @version 2021/1/23
 */
public class MaxDepthN {
    public int maxDepth(Node root) {
        return dfs(root);
    }

    private int dfs(Node root) {
        if (root == null) {return 0;}
        if (root.children == null) {return 1;}
        int max = 0;
        for (Node child : root.children) {
            max = Math.max(max, dfs(child));
        }
        return 1 + max;
    }

    public int bfs(Node root) {
        if (root == null) {return 0;}
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.children != null) {
                    for (Node child : node.children) {
                        queue.offer(child);
                    }
                }
            }
        }
        return depth;
    }

    private int maxDepth = 0;

    public int maxDepth2(Node root) {
        if (root == null) { return 0; }
        maxDepth(root, 1);
        return maxDepth;
    }

    private void maxDepth(Node root, int level) {
        if (root == null || root.children == null || root.children.isEmpty()) {
            if (maxDepth < level) {
                maxDepth = level;
            }
            return;
        }
        for (Node child : root.children) {
            maxDepth(child, level + 1);
        }
    }
}
