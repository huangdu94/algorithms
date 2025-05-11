package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

import work.huangdu.data_structure.TreeNode;

/**
 * @author huangdu
 */
public class ReplaceValueInTree {
    public TreeNode replaceValueInTree(TreeNode root) {
        List<Integer> levels = new ArrayList<>();
        levelOrder(root, levels, 0);
        root.val = 0;
        dfs(root, levels, 0);
        return root;
    }

    private void levelOrder(TreeNode root, List<Integer> levels, int depth) {
        if (root == null) {return;}
        if (levels.size() == depth) {levels.add(0);}
        levels.set(depth, levels.get(depth) + root.val);
        levelOrder(root.left, levels, depth + 1);
        levelOrder(root.right, levels, depth + 1);
    }

    private void dfs(TreeNode root, List<Integer> levels, int depth) {
        if (root.left == null && root.right == null) {return;}
        int leftVal = root.left == null ? 0 : root.left.val, rightVal = root.right == null ? 0 : root.right.val;
        if (root.left != null) {
            root.left.val = levels.get(depth + 1) - leftVal - rightVal;
            dfs(root.left, levels, depth + 1);
        }
        if (root.right != null) {
            root.right.val = levels.get(depth + 1) - leftVal - rightVal;
            dfs(root.right, levels, depth + 1);
        }
    }
}
