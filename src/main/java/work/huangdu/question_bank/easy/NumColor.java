package work.huangdu.question_bank.easy;

import java.util.HashSet;
import java.util.Set;

import work.huangdu.data_structure.TreeNode;

/**
 * LCP 44. 开幕式焰火
 * 「力扣挑战赛」开幕式开始了，空中绽放了一颗二叉树形的巨型焰火。
 * 给定一棵二叉树 root 代表焰火，节点值表示巨型焰火这一位置的颜色种类。请帮小扣计算巨型焰火有多少种不同的颜色。
 * 示例 1：
 * 输入：root = [1,3,2,1,null,2]
 * 输出：3
 * 解释：焰火中有 3 个不同的颜色，值分别为 1、2、3
 * 示例 2：
 * 输入：root = [3,3,3]
 * 输出：1
 * 解释：焰火中仅出现 1 个颜色，值为 3
 * 提示：
 * 1 <= 节点个数 <= 1000
 * 1 <= Node.val <= 1000
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/4/26
 */
public class NumColor {
    private final Set<Integer> set = new HashSet<>();

    public int numColor(TreeNode root) {
        inorder(root);
        return set.size();
    }

    private void inorder(TreeNode node) {
        if (node == null) {return;}
        set.add(node.val);
        inorder(node.left);
        inorder(node.right);
    }
}
