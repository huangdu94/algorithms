package work.huangdu.specific.traversal;

import java.util.ArrayList;
import java.util.List;

import work.huangdu.data_structure.TreeNode;

/**
 * *    1
 * *   / \
 * *  2   3
 * * /\   /\
 * *4  5 6  7
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/10/18
 */
public class Test {
    private static final TreeNode root = new TreeNode(1);

    static {
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
    }

    public static void main(String[] args) {
        System.out.println("--- recursion ---");
        Traversal recursion = new Recursion();
        System.out.println("-- pre order --");
        final List<Integer> recursionPreOrderList = new ArrayList<>();
        recursion.setOperation(Traversal.recordList(recursionPreOrderList));
        recursion.preorder(root);
        System.out.println(recursionPreOrderList);
        System.out.println("-- -- --");
        System.out.println("-- in order --");
        final List<Integer> recursionInOrderList = new ArrayList<>();
        recursion.setOperation(Traversal.recordList(recursionInOrderList));
        recursion.inorder(root);
        System.out.println(recursionInOrderList);
        System.out.println("-- -- --");
        System.out.println("-- post order --");
        final List<Integer> recursionPostOrderList = new ArrayList<>();
        recursion.setOperation(Traversal.recordList(recursionPostOrderList));
        recursion.postorder(root);
        System.out.println(recursionPostOrderList);
        System.out.println("-- -- --");
        System.out.println("--- --- ---");

        System.out.println("--- stack ---");
        Traversal stack = new Stack();
        show(recursionPreOrderList, recursionInOrderList, recursionPostOrderList, stack);

        System.out.println("--- morris ---");
        Traversal morris = new Morris();
        show(recursionPreOrderList, recursionInOrderList, recursionPostOrderList, morris);
    }

    private static void show(List<Integer> standardPreOrderList, List<Integer> standardInOrderList, List<Integer> standardPostOrderList, Traversal traversal) {
        System.out.println("-- pre order --");
        final List<Integer> preOrderList = new ArrayList<>();
        traversal.setOperation(Traversal.recordList(preOrderList));
        traversal.preorder(root);
        System.out.println(preOrderList.equals(standardPreOrderList));
        System.out.println("-- -- --");
        System.out.println("-- in order --");
        final List<Integer> inOrderList = new ArrayList<>();
        traversal.setOperation(Traversal.recordList(inOrderList));
        traversal.inorder(root);
        System.out.println(inOrderList.equals(standardInOrderList));
        System.out.println("-- -- --");
        System.out.println("-- post order --");
        final List<Integer> postOrderList = new ArrayList<>();
        traversal.setOperation(Traversal.recordList(postOrderList));
        traversal.postorder(root);
        System.out.println(postOrderList.equals(standardPostOrderList));
        System.out.println("-- -- --");
        System.out.println("--- --- ---");
    }
}
