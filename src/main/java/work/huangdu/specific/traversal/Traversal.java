package work.huangdu.specific.traversal;

import java.util.List;
import java.util.function.Consumer;

import work.huangdu.data_structure.TreeNode;

/**
 * 遍历接口
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/10/18
 */
public interface Traversal {
    void preorder(TreeNode root);

    void inorder(TreeNode root);

    void postorder(TreeNode root);

    void setOperation(Consumer<TreeNode> operation);

    static Consumer<TreeNode> recordList(List<Integer> list) {
        return treeNode -> list.add(treeNode.val);
    }
}
