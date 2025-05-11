package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

import work.huangdu.data_structure.TreeNode;

/**
 * 1305. 两棵二叉搜索树中的所有元素
 * 给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 * 示例 1：
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]
 * 示例 2：
 * 输入：root1 = [1,null,8], root2 = [8,1]
 * 输出：[1,1,8,8]
 * 提示：
 * 每棵树的节点数在 [0, 5000] 范围内
 * -10^5 <= Node.val <= 10^5
 *
 * @author huangdu
 * @version 2022/5/1
 */
public class GetAllElements {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> inorder1 = new ArrayList<>(), inorder2 = new ArrayList<>(), ans = new ArrayList<>();
        inorder(root1, inorder1);
        inorder(root2, inorder2);
        int n1 = inorder1.size(), n2 = inorder2.size(), i1 = 0, i2 = 0;
        while (i1 < n1 || i2 < n2) {
            int num1 = i1 < n1 ? inorder1.get(i1) : Integer.MAX_VALUE,
                num2 = i2 < n2 ? inorder2.get(i2) : Integer.MAX_VALUE;
            if (num1 <= num2) {
                ans.add(num1);
                i1++;
            } else {
                ans.add(num2);
                i2++;
            }
        }
        return ans;
    }

    private void inorder(TreeNode root, List<Integer> inorder) {
        if (root == null) {return;}
        inorder(root.left, inorder);
        inorder.add(root.val);
        inorder(root.right, inorder);
    }
}
