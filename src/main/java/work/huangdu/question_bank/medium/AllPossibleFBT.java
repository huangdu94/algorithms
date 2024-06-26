package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import work.huangdu.data_structure.TreeNode;

/**
 * 894. 所有可能的真二叉树
 * 给你一个整数 n ，请你找出所有可能含 n 个节点的 真二叉树 ，并以列表形式返回。答案中每棵树的每个节点都必须符合 Node.val == 0 。
 * 答案的每个元素都是一棵真二叉树的根节点。你可以按 任意顺序 返回最终的真二叉树列表。
 * 真二叉树 是一类二叉树，树中每个节点恰好有 0 或 2 个子节点。
 * 示例 1：
 * 输入：n = 7
 * 输出：[[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 * 示例 2：
 * 输入：n = 3
 * 输出：[[0,0,0]]
 * 提示：
 * 1 <= n <= 20
 * * // Definition for a binary tree node.
 * * public class TreeNode {
 * *     int val;
 * *     TreeNode left;
 * *     TreeNode right;
 * *     TreeNode() {}
 * *     TreeNode(int val) { this.val = val; }
 * *     TreeNode(int val, TreeNode left, TreeNode right) {
 * *         this.val = val;
 * *         this.left = left;
 * *         this.right = right;
 * *     }
 * * }
 *
 * @author huangdu
 */
public class AllPossibleFBT {
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> fullBinaryTrees = new ArrayList<TreeNode>();
        if (n % 2 == 0) {
            return fullBinaryTrees;
        }
        if (n == 1) {
            fullBinaryTrees.add(new TreeNode(0));
            return fullBinaryTrees;
        }
        for (int i = 1; i < n; i += 2) {
            List<TreeNode> leftSubtrees = allPossibleFBT(i);
            List<TreeNode> rightSubtrees = allPossibleFBT(n - 1 - i);
            for (TreeNode leftSubtree : leftSubtrees) {
                for (TreeNode rightSubtree : rightSubtrees) {
                    TreeNode root = new TreeNode(0, leftSubtree, rightSubtree);
                    fullBinaryTrees.add(root);
                }
            }
        }
        return fullBinaryTrees;
    }

    private static int len = 3;
    private static final List<TreeNode>[] ANS_ARRAY = new List[20];

    static {
        ANS_ARRAY[1] = Arrays.asList(new TreeNode(0));
    }

    public List<TreeNode> allPossibleFBT2(int n) {
        if ((n & 1) == 0) { return new ArrayList(0); }
        if (n < len) { return ANS_ARRAY[n]; }
        for (int i = len; i <= n; i += 2) {
            List<TreeNode> ans = new ArrayList();
            for (int j = 1; j < i; j += 2) {
                for (TreeNode left : ANS_ARRAY[j]) {
                    for (TreeNode right : ANS_ARRAY[i - j - 1]) {
                        ans.add(new TreeNode(0, left, right));
                    }
                }
            }
            ANS_ARRAY[i] = ans;
        }
        len = n + 2;
        return ANS_ARRAY[n];
    }
}
