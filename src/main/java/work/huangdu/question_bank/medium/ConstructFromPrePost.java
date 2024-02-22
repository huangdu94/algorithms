package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;

import work.huangdu.data_structure.TreeNode;

/**
 * 889. 根据前序和后序遍历构造二叉树
 * 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
 * 如果存在多个答案，您可以返回其中 任何 一个。
 * 示例 1：
 * 输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 * 示例 2:
 * 输入: preorder = [1], postorder = [1]
 * 输出: [1]
 * 提示：
 * 1 <= preorder.length <= 30
 * 1 <= preorder[i] <= preorder.length
 * preorder 中所有值都 不同
 * postorder.length == preorder.length
 * 1 <= postorder[i] <= postorder.length
 * postorder 中所有值都 不同
 * 保证 preorder 和 postorder 是同一棵二叉树的前序遍历和后序遍历
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class ConstructFromPrePost {
    // TODO 复制粘贴需要复习
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;
        Map<Integer, Integer> postMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            postMap.put(postorder[i], i);
        }
        return dfs(preorder, postMap, 0, n - 1, 0, n - 1);
    }

    public TreeNode dfs(int[] preorder, Map<Integer, Integer> postMap, int preLeft, int preRight, int postLeft, int postRight) {
        if (preLeft > preRight) {
            return null;
        }
        int leftCount = 0;
        if (preLeft < preRight) {
            leftCount = postMap.get(preorder[preLeft + 1]) - postLeft + 1;
        }
        return new TreeNode(preorder[preLeft],
            dfs(preorder, postMap, preLeft + 1, preLeft + leftCount, postLeft, postLeft + leftCount - 1),
            dfs(preorder, postMap, preLeft + leftCount + 1, preRight, postLeft + leftCount, postRight - 1));
    }
}
