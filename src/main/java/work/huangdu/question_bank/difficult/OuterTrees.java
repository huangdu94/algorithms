package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 587. 安装栅栏
 * 在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。
 * 示例 1:
 * 输入: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * 输出: [[1,1],[2,0],[4,2],[3,3],[2,4]]
 * 解释:
 * 示例 2:
 * 输入: [[1,2],[2,2],[4,2]]
 * 输出: [[1,2],[2,2],[4,2]]
 * 解释:
 * 即使树都在一条直线上，你也需要先用绳子包围它们。
 * 注意:
 * 所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。
 * 输入的整数在 0 到 100 之间。
 * 花园至少有一棵树。
 * 所有树的坐标都是不同的。
 * 输入的点没有顺序。输出顺序也没有要求。
 *
 * @author huangdu
 * @version 2022/4/25
 */
public class OuterTrees {
    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;
        if (n <= 3) {return trees;}
        // 寻找基准点（最左，x坐标一样的话最下）
        int top = 0, targetIndex = 0;
        int[] start = trees[targetIndex];
        for (int i = 1; i < n; i++) {
            int[] tree = trees[i];
            if (start[0] > tree[0] || start[0] == tree[0] && start[1] > tree[1]) {
                targetIndex = i;
                start = tree;
            }
        }
        swap(trees, targetIndex, top++);
        int[] cur = start;
        while (top < n) {
            targetIndex = top;
            int[] next = trees[targetIndex];
            for (int i = top + 1; i < n; i++) {
                int[] other = trees[i];
                int vp = vectorProduct(cur, next, other);
                if (vp < 0 || vp == 0 && distanceSquare(cur, next) > distanceSquare(cur, other)) {
                    targetIndex = i;
                    next = other;
                }
            }
            if (vectorProduct(cur, next, start) < 0) {break;}
            swap(trees, targetIndex, top++);
            cur = next;
        }
        // 转换结果
        return Arrays.copyOf(trees, top);
    }

    /**
     * 求 向量tree0->tree1 向量tree1->tree2 的向量积（结果肯定是只有z方向，最后的结果即为向量从原点开始的话结束点的z坐标）
     * 该值如果
     * 大于0则表示tree2在tree0->tree1左侧，
     * 小于0则表示tree2在tree0->tree1右侧
     * 等于0则表示tree2和tree0->tree1在一条直线上
     *
     * @param tree0 已经加入结果集的点
     * @param tree1 目前选择的点
     * @param tree2 其它点
     * @return 向量积
     */
    private int vectorProduct(int[] tree0, int[] tree1, int[] tree2) {
        return (tree1[0] - tree0[0]) * (tree2[1] - tree1[1]) - (tree1[1] - tree0[1]) * (tree2[0] - tree1[0]);
    }

    private int distanceSquare(int[] tree0, int[] tree1) {
        return (tree1[0] - tree0[0]) * (tree1[0] - tree0[0]) + (tree1[1] - tree0[1]) * (tree1[1] - tree0[1]);
    }

    private void swap(int[][] trees, int a, int b) {
        int[] temp = trees[a];
        trees[a] = trees[b];
        trees[b] = temp;
    }

    public static void main(String[] args) {
        int[][] trees = {{1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}};
        OuterTrees ot = new OuterTrees();
        System.out.println(Arrays.deepToString(ot.outerTrees(trees)));
    }
}