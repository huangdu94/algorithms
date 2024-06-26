package work.huangdu.question_bank.easy;

import work.huangdu.data_structure.TreeNode;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 * 示例：
 * 输入：
 * *      4
 * *    /   \
 * *   2     7
 * *  / \   / \
 * * 1   3 6   9
 * 输出：
 * *      4
 * *    /   \
 * *   7     2
 * *  / \   / \
 * * 9   6 3   1
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 *
 * @author huangdu
 * @version 2020/8/15 13:02
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }
}
