package work.huangdu.question_bank.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * 1104. 二叉树寻路
 * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
 * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
 * 示例 1：
 * 输入：label = 14
 * 输出：[1,3,4,14]
 * 示例 2：
 * 输入：label = 26
 * 输出：[1,2,6,10,26]
 * 提示：
 * 1 <= label <= 10^6
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/7/29
 */
public class PathInZigZagTree {
    private static final int[] sums;

    static {
        sums = new int[20];
        for (int i = 0, sum = 0; i < 20; i++) {sums[i] = (sum += (1 << i));}
    }

    private int getRow(int label) {
        int row = 0;
        while (sums[row] < label) {row++;}
        return row;
    }

    private int getParent(int cur, int row) {
        if ((row & 1) == 0) {
            int parentIndex = (cur - (1 << row)) >> 1, preRowMax = (1 << row) - 1;
            return preRowMax - parentIndex;
        } else {
            int parentIndex = ((1 << (row + 1)) - 1 - cur) >> 1, preRowMin = 1 << (row - 1);
            return preRowMin + parentIndex;
        }
    }

    public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> paths = new LinkedList<>();
        paths.addFirst(label);
        int row = getRow(label);
        while (row > 0) {
            label = getParent(label, row--);
            paths.addFirst(label);
        }
        return paths;
    }
}
